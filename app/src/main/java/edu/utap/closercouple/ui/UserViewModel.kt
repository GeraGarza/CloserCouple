package edu.utap.closercouple.ui.main.dates

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utap.closercouple.Data
import edu.utap.closercouple.Repository
import kotlin.random.Random

class UserViewModel : ViewModel() {
    private val TAG: String = UserViewModel::class.java.simpleName
    private var repository = Repository()
    private var list = MutableLiveData<List<Data>>().apply {
        value = repository.fetchData()
    }
    internal var selected = -1
    private val random = Random(System.currentTimeMillis())
    private var favAlbums = MutableLiveData<List<Data>>().apply {
        value = mutableListOf()
    }
    internal fun getList(): LiveData<List<Data>> {
        return list
    }

    fun getListAt(position: Int) : Data? {
        val localList = list.value?.toList()
        localList?.let {
            if( position >= it.size ) return null
            return it[position]
        }
        return null
    }
    internal fun observeFav(): LiveData<List<Data>> {
        return favAlbums
    }
    fun addFav(albumRec: Data) {
        val localList = favAlbums.value?.toMutableList()
        localList?.let {
            it.add(albumRec)
            favAlbums.value = it
        }
    }
    fun isFav(albumRec: Data): Boolean {
        return favAlbums.value?.contains(albumRec) ?: false
    }
    fun removeFav(albumRec: Data) {
        val localList = favAlbums.value?.toMutableList()
        localList?.let {
            it.remove(albumRec)
            favAlbums.value = it
        }
    }

    // Add random object from repository.fetchData() to end of list
    fun addNetObject() {
        val allList = repository.fetchData()
        val localList = list.value?.toMutableList()
        localList?.let {
            it.add(allList[random.nextInt(0, allList.size)])
            list.value = it
        }
    }
    fun removeAt(position: Int) {
        val localList = list.value?.toMutableList()
        localList?.let {
            it.removeAt(position)
            list.value = it
        }
    }
    fun swapItem(from: Int, to: Int) {
        if( from == to ) return
        val localList = list.value?.toMutableList()
        localList?.let {
            val toItem = it[to]
            it[to] = it[from]
            it[from] = toItem
            list.value = it
        }
    }
    fun replaceList(newList: List<Data>) {
        list.value = newList
        selected = -1
    }
    fun clearList() {
        list.value = listOf()
        selected = -1
    }
    fun getItemCount() : Int {
        return list.value?.size ?: 0
    }

    override fun onCleared() {
        super.onCleared()
        // NB: A place to clean up e.g., network connections when the ViewModel is being destroyed
        Log.d(TAG, "on cleared called")
    }
}