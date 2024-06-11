package com.example.ibericomics.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.databinding.ActivityChangePasswordBinding
import com.example.ibericomics.models.ChangePasswordRequest
import com.example.ibericomics.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Aquí deberías obtener el usuario actual desde una fuente de datos, como SharedPreferences o una base de datos local
        user = User(username = "username", password = "password", fullName = "", email = "", id = 1)

        binding.btnChangePassword.setOnClickListener {
            val oldPassword = binding.etCurrentPassword.text.toString()
            val newPassword = binding.etNewPassword.text.toString()

            if (oldPassword.isNotEmpty() && newPassword.isNotEmpty()) {
                val changePasswordRequest = ChangePasswordRequest(userId = user.id ?: 0, oldPassword = oldPassword, newPassword = newPassword)
                ApiService.api.changePassword(changePasswordRequest).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@ChangePasswordActivity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@ChangePasswordActivity, "Failed to change password", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@ChangePasswordActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Please enter both current and new passwords", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnReturn.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
