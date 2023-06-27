package com.example.mvvm.data.local.database

import android.content.Context
import androidx.room.*

const val Table_Number = "Number"
const val DB_Version = 1

@Database(entities = [YsNumber::class], version = DB_Version)
@TypeConverters(Converters::class)
abstract class DataBase: RoomDatabase() {
    companion object {
        private var instance: DataBase? = null

        fun instance(context: Context): DataBase {
            return instance ?:
                Room.databaseBuilder(context, DataBase::class.java, "Sample")
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
        }
    }

    abstract fun getNumberDao(): NumberDao
}

@Entity(tableName = Table_Number, primaryKeys = ["userID"])
data class YsNumber(var userID: String = "", var number: Int)