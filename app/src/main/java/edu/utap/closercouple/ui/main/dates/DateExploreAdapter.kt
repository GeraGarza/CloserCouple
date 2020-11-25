package edu.utap.closercouple.ui.main.dates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.R


class DateExploreAdapter(private val viewModel: MainViewModel) :
    ListAdapter<String, DateExploreAdapter.VH>(DateDiff()) {
    class DateDiff : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
                    && oldItem == newItem
                    && oldItem == newItem
                    && oldItem == oldItem
        }
    }

    inner class VH(view: View) :
        RecyclerView.ViewHolder(view) {

        private var title = itemView.findViewById<TextView>(R.id.row_title)

        init {

        }

        fun bind(item: String) {
            title.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_date, parent, false)
        return VH(itemView)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


}