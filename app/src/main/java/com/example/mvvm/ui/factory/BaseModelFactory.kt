package com.example.mvvm.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.local.LocalData
import com.example.mvvm.ui.component.fragment.sample.SampleViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class BaseModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SampleViewModel::class.java) -> SampleViewModel(LocalData(context)) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}