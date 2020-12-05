package edu.utap.closercouple.ui.main.dates.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*


class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var name_title: TextView
    private lateinit var user_name: TextView
    private lateinit var user_number: TextView
    private lateinit var user_location: TextView
    private lateinit var user_email: TextView


    companion object {
        fun newInstance(title: String): ProfileFragment {
            val profileFragment = ProfileFragment()
            // Notice how this process resembles Bundles in Intents
            val b = Bundle()
            b.putString("NAME", title)
            // NB: Fragment has an arguments property
            profileFragment.arguments = b
            return profileFragment
        }
    }

    private fun saveUserInfo() {
        val userInfo = UserViewModel.UserInfo(
            user_name.text.toString(), user_number.text.toString(),
            user_location.text.toString(), user_email.text.toString()
        )
        viewModel.updateUserInfo(userInfo)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        name_title = activity?.findViewById(R.id.profile_name_title) as TextView
        user_name = activity?.findViewById(R.id.et_user_name) as TextView
        user_number = activity?.findViewById(R.id.et_partners_name) as TextView
        user_location = activity?.findViewById(R.id.et_user_email) as TextView
        user_email = activity?.findViewById(R.id.et_user_location) as TextView

        val userInfo = viewModel.getUserInfo()
        name_title.text = userInfo.name
        user_name.text = userInfo.name
        user_number.text = userInfo.number
        user_location.text = userInfo.location
        user_email.text = userInfo.email


        val textView = activity?.findViewById(R.id.save_btn) as TextView
        val fm = requireActivity().supportFragmentManager

        textView.setOnClickListener {
            if (fm.backStackEntryCount > 0) {
                saveUserInfo()
                fm.popBackStackImmediate()
                val parent = fm.fragments.last() as AccountFragment
                parent.onResume()
            }
        }
    }

    //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val view = inflater.inflate(R.layout.fragment_profile, container, false)


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