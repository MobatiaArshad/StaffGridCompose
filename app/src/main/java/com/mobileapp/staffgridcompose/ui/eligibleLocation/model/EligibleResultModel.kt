package com.mobileapp.staffgridcompose.ui.eligibleLocation.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class EligibleResultModel(
    val title: String = "",
    val data: List<EligibleResultData> = listOf(),
    val isExpanded: MutableState<Boolean> = mutableStateOf(true)
)

data class EligibleResultData(
    val name: String
)