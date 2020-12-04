package edu.utap.closercouple.ui

import androidx.lifecycle.MutableLiveData
import edu.utap.closercouple.ui.Model.DateItem

class ViewModelDBHelper(
    notesList: MutableLiveData<List<DateItem>>
) {
  //  private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        dbFetchNotes(notesList)
    }
    private fun elipsizeString(string: String) : String {
        if(string.length < 10)
            return string
        return string.substring(0..9) + "..."
    }

    /////////////////////////////////////////////////////////////
    // Interact with Firestore db
    // https://firebase.google.com/docs/firestore/query-data/get-data
    //
    // If we want to listen for real time updates use this
    // .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
    // But be careful about how listener updates live data
    // and noteListener?.remove() in onCleared
    private fun dbFetchNotes(notesList: MutableLiveData<List<DateItem>>) {
//        db.collection("allNotes")
//            .orderBy("timeStamp")//, Query.Direction.DESCENDING)
//            .limit(100)
//            .get()
//            .addOnSuccessListener { result ->
//                Log.d(javaClass.simpleName, "allNotes fetch ${result!!.documents.size}")
//                // NB: This is done on a background thread
//
////                notesList.postValue(result.documents.mapNotNull {
////                    it.toObject(DateItem::class.java)
////                })
//            }
//            .addOnFailureListener {
//                Log.d(javaClass.simpleName, "allNotes fetch FAILED ", it)
//            }
    }

    // After we successfully modify the db, we refetch the contents to update our
    // live data.  Hence the dbFetchNotes() calls below.
    fun updateNote(
        note: DateItem,
        notesList: MutableLiveData<List<DateItem>>
    ) {
        val pictureUUIDs = note.pictureUUIDs
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

    fun createNote(
        note: DateItem,
        notesList: MutableLiveData<List<DateItem>>
    ) {
        //SSS
//        note.noteID = db.collection("allNotes").document().id
        //EEE // XXX set note.noteID

//        db.collection("allNotes")
//            .document(note.noteID)
//            .set(note)
//            .addOnSuccessListener {
//                Log.d(
//                    javaClass.simpleName,
//                    "Note create \"${elipsizeString(note.text)}\" id: ${note.noteID}"
//                )
//                dbFetchNotes(notesList)
//            }
//            .addOnFailureListener { e ->
//                Log.d(javaClass.simpleName, "Note create FAILED \"${elipsizeString(note.text)}\"")
//                Log.w(javaClass.simpleName, "Error ", e)
//            }
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
}