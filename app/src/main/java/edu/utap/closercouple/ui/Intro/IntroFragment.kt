package edu.utap.closercouple.ui.Intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.Model.DateItem
import edu.utap.closercouple.ui.main.dates.Date.DateFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_date.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

class IntroFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()


    companion object {
        fun newInstance(): IntroFragment {
            return IntroFragment()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // bottom_nav.visibility = View.INVISIBLE




        return inflater.inflate(R.layout.fragment_create_date, container, false)
    }

}