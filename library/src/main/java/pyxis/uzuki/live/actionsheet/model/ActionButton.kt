package pyxis.uzuki.live.actionsheet.model

import android.graphics.Color
import android.support.annotation.ColorInt

data class ActionButton(val title: String, @ColorInt val color: Int = Color.BLACK)