package edu.utap.closercouple.ui.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_connect_account.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

class ConnectAccountFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()


    companion object {
        fun newInstance(title: String): ConnectAccountFragment {
            val accountFragment = ConnectAccountFragment()
            val b = Bundle()
            b.putString("NAME", title)
            accountFragment.arguments = b
            return accountFragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = requireActivity().supportFragmentManager
        val save_btn = requireActivity().findViewById<TextView>(R.id.save_btn)
        save_btn.visibility = View.GONE

        connect_partner_btn.setOnClickListener {
            if (fm.backStackEntryCount > 0) {
                fm.popBackStackImmediate()
                val parent = fm.fragments.last() as AccountFragment
                parent.onResume()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val mainAct = (activity as MainActivity?)
        mainAct?.supportActionBar?.let {
            val ab = layoutInflater.inflate(R.layout.util_action_bar_icon, container, false)
            mainAct.initActionBar(ab, true)
            ab.actionTitle.text = arguments?.getString("NAME")
        }



        val toolbar = mainAct?.findViewById(R.id.toolbar) as Toolbar
        val fm = requireActivity().supportFragmentManager
        toolbar.setNavigationOnClickListener {
            if (fm.backStackEntryCount > 0) {
                fm.fragments.last().onResume()
                fm.popBackStackImmediate()
            }
        }



        return inflater.inflate(R.layout.fragment_connect_account, container, false)
    }

}