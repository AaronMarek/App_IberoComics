package com.example.ibericomics.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibericomics.R
import com.example.ibericomics.api.ApiService
import com.example.ibericomics.databinding.ActivityChapterBinding
import com.example.ibericomics.models.Chapter
import com.example.ibericomics.recycler.RecyclerChapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChapterBinding
    private lateinit var chapterAdapter: RecyclerChapter
    private var chapterList: MutableList<Chapter> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el nombre del cómic desde el intent y configurarlo en el TextView
        val comicTitle = intent.getStringExtra("comicTitle") ?: "Comic"
        binding.tvComicTitle.text = comicTitle

        // Configurar el RecyclerView
        chapterAdapter = RecyclerChapter(this, chapterList)
        binding.chapterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chapterRecyclerView.adapter = chapterAdapter

        // Obtener el ID del cómic desde el intent
        val comicId = intent.getIntExtra("comicId", -1)

        // Añadir capítulos de ejemplo si es "comic 1"
        if (comicId == -1) {
            addSampleChapters()
        } else {
            fetchChapters(comicId)
        }

        binding.addToFavoritesButton.setOnClickListener {
            // Aquí es donde implementarás la lógica para añadir a favoritos
            Toast.makeText(this, "$comicTitle se ha añadido a favoritos!", Toast.LENGTH_SHORT).show()
        }

        // Configurar el botón de retroceso
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun addSampleChapters() {
        val sampleChapters = listOf(
            Chapter(1, "Beginning", chapterNumber = 1, comicId = 1),
            Chapter(2, "Continuation", chapterNumber = 2, comicId = 1)
        )
        chapterList.clear()
        chapterList.addAll(sampleChapters)
        chapterAdapter.notifyDataSetChanged()
    }

    private fun fetchChapters(comicId: Int) {
        ApiService.api.getChapters(comicId).enqueue(object : Callback<List<Chapter>> {
            override fun onResponse(call: Call<List<Chapter>>, response: Response<List<Chapter>>) {
                if (response.isSuccessful) {
                    chapterList.clear()
                    response.body()?.let {
                        chapterList.addAll(it)
                    }
                    chapterAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Chapter>>, t: Throwable) {
                // Manejar error
            }
        })
    }
}
