package com.example.ibericomics.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibericomics.R
import com.example.ibericomics.databinding.ActivityComicBinding
import com.example.ibericomics.recycler.RecyclerPages

class PagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val comicTitle = intent.getStringExtra("comicTitle")
        val selectedChapter = intent.getStringExtra("selectedChapter")

        binding.comicTitleTextView.text = comicTitle
        binding.chapterTitleTextView.text = selectedChapter

        // Ejemplo de lista de imágenes para las páginas del cómic
        val pages = listOf(
            R.drawable.panel1, // Reemplaza con tus recursos de imágenes
            R.drawable.panel2,
            R.drawable.panel3
        )

        // Configura el RecyclerView
        binding.pagesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pagesRecyclerView.adapter = RecyclerPages(pages)

        binding.btnPrevious.setOnClickListener {
            // Lógica para ir al episodio anterior
        }

        binding.btnChapters.setOnClickListener {
            onBackPressed()
        }

        binding.btnNext.setOnClickListener {
            // Lógica para ir al siguiente episodio
        }
    }
}
