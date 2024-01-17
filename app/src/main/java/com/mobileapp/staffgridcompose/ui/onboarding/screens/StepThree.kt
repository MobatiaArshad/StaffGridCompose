package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.BackBtn
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.OnBoardShrinkVIew
import com.mobileapp.staffgridcompose.ui.onboarding.model.DropDownData
import com.mobileapp.staffgridcompose.ui.onboarding.model.OnboardCells

@Preview(showSystemUi = true)
@Composable
fun StepThree(navController: NavHostController = rememberNavController()) {
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
                    text = "3. Skills & Qualifications",
                    modifier = Modifier.padding(top = 5.dp, bottom = 14.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                )
                Column(  Modifier.verticalScroll(rememberScrollState()).fillMaxHeight()
                ) {

                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp, top = 14.dp),
                        OnboardCells(
                            title = "POSITION, LICENSE AND CERTIFICATION\n" + "INFORMATION",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(1) },

                            )
                    )
                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp),
                        OnboardCells(
                            title = "ACLS Card",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(2) },

                            )
                    )
                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp),
                        OnboardCells(
                            title = "BLS/CPR",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(2) },

                            )
                    )
                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp),
                        OnboardCells(
                            title = "RN",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(1) },

                            )
                    )
                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp),
                        OnboardCells(
                            title = "Phlebotomist License 2",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(1) },

                            )
                    )
                    OnBoardShrinkVIew(
                        modifier = Modifier.padding(bottom = 10.dp),
                        OnboardCells(
                            title = "Diploma Certificate",
                            data = listOf(
                                DropDownData("Item 1.1"),
                                DropDownData("Item 1.2"),
                                DropDownData("Item 1.3")
                            ),
                            isExpanded = remember { mutableStateOf(false) },
                            type = remember { mutableIntStateOf(1) },

                            )
                    )
                }
            }

                BlueButton(modifier = Modifier.padding(bottom = 52.dp),label = "Save & Next") {

                }


        }
    }
}


