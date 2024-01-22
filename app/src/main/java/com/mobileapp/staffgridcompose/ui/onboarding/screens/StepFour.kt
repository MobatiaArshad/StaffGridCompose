package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.mobileapp.staffgridcompose.ui.onboarding.OnboardingViewModel

@Preview(showSystemUi = true)
@Composable
fun StepFour(
    navController: NavHostController = rememberNavController(),
    viewModel: OnboardingViewModel = viewModel(),
) {
    Box(
        modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                BackBtn {
                    navController.navigate(Screen.StepTwo.route)
                }
                Text(
                    text = "4. Skills & Qualifications",
                    modifier = Modifier.padding(top = 5.dp, bottom = 14.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                )

            }

            Box(
                modifier = Modifier.padding(bottom = 52.dp)
            ) {
                BlueButton(label = "Save & Next") {

                }

            }


        }

    }
}

