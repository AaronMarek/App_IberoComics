// ChapterActivity.kt
package com.example.ibericomics.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibericomics.databinding.ActivityChapterBinding
import com.example.ibericomics.models.ChapterData
import com.example.ibericomics.recycler.RecyclerChapter

class ChapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChapterBinding
    private lateinit var chapterAdapter: RecyclerChapter
    private lateinit var chapterList: MutableList<ChapterData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val comicTitle = intent.getStringExtra("comicTitle")
        binding.tvComicTitle.text = comicTitle

        // Simulando una lista de capítulos
        val chapters = listOf("Chapter 1", "Chapter 2", "Chapter 3")
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, chapters)
        binding.chapterListView.adapter = adapter

        binding.chapterListView.setOnItemClickListener { parent, view, position, id ->
            val selectedChapter = chapters[position]
            val intent = Intent(this, PagesActivity::class.java).apply {
                putExtra("comicTitle", comicTitle)
                putExtra("selectedChapter", selectedChapter)
            }
            startActivity(intent)
        }


        binding.addToFavoritesButton.setOnClickListener {
            // Aquí es donde implementarás la lógica para añadir a favoritos
            Toast.makeText(this, "$comicTitle added to favorites!", Toast.LENGTH_SHORT).show()

            // Aquí puedes implementar la lógica para añadir a favoritos en el futuro
            // Por ejemplo, podrías llamar a una función que guarde el cómic en una base de datos
            // addComicToFavorites(comicTitle)
        }
        // Configurar el botón de retroceso
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun addComicToFavorites(comicTitle: String?) {
        // Implementar la lógica para añadir el cómic a la lista de favoritos
    }
}

