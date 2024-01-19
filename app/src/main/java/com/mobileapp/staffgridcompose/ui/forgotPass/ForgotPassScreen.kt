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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R

@Preview(showSystemUi = true)
@Composable
fun ForgotPassScreen(
    navController: NavHostController = rememberNavController(),
) {
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



        }
    }
}