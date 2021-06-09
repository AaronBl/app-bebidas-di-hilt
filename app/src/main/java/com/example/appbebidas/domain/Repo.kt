package com.example.appbebidas.domain

import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName:String) : Resource<List<Drink>>
    suspend fun getTragoFavoritos():Resource<List<DrinkEntity>>
    suspend fun insertTrago(trago:DrinkEntity)
    suspend fun deleteDrink(drink:DrinkEntity)
}