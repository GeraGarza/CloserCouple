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
import edu.utap.closercouple.Glide
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.Model.User
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*


class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()



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

        val dn = et_display_name.text.toString()
        if(dn=="")
            return

        val user = viewModel.getUserInfo()
        val usr = User(user.uid ,dn, user.username ,user.email ,user.photoUrl ,
            user.location ,user.interests ,user.userDBID ,user.userDatesIDs ,user.partnersUsername ,user.partnerID )

        viewModel.updateUserInfo(usr)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

        viewModel.observeUserInfo().observe(viewLifecycleOwner,
            {
                Glide.glideFetch(it.photoUrl,it.photoUrl,profil_pic)

                et_display_name.setText(it.displayName)
                et_user_name.setText(it.username)
                et_partners_name.setText(it.partnersUsername)
                et_user_email.setText(it.email)
                et_user_location.setText(it.location)
                profile_name_title.setText(it.displayName)

            })




        return view
    }

}