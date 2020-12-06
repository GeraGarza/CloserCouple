package edu.utap.closercouple.ui.main.dates.Account

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.Model.InterestItem
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlin.random.Random


/**
 * Created by witchel on 1/29/18.  Subsequently modified.
 */

class InterestAdapter(
    private val viewModel: UserViewModel,
    private val mContext: Context,
    private val colorList: List<InterestItem>
) : RecyclerView.Adapter<InterestAdapter.ViewHolder>() {

    private var random = Random(System.currentTimeMillis())


    inner class ViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        // private var textView = ??????
        private var interestCard: TextView = v.findViewById(R.id.tv)
        private var card: CardView = v.findViewById(R.id.interest_card)
        private var cardIcon: ImageView = v.findViewById(R.id.card_icon)
        private var checkedIcon: ImageView = v.findViewById(R.id.checked)
        private var red =  ContextCompat.getColor(v.context, R.color.active_icon)

        init {
        }

        fun bind(pos: Int) {

            val interest = viewModel.getInterestListAt(pos)
            val shape = GradientDrawable()
            shape.cornerRadius = 30f

            if (interest.selected) {
                shape.setColor(red)
                checkedIcon.visibility = View.VISIBLE
            } else {
                shape.setColor(interest.color)
                checkedIcon.visibility = View.INVISIBLE
            }
            card.setOnClickListener {
                viewModel.toggleInterestItemAt(pos)
                if (interest.selected) {
                    shape.setColor(red)
                    checkedIcon.visibility = View.VISIBLE

                } else {
                    shape.setColor(interest.color)
                    checkedIcon.visibility = View.INVISIBLE

                }
            }
            cardIcon.setImageDrawable(getDrawable(mContext, interest.icon));
            interestCard.text = String.format("%s", interest.name)
            card.background = shape
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