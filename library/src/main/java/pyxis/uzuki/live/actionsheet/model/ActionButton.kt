package pyxis.uzuki.live.actionsheet.model

import android.graphics.Color
import android.support.annotation.ColorInt
import java.io.Serializable

data class ActionButton(val title: String, @ColorInt val color: Int = Color.BLACK): Serializable