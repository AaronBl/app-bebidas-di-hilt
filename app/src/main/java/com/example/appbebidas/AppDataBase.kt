package com.example.appbebidas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appbebidas.data.model.DrinkEntity
import com.example.appbebidas.domain.TragoDao

@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun tragoDao(): TragoDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
// if INSTANCE == NULL create a new instance if not only return the instance elvisOperator
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext, AppDataBase::class.java, "tragos"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}