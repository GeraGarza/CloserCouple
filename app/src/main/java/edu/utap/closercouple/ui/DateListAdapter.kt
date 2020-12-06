package edu.utap.closercouple.ui.main.dates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.DateList
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.Model.DateItem


class DateListAdapter(private val viewModel: UserViewModel, lType: DateList) :
    ListAdapter<DateItem, DateListAdapter.VH>(DateDiff()) {
    class DateDiff : DiffUtil.ItemCallback<DateItem>() {

        override fun areItemsTheSame(oldItem: DateItem, newItem: DateItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DateItem, newItem: DateItem): Boolean {
            return oldItem == newItem
                    && oldItem == newItem
                    && oldItem == newItem
                    && oldItem == oldItem
        }
    }

    private val listType = lType
    inner class VH(view: View) :
        RecyclerView.ViewHolder(view) {

        private var title = itemView.findViewById<TextView>(R.id.row_title)
        private var details = itemView.findViewById<LinearLayout>(R.id.row_date_details)
        private var row_save = itemView.findViewById<ImageButton>(R.id.row_save)

        init {

        }

        fun bind(item: DateItem) {
            if(DateList.Memories ==  listType) {
                details.visibility = View.INVISIBLE
                row_save.visibility = View.INVISIBLE
            }
            else {
                details.visibility = View.VISIBLE
                row_save.visibility = View.VISIBLE

            }


            title.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {


        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.util_row_date, parent, false)
        return VH(itemView)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {

        if(DateList.Memories ==  listType) {
            holder.bind(viewModel.getMemoryDate(holder.adapterPosition))
        }
        else {
            holder.bind(viewModel.getExploreDate(holder.adapterPosition))
        }

    }



}