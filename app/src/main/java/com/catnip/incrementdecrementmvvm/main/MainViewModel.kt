package com.catnip.incrementdecrementmvvm.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.catnip.incrementdecrementmvvm.data.CounterDataSourceImpl
import com.catnip.incrementdecrementmvvm.data.CounterdataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataSource: CounterdataSource
) : ViewModel() {
    val counter: LiveData<Int>
        get() = dataSource.getCounterFlow().asLiveData(Dispatchers.Main)

    val price: LiveData<Int>
        get() = dataSource.getPriceFlow().asLiveData(Dispatchers.Main)

    fun increment() {
        viewModelScope.launch {
            dataSource.increment()
        }
    }

    fun decrement() {
        viewModelScope.launch {
            dataSource.decrement()
        }
    }
}