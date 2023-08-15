package com.example.projecteyebrow.view.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.TemporaryCommunityItem
import com.example.projecteyebrow.databinding.TemporaryContentItemBinding

class TempContentAdapter(
    private val tempContentList: List<TemporaryCommunityItem>
): RecyclerView.Adapter<TempContentAdapter.TempContentViewHolder>() {

    inner class TempContentViewHolder(
        private val binding: TemporaryContentItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tempContent: TemporaryCommunityItem) {
            binding.TemporaryContentTitle.text = tempContent.title
            binding.TemporaryContentTxt.text = tempContent.content
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