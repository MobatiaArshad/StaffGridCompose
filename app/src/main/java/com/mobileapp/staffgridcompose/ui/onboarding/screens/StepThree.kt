package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButtonWithIcon
import com.mobileapp.staffgridcompose.ui.onboarding.OnBoardShrinkVIew
import com.mobileapp.staffgridcompose.ui.onboarding.OnboardingViewModel
import com.mobileapp.staffgridcompose.ui.onboarding.model.DropDownData
import com.mobileapp.staffgridcompose.ui.onboarding.model.OnboardCells

@Preview(showSystemUi = true)
@Composable
fun StepThree(navController: NavHostController = rememberNavController(), viewModel: OnboardingViewModel = viewModel()) {
    var id = 0
    val mainListData = viewModel.stepThreeList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
            .fillMaxSize()
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
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

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        Modifier.fillMaxHeight(0.84f)
                    ) {
                        item {
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
                        }

                        item {
                            OnBoardShrinkVIew(
                                modifier = Modifier.padding(bottom = 10.dp),
                                OnboardCells(
                                    title = "ACL Card",
                                    data = listOf(
                                        DropDownData("Item 1.1"),
                                        DropDownData("Item 1.2"),
                                        DropDownData("Item 1.3")
                                    ),
                                    isExpanded = remember { mutableStateOf(false) },
                                    type = remember { mutableIntStateOf(2) },

                                    )
                            )

                        }

                        item {
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

                        }

                        item {
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

                        }

                        item {
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

                        }

                        item {
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
                        items(mainListData.value) { data ->
                            OnBoardShrinkVIew(
                                modifier = Modifier.padding(bottom = 10.dp, top = 14.dp),
                                OnboardCells(
                                    title = data.title,
                                    data = listOf(
                                        DropDownData("Item 1.1"),
                                        DropDownData("Item 1.2"),
                                        DropDownData("Item 1.3")
                                    ),
                                    isExpanded = remember { mutableStateOf(true) },
                                    type = remember { mutableIntStateOf(2) },

                                    )
                            )
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Other Certification",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF1F1F1F),
                                    )
                                )
                                BlueButtonWithIcon(iconId = R.drawable.ic_plus, height = 28.dp){
                                    id += 1
                                    viewModel.addNewDataToCell(id)
                                }
                            }

                        }

                    }

                    BlueButton(modifier = Modifier.padding(bottom = 52.dp), label = "Save & Next") {
                        navController.navigate(Screen.StepFour.route)
                    }
                }

            }

        }
    }
}

/*



*/


