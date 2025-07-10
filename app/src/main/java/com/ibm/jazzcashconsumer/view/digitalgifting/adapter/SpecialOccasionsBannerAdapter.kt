package com.ibm.jazzcashconsumer.view.digitalgifting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.jazzcashconsumer.R
import com.ibm.jazzcashconsumer.view.digitalgifting.model.SpecialOccasionsItemModel

class SpecialOccasionsBannerAdapter(
    private var items: List<SpecialOccasionsItemModel>,
    private val onItemClick: ((SpecialOccasionsItemModel, Int) -> Unit)? = null
) : RecyclerView.Adapter<SpecialOccasionsBannerAdapter.ViewHolder>() {

    fun updateData(newItems: List<SpecialOccasionsItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_occasion_banner, parent, false)
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
        private val description: TextView = itemView.findViewById(R.id.tvDescription)
        private val daysLeft: TextView? = itemView.findViewById(R.id.tvDaysLeft)
        private val card: View = itemView

        fun bind(item: SpecialOccasionsItemModel) {
            icon.setImageResource(item.iconResId)
            title.text = item.title
            description.text = item.description
            daysLeft?.let {
                it.visibility = View.VISIBLE
                it.text = "${item.daysLeft} Days"
            }
            item.backgroundResId?.let { card.setBackgroundResource(it) }
        }
    }
} 