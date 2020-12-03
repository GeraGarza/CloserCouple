package edu.utap.closercouple.ui.main.dates.Explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import kotlinx.android.synthetic.main.main_rv.*
import kotlinx.android.synthetic.main.util_action_bar.view.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*

class ExploreFragment  : Fragment() {
    private lateinit var adapter: ExploreDateAdapter
    private lateinit var rv: RecyclerView
    private val viewModel: ExploreViewModel by activityViewModels()

    companion object {
        fun newInstance(title: String): ExploreFragment {
            val exploreFragment = ExploreFragment()
            val b = Bundle()
            b.putString("NAME", title)
            exploreFragment.arguments = b
            return exploreFragment
        }
    }


    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.recyclerView)
        val rv_search = root.findViewById<LinearLayout>(R.id.rv_search)

        adapter = ExploreDateAdapter(viewModel)
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