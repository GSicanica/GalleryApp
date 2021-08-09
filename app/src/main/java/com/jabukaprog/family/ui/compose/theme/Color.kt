package com.jabukaprog.family.ui.compose.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object AppColors {
    val bgLight = Color.White
    val bgDark = Color(0xFF1A1A1A)
    val bgMedium = Color(0xFF323232)
    val textLight = Color.White
    val textDark = Color(0xFF393E46)
    val textMedium = Color(0xFF929599)
    val onlineIndicator = Color(0xFF19D42B)
}

val softGray = Color(0xFFDFDFDF)
val iconsBackground = Color(0xFFF0F0F0)

val Blue300 = Color(0xFF64B5F6)
val Blue400 = Color(0xFF42A5F5)
val Blue500 = Color(0xFF2196F3)
val Blue600 = Color(0xFF1E88E5)
val Blue700 = Color(0xFF1976D2)
val Blue800 = Color(0xFF1565C0)

val Teal300 = Color(0xFF1AC6FF)

val Grey1 = Color(0xFFF2F2F2)

val Black1 = Color(0xFF222222)
val Black2 = Color(0xFF000000)

val RedErrorDark = Color(0xFFB00020)
val RedErrorLight = Color(0xFFEF5350)


private val red200 = Color(0XFFEF9A9A)
private val red500 = Color(0XFFF44336)
private val red700 = Color(0XFFD32F2F)
private val pink200 = Color(0XFFF48FB1)
private val pink500 = Color(0XFFE91E63)
private val pink700 = Color(0XFFC2185B)
private val purple200 = Color(0XFFCE93D8)
val purple500 = Color(0XFF9C27B0)
val purple700 = Color(0XFF7B1FA2)
private val deepPurple200 = Color(0XFFB39DDB)
private val deepPurple500 = Color(0XFF673AB7)
private val deepPurple700 = Color(0XFF512DA8)
private val indigo200 = Color(0XFF9FA8DA)
private val indigo500 = Color(0XFF3F51B5)
private val indigo700 = Color(0XFF303f9f)
private val blue200 = Color(0XFF90CAF9)
private val blue500 = Color(0xFF2195F2)
private val blue700 = Color(0xFF1976D2)
private val lightBlue200 = Color(0XFF81D4FA)
private val lightBlue500 = Color(0XFF03A9F4)
private val lightBlue700 = Color(0XFF0288D1)
private val cyan200 = Color(0XFF80DEEA)
private val cyan500 = Color(0xFF00BCD4)
private val cyan700 = Color(0xFF0097A7)
private val teal200 = Color(0XFF80DEEA)
private val teal500 = Color(0XFF009688)
private val teal700 = Color(0XFF00796B)
private val green200 = Color(0XFFA5D6A7)
private val green500 = Color(0XFF4CAF50)
private val green700 = Color(0XFF388E3C)
private val lightGreen200 = Color(0XFFC5E1A5)
private val lightGreen500 = Color(0XFF8BC34A)
private val lightGreen700 = Color(0XFF689F38)
private val lime200 = Color(0XFFE6EE9C)
private val lime500 = Color(0XFFCDDC39)
private val lime700 = Color(0XFFAFB42B)
private val yellow200 = Color(0XFFFFF59D)
private val yellow500 = Color(0XFFFFEB3B)
private val yellow700 = Color(0XFFFBC02D)
private val amber200 = Color(0XFFFFE082)
private val amber500 = Color(0XFFFFC107)
private val amber700 = Color(0XFFFFA000)
private val orange200 = Color(0XFFFFCC80)
private val orange500 = Color(0XFFFF9800)
private val orange700 = Color(0XFFF57C00)
private val deepOrange200 = Color(0XFFFFAB91)
private val deepOrange500 = Color(0XFFFF5722)
private val deepOrange700 = Color(0XFFE64A19)

/* Material Red */
private val DarkRedColorPalette = darkColors(
    primary = red200,
    primaryVariant = red500,
    secondary = teal200,
    surface = red200,
)
private val LightRedColorPalette = lightColors(
    primary = red500,
    primaryVariant = red700,
    secondary = teal200,
    surface = red500,
)

/* Material Pink */
private val DarkPinkColorPalette = darkColors(
    primary = pink200,
    primaryVariant = pink500,
    secondary = teal200,
    surface = pink200,
)
private val LightPinkColorPalette = lightColors(
    primary = pink500,
    primaryVariant = pink700,
    secondary = teal200,
    surface = pink500,
)

/* Material Purple */
private val DarkPurpleColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple500,
    secondary = teal200,
    surface = purple200,
)
private val LightPurpleColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200,
    surface = purple500,
)

/* Material Deep Purple */
private val DarkDeepPurpleColorPalette = darkColors(
    primary = deepPurple200,
    primaryVariant = deepPurple500,
    secondary = teal200,
    surface = deepPurple200,
)
private val LightDeepPurpleColorPalette = lightColors(
    primary = deepPurple500,
    primaryVariant = deepPurple700,
    secondary = teal200,
    surface = deepPurple500,
)

/* Material Indigo */
private val DarkIndigoColorPalette = darkColors(
    primary = indigo200,
    primaryVariant = indigo500,
    secondary = teal200,
    surface = indigo200,
)
private val LightIndigoColorPalette = lightColors(
    primary = indigo500,
    primaryVariant = indigo700,
    secondary = teal200,
    surface = indigo500,
)

/* Material Blue */
private val DarkBlueColorPalette = darkColors(
    primary = blue200,
    primaryVariant = blue500,
    secondary = teal200,
    surface = blue200,
)
private val LightBlueColorPalette = lightColors(
    primary = blue500,
    primaryVariant = blue700,
    secondary = teal200,
    surface = blue500,
)

