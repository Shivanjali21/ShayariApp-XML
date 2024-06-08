package com.practice.shayariapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.shayariapp.R
import com.practice.shayariapp.databinding.RvAllShayariCategoryBinding
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory

class AllShayariCategoryAdapter : RecyclerView.Adapter<AllShayariCategoryAdapter.AllShayariVH>() {

    var onItemClick: ((AllShayariCategory) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<AllShayariCategory>(){
        override fun areItemsTheSame(oldItem: AllShayariCategory, newItem: AllShayariCategory): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: AllShayariCategory, newItem: AllShayariCategory): Boolean {
           return oldItem == newItem
        }
    }

    val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllShayariCategoryAdapter.AllShayariVH {
       val binding= RvAllShayariCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return AllShayariVH(binding)
    }

    override fun getItemCount(): Int {
      return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: AllShayariCategoryAdapter.AllShayariVH, position: Int) {
        holder.binding.apply {
            mtvAShayariName.text = asyncListDiffer.currentList[position].name
            Glide.with(holder.itemView.context)
                .load(R.drawable.shayari_logo)
                .centerCrop()
                .placeholder(R.drawable.shayari_logo)
                .into(ivShayari)
            mcvShayariRoot.setOnClickListener {
                onItemClick?.invoke(asyncListDiffer.currentList[position])
            }
        }
    }

    inner class AllShayariVH (val binding: RvAllShayariCategoryBinding) : RecyclerView.ViewHolder (binding.root)
}