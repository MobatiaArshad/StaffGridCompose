package com.mobileapp.staffgridcompose.ui.onboarding.screens

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.navController.Screen
import com.mobileapp.staffgridcompose.ui.onboarding.BackBtn
import com.mobileapp.staffgridcompose.ui.onboarding.BlueButton
import com.mobileapp.staffgridcompose.ui.onboarding.OnBoardShrinkVIew
import com.mobileapp.staffgridcompose.ui.onboarding.OnboardingViewModel
import com.mobileapp.staffgridcompose.ui.onboarding.model.DropDownData
import com.mobileapp.staffgridcompose.ui.onboarding.model.OnboardCells
import com.mobileapp.staffgridcompose.utils.UploadImageAlertDialog
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalPermissionsApi::class)
@Preview(showSystemUi = true)
@Composable
fun StepFour(
    navController: NavHostController = rememberNavController(),
    viewModel: OnboardingViewModel = viewModel(),
) {
    val openImageChooser = remember { mutableStateOf(false) }

    var imageFile by remember { mutableStateOf<File?>(null) }

    val context = LocalContext.current.applicationContext

    val state = rememberPermissionState(Manifest.permission.CAMERA)
    val scaffoldState = rememberScaffoldState()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { wasGranted ->
        if (wasGranted) {
            openImageChooser.value= true
            Toast.makeText(context, "📸 Photo in 3..2..1", Toast.LENGTH_SHORT).show()
        }
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = scaffoldState.snackbarHostState



    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        }


    // For camera image
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { cameraBitmap ->
        cameraBitmap?.let {
            val fileName = "IMG_${System.currentTimeMillis()}.jpg"
            imageFile = File(context.filesDir, fileName)
            try {
                val out = FileOutputStream(imageFile)
                it.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Got image data. Use it according to your need(imageFile)
        }
    }
    if (openImageChooser.value) {
        UploadImageAlertDialog(
            onCameraClick = {
                cameraLauncher.launch()
                openImageChooser.value = false
            },
            onGalleryClick = {
                imagePickerLauncher.launch("image/*")
                openImageChooser.value = false
            },
            onDismissClick = { openImageChooser.value = false }
        )
    }

    Box(
        modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                BackBtn {
                    navController.navigate(Screen.StepTwo.route)
                }
                Text(
                    text = "4. Skills & Qualifications",
                    modifier = Modifier.padding(top = 5.dp, bottom = 14.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                )
                OnBoardShrinkVIew(
                    modifier = Modifier.padding(bottom = 10.dp),
                    OnboardCells(
                        title = "ACL Card",
                        data = listOf(
                            DropDownData("Item 1.1"),
                            DropDownData("Item 1.2"),
                            DropDownData("Item 1.3")
                        ),
                        isExpanded = remember { mutableStateOf(true) },
                        type = remember { mutableIntStateOf(2) },

                        )
                ){
                    when (state.status) {
                        PermissionStatus.Granted -> {
                            // TODO do work (ie forward to viewmodel)
                            Toast.makeText(context, "📸 Photo in 3..2..1", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            if (state.status.shouldShowRationale) {
                                scope.launch {
                                    val result =
                                        snackbarHostState.showSnackbar(
                                            message = "Permission required",
                                            actionLabel = "Go to settings"
                                        )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        val intent = Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", context.packageName, null)
                                        )
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)                                }
                                }
                            } else {
                                launcher.launch(Manifest.permission.CAMERA)
                            }
                        }
                    }

                }
                Image(
                    rememberAsyncImagePainter(imageFile),
                    contentDescription = "...",
                )

//                OptionalPermissionScreen()


            }

            Box(
                modifier = Modifier.padding(bottom = 52.dp)
            ) {
                BlueButton(label = "Save & Next") {

                }

            }


        }

    }
}

