package com.example.ibericomics.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ibericomics.R
import com.example.ibericomics.models.ChapterData

class RecyclerChapter(private val context: Context, private val chapterList: List<ChapterData>) :
    RecyclerView.Adapter<RecyclerChapter.ChapterViewHolder>() {

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textChapterNumber: TextView = itemView.findViewById(R.id.textChapterNumber)
        val textChapterName: TextView = itemView.findViewById(R.id.textChapterName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item_layout, parent, false)
        return ChapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.textChapterNumber.text = "Chapter ${chapter.chapterNumber}"
        holder.textChapterName.text = chapter.chapterName
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}

