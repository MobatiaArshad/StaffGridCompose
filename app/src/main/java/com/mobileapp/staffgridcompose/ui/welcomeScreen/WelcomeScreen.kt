package com.mobileapp.staffgridcompose.ui.welcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.LightBlueButton
import com.mobileapp.staffx.ui.mainActivity.theme.inter
import com.mobileapp.staffx.ui.mainActivity.theme.interSemiBold
import com.mobileapp.staffx.ui.mainActivity.theme.white

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreen(
    navController: NavController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.theme_navi_blue))
    ) {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight(700),
                    color = white,
                    modifier = Modifier.padding(top = 30.dp)
                )

                Text(
                    text = "You are successfully created StaffX account",
                    fontSize = 14.sp,
                    lineHeight = 16.87.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF3F7F9),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Image(
                modifier = Modifier.padding(top = 33.dp),
                painter = painterResource(id = R.drawable.welcome_ico),
                contentDescription = "",

            )

            LightBlueButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 52.dp),
                label = "Proceed To Onboarding"
            ) {

            }
        }
    }
}