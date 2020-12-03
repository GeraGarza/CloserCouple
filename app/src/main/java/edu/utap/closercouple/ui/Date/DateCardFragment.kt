package edu.utap.closercouple.ui.main.dates.Date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R

class DateCardFragment: Fragment()  {


    companion object {
        fun newInstance(title: String): DateCardFragment {
            val dateCardFragment = DateCardFragment()
            // Notice how this process resembles Bundles in Intents
            val b = Bundle()
            b.putString("NAME", title)
            b.putBoolean("HAS_DATE", false)
            // NB: Fragment has an arguments property
            dateCardFragment.arguments = b
            return dateCardFragment
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


        val bundle = this.arguments
        val hasDate = bundle?.getBoolean("HAS_DATE")?: false

        return (if (hasDate) inflater.inflate(R.layout.fragment_date_value, container, false)
                else inflater.inflate(R.layout.fragment_date_empty, container, false))
    }

}