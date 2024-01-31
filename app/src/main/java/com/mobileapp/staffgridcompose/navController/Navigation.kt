package com.a71cities.jetpackcomposersklss.navController

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobileapp.staffgridcompose.utils.Constant.IS_FROM_REG
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.eligibleLocation.EligibleLocScreen
import com.mobileapp.staffgridcompose.ui.forgotPass.ForgotPassScreen
import com.mobileapp.staffgridcompose.ui.homeScreen.HomeScreen
import com.mobileapp.staffgridcompose.ui.login.LoginScreen
import com.mobileapp.staffgridcompose.ui.onboarding.screens.PermissionScreen
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepFour
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepOne
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepThree
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepTwo
import com.mobileapp.staffgridcompose.ui.signUp.SignUpScreen
import com.mobileapp.staffgridcompose.ui.verifyEmail.VerifyEmailScreen
import com.mobileapp.staffgridcompose.utils.routeOptArgName

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.SocialAuth.route
) {
    
    val tweenTime = 400

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Screen.SocialAuth.route) {

        }
        composable(route = Screen.Login.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }, popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.ForgotPassword.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }, popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }
        ) {
            ForgotPassScreen(navController = navController)
        }

        composable(
            route = Screen.VerificationScreen.route + routeOptArgName(IS_FROM_REG),
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }, popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            },
            arguments = listOf(
                navArgument(IS_FROM_REG) {
                    defaultValue = false
                }
            ),
        ) {

            val isFromReg = it.arguments!!.getBoolean(IS_FROM_REG)

            VerifyEmailScreen(
                navController = navController,
                isFromReg = isFromReg
            )
        }

        composable(
            route = Screen.Register.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(tweenTime)
                )
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }, popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(tweenTime)
                )
            }
        ) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.OverView.route) {

        }
        composable(route = Screen.Interests.route) {

        }
        composable(route = Screen.ContentManager.route) {

        }
        composable(route = Screen.Profile.route) {

        }

        composable(route = Screen.EligibleLocation.route) {
            EligibleLocScreen(navController = navController)
        }
        composable(route = Screen.StepOne.route) {
            StepOne(navController = navController)
        }
        composable(route = Screen.StepTwo.route) {
            StepTwo(navController = navController)
        }
        composable(route = Screen.StepThree.route) {
            StepThree(navController = navController)
        }
        composable(route = Screen.StepFour.route) {
            StepFour(navController = navController)
        }
        composable(route = Screen.PermissionScreen.route) {
            PermissionScreen(navController = navController)
        }
    }
}