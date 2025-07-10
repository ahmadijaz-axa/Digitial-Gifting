package com.ibm.jazzcashconsumer.view.digitalgifting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.jazzcashconsumer.R
import com.ibm.jazzcashconsumer.view.digitalgifting.model.EverydayOccasionsItemModel

class EverydayOccasionsAdapter(
    private var items: List<EverydayOccasionsItemModel>,
    private val onItemClick: ((EverydayOccasionsItemModel, Int) -> Unit)? = null
) : RecyclerView.Adapter<EverydayOccasionsAdapter.ViewHolder>() {

    fun updateData(newItems: List<EverydayOccasionsItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everyday_occasion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items[position], position)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.ivIcon)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)

        fun bind(item: EverydayOccasionsItemModel) {
            icon.setImageResource(item.iconResId)
            title.text = item.title
        }
    }
} 