package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R

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
        //tv_text.text = arguments?.getString("NAME") ;
        (activity as MainActivity).supportActionBar?.hide();

        childFragmentManager
            .beginTransaction()
            .add(R.id.date_frame, DateCardFragment.newInstance("Card"))
            .commit()
        
    }

        //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_date, container, false)
    }

}