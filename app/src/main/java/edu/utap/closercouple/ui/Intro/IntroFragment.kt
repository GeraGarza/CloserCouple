package edu.utap.closercouple.ui.Intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
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
import kotlinx.android.synthetic.main.fragment_intro.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

class IntroFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    var cur_page = 0

    companion object {
        fun newInstance(): IntroFragment {
            return IntroFragment()
        }
    }

    fun renderPage(){

        if (cur_page==1){

            intro_title.text = "Choosing your availability\n" + "is easy"
            intro_desc.text = "Very busy? Select your available days and we will find a time that works best."
            intro_img.setImageDrawable(AppCompatResources.getDrawable(requireActivity(), R.drawable.ic_calendar_heart));

        }

        if (cur_page==2){
            intro_title.text = "And dates that are based\n" +
                    "on your interests."
            intro_desc.text = "Very busy? Select your available days and we will find a time that works best."
            intro_img.setImageDrawable(AppCompatResources.getDrawable(requireActivity(), R.drawable.ic_interests));


        }
        if (cur_page==3){
            intro_title.text = "Got all that?"
            intro_desc.text = "Read the FAQs in the Accounts tab to learn more. And thanks for Joining the CloserCouple community!"
            intro_img.setImageDrawable(AppCompatResources.getDrawable(requireActivity(), R.drawable.ic_table));

        }

        if (cur_page==4){

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, DateFragment.newInstance("Date"))
                .commit()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        intro_next.setOnClickListener {
            cur_page +=1
            renderPage()

        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

}