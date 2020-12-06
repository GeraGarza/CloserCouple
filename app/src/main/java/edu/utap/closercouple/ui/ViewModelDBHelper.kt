package edu.utap.closercouple.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import edu.utap.closercouple.ui.Model.DateItem
import com.google.firebase.firestore.FirebaseFirestore
import edu.utap.closercouple.ui.Model.User
import edu.utap.closercouple.ui.main.dates.UserViewModel

class ViewModelDBHelper(
    ExploreDatesList: MutableLiveData<List<DateItem>>,
    MemoryDatesList: MutableLiveData<List<DateItem>>
) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        dbFetchDates(ExploreDatesList)
        dbFetchDates(MemoryDatesList)
    }
    private fun elipsizeString(string: String) : String {
        if(string.length < 10)
            return string
        return string.substring(0..9) + "..."
    }

    /////////////////////////////////////////////////////////////
    // Interact with Firestore db
    // https://firebase.google.com/docs/firestore/query-data/get-data
    private fun dbFetchDates(ExploreDatesList: MutableLiveData<List<DateItem>>) {

        if(FirebaseAuth.getInstance().currentUser == null) {
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
                println( "allDates fetch FAILED ")
            }
    }




    fun dbFetchUser(user: MutableLiveData<User>) {


        db.collection("globalUsers").document(user.value!!.uid)
            .get()
            .addOnSuccessListener { result ->

                if (result.data==null){
                    println("NO User")
                    addUserToFirebase(user)
                    return@addOnSuccessListener
                }

                val userResult = result.data?.values?.first() as HashMap<*, *>
                val uid = userResult["uid"].toString()
                val displayName = userResult["displayName"].toString()
                val username = userResult["username"].toString()
                val email = userResult["email"].toString()
                val photoUrl = userResult["photoUrl"].toString()
                val location = userResult["location"].toString()
                val userDBID = userResult["userDBID"].toString()
                val partnersName = userResult["partnersName"].toString()
                val partnerID = userResult["partnerID"].toString()

                val interests  = (userResult["interests"] as ArrayList<String>).toList()
                val userDatesIDs =(userResult["userDatesIDs"] as ArrayList<String>).toList()
                val _user = User(uid ,displayName ,username ,email ,photoUrl ,location ,interests ,userDBID ,userDatesIDs ,partnersName ,partnerID )
                user.postValue(_user)

            }
            .addOnFailureListener {
                println( "getUser fetch FAILED ")
            }
    }





    // After we successfully modify the db, we refetch the contents to update our
    // live data.  Hence the dbFetchNotes() calls below.
    fun updateNote(
        note: DateItem,
        notesList: MutableLiveData<List<DateItem>>
    ) {
//        val pictureUUIDs = note.pictureUUIDs
        //SSS
//        db.collection("allNotes")
//            .document(note.noteID)
//            .set(note)
//                //EEE // XXX Writing a note
//            .addOnSuccessListener {
//                Log.d(
//                    javaClass.simpleName,
//                    "Note update \"${elipsizeString(note.text)}\" len ${pictureUUIDs.size} id: ${note.noteID}"
//                )
//                dbFetchNotes(notesList)
//            }
//            .addOnFailureListener { e ->
//                Log.d(javaClass.simpleName, "Note update FAILED \"${elipsizeString(note.text)}\"")
//                Log.w(javaClass.simpleName, "Error ", e)
//            }
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
                Log.d(javaClass.simpleName, "Note create FAILED \"${elipsizeString(dateItem.title)}\"")
                Log.w(javaClass.simpleName, "Error ", e)
            }
    }

    fun removeNote(
        note: DateItem,
        notesList: MutableLiveData<List<DateItem>>
    ) {
//        db.collection("allNotes").document(note.noteID).delete()
//            .addOnSuccessListener {
//                Log.d(
//                    javaClass.simpleName,
//                    "Note delete \"${elipsizeString(note.text)}\" id: ${note.noteID}"
//                )
//                dbFetchNotes(notesList)
//            }
//            .addOnFailureListener { e ->
//                Log.d(javaClass.simpleName, "Note deleting FAILED \"${elipsizeString(note.text)}\"")
//                Log.w(javaClass.simpleName, "Error adding document", e)
//            }
    }

    fun userInDB(user: User){


    }


    fun addUserToFirebase(user: MutableLiveData<User>){
        user.value!!.userDBID = db.collection("globalUsers").document().id

        db.collection("globalUsers")
            .document(user.value!!.uid)
            .set(user)
            .addOnSuccessListener {
                dbFetchUser(user)
            }
            .addOnFailureListener { e ->
                Log.w(javaClass.simpleName, "Error Creating User", e)
            }
    }

    fun updateUserInFirebase( user: MutableLiveData<User>){

        db.collection("globalUsers")
            .document(user.value!!.uid)
            .set(user)
            .addOnSuccessListener {
                dbFetchUser(user)
            }
            .addOnFailureListener { e ->
                println("Error $e")
            }
    }



}