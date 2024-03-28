package com.lalabib.latihan.simpleproduct.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.adapter.OrderAdapter
import com.lalabib.latihan.simpleproduct.databinding.ActivityAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var orderAdapter: OrderAdapter
    private val adminViewModel: AdminViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupUser()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.admin_title)
        }
    }

    private fun setupUser() {
        adminViewModel.getUser.observe(this) { user ->
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }

    private fun setupData() {
        orderAdapter = OrderAdapter()

        binding.rvOrder.apply {
            layoutManager = LinearLayoutManager(this@AdminActivity)
            adapter = orderAdapter
        }

        adminViewModel.getAllOrder.observe(this) {
            orderAdapter.submitList(it)
        }
    }

    private fun logout() {
        AlertDialog.Builder(this@AdminActivity).apply {
            setTitle(getString(R.string.confirm_logout))
            setMessage(getString(R.string.logout_message))
            setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                adminViewModel.logout()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.admin_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}