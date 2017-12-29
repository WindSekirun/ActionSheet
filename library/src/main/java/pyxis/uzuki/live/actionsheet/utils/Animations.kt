package pyxis.uzuki.live.actionsheet.utils

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import pyxis.uzuki.live.actionsheet.Constants

private val type = TranslateAnimation.RELATIVE_TO_SELF

fun createTranslationInAnimation(): Animation = TranslateAnimation(type, 0f, type, 0f, type, 1f, type, 0f).apply {
    duration = Constants.TRANSLATE_DURATION
}

fun createAlphaInAnimation(): Animation = AlphaAnimation(0f, 1f).apply {
    duration = Constants.ALPHA_DURATION
}

fun createTranslationOutAnimation(): Animation = TranslateAnimation(type, 0f, type, 0f, type, 0f, type, 1f).apply {
    duration = Constants.TRANSLATE_DURATION
    fillAfter = true
}