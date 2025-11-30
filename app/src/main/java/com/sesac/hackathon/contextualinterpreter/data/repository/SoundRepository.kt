package com.sesac.hackathon.contextualinterpreter.data.repository

import com.sesac.hackathon.contextualinterpreter.data.model.DangerLevel
import com.sesac.hackathon.contextualinterpreter.data.model.SoundEvent
import com.sesac.hackathon.contextualinterpreter.data.model.SoundType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class SoundRepository {
    fun getSoundEvents(): Flow<SoundEvent> = flow {
        var idCounter = 0L
        while (true) {
            delay(Random.nextLong(2000, 5000)) // Emit event every 2-5 seconds
            
            val type = SoundType.values().random()
            val dangerLevel = when (type) {
                SoundType.SIREN, SoundType.CAR_HORN -> DangerLevel.HIGH
                SoundType.WATER_BOILING -> DangerLevel.MEDIUM
                else -> DangerLevel.LOW
            }
            
            val event = SoundEvent(
                id = idCounter++,
                type = type,
                directionAngle = Random.nextFloat() * 360f,
                distance = Random.nextFloat() * 10f,
                dangerLevel = dangerLevel,
                timestamp = System.currentTimeMillis(),
                label = type.name.replace("_", " ")
            )
            emit(event)
        }
    }
}
