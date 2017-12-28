package pyxis.uzuki.live.actionsheet.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import pyxis.uzuki.live.richutilskt.utils.tryCatch

/**
 * create FrameLayout.LayoutParams
 */
fun createFrameParams(width: Int = FrameParams.MATCH_PARENT, height: Int = FrameParams.MATCH_PARENT) = FrameParams(width, height)

/**
 * create LinearLayout.LayoutParams
 */
fun createLinearParams(width: Int = LinearParams.MATCH_PARENT, height: Int = LinearParams.WRAP_CONTENT) = LinearParams(width, height)

/**
 * get drawable by DrawableRes
 */
fun Context.getDrawableRes(@DrawableRes value: Int) = ContextCompat.getDrawable(this, value)!!

/**
 * dip to px
 */
fun Context.dip2px(value: Int) = value.toFloat() * resources.displayMetrics.density

/**
 * get NavigationBar Height
 */
fun Context.getNavBarHeight(): Int {
    var navigationBarHeight = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val id = this.getIdentifier("navigation_bar_height", "dimen")
        if (id > 0 && checkDeviceHasNavigationBar()) {
            navigationBarHeight = this.resources.getDimensionPixelSize(id)
        }
    }
    return navigationBarHeight
}

/**
 * get identifier
 */
fun Context.getIdentifier(name: String, defType: String) = this.resources.getIdentifier(name, defType, "android")

@SuppressLint("PrivateApi")
private fun Context.checkDeviceHasNavigationBar(): Boolean = tryCatch {
    val id = this.getIdentifier("config_showNavigationBar", "bool")
    val systemPropertiesClass = Class.forName("android.os.SystemProperties")
    val methods = systemPropertiesClass.getMethod("get", String::class.java)
    val navBarOverride = methods.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
    when {
        navBarOverride == "1" -> false
        navBarOverride == "0" -> true
        id > 0 -> this.resources.getBoolean(id)
        else -> false
    }
}
