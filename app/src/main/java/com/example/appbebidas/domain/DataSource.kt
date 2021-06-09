package com.example.appbebidas.domain

import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.vo.Resource

interface  DataSource {
    suspend fun getTragosByName(tragosName: String): Resource<List<Drink>>
    suspend fun insertTragoIntoRoom(drinkEntity: DrinkEntity)
    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>>
    suspend fun deleteDrink(drink: DrinkEntity)
}