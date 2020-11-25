package edu.utap.closercouple.ui.main.dates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.utap.closercouple.R

class ExploreFragment  : Fragment() {
    private lateinit var adapter: DateExploreAdapter
    private lateinit var rv: RecyclerView
    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance(title: String): ExploreFragment {
            val exploreFragment = ExploreFragment()
            val b = Bundle()
            b.putString("NAME", title)
            exploreFragment.arguments = b
            return exploreFragment
        }
    }

    // Set up the adapter
    private fun initAdapter(root: View) {
        adapter = DateExploreAdapter(viewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(root.context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeFrag = inflater.inflate(R.layout.fragment_home, container, false)
        initAdapter(homeFrag)
        return homeFrag
    }

}