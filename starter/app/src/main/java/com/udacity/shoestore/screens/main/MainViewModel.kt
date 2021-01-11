package com.udacity.shoestore.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.mapper.ShoeDataMapper
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeDataView

class MainViewModel : ViewModel() {
    private val _shoeListViewData = MutableLiveData<List<Shoe>>()
    val shoeListViewData: LiveData<List<ShoeDataView>>
        get() = Transformations.map(_shoeListViewData) {
            it.map { shoe -> ShoeDataMapper.mapToViewData(shoe) }
        }

    init {
        _shoeListViewData.value = mutableListOf(
            Shoe("shoe 1", 12.0, "company 1", "desc 1", emptyList<String>()),
            Shoe("shoe 2", 5.0, "company 2", "desc 2", emptyList<String>()),
            Shoe("shoe 3", 11.0, "company 3", "desc 3", emptyList<String>())
        )
    }
}
