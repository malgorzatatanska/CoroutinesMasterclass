package com.plcoding.coroutinesmasterclass.sections.flows_in_practise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.runningReduce
import kotlinx.coroutines.flow.stateIn

class TimerViewModel : ViewModel() {

    // 10 emisji  na sekunde
    val formattedTime = timeAndEmit(100f)
        .runningReduce { totalElapsedTime, newElapsedTime ->
            totalElapsedTime + newElapsedTime
        }
        .map { totalElapsedTime ->
            totalElapsedTime.toComponents { hours, minutes, seconds, nanoseconds ->
                String.format(
                    "%02d:%02d:%02d:%02d",
                    hours,
                    minutes,
                    seconds,
                    nanoseconds / (1000000L * 10L)
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            "00:00:00:00"
        )

    val totalProgressMiliseconds = 5000L
    val progress = timeAndEmit(100f)
        .runningReduce { totalTime, newElapsedTime ->
            totalTime + newElapsedTime
        }
        .map { totalDuration ->
            (totalDuration.inWholeMilliseconds / totalProgressMiliseconds.toFloat())
                .coerceIn(0f, 1f)
        }
        // the filter does not have to be here, the coerceIn already limits
        // values
        .filter { progressFraction ->
            progressFraction in (0f..1f)

        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0f)

}