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
import edu.utap.closercouple.ui.Account.ConnectAccountFragment
import edu.utap.closercouple.ui.Account.InterestFragment
import edu.utap.closercouple.ui.main.dates.Account.ProfileFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.util_action_bar_icon.view.*

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
        account_account_icon.setOnClickListener { clickedIcon(ProfileFragment.newInstance("Profile")) }
        account_interests_btn.setOnClickListener { clickedIcon(InterestFragment.newInstance("Interests")) }
        account_interests_icon.setOnClickListener { clickedIcon(InterestFragment.newInstance("Interests")) }
        account_connect_account.setOnClickListener{ clickedIcon(ConnectAccountFragment.newInstance("Connect Account")) }


        viewModel.observeProfileStatus().observe(viewLifecycleOwner,
            {
                if(it){
                    account_account_icon.setImageDrawable(profileDoneIcon);
                    account_account_check.setImageDrawable(checkIcon)
                }else account_account_icon.setImageDrawable(profileEmptyIcon);

            })

        viewModel.observeInterestsStatus().observe(viewLifecycleOwner,
            {
                if(it){
                    account_interests_icon.setImageDrawable(interestsDoneIcon)
                    account_interests_check.setImageDrawable(checkIcon)
                }else account_interests_icon.setImageDrawable(interestsEmptyIcon)

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

}