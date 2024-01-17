package com.mobileapp.staffgridcompose.ui.onboarding.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class OnboardCells (
    val title: String = "",
    val data: List<DropDownData> = listOf(),
    val isExpanded: MutableState<Boolean> = mutableStateOf(true),
    val type: MutableState<Int> = mutableIntStateOf(1)
)
data class DropDownData(
    val name: String= "POSITION, LICENSE AND CERTIFICATION\n" + "INFORMATION"
)