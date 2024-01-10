package com.mobileapp.staffgridcompose.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SampleCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)){
        Row(
        ) {
            Text(text = "SampleText")
            Text(text = "Arshad")
        }

    }

}