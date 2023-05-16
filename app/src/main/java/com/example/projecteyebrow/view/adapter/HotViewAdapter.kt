package com.example.projecteyebrow.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.HotviewItemBinding
import com.example.projecteyebrow.view.adapter.adapterItems.HotViewItem

class HotViewAdapter(
    private val HotViewItems: ArrayList<HotViewItem>
): RecyclerView.Adapter<HotViewAdapter.HotViewItemViewHolder>() {

    inner class HotViewItemViewHolder(
        private val binding: HotviewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(hotViewItem: HotViewItem) {
            binding.HotViewTitle.text = hotViewItem.hotViewTitle
            binding.HotViewSubTitle.text = hotViewItem.hotViewSubTitle
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotViewItemViewHolder {
        val binding: HotviewItemBinding = HotviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return HotViewItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HotViewItemViewHolder,
        position: Int
    ) = holder.bind(HotViewItems[position])

    override fun getItemCount(): Int = HotViewItems.size

}