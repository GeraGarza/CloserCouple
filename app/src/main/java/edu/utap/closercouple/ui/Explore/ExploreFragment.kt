package edu.utap.closercouple.ui.main.dates.Explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.closercouple.DateList
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.Explore.AddDateFragment
import edu.utap.closercouple.ui.main.dates.Account.ProfileFragment
import edu.utap.closercouple.ui.main.dates.DateListAdapter
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.main_rv.*
import kotlinx.android.synthetic.main.util_action_bar.view.*

class ExploreFragment  : Fragment() {
    private lateinit var adapter: DateListAdapter
    private lateinit var rv: RecyclerView
    private val viewModel: UserViewModel by activityViewModels()

    companion object {
        fun newInstance(title: String): ExploreFragment {
            val exploreFragment = ExploreFragment()
            val b = Bundle()
            b.putString("NAME", title)
            exploreFragment.arguments = b
            return exploreFragment
        }
    }

    private fun clickedAddDate(frag: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .replace(R.id.main_frame, frag)
            .addToBackStack(null)
            .commit()
    }

    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.recyclerView)
        val rv_search = root.findViewById<LinearLayout>(R.id.rv_search)
        val add_date_btn = root.findViewById<Button>(R.id.add_date_btn)
        add_date_btn.setOnClickListener {
            clickedAddDate(AddDateFragment.newInstance("Add Date"))
        }
        adapter = DateListAdapter(viewModel, DateList.Explore)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(rv.context, LinearLayoutManager.VERTICAL)
        itemDecor.setDrawable(ContextCompat.getDrawable(rv.context, (R.drawable.divider))!!)
        rv.addItemDecoration(itemDecor)
        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipe.isEnabled = false
        rv_search.visibility = View.VISIBLE
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
            mainAct.supportActionBar?.let {
                val ab = layoutInflater.inflate(R.layout.util_action_bar, container, false)
                mainAct.initActionBar(ab, false)
                ab.actionTitle.text =  arguments?.getString("NAME")
            }
        }
        return view
    }

}