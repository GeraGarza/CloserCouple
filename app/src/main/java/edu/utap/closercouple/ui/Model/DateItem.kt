package edu.utap.closercouple.ui.Model

data class DateItem(
    // Auth information
    var name: String = "",
    var ownerUid: String = "",
    // Text
    var text: String = "",
    var pictureUUIDs: List<String> = listOf(),
    // Written on the server
    //@ServerTimestamp val timeStamp: Timestamp? = null,
    // NoteID is generated by firestore, used as primary key
    var noteID: String = ""
)