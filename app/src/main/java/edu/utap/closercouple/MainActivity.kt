package edu.utap.closercouple

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.utap.closercouple.ui.Intro.IntroFragment
import edu.utap.closercouple.ui.main.dates.Date.DateFragment
import edu.utap.closercouple.ui.main.dates.Explore.AccountFragment
import edu.utap.closercouple.ui.main.dates.Explore.ExploreFragment
import edu.utap.closercouple.ui.main.dates.Memories.MemoriesFragment
import edu.utap.closercouple.ui.main.dates.UserViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    private val viewModel: UserViewModel by viewModels()

    // [END declare_auth]
    // private lateinit var binding: ActivityGoogleBinding
    private val RC_SIGN_IN: Int = 9001

    private var mSignInClient: GoogleSignInClient? = null
    private lateinit var googleSignInClient: GoogleSignInClient

    // https://stackoverflow.com/questions/24838155/set-onclick-listener-on-action-bar-title-in-android/29823008#29823008
    fun initActionBar(view: View, backButton: Boolean = false) {

        if (backButton)
            toolbar.setNavigationIcon(R.drawable.ic_back)
        else
            toolbar.navigationIcon = null

        supportActionBar?.customView = view
    }


    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayShowCustomEnabled(true)
            val view = layoutInflater.inflate(R.layout.util_action_bar, null)

            initActionBar(view)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initToolbar()
        bottom_nav.itemIconTintList = null;


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        bottom_nav.visibility = View.GONE

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        sign_in_btn.setOnClickListener { signIn() }
        auth = Firebase.auth
        viewModel.firestoreInit(auth)


        mSignInClient = GoogleSignIn.getClient(this, gso);

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setOnNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked = true
            when (menuItem.itemId) {
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
            .replace(R.id.main_frame, IntroFragment.newInstance())
            .commit()

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser!=null){
            landing_page.visibility = View.GONE
            bottom_nav.visibility = View.VISIBLE
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            if (task.isSuccessful) {

                val acct = task.result!!
                firebaseAuthWithGoogle(acct.idToken!!)
                landing_page.visibility = View.GONE
                bottom_nav.visibility = View.VISIBLE
                viewModel.firestoreInit(auth)


            } else {
                println("signInWithCredential:failure")
            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                } else {
                    println("signInWithCredential:failure")
                }
            }
    }


    fun signOut() {
        // Firebase sign out
        auth.signOut()
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            landing_page.visibility = View.VISIBLE
            bottom_nav.visibility = View.GONE
        }
    }

}