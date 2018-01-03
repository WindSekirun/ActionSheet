package pyxis.uzuki.live.actionsheet.config

import android.support.annotation.ColorInt
import pyxis.uzuki.live.actionsheet.listener.OnActionButtonClickListener
import pyxis.uzuki.live.actionsheet.listener.OnDismissListener
import pyxis.uzuki.live.actionsheet.model.ActionButton
import java.io.Serializable

/**
 * ActionSheet
 * Class: ActionSheetConfig
 * Created by Pyxis on 2017-12-28.
 *
 * Description:
 */
data class ActionSheetConfig(val items: ArrayList<ActionButton>, val cancelButton: ActionButton, val onDismissListener: OnDismissListener?,
                             val onActionButtonClickListener: OnActionButtonClickListener?, val cancelableOnTouchOutside: Boolean) : Serializable {

    class Builder {
        private var items = arrayListOf<ActionButton>()
        private var cancelButton = ActionButton("cancel")
        private var onDismissListener: OnDismissListener? = null
        private var onActionButtonClickListener: OnActionButtonClickListener? = null
        private var cancelable: Boolean = false

        /**
         * add item with Strings, color will set by default (Color.BLACK)
         *
         * @param[items] vararg of title of button
         */
        fun addItem(vararg items: String) = apply { this.items.addAll(items.map { ActionButton(it) }) }

        /**
         * add item with Strings, ColorInt.
         *
         * @param[title] title of button
         * @param[color] ColorInt
         */
        fun addItem(title: String, @ColorInt color: Int) = apply { this.items.add(ActionButton(title, color)) }

        /**
         * add items with ActionButton
         *
         * @param[items] vararg of [ActionButton] object
         */
        fun addItem(vararg items: ActionButton) = apply { this.items.addAll(items) }

        /**
         * set items with ArrayList<ActionButton>
         *
         * @param[items] ArrayList of ActionButton
         */
        fun setItems(items: ArrayList<ActionButton>) = apply { this.items.addAll(items) }

        /**
         * set items with List<ActionButton>
         *
         * @param[items] List of ActionButton
         */
        fun setItems(items: List<ActionButton>) = apply { this.items.addAll(items) }

        /**
         * set cancel button with String, color will set by default (Color.BLACK)
         *
         * @param[title] vararg of title of button
         */
        fun setCancelButton(title: String) = apply { this.cancelButton = ActionButton(title) }

        /**
         * set cancel button with String, color
         *
         * @param[title] title of button
         * @param[color] ColorInt
         */
        fun setCancelButton(title: String, @ColorInt color: Int) = apply { this.cancelButton = ActionButton(title, color) }

        /**
         * set cancel button with ActionButton
         *
         * @param[cancelButton] [ActionButton] object
         */
        fun setCancelButton(cancelButton: ActionButton) = apply { this.cancelButton = cancelButton }

        /**
         * set listener for dismissing
         *
         * @param[listener] [OnDismissListener] object
         */
        fun setOnDismissListener(listener: OnDismissListener) = apply { this.onDismissListener = listener }

        /**
         * set listener for click button
         *
         * @param[listener] [OnActionButtonClickListener] object
         */
        fun setOnActionButtonClickListener(listener: OnActionButtonClickListener) = apply { this.onActionButtonClickListener = listener }

        /**
         * set flag of dismiss when touch outside of ActionSheet
         *
         * @param[cancelable] true if dismiss when touch outside of ActionSheet, otherwise false
         */
        fun setCancelableOnTouchOutside(cancelable: Boolean) = apply { this.cancelable = cancelable }

        /**
         * build ActionSheetConfig.
         *
         * to show ActionSheet, you may call ActionSheet.show(this, getSupportFragmentManager(), config);
         */
        fun build() = ActionSheetConfig(items, cancelButton, onDismissListener, onActionButtonClickListener, cancelable)
    }
}