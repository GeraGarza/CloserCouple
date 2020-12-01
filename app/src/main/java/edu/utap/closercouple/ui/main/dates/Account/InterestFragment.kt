package edu.utap.closercouple.ui.main.dates.Explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Account.InterestAdapter
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import kotlinx.android.synthetic.main.util_action_bar.view.*
import kotlinx.android.synthetic.main.util_action_bar.view.actionTitle
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

class InterestFragment  : Fragment() {
    private lateinit var adapter: InterestAdapter
    private lateinit var rv: RecyclerView

    companion object {
        fun newInstance(title: String): InterestFragment {
            val exploreFragment = InterestFragment()
            val b = Bundle()
            b.putString("NAME", title)
            exploreFragment.arguments = b
            return exploreFragment
        }
    }


    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = InterestAdapter(requireContext(), InterestsList.getAll())
        rv.adapter = adapter
        rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipe.isEnabled = false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_rv, container, false)
        initRecyclerView(view)



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