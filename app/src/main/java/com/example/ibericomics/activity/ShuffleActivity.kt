package com.example.ibericomics.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibericomics.R
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.recycler.RecyclerComic
import com.example.ibericomics.databinding.ActivityShuffleBinding
import com.example.ibericomics.models.Comic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShuffleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShuffleBinding
    private lateinit var comicAdapter: RecyclerComic
    private lateinit var comicList: MutableList<Comic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShuffleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener cómics desde la API
        ApiService.api.getAllComics().enqueue(object : Callback<List<Comic>> {
            override fun onResponse(call: Call<List<Comic>>, response: Response<List<Comic>>) {
                if (response.isSuccessful) {
                    comicList = response.body()!!.toMutableList()
                    comicAdapter = RecyclerComic(this@ShuffleActivity, comicList)
                    binding.recyclerView.adapter = comicAdapter

                    comicAdapter.setOnItemClickListener { comic ->
                        val intent = Intent(this@ShuffleActivity, ChapterActivity::class.java)
                        intent.putExtra("comicTitle", comic.title)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comic>>, t: Throwable) {
                // Manejar fallo
            }
        })
        binding.btnShuffle.setOnClickListener {
            // No es necesario iniciar nuevamente la ShuffleActivity
        }

        binding.btnShuffleRandom.setOnClickListener {
            comicList.shuffle()
            comicAdapter.notifyDataSetChanged()
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnUsers.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
