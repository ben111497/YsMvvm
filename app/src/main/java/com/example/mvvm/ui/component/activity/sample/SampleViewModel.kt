package com.example.mvvm.ui.component.activity.sample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SampleViewModel(handle: SavedStateHandle) : ViewModel() {
    private val repository = SampleRepository()   //存放與 UI 較無關聯的資料

    init {
        handle.get<Int>("KEY")?.let {
            /**
             * 取值
             */
            repository.setSaveState(handle)
        } ?: repository.setSaveState(SavedStateHandle().also { it["KEY"] = 0 })
    }
}