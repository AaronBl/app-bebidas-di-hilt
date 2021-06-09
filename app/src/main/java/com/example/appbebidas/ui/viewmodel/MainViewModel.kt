package com.example.appbebidas.ui.viewmodel

import androidx.lifecycle.*
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.domain.Repo
import com.example.appbebidas.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: Repo) : ViewModel() {
    private val tragosdata = MutableLiveData<String>()

    fun setTrago(tragosName: String) {
        tragosdata.value = tragosName
    }

    init {
        setTrago("margarita")
    }


    val fetchTragosList = tragosdata.switchMap { nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            try {
                emit(repo.getTragosList(nombreTrago))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun guardarTrago(trago: DrinkEntity) {
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }

    fun getTragosFavoritos() = liveData(Dispatchers.IO) {

        emit(Resource.loading())
        try {
            emit(repo.getTragoFavoritos())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

    fun deleteDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }
}