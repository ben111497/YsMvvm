package com.example.mvvm.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.local.LocalData
import com.example.mvvm.ui.component.fragment.homepage.HomepageViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class BaseModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomepageViewModel::class.java) -> HomepageViewModel(LocalData(context)) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}