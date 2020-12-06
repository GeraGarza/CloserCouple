package edu.utap.closercouple.ui.main.dates

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.utap.closercouple.Data
import edu.utap.closercouple.Repository
import edu.utap.closercouple.ui.Model.DateItem
import edu.utap.closercouple.ui.Model.InterestItem
import edu.utap.closercouple.ui.ViewModelDBHelper
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import edu.utap.closercouple.ui.Model.User


class UserViewModel : ViewModel() {
    private var repository = Repository()
    private var ExploreDatesList = MutableLiveData<List<DateItem>>()
    private var MemoryDatesList = MutableLiveData<List<DateItem>>().apply {
        value = listOf( DateItem( "Movie Night","Watch a new movie together ",
            listOf("Entertainment"), 1, 1,
            "https://thumbor.forbes.com/thumbor/fit-in/1200x0/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F1055293068%2F0x0.jpg%3FcropX1%3D0%26cropX2%3D6720%26cropY1%3D0%26cropY2%3D4480",
        ))
    }
    private var totalInterestsSelected = MutableLiveData<Int>()
    private var userAuth = MutableLiveData<FirebaseUser>()

    private val dbHelp = ViewModelDBHelper(ExploreDatesList, MemoryDatesList)
    private lateinit var auth: FirebaseAuth
    private var list = MutableLiveData<List<Data>>().apply {
        value = repository.fetchData()
    }
    private var interestsList = MutableLiveData<List<InterestItem>>().apply {
        value = InterestsList.getAll()
    }

    internal fun getList(): LiveData<List<Data>> {
        return list
    }

    private var user = MutableLiveData<User>().apply {
        value = User("", "", "", "", "", "", listOf(), "", listOf(), "", "")
    }

    private var completedProfile = MutableLiveData<Boolean>().apply {
        value = false
    }



    fun updateUserInfo(usr: User) {
        user.value = usr
        completedProfile.value = true
        println(user.value!!.displayName)
        dbHelp.updateUserInFirebase(user)
    }

    fun observeUserAuth(): LiveData<FirebaseUser>{
        return userAuth
    }


    fun observeExploreDates(): LiveData<List<DateItem>> {
        return ExploreDatesList
    }

    fun observeMemoryDates(): LiveData<List<DateItem>> {
        return MemoryDatesList
    }

    fun observeUserInfo(): MutableLiveData<User> {
        return user
    }

    fun observeProfileStatus(): MutableLiveData<Boolean> {
        return completedProfile
    }

    fun observeInterestsCount(): MutableLiveData<Int> {
        return totalInterestsSelected
    }

    fun getUserInfo(): User {
        return user.value!!
    }

    fun getUsername(): String {
        return user.value!!.username
    }


    fun getInterestListAt(position: Int): InterestItem {
        val localList = interestsList.value!!.toList()
        return localList[position]
    }

    fun toggleInterestItemAt(position: Int?) {
        if(position==null || position==-1){
            return
        }

        val localList = interestsList.value!!.toList()
        localList[position].selected = !localList[position].selected
        interestsList.value = localList
        if(localList[position].selected)
            addInterestToUserInFirebase(position)
        else
            removeInterestFromUserInFirebase(position)
    }





    /////////////////////////////////////////////////////////////
    // Notes, memory cache and database interaction

    fun isExploreDatesEmpty(): Boolean {
        return ExploreDatesList.value.isNullOrEmpty()
    }

    // Get a note from the memory cache
    fun getExploreDate(position: Int): DateItem {
        val date = ExploreDatesList.value?.get(position)
        return date!!
    }
    /////////////////////////////////////////////////////////////

    // Notes, memory cache and database interaction

    fun isMemoryDatesEmpty(): Boolean {
        return MemoryDatesList.value.isNullOrEmpty()
    }

    // Get a note from the memory cache
    fun getMemoryDate(position: Int): DateItem {
        val date = MemoryDatesList.value?.get(position)
        return date!!
    }
    /////////////////////////////////////////////////////////////

    fun getItemCount(): Int {
        return list.value?.size ?: 0
    }

    fun getListAt(position: Int): Data? {
        val localList = list.value?.toList()
        localList?.let {
            if (position >= it.size) return null
            return it[position]
        }
        return null
    }


    fun createExploreDate(dt : DateItem) {
        var date = DateItem(
            title = "Applebees",
            description = "description",
            categories = listOf("food", "drinks"),
            cost = 1,
            ratings = 1,
            thumbnail = "thumbnail",
        )
        if(dt.title!="")date = dt
        dbHelp.createDate(date, ExploreDatesList)
    }



    private fun addInterestToUserInFirebase(position: Int) {
        val interest = getInterestListAt(position).name
        val localList = user.value?.interests?.toMutableList()!!
        localList.add(interest)
        user.value!!.interests = localList
        dbHelp.updateUserInFirebase(user)
        totalInterestsSelected.postValue(localList.size)

    }

    private fun removeInterestFromUserInFirebase(position: Int) {
        val interest = getInterestListAt(position).name
        val localList = user.value?.interests?.toMutableList()!!
        localList.remove(interest)
        user.value!!.interests = localList
        dbHelp.updateUserInFirebase(user)
        totalInterestsSelected.postValue(localList.size)
    }


    fun addUserPartnerInFirebase(){
        dbHelp.addUserPartnerInFirebase(user,  "Joe")
    }


    /////////////////////////////////////////////////////////////
    fun firestoreInit(_auth: FirebaseAuth) {
        auth = _auth
        if(auth.currentUser != null)
            userAuth.postValue(auth.currentUser!!)

        //dbHelp.addUserPartnerToUserInFirebase(user)
    }



    fun setUpUser() {

        val localList = interestsList.value?.toMutableList()
        for (interest in user.value!!.interests) {
            val index = localList?.indexOfFirst {  it.name == interest }
            if(index==null || index==-1)
                return

            localList[index].selected = !localList[index].selected
            totalInterestsSelected.postValue( user.value!!.interests.size)
        }
    }

    fun logOutUser() {

        // clear/re-setup user on log out
        user.value = User("", "", "", "", "", "", listOf(), "", listOf(), "", "")
    }



}