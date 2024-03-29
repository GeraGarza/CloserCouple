package edu.utap.closercouple.ui.Explore

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
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_date.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

class AddDateFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()


    companion object {
        fun newInstance(title: String): AddDateFragment {
            val accountFragment = AddDateFragment()
            val b = Bundle()
            b.putString("NAME", title)
            accountFragment.arguments = b
            return accountFragment
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = activity?.findViewById(R.id.done_create_date_btn) as Button

        val fm = requireActivity().supportFragmentManager
        val save_btn = requireActivity().findViewById<TextView>(R.id.save_btn)
        save_btn.visibility = View.GONE

        textView.setOnClickListener {
            if (fm.backStackEntryCount > 0) {
                fm.popBackStackImmediate()
                val parent = fm.fragments.last() as ExploreFragment
                parent.onResume()
            }
        }

        done_create_date_btn.setOnClickListener {
            var cats: MutableList<String> = mutableListOf()

            var checked = create_date_food.isChecked
            if (checked){
                cats.add("Food")
            }
            checked = create_date_drink.isChecked
            if (checked){
                cats.add("Drink")
            }
            checked = create_date_outdoor.isChecked
            if (checked){
                cats.add("Outdoors")
            }

            val date = DateItem(
                title = et_create_date_place.text.toString(),
                description = et_create_date_duration.text.toString(),
                categories = cats.toList(),
                cost = 2,
                ratings = 1,
                thumbnail = et_create_date_picture.text.toString(),
            )


            viewModel.createExploreDate(date)
            if (fm.backStackEntryCount > 0) {
                fm.popBackStackImmediate()
                val parent = fm.fragments.last() as ExploreFragment
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



        return inflater.inflate(R.layout.fragment_create_date, container, false)
    }

}