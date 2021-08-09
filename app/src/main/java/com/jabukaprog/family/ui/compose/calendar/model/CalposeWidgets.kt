package com.jabukaprog.family.ui.compose.calendar.model

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import java.time.DayOfWeek
import java.time.YearMonth

data class CalposeWidgets(
    val header: @Composable (month: YearMonth, todayMonth: YearMonth, actions: CalposeActions) -> Unit,
    val headerDayRow: @Composable (headerDayList: Set<DayOfWeek>) -> Unit,
    val day: @Composable RowScope.(dayDate: CalposeDate, todayDate: CalposeDate) -> Unit,
    val priorMonthDay: @Composable RowScope.(dayDate: CalposeDate) -> Unit,
    val nextMonthDay: @Composable RowScope.(dayDate: CalposeDate) -> Unit = priorMonthDay,
    val headerContainer: @Composable ((@Composable () -> Unit) -> Unit)? = null,
    val monthContainer: @Composable ((@Composable () -> Unit) -> Unit)? = null,
)