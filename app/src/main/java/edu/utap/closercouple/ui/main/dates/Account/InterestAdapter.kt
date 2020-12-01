package edu.utap.closercouple.ui.main.dates.Account

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import kotlin.random.Random


/**
 * Created by witchel on 1/29/18.  Subsequently modified.
 */

class InterestAdapter(
    private val mContext: Context,
    private val colorList: List<InterestsList.InterestItem>
)
    : RecyclerView.Adapter<InterestAdapter.ViewHolder>() {

    private var random = Random(System.currentTimeMillis())
    // Create a new, writable list that we initialize with colorList
    private var list = mutableListOf<InterestsList.InterestItem>().apply {
        addAll(colorList.shuffled())
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // private var textView = ??????
        private var colorCard: TextView = v.findViewById(R.id.tv)

        init {

        }

        fun bind(pos: Int) {
            // XXX Write me.
            val color = list[pos]
            val luminance = getLuminance(color.color)
            if(luminance<0.3) colorCard.setTextColor(Color.parseColor("#FFFFFF"))
            else colorCard.setTextColor(Color.parseColor("#000000"))

            colorCard.text  = String.format("%s %1.2f ", color.name, luminance)
            val shape = GradientDrawable()
            shape.cornerRadius = 30f
            shape.setColor(color.color)
            colorCard.background = shape

        }
    }

    override fun getItemCount() = InterestsList.size()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new View
        // class responisble for turning xml to view objects, inflate is the methods that turns layout to view
        // first argument is the row we are inflating, recycle view, dont add to rv just yet
        val v = LayoutInflater.from(mContext).inflate(R.layout.card_interest, parent, false)

        // Whenever the recycle viewer needs a view holder, we give it one of our viewholders which each
        // wraps a row layout; so on createviewholder will only be called for the amount of items that fit on screen
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // will be called over and over, while items show on screen
        // when scroll, or updating item
        holder.bind(position)
    }


    // A static function for computing luminance
    companion object {
        fun getLuminance(color: Int): Float {
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)

            val hsl = FloatArray(3)
            ColorUtils.RGBToHSL(red, green, blue, hsl)
            return hsl[2]
        }
    }
}