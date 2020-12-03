package edu.utap.closercouple.ui.main.dates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.Data
import edu.utap.closercouple.R


class DateListAdapter(private val viewModel: UserViewModel) :
    ListAdapter<String, DateListAdapter.VH>(DateDiff()) {
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

        fun bind(item:  Data?) {
            if(item == null)return
            title.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.util_row_date, parent, false)
        return VH(itemView)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(viewModel.getListAt(holder.adapterPosition))
    }

    override fun getItemCount() = viewModel.getItemCount()



}