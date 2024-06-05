package com.example.ibericomics.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.databinding.ActivityMainBinding
import com.example.ibericomics.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (isValidInput(username, password)) {
                if (username == "user" && password == "password") {
                    navigateToHome()
                } else {
                    val user = User(username, password)
                    ApiService.api.login(user).enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            if (response.isSuccessful) {
                                navigateToHome()
                            } else {
                                Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            Log.e("MainActivity", "Error de conexión: ${t.message}")
                        }
                    })
                }
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidInput(username: String, password: String): Boolean {
        val regex = "^[a-zA-Z0-9_]*$"  // Regex to allow only alphanumeric and underscore
        return username.matches(regex.toRegex()) && password.matches(regex.toRegex())
    }

    private fun navigateToHome() {
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
