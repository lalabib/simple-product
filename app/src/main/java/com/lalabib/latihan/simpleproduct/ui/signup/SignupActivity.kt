package com.lalabib.latihan.simpleproduct.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.databinding.ActivitySignupBinding
import com.lalabib.latihan.simpleproduct.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.signup_title)
        }
    }


    private fun setupSignupData() {
        binding.apply {
            val username = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val role = getString(R.string.buyer)

            when {
                username.isEmpty() -> {
                    tfUsername.error = getString(R.string.cant_empty)
                }
                email.isEmpty() -> {
                    tfEmail.error = getString(R.string.cant_empty)
                }
                password.isEmpty() -> {
                    tfPassword.error = getString(R.string.cant_empty)
                }
                else -> {
                    val user = UserEntity (
                        id = UUID.randomUUID().toString(),
                        name = username,
                        email = email,
                        password = password,
                        role = role,
                    )
                    signupViewModel.insertUser(user)

                    Toast.makeText(
                        this@SignupActivity,
                        R.string.signup_success,
                        Toast.LENGTH_SHORT
                    ).show()
                    moveToLogin()
                }
            }
        }

    }

    private fun setupAction() {
        binding.apply {
            btnSignup.setOnClickListener { setupSignupData() }
            tvLogin.setOnClickListener { moveToLogin() }
        }
    }

    private fun moveToLogin() {
        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        finish()
    }
}