package com.catnip.incrementdecrementmvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

interface CounterdataSource {
    fun getCounterFlow(): Flow<Int>
    fun getPriceFlow(): Flow<Int>
    suspend fun increment()
    suspend fun decrement()
}

class CounterDataSourceImpl() : CounterdataSource {
    private val priceFlow = MutableStateFlow(0)
    private val counterFlow = MutableStateFlow(0)

    override fun getPriceFlow(): Flow<Int> = priceFlow
    override fun getCounterFlow(): Flow<Int> = counterFlow
    override suspend fun increment() {
        val currentValue = counterFlow.first()
        val value = currentValue + 1
        val price = 18000 * value
        counterFlow.emit(value)
        priceFlow.emit(price)
    }

    override suspend fun decrement() {
        val currentValue = counterFlow.first()
        if (currentValue <= 0) return
        val value = currentValue - 1
        val price = 18000 * value
        counterFlow.emit(value)
        priceFlow.emit(price)
    }

}