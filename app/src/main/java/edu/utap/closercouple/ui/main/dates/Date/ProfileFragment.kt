package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R

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
        //tv_text.text = arguments?.getString("NAME") ;
    }

    //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

}