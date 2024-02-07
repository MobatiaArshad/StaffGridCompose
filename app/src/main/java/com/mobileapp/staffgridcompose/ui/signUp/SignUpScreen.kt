package com.mobileapp.staffgridcompose.ui.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.utils.Constant.IS_FROM_REG
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.LightBlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.MarkUpText
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.utils.routeOptArgs
import com.mobileapp.staffx.ui.mainActivity.theme.craftsWorkBold
import com.mobileapp.staffx.ui.mainActivity.theme.white

@Preview(showSystemUi = true)
@Composable
fun SignUpScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: SignUpViewModel = viewModel()
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val fName by viewModel.fName.collectAsState()

    val lname = remember {
        mutableStateOf(viewModel.lname.value)
    }
    val email = remember {
        mutableStateOf(viewModel.email.value)
    }
    val password = remember {
        mutableStateOf(viewModel.password.value)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.theme_navi_blue))
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.padding(top = 113.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.staffx_ico),
                    contentDescription = "StaffX",
                    modifier = Modifier
                        .width(106.dp)
                        .height(36.dp)
                )
            }


            Column(
                modifier = Modifier.padding(start = 29.dp, end = 29.dp)
            ) {

                Text(
                    modifier = Modifier
                        .padding(top = 31.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navController.navigate(Screen.ForgotPassword.route)
                        },
                    text = "Sign Up",
                    fontSize = 14.sp,
                    lineHeight = 19.5.sp,
                    fontFamily = craftsWorkBold,
                    fontWeight = FontWeight(700),
                    color = white,
                )

                RoundedOutlinedTextField(
                    value = fName,
                    onValueChange = viewModel::fNameTextChange,
                    hint = "First Name",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 19.dp),
                    isPassword = false
                )


                RoundedOutlinedTextField(
                    value = lname.value,
                    onValueChange = {
                        lname.value = it
                    },
                    hint = "Last Name",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 32.dp),
                    isPassword = false
                )


                RoundedOutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    hint = "Email",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 32.dp),
                    isPassword = false
                )

                RoundedOutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    hint = "Password",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 32.dp),
                    isPassword = true
                )

                LightBlueButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    label = "SignUp"
                ) {
                    navController.navigate(Screen.VerificationScreen.route + routeOptArgs(IS_FROM_REG,true))
                }

            }

            MarkUpText(
                Modifier.padding(top = 102.dp),
                msg = "Already have an account? ",
                clickTxt = "SignIn"
            ) {
                navController.navigateUp()
            }
        }

    }

}