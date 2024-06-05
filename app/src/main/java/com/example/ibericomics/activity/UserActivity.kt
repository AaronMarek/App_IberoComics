package com.example.ibericomics.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibericomics.R
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.recycler.RecyclerComic
import com.example.ibericomics.databinding.ActivityUserBinding
import com.example.ibericomics.models.Comic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var comicAdapter: RecyclerComic
    private lateinit var comicList: MutableList<Comic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener cómics desde la API
        ApiService.api.getAllComics().enqueue(object : Callback<List<Comic>> {
            override fun onResponse(call: Call<List<Comic>>, response: Response<List<Comic>>) {
                if (response.isSuccessful) {
                    comicList = response.body()!!.toMutableList()
                    comicAdapter = RecyclerComic(this@UserActivity, comicList)
                    binding.recyclerView.adapter = comicAdapter

                    comicAdapter.setOnItemClickListener { comic ->
                        val intent = Intent(this@UserActivity, ChapterActivity::class.java)
                        intent.putExtra("comicTitle", comic.title)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comic>>, t: Throwable) {
                // Manejar fallo
            }
        })

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.btnShuffle.setOnClickListener {
            startActivity(Intent(this, ShuffleActivity::class.java))
            finish()
        }

        binding.btnUsers.setOnClickListener {
            // No es necesario iniciar nuevamente la UserActivity
        }

        binding.btnLogout.setOnClickListener {

        }
    }
}
