package edu.utap.closercouple.ui.main.dates

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
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
    private var MemoryDatesList = MutableLiveData<List<DateItem>>()
    private var totalInterestsSelected = MutableLiveData<Int>()

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



    fun updateUserInfo(userName: String, userNum: String, usrLoc: String, usrEm: String) {
        user.value?.displayName = userName
        user.value?.location = usrLoc
        completedProfile.value = true
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
        Log.d(javaClass.simpleName, "notesList.value ${ExploreDatesList.value}")
        Log.d(javaClass.simpleName, "getNode $position list len ${ExploreDatesList.value?.size}")
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
        Log.d(javaClass.simpleName, "notesList.value ${ExploreDatesList.value}")
        Log.d(javaClass.simpleName, "getNode $position list len ${ExploreDatesList.value?.size}")
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


    fun createExploreDate() {
        val date = DateItem(
            title = "Applebees",
            description = "description",
            categories = listOf("food", "drinks"),
            cost = 1,
            ratings = 1,
            thumbnail = "thumbnail",
        )
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


    /////////////////////////////////////////////////////////////
    fun firestoreInit(_auth: FirebaseAuth) {
        auth = _auth
        val curUser = auth.currentUser!!
        val uid = curUser.uid
        val displayName =  curUser.displayName.toString()
        val username = ""
        val email = curUser.email.toString()
        val photoUrl = curUser.photoUrl.toString()
        val location = ""
        val interests = listOf<String>()
        val userDBID = ""
        val userDatesIDs = listOf<String>()
        val partnersName = ""
        val partnersID = ""
        user.value = User(uid,displayName,username,email,photoUrl,location,interests,userDBID,userDatesIDs ,partnersName,partnersID)

        dbHelp.dbFetchUser(user)



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



}