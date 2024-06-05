package com.example.ibericomics.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ibericomics.databinding.ActivityChangePasswordBinding
import com.example.ibericomics.models.User

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = User(username = "username", password = "password")

        binding.btnChangePassword.setOnClickListener {
            val oldPassword = binding.etCurrentPassword.text.toString()
            val newPassword = binding.etNewPassword.text.toString()

            if (oldPassword == user.password) {
                user.password = newPassword
                Toast.makeText(this, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "La contraseña actual es incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnReturn.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}