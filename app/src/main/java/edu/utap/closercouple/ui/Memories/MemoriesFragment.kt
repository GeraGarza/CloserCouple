package edu.utap.closercouple.ui.main.dates.Memories

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
import edu.utap.closercouple.ui.main.dates.DateListAdapter
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.util_action_bar.view.*

class MemoriesFragment  : Fragment() {
    private lateinit var adapter: DateListAdapter
    private lateinit var rv: RecyclerView
    private val viewModel: UserViewModel by activityViewModels()

    companion object {
        fun newInstance(title: String): MemoriesFragment {
            val memoriesFragment = MemoriesFragment()
            val b = Bundle()
            b.putString("NAME", title)
            memoriesFragment.arguments = b
            return memoriesFragment
        }
    }


    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.recyclerView)
        val rv_search = root.findViewById<LinearLayout>(R.id.rv_search)
        val add_date_btn = root.findViewById<Button>(R.id.add_date_btn)

        adapter = DateListAdapter(viewModel, DateList.Memories)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(rv.context, LinearLayoutManager.VERTICAL)
        itemDecor.setDrawable(ContextCompat.getDrawable(rv.context, (R.drawable.divider))!!)
        rv.addItemDecoration(itemDecor)
        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipe.isEnabled = false
        rv_search.visibility = View.VISIBLE
        add_date_btn.visibility = View.GONE

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