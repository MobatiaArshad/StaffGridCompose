package com.mobileapp.staffgridcompose.ui.onboarding.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

data class PermissionsInfoItem(
    val icon: ImageVector, // Icon for Showing the Permission Item
    val iconColor: Color, // Color of the Icon
    val iconBackGroundColor: Color, // Background Color of the Icon
    val title: String, // Title for our permission item
    val description: AnnotatedString, // Description for our permission item
    val versionCode: IntRange, // At which android versions do we need to show this permission item
) {
    companion object {
        @Composable
        fun permissionInfoItems() = listOf(

            //Show only in Android 13 or 14 above
            PermissionsInfoItem(
                icon = Icons.Rounded.Notifications,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Notification",
                description = buildAnnotatedString {
                    append("To keep you up to date with the latest the ")
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("updates")
                    }
                    append(".")
                },
                versionCode = PermissionsHandler.tiramisuOrAboveTiramisu
            ),

            //Show only in Android 13 or 14 above
            PermissionsInfoItem(
                icon = Icons.Rounded.Face,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Media",
                description = buildAnnotatedString {
                    append("To save and read our saved")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" images")
                    }
                    append(" for sharing purpose.")
                },
                versionCode = PermissionsHandler.tiramisuOrAboveTiramisu
            ),

            //Show only in Android 11 or 12
            PermissionsInfoItem(
                icon = Icons.Rounded.Face,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Read Storage",
                description = buildAnnotatedString {
                    append("To save and read our saved")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" files")
                    }
                    append(" for sharing purpose.")
                },
                versionCode = PermissionsHandler.betweenRandS
            ),

            //Show only in Android 7 or 9
            PermissionsInfoItem(
                icon = Icons.Rounded.Face,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Read Storage",
                description = buildAnnotatedString {
                    append("To read our saved")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" files")
                    }
                    append(" for sharing purpose.")
                },
                versionCode = PermissionsHandler.betweenNoughtAndPie
            ),

            //Show only in Android 7 or 9
            PermissionsInfoItem(
                icon = Icons.Rounded.Face,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Write Storage",
                description = buildAnnotatedString {
                    append("To save")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" files")
                    }
                    append(" for future use.")
                },
                versionCode = PermissionsHandler.betweenNoughtAndPie
            ),

            //Shows in All Application Supported Android Versions
            PermissionsInfoItem(
                icon = Icons.Rounded.PlayArrow,
                iconColor = MaterialTheme.colorScheme.tertiary,
                iconBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                title = "Camera",
                description = buildAnnotatedString {
                    append("To Scan and read the")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" QR CODE")
                    }
                    append(" for accurate results.")
                },
                versionCode = PermissionsHandler.allApplicationSupportedVersions
            ),

            )
    }
}