package com.example.projecteyebrow.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.TattooistItemBinding
import com.example.projecteyebrow.view.adapter.adapterItems.TattooistItem

class TattooistAdapter(
    private val tattooistItems: ArrayList<TattooistItem>
): RecyclerView.Adapter<TattooistAdapter.TattooistItemViewHolder>() {

    inner class TattooistItemViewHolder(
        private val binding: TattooistItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tattooistItem: TattooistItem) {
            binding.TattooistTitle.text = tattooistItem.tattooistName
            binding.BrandNewSubTitle.text = tattooistItem.tattooistRating
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TattooistItemViewHolder {
        val binding: TattooistItemBinding = TattooistItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return TattooistItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TattooistItemViewHolder,
        position: Int
    ) = holder.bind(tattooistItems[position])

    override fun getItemCount(): Int = tattooistItems.size
}