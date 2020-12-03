package edu.utap.closercouple.ui.main.dates.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*


class ProfileFragment: Fragment()  {


    companion object {
        fun newInstance(title: String): ProfileFragment {
            val profileFragment = ProfileFragment()
            // Notice how this process resembles Bundles in Intents
            val b = Bundle()
            b.putString("NAME", title)
            // NB: Fragment has an arguments property
            profileFragment.arguments = b
            return profileFragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val textView = activity?.findViewById(R.id.save_interests_btn) as TextView
        val fm = requireActivity().supportFragmentManager

        textView.setOnClickListener {
            if (fm.backStackEntryCount > 0) {
                fm.fragments.last().onResume()
                fm.popBackStackImmediate()
            }
        }
    }

    //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        val mainAct = (activity as MainActivity?)
        mainAct?.supportActionBar?.let {
            val ab = layoutInflater.inflate(R.layout.util_action_bar_icon, container, false)
            mainAct.initActionBar(ab, true)
            ab.actionTitle.text =  arguments?.getString("NAME")
        }


        val toolbar = mainAct?.findViewById(R.id.toolbar) as Toolbar
        val fm = requireActivity().supportFragmentManager
        toolbar.setNavigationOnClickListener {
            if (fm.backStackEntryCount > 0) {
                fm.fragments.last().onResume()
                fm.popBackStackImmediate()
            }
        }

        return view
    }

}