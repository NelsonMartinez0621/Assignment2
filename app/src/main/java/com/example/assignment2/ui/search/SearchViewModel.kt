package com.example.assignment2.ui.search

import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2.data.model.Brewery
import com.example.assignment2.data.model.BreweryItem
import com.example.assignment2.data.repository.Repository
import com.example.assignment2.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
     val repository: Repository
) : ViewModel() {

    private val _breweryItem = MutableLiveData<NetworkResult<BreweryItem>>()

    val breweryItem: LiveData<NetworkResult<BreweryItem>> = _breweryItem


    fun getSearchedBrewery(query: String?) {
        viewModelScope.launch {
            val result = repository.getSearchedBrewery(query)
            if (result.isSuccessful) {
                _breweryItem.value = NetworkResult.Success(result.body()!!)
            } else {
                _breweryItem.value = NetworkResult.Error(result.message())
            }
        }
    }

    fun setupSearchView(searchView: SearchView){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchedBrewery(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}