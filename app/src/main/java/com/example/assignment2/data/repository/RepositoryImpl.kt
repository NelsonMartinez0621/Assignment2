package com.example.assignment2.data.repository

import com.example.assignment2.data.model.Brewery
import com.example.assignment2.data.model.BreweryItem
import com.example.assignment2.data.remote.ApiDetail
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiDetail: ApiDetail
    ) :Repository{


    override suspend fun getBreweries(): Response<Brewery> {
        return apiDetail.getBreweries()
    }

    override suspend fun getSearchedBrewery(query: String?): Response<BreweryItem> {
        return apiDetail.getSearchedBrewery(query)
    }
}