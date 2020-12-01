package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.Memories.MemoriesFragment
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.main_activity.*


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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        account_create_btn.setOnClickListener {

            val navView = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
            navView?.selectedItemId = R.id.navigation_account;

            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.main_frame, ProfileFragment.newInstance("Explore"))
                .addToBackStack(null)
                .commit()
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
            mainAct?.supportActionBar?.let { mainAct.initActionBar(it, false) }


            return inflater.inflate(R.layout.fragment_date, container, false)
    }

}