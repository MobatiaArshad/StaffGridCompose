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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.BlueCheckBox
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData

@Composable
fun StepOne(data: PassingData?, click: (PassingData) -> Unit) {

    val firstName = remember {
        mutableStateOf(data?.firstName?: "")
    }
    val lastName = remember {
        mutableStateOf(data?.lastName?: "")
    }
    val city = remember {
        mutableStateOf(data?.city?: "")
    }
    val state = remember {
        mutableStateOf(data?.state?: "")
    }
    val postalCode = remember {
        mutableIntStateOf(data?.postalCode ?: 0)
    }
    val cellNumber = remember {
        mutableIntStateOf(data?.cellNo ?: 0)
    }
    val email = remember {
        mutableStateOf(data?.email?: "")
    }
    val isChecked = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
    ) {
        Column(
            modifier= Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "1. Contact Information",
                    modifier = Modifier.padding(top = 5.dp, bottom = 14.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                )
                RoundedOutlinedTextField(
                    value = firstName.value,
                    onValueChange = { newString ->
                        firstName.value = newString
                    }, hint = "First Name"
                )
                RoundedOutlinedTextField(
                    value = lastName.value,
                    onValueChange = { newString ->
                        lastName.value = newString
                    }, hint = "Last Name"
                )
                RoundedOutlinedTextField(
                    value = city.value,
                    onValueChange = { newString ->
                        city.value = newString
                    }, hint = "City"
                )
                RoundedOutlinedTextField(
                    value = state.value,
                    onValueChange = { newString ->
                        state.value = newString
                    }, hint = "State"
                )
                RoundedOutlinedTextField(
                    value = if (postalCode.intValue != 0) postalCode.intValue.toString() else "",
                    onValueChange = { newString ->
                        postalCode.intValue = newString.toInt()
                    }, hint = "Postal Code",
                    keyboardType = KeyboardType.Number
                )
                RoundedOutlinedTextField(
                    value = if (cellNumber.intValue != 0) cellNumber.intValue.toString() else "",
                    onValueChange = { newString ->
                        cellNumber.intValue = newString.toInt()
                    }, hint = "Cell Phone Number",
                    keyboardType = KeyboardType.Number
                )
                RoundedOutlinedTextField(
                    value = email.value,
                    onValueChange = { newString ->
                        email.value = newString
                    }, hint = "Email"
                )
                BlueCheckBox(isChecked = isChecked.value,label="Have you ever had a license suspended, revoked, or under investigation?",onValueChange = {
                    isChecked.value = it
                })
            }
            Box(modifier = Modifier.padding(bottom= 52.dp)){
                BlueButton(label = "Save & Next") {
                    click.invoke(
                            PassingData(
                                firstName = firstName.value,
                                lastName = lastName.value,
                                city = city.value,
                                state = state.value,
                                postalCode = postalCode.intValue,
                                cellNo = cellNumber.intValue,
                                email = email.value,
                            )
                        )
                }
            }


        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StepOnePreview() {
    StepOne(PassingData()) {} // Provide a dummy implementation for onClick
}
