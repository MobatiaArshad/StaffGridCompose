package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.BackBtn
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.BlueCheckBox
import com.mobileapp.staffgridcompose.ui.onboarding.Loader
import com.mobileapp.staffgridcompose.ui.onboarding.OnboardingViewModel
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData

@Composable
fun StepOne(
    navController: NavHostController = rememberNavController(),
    viewModel: OnboardingViewModel = viewModel(),
) {

    val backCallback = remember {
        object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                navController.navigate(Screen.Home.route)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            backCallback.remove() // Remove callback when composable is disposed
        }
    }
    BackHandler(enabled = true) {
        backCallback.handleOnBackPressed()
        true // consume the event
    }


    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val city = remember {
        mutableStateOf("")
    }
    val state = remember {
        mutableStateOf("")
    }
    val postalCode = remember {
        mutableIntStateOf(0)
    }
    val cellNumber = remember {
        mutableLongStateOf(0)
    }
    val email = remember {
        mutableStateOf("")
    }
    val isChecked = remember {
        mutableStateOf(false)
    }
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(viewModel.passedData) {
        viewModel.passedData.collect { newData ->
            firstName.value = newData.firstName ?: ""
            lastName.value = newData.lastName ?: ""
            city.value = newData.city ?: ""
            state.value = newData.state ?: ""
            email.value = newData.email ?: ""
            postalCode.intValue = newData.postalCode
            cellNumber.longValue = newData.cellNo
        }
    }

    LaunchedEffect(viewModel.isDataLoaded){
        viewModel.isDataLoaded.collect {
            if (it) navController.navigate(Screen.StepTwo.route)
        }
    }
    LaunchedEffect(viewModel.isLoading){
        viewModel.isLoading.collect {
            println("DATA CALLED = \t $it")
           isLoading= it
        }
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
                BackBtn {
                    navController.navigate(Screen.Home.route)
                }
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
                    value = if (cellNumber.longValue.toInt() != 0) cellNumber.longValue.toString() else "",
                    onValueChange = { newString ->
                        cellNumber.longValue = newString.toLong()
                    }, hint = "Cell Phone Number",
                    keyboardType = KeyboardType.Number
                )
                RoundedOutlinedTextField(
                    value = email.value,
                    onValueChange = { newString ->
                        email.value = newString
                    }, hint = "Email"
                )
                BlueCheckBox(
                    isChecked = isChecked.value,
                    label = "Have you ever had a license suspended, revoked, or under investigation?",
                    onValueChange = {
                        isChecked.value = it
                    })
                Loader(isLoading = isLoading)
            }
            Box(modifier = Modifier.padding(bottom = 52.dp)) {
                BlueButton(label = "Save & Next") {
                    viewModel.uploadDataToApi(
                        PassingData(
                        firstName = "firstName",
                        lastName = "lastName",
                        city = "city",
                        state = "state",
                        postalCode = 68555,
                        cellNo = 90486756546,
                        email = "email",
                        streetAddress = "Street Address 1",
                        streetAddress2 = "Street Address 2",
                        emergencyContactNumber = 5645764435)
                    )
                }
            }


        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StepPreview() {
    StepOne() // Provide a dummy implementation for onClick
}

