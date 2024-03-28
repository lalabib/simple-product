package com.lalabib.latihan.simpleproduct.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lalabib.latihan.simpleproduct.adapter.ProductAdapter
import com.lalabib.latihan.simpleproduct.databinding.ActivityHomeBinding
import com.lalabib.latihan.simpleproduct.ui.detail.DetailActivity
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
        productAdapter = ProductAdapter { product ->
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, product.id)
                startActivity(this)
            }
        }

        binding.rvProduct.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = productAdapter
        }

        homeViewModel.getProduct.observe(this@HomeActivity) {
            productAdapter.submitList(it)
        }
    }
}