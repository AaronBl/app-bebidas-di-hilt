package com.example.appbebidas.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val cocktailId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = ""
) :Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList:List<Drink>)

@Entity(tableName = "tragos")
data class DrinkEntity(
    @PrimaryKey
    val tragoId:String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name ="trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = ""
)
