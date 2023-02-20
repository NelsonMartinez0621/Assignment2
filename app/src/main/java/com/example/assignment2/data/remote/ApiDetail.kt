package com.example.assignment2.data.remote

import com.example.assignment2.data.model.Brewery
import com.example.assignment2.data.model.BreweryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDetail {
    @GET(ApiReference.BASE_URL)
    suspend fun getBreweries(): Response<Brewery>

    @GET(ApiReference.SEARCH_END_POINT)
    suspend fun getSearchedBrewery(@Path("search") query: String?): Response<BreweryItem>
}