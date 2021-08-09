package com.jabukaprog.family.ui.compose.calendar.model


data class CalposeActions (
    val onClickedPreviousMonth: () -> Unit,
    val onClickedNextMonth: () -> Unit,
    val onSwipedPreviousMonth: () -> Unit = onClickedPreviousMonth,
    val onSwipedNextMonth: () -> Unit = onClickedNextMonth
)