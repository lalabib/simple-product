package com.lalabib.latihan.simpleproduct.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.adapter.OrderAdapter
import com.lalabib.latihan.simpleproduct.databinding.ActivityOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderAdapter: OrderAdapter
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.order_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupData() {
        orderAdapter = OrderAdapter()

        binding.rvOrder.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            adapter = orderAdapter
        }

        orderViewModel.getAllOrder.observe(this) {
            orderAdapter.submitList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}