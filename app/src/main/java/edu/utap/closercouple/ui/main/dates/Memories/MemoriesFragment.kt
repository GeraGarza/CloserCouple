package edu.utap.closercouple.ui.main.dates.Memories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R

class MemoriesFragment  : Fragment() {
    private lateinit var adapter: MemoriesDateAdapter
    private lateinit var rv: RecyclerView
    private val viewModel: MemoriesViewModel by activityViewModels()

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
        adapter = MemoriesDateAdapter(viewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(rv.context, LinearLayoutManager.VERTICAL)
        itemDecor.setDrawable(ContextCompat.getDrawable(rv.context, (R.drawable.divider))!!)
        rv.addItemDecoration(itemDecor)
        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipe.isEnabled = false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_rv, container, false)
        (activity as MainActivity).supportActionBar?.show();
        initRecyclerView(view)
        return view
    }

}