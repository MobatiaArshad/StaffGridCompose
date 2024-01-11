package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.BlueCheckBox
import com.mobileapp.staffgridcompose.ui.onboarding.DropdownMenuWithTextField
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData

@Composable
fun StepTwo(data: PassingData?, click: (PassingData) -> Unit) {

    val streetAddress = remember {
        mutableStateOf(data?.streetAddress ?: "")
    }
    val streetAddress2 = remember {
        mutableStateOf(data?.streetAddress2 ?: "")
    }
    val emergencyContactNumber = remember {
        mutableIntStateOf(data?.emergencyContactNumber ?: 0)
    }
    val appliedBefore = remember {
        mutableStateOf(false)
    }
    val eligibleOrNOt = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "2. Application Information",
                    modifier = Modifier.padding(top = 5.dp, bottom = 14.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                )
                RoundedOutlinedTextField(
                    value = streetAddress.value,
                    onValueChange = { value ->
                        streetAddress.value = value
                    }, hint = "Street Address"
                )
                RoundedOutlinedTextField(
                    value = streetAddress2.value,
                    onValueChange = { value ->
                        streetAddress2.value = value
                    }, hint = "Street Address2"
                )
                RoundedOutlinedTextField(
                    value = if (emergencyContactNumber.intValue != 0) emergencyContactNumber.intValue.toString() else "",
                    onValueChange = { value ->
                        emergencyContactNumber.intValue = value.toInt()
                    }, hint = "Emergency Contact Number"
                )
                DropdownMenuWithTextField("Relation to Applicant"){
                    println("Label= $it")
                }
                DropdownMenuWithTextField("What kind of phone do you have"){
                    println("Label= $it")
                }
                BlueCheckBox(isChecked = appliedBefore.value, label = "Have you applied with GoRN before?", onValueChange = {
                    appliedBefore.value = it
                })
                BlueCheckBox(isChecked = eligibleOrNOt.value, label = "Are you legally eligible for employment in the United States?", onValueChange = {
                    eligibleOrNOt.value = it
                })
            }
            Box(modifier = Modifier.padding(bottom = 52.dp)) {
                BlueButton(label = "Save & Next") {
                    click.invoke(
                        PassingData()
                    )
                }
            }


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StepTwoPreview() {
    StepTwo(PassingData()) {}
}


