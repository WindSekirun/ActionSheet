package pyxis.uzuki.live.actionsheet

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import pyxis.uzuki.live.actionsheet.config.ActionSheetConfig
import pyxis.uzuki.live.actionsheet.model.ActionButton
import pyxis.uzuki.live.actionsheet.utils.*
import pyxis.uzuki.live.richutilskt.utils.dip2px
import pyxis.uzuki.live.richutilskt.utils.hideKeyboard


/**
 * ActionSheet
 * Class: ActionSheet
 * Created by Pyxis on 2017-12-28.
 *
 * Description:
 */
class ActionSheet : Fragment(), View.OnClickListener {
    private lateinit var mView: View
    private lateinit var mBg: View
    private lateinit var mPanel: LinearLayout
    private lateinit var mGroup: ViewGroup
    private lateinit var sheetConfig: ActionSheetConfig

    private var isCancel = true
    private var mDismissed = true

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putBoolean(Constants.EXTRA_DISMISSED, mDismissed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDismissed = savedInstanceState?.getBoolean(Constants.EXTRA_DISMISSED) ?: false
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sheetConfig = arguments.getSerializable(Constants.SHEET_CONFIG) as ActionSheetConfig

        activity.hideKeyboard()

        mView = createView()
        mGroup = activity.window.decorView as ViewGroup
        createItems()

        mGroup.addView(mView)
        mBg.startAnimation(createAlphaInAnimation())
        mPanel.startAnimation(createTranslationInAnimation())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        mPanel.startAnimation(createTranslationOutAnimation())
        mView.postDelayed({ mGroup.removeView(mView) }, Constants.ALPHA_DURATION)
        sheetConfig.onDismissListener?.onDismiss(this, isCancel)
        super.onDestroyView()
    }

    /**
     * show ActionSheet
     */
    fun show(manager: FragmentManager) {
        if (!mDismissed || manager.isDestroyed) {
            return
        }
        mDismissed = false
        Handler().post {
            val transaction = manager.beginTransaction()
            transaction.add(this@ActionSheet, "ActionSheet")
            transaction.addToBackStack(null)
            transaction.commitAllowingStateLoss()
        }
    }

    /**
     * hide ActionSheet
     */
    fun dismiss() {
        if (mDismissed) {
            return
        }
        mDismissed = true
        Handler().post({
            fragmentManager.popBackStack()
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(this@ActionSheet)
            transaction.commitAllowingStateLoss()
        })
    }

    private fun createView(): View {
        val parent = FrameLayout(activity)
        parent.layoutParams = createFrameParams()
        mBg = View(activity)
        mBg.layoutParams = createFrameParams()
        mBg.setBackgroundColor(Color.argb(19, 0, 0, 0))
        mBg.id = Constants.BG_VIEW_ID
        mBg.setOnClickListener(this)

        mPanel = LinearLayout(activity)
        val params = createFrameParams(height = FrameParams.WRAP_CONTENT)
        params.gravity = Gravity.BOTTOM
        mPanel.layoutParams = params
        mPanel.orientation = LinearLayout.VERTICAL
        parent.setPadding(0, 0, 0, activity.getNavBarHeight())
        parent.addView(mBg)
        parent.addView(mPanel)
        return parent
    }

    private fun createItems() {
        for (i in 0 until sheetConfig.items.size) {
            val button = createButton(sheetConfig.items[i])
            button.id = Constants.CANCEL_BUTTON_ID + i + 1
            button.background = getButtonBackground(i)

            val divider = View(activity)
            divider.setBackgroundColor(Color.parseColor("#e4e4e4"))

            val dividerParam = createLinearParams(height = activity.dip2px(1))

            val params = createLinearParams(height = activity.dip2px(50))
            mPanel.addView(button, params)

            if (i != sheetConfig.items.size - 1) {
                mPanel.addView(divider, dividerParam)
            }
        }

        val button = createButton(sheetConfig.cancelButton)
        button.id = Constants.CANCEL_BUTTON_ID
        button.background = activity.getDrawableRes(R.drawable.bg_popup_single)

        val params = createLinearParams(height = activity.dip2px(50))
        val dip20 = activity.dip2px(20)
        params.topMargin = activity.dip2px(10)
        mPanel.addView(button, params)
        mPanel.background = ColorDrawable(Color.TRANSPARENT)
        mPanel.setPadding(dip20, dip20, dip20, dip20)
    }

    private fun createButton(actionButton: ActionButton) = Button(activity, null, android.R.attr.borderlessButtonStyle).apply {
        setOnClickListener(this@ActionSheet)
        text = actionButton.title
        setAllCaps(false)
        setTextColor(actionButton.color)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, activity.dip2px(16).toFloat())
    }

    private fun getButtonBackground(i: Int): Drawable = when (sheetConfig.items.size) {
        1 -> activity.getDrawableRes(R.drawable.bg_popup_single)
        2 -> when (i) {
            0 -> activity.getDrawableRes(R.drawable.bg_popup_top)
            1 -> activity.getDrawableRes(R.drawable.bg_popup_bottom)
            else -> activity.getDrawableRes(R.drawable.bg_popup_bottom)
        }
        else -> {
            when (i) {
                0 -> activity.getDrawableRes(R.drawable.bg_popup_top)
                (sheetConfig.items.size - 1) -> activity.getDrawableRes(R.drawable.bg_popup_bottom)
                else -> activity.getDrawableRes(R.drawable.bg_popup_middle)
            }
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id ?: 0
        if (id == Constants.BG_VIEW_ID && !sheetConfig.cancelableOnTouchOutside) {
            return
        }

        dismiss()
        if (id != Constants.CANCEL_BUTTON_ID && id != Constants.BG_VIEW_ID) {
            if (sheetConfig.onActionButtonClickListener != null) {
                val index = (id - Constants.CANCEL_BUTTON_ID - 1)
                val actionButton = sheetConfig.items[index]
                sheetConfig.onActionButtonClickListener?.onActionButtonClick(this, actionButton, index)
            }
            isCancel = false
        }
    }

    companion object {

        @JvmStatic
        fun show(context: Context, fragmentManager: FragmentManager, sheetConfig: ActionSheetConfig): ActionSheet {
            val bundle = Bundle()
            bundle.putSerializable(Constants.SHEET_CONFIG, sheetConfig)

            val actionSheet = Fragment.instantiate(context, ActionSheet::class.java.name, bundle) as ActionSheet
            actionSheet.show(fragmentManager)
            return actionSheet
        }

    }
}