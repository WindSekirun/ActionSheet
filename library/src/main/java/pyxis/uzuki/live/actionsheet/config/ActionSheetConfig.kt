package pyxis.uzuki.live.actionsheet.config

import android.support.annotation.ColorInt
import pyxis.uzuki.live.actionsheet.listener.OnActionButtonClickListener
import pyxis.uzuki.live.actionsheet.listener.OnDismissListener
import pyxis.uzuki.live.actionsheet.model.ActionButton
import java.io.Serializable
import java.util.*

/**
 * ActionSheet
 * Class: ActionSheetConfig
 * Created by Pyxis on 2017-12-28.
 *
 * Description:
 */
data class ActionSheetConfig(val items: List<ActionButton>, val cancelButton: ActionButton, val onDismissListener: OnDismissListener?,
                             val onActionButtonClickListener: OnActionButtonClickListener?, val cancelableOnTouchOutside: Boolean) : Serializable {

    class Builder {
        private var items = arrayListOf<ActionButton>()
        private var cancelButton = ActionButton("cancel")
        private var onDismissListener: OnDismissListener? = null
        private var onActionButtonClickListener: OnActionButtonClickListener? = null
        private var cancelable: Boolean = false

        fun addItem(vararg items: ActionButton) = apply { this.items.addAll(items) }

        fun setItems(items: ArrayList<ActionButton>) = apply { this.items.addAll(items) }

        fun setCancelButton(title: String, @ColorInt color: Int) = apply { this.cancelButton = ActionButton(title, color) }

        fun setCancelButton(cancelButton: ActionButton) = apply { this.cancelButton = cancelButton }

        fun setOnDismissListener(listener: OnDismissListener) = apply { this.onDismissListener = listener }

        fun setOnActionButtonClickListener(listener: OnActionButtonClickListener) = apply { this.onActionButtonClickListener = listener }

        fun setCancelableOnTouchOutside(cancelable: Boolean) = apply { this.cancelable = cancelable }

        fun build() = ActionSheetConfig(items, cancelButton, onDismissListener, onActionButtonClickListener, cancelable)
    }
}