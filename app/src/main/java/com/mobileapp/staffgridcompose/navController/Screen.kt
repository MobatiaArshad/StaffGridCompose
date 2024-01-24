package com.mobileapp.staffgridcompose.navController

sealed class Screen(val route: String) {

    data object SocialAuth : Screen("social_auth_screen")
    data object Login : Screen("login_screen")
    data object ForgotPassword : Screen("forgot_password_screen")
    data object VerificationScreen : Screen("verification_screen")
    data object Register : Screen("register_screen")

    data object Home : Screen("home_screen")
    data object OverView : Screen("over_view")
    data object Interests : Screen("interests")
    data object ContentManager : Screen("content_manager")
    data object Profile : Screen("profile")

    data object EligibleLocation : Screen("eligible_loc")
    data object StepOne : Screen("step_one")
    data object StepTwo : Screen("step_two")
    data object StepThree : Screen("step_three")
    object StepFour : Screen("step_Four")
}
