package com.example.mvvm.ui.component.activity.sample

import androidx.lifecycle.SavedStateHandle

class SampleRepository {
    private var saveState: SavedStateHandle? = null
    var name: String = ""

    interface MainListener {
        fun onNameChange(name: String)
    }

    fun setSaveState(value: SavedStateHandle) { saveState = value }
    fun getSaveState(): SavedStateHandle? = saveState
}