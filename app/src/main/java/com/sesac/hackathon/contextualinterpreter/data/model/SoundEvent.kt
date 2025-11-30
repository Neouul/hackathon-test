package com.sesac.hackathon.contextualinterpreter.data.model

enum class SoundType {
    SIREN, CAR_HORN, DOORBELL, DOG_BARK, WATER_BOILING, UNKNOWN
}

enum class DangerLevel {
    HIGH, MEDIUM, LOW, NONE
}

data class SoundEvent(
    val id: Long,
    val type: SoundType,
    val directionAngle: Float, // 0.0 to 360.0 degrees
    val distance: Float, // in meters
    val dangerLevel: DangerLevel,
    val timestamp: Long,
    val label: String
)
