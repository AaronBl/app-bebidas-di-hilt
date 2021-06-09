package com.example.appbebidas.domain

import com.example.appbebidas.data.model.Drink
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {

     override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragosByName(tragoName)
    }

    override suspend fun getTragoFavoritos(): Resource<List<DrinkEntity>> {
                return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
            dataSource.insertTragoIntoRoom( trago )
    }

    override suspend fun deleteDrink(drink:DrinkEntity) {
        dataSource.deleteDrink(drink)
    }
}