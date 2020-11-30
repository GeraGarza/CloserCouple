package edu.utap.closercouple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.ui.main.dates.DateFragment
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.Memories.MemoriesFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {



    // https://stackoverflow.com/questions/24838155/set-onclick-listener-on-action-bar-title-in-android/29823008#29823008
    private fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val customView: View =
            layoutInflater.inflate(R.layout.util_action_bar, null)
        // Apply the custom view
        actionBar.customView = customView
        nav_view.itemIconTintList = null;


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
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
            .replace(R.id.main_frame, DateFragment.newInstance("Start"))
            .commit()

    }
}