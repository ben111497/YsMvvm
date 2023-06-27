package com.example.mvvm.data.local

import android.content.Context
import com.example.mvvm.data.local.database.DataBase
import com.example.mvvm.data.local.database.YsNumber
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

@DelicateCoroutinesApi
open class LocalData constructor(private val context: Context) {
    private val dataBase: DataBase get() { return DataBase.instance(context) }

    fun getNumber(): Flow<YsNumber?> {
        return try {
            dataBase.getNumberDao().getByUserID("")
        } catch (e: Exception) {
            setNumber("", 0)
            throw IllegalArgumentException("UnFind")
        }
    }

    fun setNumber(userID: String = "", number: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                dataBase.getNumberDao().insert(YsNumber(userID, number))
            } catch (e: Exception) { }
        }
    }
}