package edu.utap.closercouple.ui.main.dates.Explore

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import edu.utap.closercouple.MainActivity
import edu.utap.closercouple.R
import edu.utap.closercouple.ui.main.dates.Account.ProfileFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.util_action_bar_icon.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*
import java.util.*

class AccountFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var checkIcon: Drawable
    private lateinit var profileDoneIcon: Drawable
    private lateinit var profileEmptyIcon: Drawable
    private lateinit var interestsDoneIcon: Drawable
    private lateinit var interestsEmptyIcon: Drawable

    companion object {
        fun newInstance(title: String): AccountFragment {
            val accountFragment = AccountFragment()
            val b = Bundle()
            b.putString("NAME", title)
            accountFragment.arguments = b
            return accountFragment
        }
    }

    fun completedInterests(img: Drawable) {
        account_interests_icon.setImageDrawable(img);
    }

    fun completedProfile(img: Drawable) {
        account_create_icon.setImageDrawable(img);
    }

    private fun clickedIcon(frag: Fragment) {
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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        account_create_btn.setOnClickListener { clickedIcon(ProfileFragment.newInstance("Profile")) }
        account_create_icon.setOnClickListener { clickedIcon(ProfileFragment.newInstance("Profile")) }
        account_interests_btn.setOnClickListener { clickedIcon(InterestFragment.newInstance("Interests")) }
        account_interests_icon.setOnClickListener { clickedIcon(InterestFragment.newInstance("Interests")) }



        viewModel.observeProfileStatus().observe(viewLifecycleOwner,
            {
                val statusPro = if (it) profileDoneIcon else profileEmptyIcon
                completedProfile(statusPro)
                if (it) account_create_check.setImageDrawable(checkIcon)
            })

        viewModel.observeInterestsStatus().observe(viewLifecycleOwner,
            {
                val statusInt = if (it) interestsDoneIcon else interestsEmptyIcon
                completedInterests(statusInt)
                if (it) account_interests_check.setImageDrawable(checkIcon)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        checkIcon = AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_done)!!
        profileDoneIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_user_done)!!
        profileEmptyIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_user_grey)!!
        interestsDoneIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_brush_done)!!
        interestsEmptyIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.icon_profle_brush_grey)!!


        val mainAct = (activity as MainActivity?)
        mainAct?.supportActionBar?.let {
            val ab = layoutInflater.inflate(R.layout.util_action_bar, container, false)
            mainAct.initActionBar(ab, false)
            ab.actionTitle.text = arguments?.getString("NAME")
        }
        return view
    }

}