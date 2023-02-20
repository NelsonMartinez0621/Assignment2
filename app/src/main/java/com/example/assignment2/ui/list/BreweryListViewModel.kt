package com.example.assignment2.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2.data.model.Brewery
import com.example.assignment2.data.repository.Repository
import com.example.assignment2.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreweryListViewModel @Inject constructor(
     private val repository: Repository
    ) : ViewModel() {

    private val _breweryList = MutableLiveData<NetworkResult<Brewery>>()

    val breweryList: LiveData<NetworkResult<Brewery>> = _breweryList

    fun getBreweryList() {
        viewModelScope.launch {
            val result = repository.getBreweries()

            if (result.isSuccessful) {
                _breweryList.value = NetworkResult.Success(result.body()!!)
            } else {
                _breweryList.value = NetworkResult.Error(result.message())
            }

        }
    }
}