package com.mobileapp.staffgridcompose.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.LightBlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.MarkUpText
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffgridcompose.utils.Loading
import com.mobileapp.staffx.ui.mainActivity.theme.craftsWorkBold
import com.mobileapp.staffgridcompose.ui.mainActivity.theme.white

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel()
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val loading by viewModel.loading.collectAsState()


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
                Modifier.padding(top = 171.dp)
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
                RoundedOutlinedTextField(
                    value = email,
                    onValueChange = viewModel::emailTextChange,
                    hint = "Email address",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 23.dp),
                    isPassword = false
                )

                RoundedOutlinedTextField(
                    value = password,
                    onValueChange = viewModel::passwordTextChange,
                    hint = "Password",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 23.dp),
                    isPassword = true
                )

                LightBlueButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    label = "Login"
                ) {
                    viewModel.showSnack(true,"Pending verification")
                }

                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 14.dp, bottom = 14.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                navController.navigate(Screen.ForgotPassword.route)
                            },
                        text = "Forgot Password",
                        fontSize = 14.sp,
                        lineHeight = 19.5.sp,
                        fontFamily = craftsWorkBold,
                        fontWeight = FontWeight(700),
                        color = white,
                    )
                }
            }

            MarkUpText(
                modifier = Modifier.padding(top = 233.dp),
                msg = "Don't have account? ",
                clickTxt = "SignUp"
            ) {
                navController.navigate(Screen.Register.route)
            }

        }

    }

    when(loading) {
        Loading.Success -> {

        }
        Loading.Failed -> {

        }
        else  -> {}
    }

}