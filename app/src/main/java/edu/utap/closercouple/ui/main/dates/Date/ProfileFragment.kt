package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import kotlinx.android.synthetic.main.fragment_profile.*


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
        profil_pic.setOnClickListener {

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
        mainAct?.supportActionBar?.let { mainAct.initActionBar(it, true)}


        val toolbar = mainAct?.findViewById(R.id.toolbar) as Toolbar
        toolbar.setNavigationOnClickListener {
            if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
                requireActivity().supportFragmentManager.popBackStackImmediate()
                mainAct.supportActionBar?.let { mainAct.initActionBar(it, false) }
            }

        }


        return view
    }

}