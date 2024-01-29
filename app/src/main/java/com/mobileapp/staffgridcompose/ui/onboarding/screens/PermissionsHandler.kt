package com.mobileapp.staffgridcompose.ui.onboarding.screens

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

class PermissionsHandler {
    companion object {

        /**
         * The application current Sdk Version
         */
        private var currentSdk = Build.VERSION.SDK_INT

        /**
         * Application Supported Sdk Versions
         * Make sure the starting and ending version version
         * are same as in the Build.gradle file
         */
        var allApplicationSupportedVersions =
            Build.VERSION_CODES.O .. Build.VERSION_CODES.UPSIDE_DOWN_CAKE
            private set

        /**
         * Use this when the current Sdk lies between
         * Android 13 - 14 ..
         */
        var tiramisuOrAboveTiramisu = Build.VERSION_CODES.TIRAMISU .. Build.VERSION_CODES.UPSIDE_DOWN_CAKE
            private set

        /**
         * Use this when the current Sdk lies between
         * Android 11 - 12 ..
         */
        var betweenRandS = Build.VERSION_CODES.R .. Build.VERSION_CODES.S
            private set

        /**
         * Use this when the current Sdk lies in
         * Android 10 ..
         */
        private var Q = Build.VERSION_CODES.Q .. Build.VERSION_CODES.Q

        /**
         * Use this when the current Sdk lies in
         * Android 7 .. 9
         */
        var betweenNoughtAndPie = Build.VERSION_CODES.N .. Build.VERSION_CODES.P
            private set

//        //Runtime permission that our application uses
//        //Declare all your permission here
//        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//        const val notification = Manifest.permission.POST_NOTIFICATIONS
//
//        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//        val readStorage: String = when (Build.VERSION.SDK_INT) {
//            in tiramisuOrAboveTiramisu -> Manifest.permission.READ_MEDIA_IMAGES
//            else -> Manifest.permission.READ_EXTERNAL_STORAGE
//        }
//
//        const val camera = Manifest.permission.CAMERA

        /**
         * This variable returns all the run time permissions
         * based on your current Sdk version of the device/
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val permissions: List<String> =
            when (currentSdk) {
                in tiramisuOrAboveTiramisu -> listOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
                )

                in betweenRandS -> listOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                in Q -> listOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                in betweenNoughtAndPie -> listOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                else -> emptyList()
            }

        /**
         * This function used to handle the outcome of the
         * Runtime permissions this function takes
         */
        @OptIn(ExperimentalPermissionsApi::class)
        fun handlePermission(
            permissionState: PermissionState,
            onSuccess: () -> Unit,
            shouldShowRationale: () -> Unit,
            onRejected: () -> Unit
        ) {
            when {
                permissionState.status.isGranted -> {
                    onSuccess()
                }

                permissionState.status.shouldShowRationale -> {
                    shouldShowRationale()
                }

                !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> {
                    onRejected()
                }
            }
        }

        //used to get our PermissionItems for Educational UI filter by
        //using versioncode.
        fun getPermissionItems(
            permissionInfoItems: List<PermissionsInfoItem>
        ): List<PermissionsInfoItem> {
            return when (Build.VERSION.SDK_INT) {
                // in 13 .. 14 or above
                in tiramisuOrAboveTiramisu -> permissionInfoItems
                .filter { //Filtering by tiramisuOrAbove 
                    it.versionCode == tiramisuOrAboveTiramisu
                }.toMutableList() // Converting to mutableList for adding common permission among multiple android versions
                .apply {
                    addAll(
                        permissionInfoItems.filter {
                            it.versionCode == allApplicationSupportedVersions
                        }
                    )
                }

                in betweenRandS -> permissionInfoItems.filter {
                    it.versionCode == betweenRandS
                }.toMutableList().apply {
                    addAll(
                        permissionInfoItems.filter {
                            it.versionCode == allApplicationSupportedVersions
                        }
                    )
                }

                in Q -> permissionInfoItems.filter {
                    it.versionCode == Q
                }.toMutableList().apply {
                    addAll(
                        permissionInfoItems.filter {
                            it.versionCode == allApplicationSupportedVersions
                        }
                    )
                }

                in betweenNoughtAndPie -> permissionInfoItems.filter {
                    it.versionCode == betweenNoughtAndPie
                }.toMutableList().apply {
                    addAll(
                        permissionInfoItems.filter {
                            it.versionCode == allApplicationSupportedVersions
                        }
                    )
                }

                else -> return emptyList()
            }
        }
    }
}