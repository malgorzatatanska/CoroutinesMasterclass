package com.plcoding.coroutinesmasterclass.sections.flows_in_practise

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.roundToLong
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun timeAndEmit(emissionsPerSecond: Float): Flow<Duration> {
    return flow {
        var lastEmitTime = System.currentTimeMillis()
        // minelo 0 sekund od ostatniego pomiaru
        emit(Duration.ZERO)

        while (true) {
            delay((1000L / emissionsPerSecond).roundToLong())

            val currentTome = System.currentTimeMillis()
            val elapsedTime = currentTome - lastEmitTime

            emit(elapsedTime.milliseconds)
            lastEmitTime = currentTome
        }
    }
}