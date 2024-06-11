package com.example.ibericomics.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibericomics.R
import com.example.ibericomics.activity.ChapterActivity
import com.example.ibericomics.models.Comic

class RecyclerComic(private val context: Context, private val comicList: List<Comic>) :
    RecyclerView.Adapter<RecyclerComic.ComicViewHolder>() {

    inner class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comicImage: ImageView = itemView.findViewById(R.id.imageComic)
        val comicTitle: TextView = itemView.findViewById(R.id.labelComic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comic_card, parent, false)
        return ComicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comicList[position]
        holder.comicTitle.text = comic.title
        Glide.with(context)
            .load(comic.coverUrl) // Asegúrate de que la clase Comic tenga un campo coverUrl
            .into(holder.comicImage)

        // Añadir OnClickListener para la imagen del cómic
        holder.comicImage.setOnClickListener {
            val intent = Intent(context, ChapterActivity::class.java)
            intent.putExtra("comicId", comic.id)
            intent.putExtra("comicTitle", comic.title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = comicList.size
}
