package com.mobileapp.staffgridcompose.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileapp.staffgridcompose.R

@Preview(showBackground = true)
@Composable
fun RoundedOutlinedTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    hint: String = "",
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF000000),
        ),
        placeholder = {
            Text(
                text = hint, style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.87.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF58595B),
                )
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFE2E8E9),
                shape = RoundedCornerShape(size = 5.dp)
            )
            .height(52.dp),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedContainerColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFFFFFFFF)
        )
    )

}

@Preview(showBackground = true)
@Composable
fun BlueCheckBox(
    isChecked: Boolean = false,
    label: String = "CheckBox",
    onValueChange: (Boolean) -> Unit = {}
) {
    Box {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onValueChange,
                enabled = true,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF01113D), checkmarkColor = Color.White
                )
            )
            Text(
                text = label, style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.87.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF58595B),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueButton(
    label: String = "",
    onCLick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = onCLick,
        border = BorderStroke(1.dp, Color(0xFF01113D)),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF01113D)),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = label, style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DropdownMenuWithTextField(
    hint: String = "",
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = selectedText,
            enabled = false,
            onValueChange = { selectedText = it },
            textStyle = TextStyle(
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            ),
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp, color = Color(0xFFE2E8E9), shape = RoundedCornerShape(size = 5.dp)
                )
                .height(52.dp)
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedContainerColor = Color(0xFFFFFFFF),
                disabledContainerColor = Color(0xFFFFFFFF)
            ),
            placeholder = {
                Text(
                    text = hint, style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF58595B),
                    )
                )
            },
            trailingIcon = {
                Icon(
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand or collapse dropdown menu",
//                    modifier = Modifier.clickable { expanded = !expanded }
                )
            })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp) // Add horizontal padding // Ensure full width alignment
                .background(Color.White) // Set background to white,
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(text = {
                    Text(
                        text = label, style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        )
                    )
                }, // Provide text content
                    onClick = {
                        selectedText = label
                        expanded = false
                        onValueChange.invoke(label)
                    },
                    interactionSource = remember { MutableInteractionSource() } // Create interaction source
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackBtn(
    click: () -> Unit = {}
) {
    Box(modifier = Modifier
        .clickable {
            click.invoke()
        }) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_btn),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Loader(
    isLoading: Boolean = true,
) {
    Box {
        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}