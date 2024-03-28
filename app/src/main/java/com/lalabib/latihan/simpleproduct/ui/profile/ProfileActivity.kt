package com.lalabib.latihan.simpleproduct.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.databinding.ActivityProfileBinding
import com.lalabib.latihan.simpleproduct.ui.order.OrderActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.profile_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupAction() {
        binding.apply {
            btnOrder.setOnClickListener {
                startActivity(Intent(this@ProfileActivity, OrderActivity::class.java))
            }

            btnLogout.setOnClickListener {

            }
        }
    }

    private fun setupData() {
        //user data
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}