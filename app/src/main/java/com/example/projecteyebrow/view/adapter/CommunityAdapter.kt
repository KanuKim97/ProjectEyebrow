package com.example.projecteyebrow.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.CommunityItem
import com.example.projecteyebrow.databinding.CommunityItemBinding

class CommunityAdapter(
    private val communityItems: ArrayList<CommunityItem>
): RecyclerView.Adapter<CommunityAdapter.CommunityItemViewHolder>() {
    inner class CommunityItemViewHolder(
        private val binding: CommunityItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(communityItem: CommunityItem) {
            binding.communityTitle.text = communityItem.title
            binding.communityContent.text = communityItem.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityItemViewHolder {
        val binding: CommunityItemBinding = CommunityItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CommunityItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CommunityItemViewHolder,
        position: Int
    ): Unit = holder.bind(communityItems[position])

    override fun getItemCount(): Int = communityItems.size

}