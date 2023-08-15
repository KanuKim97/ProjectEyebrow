package com.example.projecteyebrow.view.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecteyebrow.databinding.MainbannerItemBinding

class BannerAdapter(
    private val mainBannerItems: ArrayList<String>
): RecyclerView.Adapter<BannerAdapter.MainBannerViewHolder>() {

    inner class MainBannerViewHolder(
        private val binding: MainbannerItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainBannerViewHolder {
        val binding: MainbannerItemBinding = MainbannerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainBannerViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = mainBannerItems.size

}