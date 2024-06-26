package com.lalabib.latihan.simpleproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.databinding.ItemProductBinding
import com.lalabib.latihan.simpleproduct.utils.SharedObject.loadImage
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(private val onItemClick: (ProductEntity) -> Unit) :
    ListAdapter<ProductEntity, ProductAdapter.ViewHolder>(ProductDiffUtil) {

    object ProductDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(onItemClick, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }
    }

    class ViewHolder(
        private val onItemClick: (ProductEntity) -> Unit,
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            binding.apply {
                val locale = Locale("id", "ID")
                val formatter = NumberFormat.getNumberInstance(locale)
                formatter.maximumFractionDigits = 3
                val formattedPrice = "Rp" + formatter.format(product.price)

                tvName.text = product.name
                tvPrice.text = formattedPrice
                loadImage(ivImage, product.image)
            }

            itemView.setOnClickListener { onItemClick(product) }
        }
    }
}