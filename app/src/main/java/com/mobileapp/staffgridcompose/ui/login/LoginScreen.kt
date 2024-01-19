package com.mobileapp.staffgridcompose.ui.login

import android.widget.Toast
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.LightBlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.RoundedOutlinedTextField
import com.mobileapp.staffx.ui.mainActivity.theme.craftsWorkBold
import com.mobileapp.staffx.ui.mainActivity.theme.inter
import com.mobileapp.staffx.ui.mainActivity.theme.white

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel()
) {
    val interactionSource = remember {
        MutableInteractionSource()
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

                LightBlueButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    label = "Login"
                ) {

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
            DontHaveAccount()

        }

    }

}

@Preview()
@Composable
fun DontHaveAccount() {
    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                color = white,
                fontFamily = inter,
                fontSize = 14.sp
            )
        ) {
            append("Don’t have an account? ")

        }

        pushStringAnnotation(
            tag = "Sign Up", annotation = "Sign Up"
        )

        withStyle(
            style = SpanStyle(
                color = white,
                fontFamily = inter,
                fontSize = 15.sp,
                fontWeight = FontWeight(600)
            )
        ) {
            append("Sign Up")
        }

        pop()
    }

    ClickableText(
        text = annotatedText, onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "Sign Up", start = offset, end = offset
            ).firstOrNull()?.let { annotation ->
                Toast.makeText(context, "Reset here Clicked " + annotation.item, Toast.LENGTH_SHORT)
                    .show()
            }
        }, modifier = Modifier.padding(top = 233.dp), style = TextStyle(
            fontSize = 14.sp,
            fontFamily = inter,
            color = white
        )
    )
}