/* Material Light Blue */
private val DarkLightBlueColorPalette = darkColors(
    primary = lightBlue200,
    primaryVariant = lightBlue500,
    secondary = teal200,
    surface = lightBlue200,
)
private val LightLightBlueColorPalette = lightColors(
    primary = lightBlue500,
    primaryVariant = lightBlue700,
    secondary = teal200,
    surface = lightBlue500,
)

/* Material Cyan */
private val DarkCyanColorPalette = darkColors(
    primary = cyan200,
    primaryVariant = cyan500,
    secondary = teal200,
    surface = cyan200,
)
private val LightCyanColorPalette = lightColors(
    primary = cyan500,
    primaryVariant = cyan700,
    secondary = teal200,
    surface = cyan500,
)

/* Material Teal */
private val DarkTealColorPalette = darkColors(
    primary = teal200,
    primaryVariant = teal500,
    secondary = red200,
    surface = teal200,
)
private val LightTealColorPalette = lightColors(
    primary = teal500,
    primaryVariant = teal700,
    secondary = teal200,
    surface = teal500,
)

/* Material Green */
private val DarkGreenColorPalette = darkColors(
    primary = green200,
    primaryVariant = green500,
    secondary = teal200,
    surface = green200,
)
private val LightGreenColorPalette = lightColors(
    primary = green500,
    primaryVariant = green700,
    secondary = teal200,
    surface = green500,
)

/* Material Light Green */
private val DarkLightGreenColorPalette = darkColors(
    primary = lightGreen200,
    primaryVariant = lightGreen500,
    secondary = teal200,
    surface = lightGreen200,
)
private val LightLightGreenColorPalette = lightColors(
    primary = lightGreen500,
    primaryVariant = lightGreen700,
    secondary = teal200,
    surface = lightGreen500,
)

/* Material Lime */
private val DarkLimeColorPalette = darkColors(
    primary = lime200,
    primaryVariant = lime500,
    secondary = teal200,
    surface = lime200,
)
private val LightLimeColorPalette = lightColors(
    primary = lime500,
    primaryVariant = lime700,
    secondary = teal200,
    surface = lime500,
)

/* Material Yellow */
private val DarkYellowColorPalette = darkColors(
    primary = yellow200,
    primaryVariant = yellow500,
    secondary = teal200,
    surface = yellow200,
)
private val LightYellowColorPalette = lightColors(
    primary = yellow500,
    primaryVariant = yellow700,
    secondary = teal200,
    surface = yellow500,
)

/* Material Amber */
private val DarkAmberColorPalette = darkColors(
    primary = amber200,
    primaryVariant = amber500,
    secondary = teal200,
    surface = amber200,
)
private val LightAmberColorPalette = lightColors(
    primary = amber500,
    primaryVariant = amber700,
    secondary = teal200,
    surface = amber500,
)

/* Material Orange */
private val DarkOrangeColorPalette = darkColors(
    primary = orange200,
    primaryVariant = orange500,
    secondary = teal200,
    surface = orange200,
)
private val LightOrangeColorPalette = lightColors(
    primary = orange500,
    primaryVariant = orange700,
    secondary = teal200,
    surface = orange500,
)

/* Material Deep Orange */
private val DarkDeepOrangeColorPalette = darkColors(
    primary = deepOrange200,
    primaryVariant = deepOrange500,
    secondary = teal200,
    surface = deepOrange200,
)
private val LightDeepOrangeColorPalette = lightColors(
    primary = deepOrange500,
    primaryVariant = deepOrange700,
    secondary = teal200,
    surface = deepOrange500,
)

enum class ColorPallete {
    RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN,
    LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE
}

fun ColorPallete.getMaterialColors(darkTheme: Boolean): Colors {
    return when (this) {
        ColorPallete.RED -> if (darkTheme) DarkRedColorPalette else LightRedColorPalette
        ColorPallete.PINK -> if (darkTheme) DarkPinkColorPalette else LightPinkColorPalette
        ColorPallete.PURPLE -> if (darkTheme) DarkPurpleColorPalette else LightPurpleColorPalette
        ColorPallete.DEEP_PURPLE -> if (darkTheme) DarkDeepPurpleColorPalette else LightDeepPurpleColorPalette
        ColorPallete.INDIGO -> if (darkTheme) DarkIndigoColorPalette else LightIndigoColorPalette
        ColorPallete.BLUE -> if (darkTheme) DarkBlueColorPalette else LightBlueColorPalette
        ColorPallete.LIGHT_BLUE -> if (darkTheme) DarkLightBlueColorPalette else LightLightBlueColorPalette
        ColorPallete.CYAN -> if (darkTheme) DarkCyanColorPalette else LightCyanColorPalette
        ColorPallete.TEAL -> if (darkTheme) DarkTealColorPalette else LightTealColorPalette
        ColorPallete.GREEN -> if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
        ColorPallete.LIGHT_GREEN -> if (darkTheme) DarkLightGreenColorPalette else LightLightGreenColorPalette
        ColorPallete.LIME -> if (darkTheme) DarkLimeColorPalette else LightLimeColorPalette
        ColorPallete.YELLOW -> if (darkTheme) DarkYellowColorPalette else LightYellowColorPalette
        ColorPallete.AMBER -> if (darkTheme) DarkAmberColorPalette else LightAmberColorPalette
        ColorPallete.ORANGE -> if (darkTheme) DarkOrangeColorPalette else LightOrangeColorPalette
        ColorPallete.DEEP_ORANGE -> if (darkTheme) DarkDeepOrangeColorPalette else LightDeepOrangeColorPalette
    }
}

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)
