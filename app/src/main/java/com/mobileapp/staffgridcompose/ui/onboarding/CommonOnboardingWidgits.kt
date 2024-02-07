package com.mobileapp.staffgridcompose.ui.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.onboarding.model.OnboardCells
import com.mobileapp.staffgridcompose.utils.ChooseDate
import com.mobileapp.staffx.ui.mainActivity.theme.inter
import com.mobileapp.staffx.ui.mainActivity.theme.naviLight
import com.mobileapp.staffx.ui.mainActivity.theme.white
import java.time.LocalDate

@Preview(showBackground = true)
@Composable
fun RoundedOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    hint: String = "",
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val viewPassword = remember { mutableStateOf(!isPassword) }

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

        keyboardOptions = if (isPassword) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        else KeyboardOptions.Default.copy(keyboardType = keyboardType),

        visualTransformation = if (viewPassword.value) VisualTransformation.None else PasswordVisualTransformation(),

        trailingIcon = {
            if (isPassword) {
                Text(
                    text = if (viewPassword.value) "Hide" else "Show",
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            viewPassword.value = !viewPassword.value
                        }
                        .padding(end = 5.dp),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF949B9C),
                )
            }
        },

        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp, color = Color(0xFFE2E8E9), shape = RoundedCornerShape(size = 5.dp)
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
    isChecked: Boolean = false, label: String = "CheckBox", onValueChange: (Boolean) -> Unit = {},
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
    modifier: Modifier = Modifier, label: String = "", onCLick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = onCLick,
        border = BorderStroke(1.dp, Color(0xFF01113D)),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF01113D)),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
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
fun LightBlueButton(
    modifier: Modifier = Modifier, label: String = "", onCLick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = onCLick,
        border = BorderStroke(1.dp, naviLight),
        shape = RoundedCornerShape(5), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.buttonColors(containerColor = naviLight),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = label, style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(700),
                color = white,
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun BlueButtonWithIcon(
    modifier: Modifier = Modifier,
    label: String = "",
    height: Dp = 52.dp,
    iconId: Int = R.drawable.ic_upload_btn,
    onCLick: () -> Unit = {},
) {
    val myIcon = painterResource(id = iconId)
    OutlinedButton(
        onClick = onCLick,
        border = BorderStroke(1.dp, Color(0xFF01113D)),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF01113D)),
        shape = RoundedCornerShape(5), // = 50% percent
        modifier = modifier.height(height)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = myIcon,
                contentDescription = "Custom Icon", // Provide a content description
            )
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

}

@Preview(showBackground = true)
@Composable
fun DropdownMenuWithTextField(
    modifier: Modifier = Modifier, hint: String = "", onValueChange: (String) -> Unit = {},
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
    click: () -> Unit = {},
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

@Preview(showBackground = true)
@Composable
fun OnBoardShrinkVIew(
    modifier: Modifier = Modifier,
    data: OnboardCells = OnboardCells(),
    onClick: () -> Unit = {}
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ), modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.clickable(
                interactionSource = interactionSource, indication = null
            ) {
                data.isExpanded.value = !data.isExpanded.value
            }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 12.dp, bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    androidx.compose.material.Text(
                        text = data.title,
                        fontSize = 14.sp,
                        lineHeight = 16.87.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF1F1F1F),
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.drop_down_black),
                        contentDescription = ""
                    )
                }

                AnimatedVisibility(visible = data.isExpanded.value) {
                    when (data.type.value) {
                        1 -> InnerCell()
                        2 -> WithButton() {
                            onClick.invoke()
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InnerCell() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            DropdownMenuWithTextField(hint = "Select Provider Type*", modifier = Modifier.padding(
                start = 15.dp, end = 15.dp, top = 19.dp, bottom = 18.dp
            ), onValueChange = {})
            DropdownMenuWithTextField(hint = "Select Position Type*",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 18.dp),
                onValueChange = {})
            DropdownMenuWithTextField(hint = "Select Applicant Type*",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 18.dp),
                onValueChange = {})

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WithButton(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            DropdownMenuWithTextField(hint = "State*", modifier = Modifier.padding(
                start = 15.dp, end = 15.dp, top = 19.dp, bottom = 18.dp
            ), onValueChange = {})
            TextWithDatePicker(
                hint = "Valid Until*",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 18.dp),
            )
            BlueButtonWithIcon(
                modifier = Modifier
                    .padding(
                        start = 15.dp, end = 15.dp, bottom = 18.dp
                    )
                    .width(171.dp), label = "TYpe"
            ) {
                onClick.invoke()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithDatePicker(modifier: Modifier = Modifier, hint: String = "", onClick: () -> Unit = {}) {
    var selectedDate by remember { mutableStateOf("") }
    val showDialog = rememberSaveable { mutableStateOf(false) }
    Column {
        OutlinedTextField(value = selectedDate,
            enabled = false,
            onValueChange = { selectedDate = it },
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
                .clickable { },
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
                    painterResource(id = R.drawable.ic_calender),
                    contentDescription = "Date Picker Icon",
                    modifier = Modifier.clickable { showDialog.value = true }
                )
            })
        if (showDialog.value) {
            ChooseDate(showDialog.value) { selectedDateInMillis ->
                // Update selectedDateMillis or other state variables here
                selectedDate = LocalDate.ofEpochDay(selectedDateInMillis / 86400000).toString()
                showDialog.value = false
            }
        }
    }
}

@Preview()
@Composable
fun MarkUpText(
    modifier: Modifier = Modifier,
    msg: String = "",
    clickTxt: String = "",
    click: () -> Unit = {}
) {
    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                color = white,
                fontFamily = inter,
                fontSize = 14.sp
            )
        ) {
            append(msg)

        }

        pushStringAnnotation(
            tag = clickTxt, annotation = clickTxt
        )

        withStyle(
            style = SpanStyle(
                color = white,
                fontFamily = inter,
                fontSize = 15.sp,
                fontWeight = FontWeight(600)
            )
        ) {
            append(clickTxt)
        }

        pop()
    }

    ClickableText(
        text = annotatedText, onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = clickTxt, start = offset, end = offset
            ).firstOrNull()?.let { annotation ->
                click.invoke()
            }
        }, modifier = modifier, style = TextStyle(
            fontSize = 14.sp,
            fontFamily = inter,
            color = white
        )
    )
}