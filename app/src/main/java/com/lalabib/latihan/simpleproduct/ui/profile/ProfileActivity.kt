package com.lalabib.latihan.simpleproduct.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.databinding.ActivityProfileBinding
import com.lalabib.latihan.simpleproduct.ui.order.OrderActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

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

            btnLogout.setOnClickListener { logout() }
        }
    }

    private fun setupData() {
        profileViewModel.getUser.observe(this) { user ->
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }

    private fun logout() {
        AlertDialog.Builder(this@ProfileActivity).apply {
            setTitle(getString(R.string.confirm_logout))
            setMessage(getString(R.string.logout_message))
            setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                profileViewModel.logout()
                finish()
                dialog.cancel()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            create()
            show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}