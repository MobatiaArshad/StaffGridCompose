package com.mobileapp.staffgridcompose.ui.onboarding.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.mobileapp.staffx.ui.mainActivity.theme.StaffXTheme


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview(showBackground = true)
fun PermissionScreen(navController: NavHostController = rememberNavController()) {
    PermissionScreenBody {
        PermissionScreenContent()
    }
}

@Composable
private fun PermissionScreenBody(
    content: @Composable (PaddingValues) -> Unit
) {
    StaffXTheme {
        Scaffold(containerColor = MaterialTheme.colorScheme.surface) {
            content(it)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun PermissionScreenContent() {

    //Here we are getting our PermissionRequiredItems based on Android version
    val permissionsRequiredItems = PermissionsHandler.getPermissionItems(
        PermissionsInfoItem.permissionInfoItems()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector  = Icons.Rounded.Settings,
                    contentDescription = "Key",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                //If the Android version is unsupported like below Nought(7) or After UpSideDownCape(14)
                if (permissionsRequiredItems.isEmpty()) {
                    Text(
                        text = "Sorry this application won't work on your device.",
                        modifier = Modifier.padding(horizontal = 40.dp),
                        textAlign = TextAlign.Center,
//                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } else {
                    Text(
                        text = "we need your permission to access some of our features to work properly",
                        modifier = Modifier.padding(horizontal = 40.dp),
                        textAlign = TextAlign.Center,
//                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            items(permissionsRequiredItems) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
//                        icon = it.icon,
//                        iconColor = it.iconColor,
                        imageVector  = it.icon,
                        contentDescription = "Key",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = it.title,
//                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.15.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = it.description,
//                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.5.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        val permissionState = rememberMultiplePermissionsState(
            permissions = PermissionsHandler
                .permissions //list of permissions that we need to request
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(
                onClick = {
                    //Add this 2 lines

                    //Used to launch multiple permission request in run time
                    //⚠️ This should always be triggered from non-composable
                    // scope, for example, from a side-effect or a
                    // non-composable callback. Otherwise, this will
                    // result in an IllegalStateException ⚠️.
                    permissionState.launchMultiplePermissionRequest()

                    //After launching the permissions we need to handle the
                    //Result of the permission weather it maybe success,Rejected..
                    performPermissions(permissionState)

                },
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .height(50.dp)
            ) {
                if (permissionsRequiredItems.isEmpty()) {
                    Text(
                        text = "Exit",
//                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Text(
                        text = "Grant Permissions",
//                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview
private fun PermissionScreenPreview() {
    StaffXTheme {
        PermissionScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun performPermissions(
    permissionsState: MultiplePermissionsState
) {
    permissionsState
        .permissions //list of permission that we requested
        .forEach { permissionState ->

            PermissionsHandler //using our permissionHandler to handle permission
                .handlePermission(
                    permissionState,
                    onSuccess = {
                        //When user accepts the permission
                        println("//When user accepts the permission")
                    },
                    onRejected = {
                        //When user reject the permission
                        println("//When user reject the permission")
                    },
                    shouldShowRationale = {
                        //When user rejected completely the permission
                        println("//When user rejected completely the permission")
                    }
                )
        }
}