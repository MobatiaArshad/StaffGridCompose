package com.mobileapp.staffgridcompose.ui.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.a71cities.jetpackcomposersklss.navController.Navigation
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffx.ui.mainActivity.theme.StaffXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StaffXTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(
                        navController,
                        startDestination = Screen.Home.route
                    )

                }
            }
        }
    }
}


