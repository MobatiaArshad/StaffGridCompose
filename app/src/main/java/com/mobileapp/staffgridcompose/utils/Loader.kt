package com.mobileapp.staffgridcompose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mobileapp.staffgridcompose.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppLoader(show: Boolean = true) {

    if (show) {
        Dialog(
            onDismissRequest = {  }
        ) {
            Surface(
                shape = RoundedCornerShape(9.dp),
                color = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Column(
                        Modifier.size(90.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(14.dp)),
                            color = colorResource(id = R.color.purple_700),
                            strokeWidth = 10.dp
                        )
                    }
                }
            }
        }
    }
}