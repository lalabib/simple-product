package com.lalabib.latihan.simpleproduct.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserPreferenceEntity
import com.lalabib.latihan.simpleproduct.databinding.ActivityLoginBinding
import com.lalabib.latihan.simpleproduct.ui.home.HomeActivity
import com.lalabib.latihan.simpleproduct.ui.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var loggedInUser: UserEntity
    private var isLoginSuccessful = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        setupView()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.login_title)
        }
    }

    private fun setupAction() {
        binding.apply {
            btnLogin.setOnClickListener { setupLoginData() }
            tvSignup.setOnClickListener { moveToSignup() }
        }
    }

    private fun setupLoginData() {
        binding.apply {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (email.isEmpty()) {
                tfEmail.error = getString(R.string.cant_empty)
                return
            }

            if (password.isEmpty()) {
                tfPassword.error = getString(R.string.cant_empty)
                return
            }


            loginViewModel.getAllUser.observe(this@LoginActivity) { users ->
                isLoginSuccessful = false
                for (user in users) {
                    if (email == user.email && password == user.password) {
                        isLoginSuccessful = true
                        loggedInUser = user
                        break
                    }
                }

                if (isLoginSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.login_success,
                        Toast.LENGTH_SHORT
                    ).show()

                    val userPreferences = loggedInUser.let {
                        UserPreferenceEntity (
                            name = it.name,
                            email = it.email,
                            password = it.password,
                            role = it.role,
                            isLogin = true
                        )
                    }

                    loginViewModel.saveUser(userPreferences)
                    moveToHome()
                }
            }
        }
    }

    private fun moveToSignup() {
        startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        finish()
    }

    private fun moveToHome() {
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }
}