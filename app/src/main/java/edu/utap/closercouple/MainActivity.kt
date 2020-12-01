package edu.utap.closercouple

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.ui.main.dates.Date.DateFragment
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.Memories.MemoriesFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {



    // https://stackoverflow.com/questions/24838155/set-onclick-listener-on-action-bar-title-in-android/29823008#29823008
    fun initActionBar(view: View, backButton: Boolean = false) {

        if (backButton)
            toolbar.setNavigationIcon(R.drawable.ic_back)
        else
            toolbar.navigationIcon = null

        supportActionBar?.customView = view
    }

    private fun initToolbar(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayShowCustomEnabled(true)
            val view = layoutInflater.inflate(R.layout.util_action_bar, null)

            initActionBar(view) }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initToolbar()
        bottom_nav.itemIconTintList = null;

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setOnNavigationItemSelectedListener{ menuItem ->

            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.navigation_dates -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, DateFragment.newInstance("Date"))
                        .commit()
                }
                R.id.navigation_explore -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, ExploreFragment.newInstance("Explore"))
                        .commit()
                }
                R.id.navigation_memories -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, MemoriesFragment.newInstance("Memories"))
                        .commit()
                }
                R.id.navigation_account -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frame, AccountFragment.newInstance("Account"))
                        .commit()
                }
            }

            false
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, DateFragment.newInstance("Date"))
            .commit()

    }
}