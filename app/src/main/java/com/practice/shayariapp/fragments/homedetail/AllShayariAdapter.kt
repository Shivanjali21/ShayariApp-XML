package com.practice.shayariapp.fragments.homedetail

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.os.Build
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.shayariapp.R
import com.practice.shayariapp.databinding.RvAllShayariBinding
import com.practice.shayariapp.model.allshayari.AllShayari

class AllShayariAdapter(private val context: Context) : RecyclerView.Adapter<AllShayariAdapter.HShayariVH>() {

   inner class HShayariVH(val binding: RvAllShayariBinding) : RecyclerView.ViewHolder(binding.root)

   private var lastSelectedPosition: Int = -1

    private val diffUtil = object : DiffUtil.ItemCallback<AllShayari>() {
        override fun areItemsTheSame(oldItem: AllShayari, newItem: AllShayari): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: AllShayari, newItem: AllShayari): Boolean {
            return oldItem == newItem
        }
    }

    val asyncDifferList = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HShayariVH {
        val binding = RvAllShayariBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HShayariVH(binding)
    }

    override fun getItemCount(): Int {
      return asyncDifferList.currentList.size
    }

    override fun onBindViewHolder(holder: HShayariVH, position: Int) {
        holder.binding.apply {
            mtvShayariContent.text = asyncDifferList.currentList[position].Shayari

            mcvShayariCopy.setOnClickListener {
                val shareShayari = mtvShayariContent.text.toString()
                val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("clip_data", shareShayari).apply {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                       description.extras = PersistableBundle().apply {
                         putBoolean(ClipDescription.MIMETYPE_TEXT_INTENT, true)
                       }
                   }
                }
                clipboardManager.setPrimaryClip(clipData)
                //Toast.makeText(context, "Copied to Clipboard", Toast.LENGTH_SHORT).show()
            }

            ivShayariLike.setOnClickListener {
                val currentPosition = holder.adapterPosition
                if (lastSelectedPosition == currentPosition) {
                    // Unselect the item
                    lastSelectedPosition = RecyclerView.NO_POSITION
                    ivShayariLike.setImageResource(R.drawable.like) // Or appropriate default icon
                } else {
                    // Select the item
                    lastSelectedPosition = currentPosition
                    ivShayariLike.setImageResource(R.drawable.dislike)
                }
                notifyItemChanged(currentPosition) // Or notifyItemRangeChanged for efficiency
            }

            mcvShayariShare.setOnClickListener {
               val shareShayari = mtvShayariContent.text.toString()
               val shareIntent =  Intent(Intent.ACTION_SEND)
               shareIntent.setType("text/plain")
               shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Shayari")
               shareIntent.putExtra(Intent.EXTRA_TEXT, shareShayari[holder.adapterPosition])
               context.startActivity(Intent.createChooser(shareIntent, "Share Via"))
            }
        }
    }
}