package com.mobileapp.staffgridcompose.ui.eligibleLocation.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class EligibleLocModel(
    val name: String = "",
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)
