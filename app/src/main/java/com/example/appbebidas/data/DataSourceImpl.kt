package com.example.appbebidas.data

import com.example.appbebidas.AppDataBase
import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.domain.DataSource
import com.example.appbebidas.vo.Resource
import com.example.appbebidas.vo.RetrofitClient

class DataSourceImpl(private val appDataBase: AppDataBase) : DataSource {

    override suspend fun getTragosByName(tragosName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getTragoByName(tragosName).drinkList)
    }

    override suspend fun insertTragoIntoRoom(drinkEntity: DrinkEntity) {
        appDataBase.tragoDao().insertFavorite(drinkEntity)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDataBase.tragoDao().getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        appDataBase.tragoDao().deleteDrink(drink)
    }


    val generateTragosList = Resource.Success(
        listOf<Drink>(
            Drink(
                "https://cocina-casera.com/wp-content/uploads/2017/11/C%C3%B3ctel-Margarita.jpg",
                "Margarita",
                "con azucar, vodka y nueces"
            ),
            Drink(
                "https://franchoxbar.files.wordpress.com/2018/07/img_20180630_181858_826.jpg",
                "Fernet",
                "con coca y dos hielos"
            ),
            Drink(
                "https://cocina-casera.com/wp-content/uploads/2017/11/C%C3%B3ctel-Margarita.jpg",
                "Toro",
                "Toro con pritty"
            ),
            Drink(
                "https://franchoxbar.files.wordpress.com/2018/07/img_20180630_181858_826.jpg",
                "Gancia",
                "gancia con Sprite"
            )

        )
    )
    /*fun getTragoList(): Resource<List<Drink>>{

            return Resource.Success(generateTragosList)
    }*/

}