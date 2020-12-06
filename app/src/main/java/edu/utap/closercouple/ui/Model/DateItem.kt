package edu.utap.closercouple.ui.Model

import com.google.firebase.firestore.ServerTimestamp
import java.sql.RowId
import java.sql.Timestamp

data class DateItem(
    // Auth information
    var title: String = "",
    var description: String = "",
    var categories: List<String> = listOf(),
    var cost: Int = 0,
    var ratings:Int = 0,
    var thumbnail: String = "",
    @ServerTimestamp val timeStamp: com.google.firebase.Timestamp? = null,
    var dateID: String = ""
)