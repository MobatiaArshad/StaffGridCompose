package com.mobileapp.staffgridcompose.extras

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import com.mobileapp.staffgridcompose.utils.SnackBarWithoutScaffold

@Composable
fun BaseCompose(
    viewModel: BaseViewModel = viewModel(),
) {

    Box(Modifier.fillMaxSize()) {
        val errorMsg by  viewModel.snackMsg.collectAsState()
        var showSnack = viewModel.showSnack

        SnackBarWithoutScaffold(message = errorMsg, showSb = showSnack, openSnackbar = {
            showSnack = it
            viewModel.resetSnack()
        })

    }

}