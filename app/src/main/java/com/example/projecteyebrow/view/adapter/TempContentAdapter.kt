package com.example.projecteyebrow.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.database.tables.TemporaryContentEntity
import com.example.projecteyebrow.databinding.TemporaryContentItemBinding

class TempContentAdapter(
    private val tempContentList: List<TemporaryContentEntity>
): RecyclerView.Adapter<TempContentAdapter.TempContentViewHolder>() {

    inner class TempContentViewHolder(
        private val binding: TemporaryContentItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tempContent: TemporaryContentEntity) {
            binding.TemporaryContentTitle.text = tempContent.contentTitle
            binding.TemporaryContentTxt.text = tempContent.TemporaryContent
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TempContentViewHolder {
        val binding: TemporaryContentItemBinding = TemporaryContentItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return TempContentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TempContentViewHolder,
        position: Int
    ): Unit = holder.bind(tempContentList[position])

    override fun getItemCount(): Int = tempContentList.size

}