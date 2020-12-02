package edu.utap.closercouple.ui.main.dates.Account

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    inner class ViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        // private var textView = ??????
        private var interestCard: TextView = v.findViewById(R.id.tv)
        init {

        }

        fun bind(pos: Int) {

            val interest = list[pos]
            val shape = GradientDrawable()
            shape.cornerRadius = 30f

            if(interest.selected){
                val color = ContextCompat.getColor(v.context, R.color.active_icon)
                shape.setColor(color)
                interestCard.setTextColor(Color.parseColor("#FFFFFF"))
                interestCard.text = String.format("%s", interest.name)
            }else {
                val luminance = getLuminance(interest.color)
                if (luminance < 0.3) interestCard.setTextColor(Color.parseColor("#FFFFFF"))
                else interestCard.setTextColor(Color.parseColor("#000000"))
                interestCard.text = String.format("%s %1.2f ", interest.name, luminance)
                shape.setColor(interest.color)
            }
            interestCard.background = shape


            interestCard.setOnClickListener {
                list[pos] = InterestsList.InterestItem(interest.color,interest.name,!list[pos].selected)
                if(list[pos].selected){
                    val color = ContextCompat.getColor(v.context, R.color.active_icon)
                    shape.setColor(color)
                    interestCard.setTextColor(Color.parseColor("#FFFFFF"))
                    interestCard.text = String.format("%s", interest.name)
                }else {
                    val luminance = getLuminance(interest.color)
                    if (luminance < 0.3) interestCard.setTextColor(Color.parseColor("#FFFFFF"))
                    else interestCard.setTextColor(Color.parseColor("#000000"))
                    interestCard.text = String.format("%s %1.2f ", interest.name, luminance)
                    shape.setColor(interest.color)
                }
            }

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