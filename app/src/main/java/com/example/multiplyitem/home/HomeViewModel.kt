package com.example.multiplyitem.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Data>>()
    val list: LiveData<List<Data>>
        get() = _list

    private var lastSelectedItemPosition = -1

    init {
        _list.value = immutableList
    }

    fun onItemClick(position: Int) {
        val currentSelectedPosition =
            if (lastSelectedItemPosition < position)
                position - lastSelectedItemPosition - 1
            else
                position

        val newList: ArrayList<Data> = ArrayList<Data>().apply {
            addAll(list.value!!)
        }

        if (lastSelectedItemPosition == currentSelectedPosition) { //For removing generated items
            newList[currentSelectedPosition].isSelected = false
            lastSelectedItemPosition = -1


            val currentSelectedData = newList[currentSelectedPosition]
            val totalItemsToRemove = currentSelectedData.value

            for (i in 1..totalItemsToRemove) {
                newList.removeAt(currentSelectedPosition + 1)
            }

        } else {                                    // For adding new items
            if (lastSelectedItemPosition != -1) {
                newList[lastSelectedItemPosition].isSelected = false

                val totalItemsToRemove = newList[lastSelectedItemPosition].value
                for (i in 1..totalItemsToRemove){
                    newList.removeAt(lastSelectedItemPosition + 1)
                }
            }

            newList[currentSelectedPosition].isSelected = !newList[currentSelectedPosition].isSelected
            lastSelectedItemPosition = currentSelectedPosition

            val tempList = ArrayList<Data>()
            val currentSelectedData = newList[currentSelectedPosition]
            val totalNewItems = currentSelectedData.value

            for (i in 1..totalNewItems) {
                tempList.add(Data(currentSelectedData.value, false, false))
            }

            newList.addAll(currentSelectedPosition + 1, tempList)
        }

        _list.value = newList
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }
    }
}

data class Data(
    val value: Int,
    var isSelected: Boolean = false,
    var isSelectable: Boolean = true
)

val immutableList: List<Data> = ArrayList<Data>().apply {
    for (i in 1..5) {
        add(Data(i))
    }
}

