package com.example.mvvm.ui.component.fragment.sample


import androidx.lifecycle.ViewModel
import com.example.mvvm.data.dto.SingleLiveData
import com.example.mvvm.data.local.LocalData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class SampleViewModel constructor(private val local: LocalData): ViewModel() {
    val number: SingleLiveData<Int> = SingleLiveData<Int>().apply { value = 0 }

    fun addNumber(userID: String, value: Int) {
        number.value = number.value?.plus(value) ?: 0 + value
        setLocalNumber(userID, number.value!!)
    }

    /**
     * Local & Api
     */
    private fun setLocalNumber(userID: String, number: Int) = local.setNumber(userID, number)
    fun getLocalNumber() {
        GlobalScope.launch(Dispatchers.Main) {
            local.getNumber().flowOn(Dispatchers.IO).collect { number.value = it?.number ?: 0 }
        }
    }
}