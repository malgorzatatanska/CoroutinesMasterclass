package com.plcoding.coroutinesmasterclass.util.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CountdownViewModel : ViewModel() {

    private val _count = MutableStateFlow(10);
    val count = _count.asStateFlow();

    fun start() {
        viewModelScope.launch {
            countDownFlow().collect { value ->
                _count.value = value
            }
        }
    }


    fun countDownFlow(): Flow<Int> = flow {
        for (i in 10 downTo 0) {
            emit(i)
            delay(1000)
        }

    }


}