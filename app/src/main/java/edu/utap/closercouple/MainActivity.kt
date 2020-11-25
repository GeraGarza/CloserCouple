package edu.utap.closercouple

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.utap.closercouple.ui.main.dates.DateFragment
import edu.utap.closercouple.ui.main.dates.ExploreFragment


import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.fragment_rv.*

class MainActivity : AppCompatActivity() {



    // https://stackoverflow.com/questions/24838155/set-onclick-listener-on-action-bar-title-in-android/29823008#29823008
    private fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val customView: View =
            layoutInflater.inflate(R.layout.action_bar, null)
        // Apply the custom view
        actionBar.customView = customView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                        .replace(R.id.container, DateFragment.newInstance("Date"))
                        .commit()
                }
                R.id.navigation_explore -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ExploreFragment.newInstance("Explore"))
                        .commit()
                }
                R.id.navigation_memories -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, DateFragment.newInstance("Memories"))
                        .commit()
                }
                R.id.navigation_account -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, DateFragment.newInstance("Account"))
                        .commit()
                }
            }

            false
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DateFragment.newInstance("Start"))
            .commit()

    }
}