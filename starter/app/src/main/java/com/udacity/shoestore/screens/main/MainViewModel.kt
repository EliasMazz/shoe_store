package com.udacity.shoestore.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.mapper.ShoeDataMapper
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeDataView

class MainViewModel : ViewModel() {
    private val _shoeListViewData = MutableLiveData<MutableList<Shoe>>()
    val shoeListViewData: LiveData<List<ShoeDataView>>
        get() = Transformations.map(_shoeListViewData) {
            it.map { shoe -> ShoeDataMapper.mapToViewData(shoe) }
        }

    init {
        _shoeListViewData.value = mutableListOf()
    }

    fun addShoeToList(
        name: String,
        company: String,
        size: String,
        description: String
    ) = _shoeListViewData.value?.add(
        ShoeDataMapper.mapFromViewData(
            name = name,
            company = company,
            size = size,
            description = description
        )
    )
}
