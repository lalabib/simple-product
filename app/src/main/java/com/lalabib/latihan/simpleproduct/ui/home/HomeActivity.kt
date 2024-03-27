package com.lalabib.latihan.simpleproduct.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.adapter.ProductAdapter
import com.lalabib.latihan.simpleproduct.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var productAdapter: ProductAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
    }

    private fun setupData() {
        productAdapter = ProductAdapter()

        binding.rvProduct.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = productAdapter
        }

        homeViewModel.getProduct.observe(this@HomeActivity) {
            productAdapter.submitList(it)
        }
    }
}