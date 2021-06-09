package com.example.appbebidas.domain

import androidx.room.*
import com.example.appbebidas.data.model.DrinkEntity

@Dao
interface TragoDao {

    @Query("Select * From tragos")
    suspend fun getAllFavoriteDrinks(): List<DrinkEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink:DrinkEntity)
    @Delete
    suspend fun deleteDrink(drink:DrinkEntity)
}