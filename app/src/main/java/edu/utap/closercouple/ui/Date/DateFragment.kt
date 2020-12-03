package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Account.ProfileFragment
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.InterestFragment
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*


class DateFragment: Fragment()  {

    companion object {
        fun newInstance(title: String): DateFragment {
            val dateFragment = DateFragment()
            // Notice how this process resembles Bundles in Intents
            val b = Bundle()
            b.putString("NAME", title)
            // NB: Fragment has an arguments property
            dateFragment.arguments = b
            return dateFragment
        }
    }

    fun accountIconClicked(frag: Fragment){
        val navView = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
        navView?.selectedItemId = R.id.navigation_account;
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_no, R.anim.exit_to_no, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(R.id.main_frame, AccountFragment.newInstance("Account"))

            .replace(R.id.main_frame, frag)
            .addToBackStack(null)
            .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        date_account_btn.setOnClickListener {
            accountIconClicked(ProfileFragment.newInstance("Profile"))
        }

        date_interest_btn.setOnClickListener {
            accountIconClicked(InterestFragment.newInstance("Interests"))
        }

    }



        //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.date_frame, DateCardFragment.newInstance("Card"))
                .commit()

            val mainAct = (activity as MainActivity?)
            mainAct?.supportActionBar?.let {
                val ab = layoutInflater.inflate(R.layout.util_action_bar, container, false)
                mainAct.initActionBar(ab, false)
                ab.actionTitle.text =  arguments?.getString("NAME")
            }


            return inflater.inflate(R.layout.fragment_date, container, false)
    }

}