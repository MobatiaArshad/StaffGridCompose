package com.mobileapp.staffgridcompose.ui.onboarding.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class StepThreeCells(
    var singleCell: SingleCell= SingleCell(),
    var boxWithMultipleDropDown: BoxWithMultipleDropDown= BoxWithMultipleDropDown()
)
data class SingleCell(
    var isThereEdt: Boolean = false,
    var isThereEdtWithDatePicker: Boolean= false,
    var numberOfCells:Int = 0

)
data class BoxWithMultipleDropDown(
    var containsOnlyDropDownOption:Boolean= false,
    var dropdownWithDatePicker:Boolean= false,
    var uploadOption:Boolean= false,
    var numberOfDpDwn:Int = 0,
    var numberOfDatePicker:Int = 0
)

data class StepThreeCell(
    var id: Int=0,
    var title: String= "Add Certificate",
    val isExpanded: MutableState<Boolean> = mutableStateOf(true),
)