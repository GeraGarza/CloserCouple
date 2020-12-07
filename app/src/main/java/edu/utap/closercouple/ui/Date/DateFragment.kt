package edu.utap.closercouple.ui.main.dates.Date

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Account.ProfileFragment
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.Account.InterestFragment
import edu.utap.closercouple.ui.Intro.IntroFragment
import edu.utap.closercouple.ui.Model.User
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*


class DateFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var checkIcon: Drawable
    private lateinit var profileDoneIcon: Drawable
    private lateinit var profileEmptyIcon: Drawable
    private lateinit var interestsDoneIcon: Drawable
    private lateinit var interestsEmptyIcon: Drawable


    companion object {
        fun newInstance(title: String): DateFragment {
            val dateFragment = DateFragment()
            // Notice how this process resembles Bundles in Intents
            val b = Bundle()
            b.putString("NAME", title)
            // NB: Fragment has an arguments property
            dateFragment.arguments = b
            return dateFragment
        }
    }

    fun accountIconClicked(frag: Fragment) {
        val navView = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav)
        navView?.selectedItemId = R.id.navigation_account;
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_no,
                R.anim.exit_to_no,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .replace(R.id.main_frame, AccountFragment.newInstance("Account"))
            .replace(R.id.main_frame, frag)
            .addToBackStack(null)
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel.setUpUser()

        date_account_icon.setOnClickListener {
            accountIconClicked(ProfileFragment.newInstance("Profile"))

        }

        date_interests_icon.setOnClickListener {
            accountIconClicked(InterestFragment.newInstance("Interests"))
        }




        // start up user once auth complete
        viewModel.observeUserAuth().observe(viewLifecycleOwner,
            {

                if(viewModel.getUsername() != "")
                    return@observe

                val uid = it.uid
                val displayName =  it.displayName.toString()
                val username = it.displayName.toString()
                val email = it.email.toString()
                val photoUrl = it.photoUrl.toString()
                val location = ""
                val interests = listOf<String>()
                val userDBID = ""
                val userDatesIDs = listOf<String>()
                val partnersUsername = ""
                val partnersID = ""
                var usr = User(uid,displayName,username,email,photoUrl,location,interests,userDBID,userDatesIDs ,partnersUsername,partnersID)
                viewModel.updateUserInfo(usr)
                viewModel.setUpUser()
            })



        viewModel.observeProfileStatus().observe(viewLifecycleOwner,
            {
                if(it){
                    date_account_icon.setImageDrawable(profileDoneIcon);
                    date_account_check.setImageDrawable(checkIcon)
                }else{
                   date_account_icon.setImageDrawable(profileEmptyIcon);
                }
            })

        viewModel.observeInterestsCount().observe(viewLifecycleOwner,
            {
                if(it!=0){
                    date_interests_icon.setImageDrawable(interestsDoneIcon)
                    date_interests_check.setImageDrawable(checkIcon)
                }else{
                    date_interests_icon.setImageDrawable(interestsEmptyIcon)
                }
            })

    }


    //https://stackoverflow.com/questions/10450348/do-fragments-really-need-an-empty-constructor
         override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_date, container, false)


        checkIcon = AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_done)!!
        profileDoneIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_user_done)!!
        profileEmptyIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_user_grey)!!
        interestsDoneIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_brush_done)!!
        interestsEmptyIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_brush_grey)!!



        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.date_frame, DateCardFragment.newInstance("Card"))
            .commit()

        val mainAct = (activity as MainActivity?)
        mainAct?.supportActionBar?.let {
            val ab = layoutInflater.inflate(R.layout.util_action_bar, container, false)
            mainAct.initActionBar(ab, false)
            ab.actionTitle.text = arguments?.getString("NAME")
        }




        viewModel.observeUserInfo().observe(viewLifecycleOwner,
            {
                val welcome_text = "Good morning"
                if(it.displayName!="")  date_welcome_tv.text = "${welcome_text}, \n${it.displayName}!"
                else date_welcome_tv.text = "$welcome_text!"
//                if(it.uid!="")
//                    viewModel.updateUserInFirebase()

            })


        return view
    }

}