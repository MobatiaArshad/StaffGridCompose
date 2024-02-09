package com.mobileapp.staffgridcompose.ui.forgotPass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffx.ui.mainActivity.theme.inter
import com.mobileapp.staffx.ui.mainActivity.theme.interSemiBold
import com.mobileapp.staffgridcompose.ui.mainActivity.theme.white

@Preview(showSystemUi = true)
@Composable
fun ForgotPassScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ForgotPassViewModel = viewModel()
) {

    val email = remember {
        mutableStateOf(viewModel.email.value)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.theme_navi_blue))
    ) {

        Row(
            Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.white_back_btn),
                contentDescription = "go back",
                modifier = Modifier
                    .padding(25.dp)
                    .clickable {
                        navController.navigateUp()
                    }
            )
        }

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
                Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Forgot your password?",
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight(700),
                    color = white,
                    modifier = Modifier.padding(top = 30.dp)
                )

                Text(
                    text = "We just need your email address to send your password reset",
                    fontSize = 14.sp,
                    lineHeight = 16.87.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF3F7F9),
                    modifier = Modifier.padding(top = 16.dp)
                )

                RoundedOutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    hint = "Email address",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 23.dp),
                    isPassword = false
                )

                LightBlueButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    label = "Send Email"
                ) {
                    navController.navigate(Screen.VerificationScreen.route) {
                        popUpTo(Screen.ForgotPassword.route) {
                            inclusive = true
                        }
                    }
                }
            }


        }
    }
}