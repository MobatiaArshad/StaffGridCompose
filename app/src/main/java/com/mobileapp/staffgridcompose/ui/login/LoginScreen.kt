package com.mobileapp.staffgridcompose.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel()
) {

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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 171.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.staffx_ico),
                contentDescription = "StaffX",
                modifier = Modifier
                    .width(106.dp)
                    .height(36.dp)
            )

            Column(
                modifier = Modifier.padding(start = 29.dp, end = 29.dp)
            ) {
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

                RoundedOutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    hint = "Password",
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.padding(top = 23.dp),
                    isPassword = true
                )
            }
        }

    }

}