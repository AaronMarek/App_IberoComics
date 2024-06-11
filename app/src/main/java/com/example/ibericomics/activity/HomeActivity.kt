package com.example.ibericomics.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibericomics.R
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.recycler.RecyclerComic
import com.example.ibericomics.databinding.ActivityHomeBinding
import com.example.ibericomics.models.Comic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var comicAdapter: RecyclerComic
    private var comicList: MutableList<Comic> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        comicAdapter = RecyclerComic(this, comicList)
        binding.recyclerView.adapter = comicAdapter

        // Añadir un cómic local
        val localComic = Comic(id = -1, title = "comic 1", coverUrl = "android.resource://com.example.ibericomics/drawable/comic1", author = "pedro", userId = 1)
        comicList.add(localComic)
        comicAdapter.notifyDataSetChanged()

        // Obtener cómics desde la API
        fetchComics()

        binding.btnShuffle.setOnClickListener {
            startActivity(Intent(this, ShuffleActivity::class.java))
            finish()
        }

        binding.btnHome.setOnClickListener {
            // No es necesario iniciar nuevamente la HomeActivity
        }

        binding.btnUsers.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }

    private fun fetchComics() {
        ApiService.api.getAllComics().enqueue(object : Callback<List<Comic>> {
            override fun onResponse(call: Call<List<Comic>>, response: Response<List<Comic>>) {
                if (response.isSuccessful) {
                    comicList.clear()
                    comicList.addAll(response.body()!!)
                    // Añadir un cómic local
                    val localComic = Comic(id = -1, title = "comic 1", coverUrl = "android.resource://com.example.ibericomics/drawable/comic1", author = "pedro", userId = 1)
                    comicList.add(localComic)
                    comicAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Comic>>, t: Throwable) {
                // Manejar error
            }
        })
    }
}
