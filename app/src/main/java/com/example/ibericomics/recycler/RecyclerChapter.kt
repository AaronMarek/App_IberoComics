package com.example.ibericomics.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ibericomics.R
import com.example.ibericomics.activity.PagesActivity
import com.example.ibericomics.models.Chapter

class RecyclerChapter(private val context: Context, private val chapterList: List<Chapter>) :
    RecyclerView.Adapter<RecyclerChapter.ChapterViewHolder>() {

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textChapterNumber: TextView = itemView.findViewById(R.id.textChapterNumber)
        val textChapterName: TextView = itemView.findViewById(R.id.textChapterName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val chapter = chapterList[position]
                val intent = Intent(context, PagesActivity::class.java).apply {
                    putExtra("comicId", chapter.comicId)
                    putExtra("chapterNumber", chapter.chapterNumber)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item_layout, parent, false)
        return ChapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.textChapterNumber.text = "Chapter ${chapter.chapterNumber}"
        holder.textChapterName.text = chapter.title
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}
