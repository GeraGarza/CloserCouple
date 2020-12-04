package edu.utap.closercouple.ui.main.dates

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utap.closercouple.Data
import edu.utap.closercouple.Repository
import edu.utap.closercouple.ui.main.dates.Repos.InterestsList
import kotlin.random.Random

class UserViewModel : ViewModel() {
    private var repository = Repository()
    private var list = MutableLiveData<List<Data>>().apply {
        value = repository.fetchData()
    }
    private var interestsList = MutableLiveData<List<InterestsList.InterestItem>>().apply {
        value = InterestsList.getAll()
    }

    internal fun getList(): LiveData<List<Data>> {
        return list
    }

    private var user = MutableLiveData<UserInfo>().apply {
        value = UserInfo("", "")
    }

    private var completedProfile = MutableLiveData<Boolean>().apply {
        value = false
    }

    private var completedInterests = MutableLiveData<Boolean>().apply {
        value = false
    }


    data class UserInfo(
        val name: String = "",
        val number: String = "",
        val location: String = "",
        val email: String = ""
    )

    fun updateUserInfo(userValues: UserInfo) {
        user.value = userValues
        completedProfile.value = true
    }

    fun updateInterestStatus() {
        completedInterests.value = true
    }


    fun observeUserInfo(): MutableLiveData<UserInfo> {
        return user
    }

    fun observeProfileStatus(): MutableLiveData<Boolean> {
        return completedProfile
    }

    fun observeInterestsStatus(): MutableLiveData<Boolean> {
        return completedInterests
    }

    fun getUserInfo(): UserInfo {
        return user.value!!
    }


    fun getInterestListAt(position: Int): InterestsList.InterestItem {
        val localList = interestsList.value!!.toList()
        return localList[position]
    }

    fun toggleInterestItemAt(position: Int) {
        val localList = interestsList.value!!.toList()
        localList[position].selected = !localList[position].selected
        interestsList.value = localList
    }


    internal fun observeInterestsList(): LiveData<List<InterestsList.InterestItem>> {
        return interestsList
    }

    fun addInterest(interest: InterestsList.InterestItem) {
        val localList = interestsList.value?.toMutableList()
        localList?.let {
            it.add(interest)
            interestsList.value = it
        }
    }

    fun removeInterest(interest: InterestsList.InterestItem) {
        val localList = interestsList.value?.toMutableList()
        localList?.let {
            it.remove(interest)
            interestsList.value = it
        }
    }


    fun isInterest(albumRec: Data): Boolean {
        return interestsList.value?.contains(albumRec) ?: false
    }

    fun removeFav(albumRec: Data) {
        val localList = interestsList.value?.toMutableList()
        localList?.let {
            it.remove(albumRec)
            interestsList.value = it
        }
    }


    fun replaceList(newList: List<Data>) {
        list.value = newList
    }

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


}