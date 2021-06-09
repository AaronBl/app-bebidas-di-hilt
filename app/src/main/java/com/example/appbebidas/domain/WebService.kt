package com.example.appbebidas.domain


import com.example.appbebidas.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getTragoByName(@Query("s") tragoName:String) : DrinkList
}