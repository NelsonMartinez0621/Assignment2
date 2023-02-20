package com.example.assignment2.data.repository

import com.example.assignment2.data.model.Brewery
import com.example.assignment2.data.model.BreweryItem
import retrofit2.Response

interface Repository {

    suspend fun getBreweries(): Response<Brewery>

    suspend fun getSearchedBrewery(query: String?): Response<BreweryItem>
}