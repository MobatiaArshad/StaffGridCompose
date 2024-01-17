package com.mobileapp.staffgridcompose.navController

sealed class Screen(val route: String) {

    object SocialAuth : Screen("social_auth_screen")
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")

    object Home : Screen("home_screen")
    object OverView : Screen("over_view")
    object Interests : Screen("interests")
    object ContentManager : Screen("content_manager")
    object Profile : Screen("profile")

    object EligibleLocation : Screen("eligible_loc")
    object StepOne : Screen("step_one")
    object StepTwo : Screen("step_two")
    object StepThree : Screen("step_three")
}
