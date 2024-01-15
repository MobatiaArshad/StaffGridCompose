package com.a71cities.jetpackcomposersklss.navController

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.eligibleLocation.EligibleLocScreen
import com.mobileapp.staffgridcompose.ui.homeScreen.HomeScreen
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepOne
import com.mobileapp.staffgridcompose.ui.onboarding.screens.StepTwo

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.SocialAuth.route
) {

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Screen.SocialAuth.route) {
//            SocialAuthScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
//            LoginScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
//            RegistrationScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.OverView.route) {
//            OverViewScreen(navController = navController)
        }
        composable(route = Screen.Interests.route) {
//            YourInterests(navController = navController)
        }
        composable(route = Screen.ContentManager.route) {
//            ContentManagerScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
//            ProfileScreen(navController = navController)
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
    }
}