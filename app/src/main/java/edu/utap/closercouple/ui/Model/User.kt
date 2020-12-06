package edu.utap.closercouple.ui.Model

data class User(
    var uid: String = "",
    var displayName: String = "",
    var username: String = "",
    var email: String = "",
    var photoUrl: String = "",
    var location: String = "",
    var interests: List<String> = listOf(),
    var userDBID:String="",
    var userDatesIDs: List<String> = listOf( "GVPpVJ363sJ9yJfzzvtz"),
    var partnersName: String = "",
    var partnerID: String = ""
)
