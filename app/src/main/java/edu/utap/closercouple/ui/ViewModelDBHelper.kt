package edu.utap.closercouple.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import edu.utap.closercouple.ui.Model.DateItem
import edu.utap.closercouple.ui.Model.User


class ViewModelDBHelper(
    ExploreDatesList: MutableLiveData<List<DateItem>>,
    MemoryDatesList: MutableLiveData<List<DateItem>>,
    newUser: MutableLiveData<Boolean>
) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val newUser = newUser

    init {
        dbFetchDates(ExploreDatesList)
        //dbFetchDates(MemoryDatesList)
    }




    private fun elipsizeString(string: String): String {
        if (string.length < 10)
            return string
        return string.substring(0..9) + "..."
    }

    /////////////////////////////////////////////////////////////
    // Interact with Firestore db
    // https://firebase.google.com/docs/firestore/query-data/get-data
    private fun dbFetchDates(ExploreDatesList: MutableLiveData<List<DateItem>>) {

        if (FirebaseAuth.getInstance().currentUser == null) {
            Log.d(javaClass.simpleName, "No one is logged in")
            ExploreDatesList.value = listOf()
            return
        }

        db.collection("globalDates")
            .limit(10)
            .get()
            .addOnSuccessListener { result ->
                println("allDates fetch ${result!!.documents.size}")
                ExploreDatesList.postValue(result.documents.mapNotNull {
                    it.toObject(DateItem::class.java)
                })
            }
            .addOnFailureListener {
                println("allDates fetch FAILED ")
            }
    }


    fun userFromQuery(userResult: HashMap<*, *>): User {
        val uid = userResult["uid"].toString()
        val displayName = userResult["displayName"].toString()
        val username = userResult["username"].toString()
        val email = userResult["email"].toString()
        val photoUrl = userResult["photoUrl"].toString()
        val location = userResult["location"].toString()
        val userDBID = userResult["userDBID"].toString()
        val partnersUsername = userResult["partnersUsername"].toString()
        val partnerID = userResult["partnerID"].toString()

        val interests = (userResult["interests"] as ArrayList<String>).toList()
        val userDatesIDs = (userResult["userDatesIDs"] as ArrayList<String>).toList()
        val _user = User(
            uid,
            displayName,
            username,
            email,
            photoUrl,
            location,
            interests,
            userDBID,
            userDatesIDs,
            partnersUsername,
            partnerID
        )
        return _user
    }


    fun dbFetchUser(user: MutableLiveData<User>) {

        db.collection("globalUsers").document(user.value!!.uid)
            .get()
            .addOnSuccessListener { result ->

                if (result.data == null) {
                    println("NO User")
                    addUserToFirebase(user)
                    newUser.postValue(true)
                    return@addOnSuccessListener
                }

                val userResult = result.data as HashMap<*, *>
                val _user = userFromQuery(userResult)
                user.postValue(_user)

            }
            .addOnFailureListener {
                println("getUser fetch FAILED ")
            }
    }



    fun createDate(
        dateItem: DateItem,
        dateList: MutableLiveData<List<DateItem>>
    ) {
        //SSS
        dateItem.dateID = db.collection("globalDates").document().id

        db.collection("globalDates")
            .document(dateItem.dateID)
            .set(dateItem)
            .addOnSuccessListener {
                Log.d(
                    javaClass.simpleName,
                    "Note create \"${elipsizeString(dateItem.title)}\" id: ${dateItem.dateID}"
                )
                dbFetchDates(dateList)
            }
            .addOnFailureListener { e ->
                Log.d(
                    javaClass.simpleName,
                    "Note create FAILED \"${elipsizeString(dateItem.title)}\""
                )
                Log.w(javaClass.simpleName, "Error ", e)
            }
    }



    fun addUserToFirebase(user: MutableLiveData<User>) {
        user.value!!.userDBID = db.collection("globalUsers").document().id

        db.collection("globalUsers")
            .document(user.value!!.uid)
            .set(user.value!!)
            .addOnSuccessListener {
                dbFetchUser(user)
            }
            .addOnFailureListener { e ->
                Log.w(javaClass.simpleName, "Error Creating User", e)
            }
    }

    fun updateUserInFirebase(user: MutableLiveData<User>) {

        db.collection("globalUsers")
            .document(user.value!!.uid)
            .set(user.value!!)
            .addOnSuccessListener {
                dbFetchUser(user)
            }
            .addOnFailureListener { e ->
                println("Error $e")
            }
    }


    fun updatePartnerInFirebase(user: User) {

        db.collection("globalUsers")
            .document(user.uid)
            .set(user)
    }


    fun addUserPartnerInFirebase(user: MutableLiveData<User>, partnersUsername: String) {


        db
            .collection("globalUsers")
            .limit(10)
            .get()
            .addOnSuccessListener { result ->
                result.documents.forEach { curDoc ->

                    val userResult = curDoc.data as HashMap<*, *>
                    val _pname = userResult["username"]
                    val hasPartner = userResult["partnersUsername"] != ""
                    if (_pname == partnersUsername && !hasPartner) {
                        user.value?.partnerID = userResult["uid"] as String
                        user.value?.partnersUsername = partnersUsername

                        val partner_user = userFromQuery(userResult)
                        partner_user.partnerID = user.value!!.uid
                        partner_user.partnersUsername = user.value!!.username

                        updateUserInFirebase(user)
                        updatePartnerInFirebase(partner_user)

                    }
                }
            }
    }


}