package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    init {
        _shoeList.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        Timber.d(shoe.toString())
        _shoeList.value!!.add(shoe)
        Timber.d(_shoeList.value.toString())
        Timber.d(shoeList.value.toString())
    }


}