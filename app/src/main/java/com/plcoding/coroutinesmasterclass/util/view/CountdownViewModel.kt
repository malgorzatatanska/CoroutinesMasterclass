package com.plcoding.coroutinesmasterclass.util.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CountdownViewModel : ViewModel() {

    private val _count = MutableStateFlow(0);
    val count = _count.asStateFlow();
    private var countingJob: Job? = null

    var input by mutableStateOf("")
        private set


    fun setCounter(value: Int) {
        input = if (value <= 0) "" else value.toString()
    }

    fun start() {
        countingJob?.cancel()

        val startValue = input.toIntOrNull() ?: 0 // ← tutaj konwersja
        _count.value = startValue
        println("wartosc inputa: ${input}")


        countingJob = viewModelScope.launch {
            countDownFlow().collect { value ->
                _count.value = value
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            countingJob?.cancel()
            countingJob = null
            _count.value = 0
            input = ""

        }

    }


    fun countDownFlow(): Flow<Int> = flow {
        for (i in input.toInt() downTo 0) {
            emit(i)
            delay(1000)
        }

    }


}