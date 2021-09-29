package com.example.mycookingrecipe.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mycookingrecipe.R
import com.example.mycookingrecipe.databinding.ActivityLoginBinding
import com.example.mycookingrecipe.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.successfullLogin.observe(this) {
            if (it) {
                callMainActivity()
            }
        }

        loginViewModel.existingLogin.observe(this) {
            if (it) {
                toastExistingAccount()
            } else {
                toastError()
            }
        }

        loginViewModel.createdLogin.observe(this) {
            if (it) {
                callMainActivity()
            }
        }

        binding.btnLogin.setOnClickListener {
            checkFields()
        }

        binding.txtCreateAccount.setOnClickListener {
            callNewAccountDialog()
        }

    }

    private fun login() {
        loginViewModel.checkLogin(this)
    }

    private fun checkFields() {
        val login = binding.edtLogin
        val password = binding.edtPassword

        if (login.text.isNullOrBlank() || password.text.isNullOrBlank()) {
            login.error = getString(R.string.edt_error)
        } else {
            login()
        }
    }

    private fun toastError() {
        Toast.makeText(this, getString(R.string.incorrect_login), Toast.LENGTH_LONG).show()
    }

    private fun toastExistingAccount() {
        Toast.makeText(this, getString(R.string.user_already_exists), Toast.LENGTH_LONG).show()
    }

    private fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun callNewAccountDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.create_account_dialog))
        dialog.setPositiveButton(getString(R.string.sim)) { _: DialogInterface, _: Int ->
            loginViewModel.createLogin()
        }
        dialog.setNegativeButton(getString(R.string.cancelar)) { _: DialogInterface, _: Int ->
            Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}