package com.jabukaprog.family.ui.compose.calendar.model

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween


data class CalposeProperties (
    val changeMonthAnimation: FiniteAnimationSpec<Float> = tween(durationMillis = 200),
    val changeMonthSwipeTriggerVelocity: Int = 300
)