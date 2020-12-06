package edu.utap.closercouple.ui.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Account.InterestAdapter
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.util_action_bar.view.*


class InterestFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
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
        val rv_search = root.findViewById<LinearLayout>(R.id.rv_search)

        adapter = InterestAdapter(viewModel, requireContext(), InterestsList.getAll())
        rv.adapter = adapter
        rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipe.isEnabled = false
        rv_search.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val save_btn = requireActivity().findViewById<TextView>(R.id.save_btn)
        save_btn.visibility = View.GONE

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

        return view
    }

}