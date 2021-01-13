package com.udacity.shoestore.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.mapper.ShoeDataMapper
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeDataView

class MainViewModel : ViewModel() {

    var shoeDataView = ShoeDataView()
    private val _shoeListViewData = MutableLiveData<MutableList<Shoe>>()
    val shoeListViewData: LiveData<List<ShoeDataView>>
        get() = Transformations.map(_shoeListViewData) {
            it.map { shoe -> ShoeDataMapper.mapToViewData(shoe) }
        }

    private val _eventNavigate = MutableLiveData<Boolean>()
    val eventNavigate: LiveData<Boolean>
        get() = _eventNavigate

    init {
        _shoeListViewData.value = mutableListOf()
        _eventNavigate.value = false
    }

    fun onSave() {
        _shoeListViewData.value?.add(
            ShoeDataMapper.mapFromViewData(shoeDataView)
        )
        shoeDataView = ShoeDataView()
        _eventNavigate.value = true
    }

    fun onCancel() {
        _eventNavigate.value = true
    }

    fun onNavigateComplete() {
        _eventNavigate.value = false
    }
}
