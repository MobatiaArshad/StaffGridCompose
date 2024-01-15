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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
import com.mobileapp.staffgridcompose.ui.onboarding.DropdownMenuWithTextField
import com.mobileapp.staffgridcompose.ui.onboarding.Loader
import com.mobileapp.staffgridcompose.ui.onboarding.OnboardingViewModel
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StepTwo(navController: NavHostController = rememberNavController(),
            viewModel: OnboardingViewModel = viewModel()
) {

    val backCallback = remember {
        object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                navController.navigate(Screen.StepOne.route)
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

    val streetAddress = remember {
        mutableStateOf("")
    }
    val streetAddress2 = remember {
        mutableStateOf( "")
    }
    val emergencyContactNumber = remember {
        mutableLongStateOf( 0)
    }
    val appliedBefore = remember {
        mutableStateOf(false)
    }
    val eligibleOrNOt = remember {
        mutableStateOf(false)
    }
    var isLoading by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = viewModel.passedData) {
        viewModel.passedData.collect { newData ->
            streetAddress.value= newData.streetAddress ?: ""
            streetAddress2.value= newData.streetAddress2 ?: ""
            appliedBefore.value= newData.appliedBefore
            eligibleOrNOt.value= newData.eligibleOrNOt
            emergencyContactNumber.longValue= newData.emergencyContactNumber
        }
    }
    LaunchedEffect(key1 = viewModel.isDataLoaded){
        viewModel.isDataLoaded.collectLatest {
            if (it) navController.navigate(Screen.Home.route)
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
                    navController.navigate(Screen.StepOne.route)
                }
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
                    value = if (emergencyContactNumber.longValue.toInt() != 0) emergencyContactNumber.longValue.toString() else "",
                    onValueChange = { value ->
                        emergencyContactNumber.longValue = value.toLong()
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
fun StepTwoPreview() {
    StepTwo()
}


