package com.mobileapp.staffx.ui.mainActivity.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobileapp.staffgridcompose.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val inter = FontFamily(Font(R.font.inter_regular))
val interBold = FontFamily(Font(R.font.inter_bold))
val interMedium = FontFamily(Font(R.font.inter_medium))
val interSemiBold = FontFamily(Font(R.font.inter_semi_bold))

val craftsWorkSemi = FontFamily(Font(R.font.space_grotesk_semi_bold))
val craftsWorkBold = FontFamily(Font(R.font.space_grotesk_bold))