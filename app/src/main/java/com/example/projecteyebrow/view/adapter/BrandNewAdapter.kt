package com.example.projecteyebrow.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.BrandnewItemBinding
import com.example.projecteyebrow.view.adapter.adapterItems.BrandNewItem

class BrandNewAdapter(
    private val brandNewItems: ArrayList<BrandNewItem>
): RecyclerView.Adapter<BrandNewAdapter.BrandNewItemViewHolder>() {

    inner class BrandNewItemViewHolder(
        private val binding: BrandnewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(brandNewItem: BrandNewItem) {
            binding.BrandNewTitle.text = brandNewItem.brandNewTitle
            binding.BrandNewSubTitle.text = brandNewItem.brandNewSubTitle
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandNewItemViewHolder {
        val binding: BrandnewItemBinding = BrandnewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return BrandNewItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BrandNewItemViewHolder,
        position: Int
    ) = holder.bind(brandNewItems[position])

    override fun getItemCount(): Int = brandNewItems.size

}