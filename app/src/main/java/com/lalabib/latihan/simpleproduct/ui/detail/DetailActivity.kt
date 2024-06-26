package com.lalabib.latihan.simpleproduct.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.databinding.ActivityDetailBinding
import com.lalabib.latihan.simpleproduct.databinding.BottomSheetOderBinding
import com.lalabib.latihan.simpleproduct.utils.EmailSender
import com.lalabib.latihan.simpleproduct.utils.SharedObject.EmailAdmin
import com.lalabib.latihan.simpleproduct.utils.SharedObject.SubjectEmail
import com.lalabib.latihan.simpleproduct.utils.SharedObject.disableView
import com.lalabib.latihan.simpleproduct.utils.SharedObject.enableView
import com.lalabib.latihan.simpleproduct.utils.SharedObject.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import java.util.UUID

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var bsBinding: BottomSheetOderBinding

    private lateinit var product: ProductEntity
    private val detailViewModel: DetailViewModel by viewModels()

    private var currentCount = 1
    private lateinit var countChangeListener: (count: Int) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupAction() {
        binding.apply {
            btnOrder.setOnClickListener {
                setupBottomSheet()
            }
        }
    }

    private fun setupData() {
        val id = intent.getStringExtra(EXTRA_DATA)
        if (id != null) {
            detailViewModel.getProductById(id).observe(this) { detailProduct ->
                product = detailProduct
                populateProducts(detailProduct)
            }
        }
    }

    private fun populateProducts(product: ProductEntity) {
        binding.apply {
            val locale = Locale("id", "ID")
            val formatter = NumberFormat.getNumberInstance(locale)
            formatter.maximumFractionDigits = 3
            val formattedPrice = "Rp" + formatter.format(product.price)

            tvName.text = product.name
            tvPrice.text = formattedPrice
            tvAuthor.text = product.author
            tvPublicationYear.text = product.publicationYear
            tvPublisher.text = product.publisher
            tvDesc.text = product.description
            loadImage(ivImage, product.image)
        }
    }

    @SuppressLint("InflateParams")
    private fun setupBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val inflater = LayoutInflater.from(this)
        bsBinding = BottomSheetOderBinding.inflate(inflater)

        dialog.setContentView(bsBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        bsBinding.apply {
            val locale = Locale("id", "ID")
            val formatter = NumberFormat.getNumberInstance(locale)
            formatter.maximumFractionDigits = 3
            val formattedPrice = "Rp" + formatter.format(product.price)

            tvName.text = product.name
            tvPrice.text = formattedPrice
            loadImage(ivImage, product.image)

            val decreaseBtn = bsBinding.decrease
            val increaseBtn = bsBinding.increase
            val count = bsBinding.count
            count.text = currentCount.toString()
            decreaseBtn.disableView()

            increaseBtn.setOnClickListener {
                currentCount = count.text.toString().toInt()
                currentCount++
                if (currentCount > 1)
                    decreaseBtn.enableView()
                count.text = currentCount.toString()
                countChangeListener(currentCount)
            }

            decreaseBtn.setOnClickListener {
                currentCount = count.text.toString().toInt()
                currentCount--
                if (currentCount <= 1)
                    decreaseBtn.disableView()
                count.text = currentCount.toString()
                countChangeListener(currentCount)
            }

            fun registerCountListener(listener: (count: Int) -> Unit) {
                countChangeListener = listener
            }

            registerCountListener { counter ->
                val priceSum = product.price * counter
                val formattedPriceSum = "Rp" + formatter.format(priceSum)
                tvPrice.text = formattedPriceSum

                bsBinding.btnOrder.setOnClickListener {
                    //save data to order_tb
                    val notes = bsBinding.addNote.text.toString()
                    val order = OrderEntity(
                        id = UUID.randomUUID().toString(),
                        name = product.name,
                        description = product.description,
                        price = product.price,
                        image = product.image,
                        author = product.author,
                        publicationYear = product.publicationYear,
                        publisher = product.publisher,
                        note = notes,
                        quantity = counter,
                        sumPrice = priceSum
                    )
                    detailViewModel.insertOrder(order)

                    //get user data
                    detailViewModel.getUser.observe(this@DetailActivity) { user ->
                        CoroutineScope(Dispatchers.IO).launch {
                            val subject = SubjectEmail
                            val messageBodySender =
                                "Hi ${user.name}, " +
                                        "\n\nKonfirmasi pesanan atas nama ${user.name} memesan produk ${product.name} harga $formattedPrice sebanyak $counter pcs dengan jumlah $formattedPriceSum dan note: $notes."
                            val messageBodyAdmin =
                                "Hi Admin, \n\nKonfirmasi pesanan atas nama ${user.name} memesan produk ${product.name} harga $formattedPrice sebanyak $counter pcs dengan jumlah $formattedPriceSum dan note: $notes."
                            val emailRecipientSender = user.email
                            val emailRecipientAdmin = EmailAdmin
                            EmailSender().sendMail(
                                subject, messageBodySender, emailRecipientSender
                            )
                            EmailSender().sendMail(
                                subject, messageBodyAdmin, emailRecipientAdmin
                            )
                        }

                    }

                    Toast.makeText(
                        this@DetailActivity,
                        getString(R.string.order_confirm),
                        Toast.LENGTH_SHORT
                    ).show()

                    dialog.cancel()
                }
            }

            countChangeListener(currentCount)
            icClose.setOnClickListener { dialog.cancel() }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}