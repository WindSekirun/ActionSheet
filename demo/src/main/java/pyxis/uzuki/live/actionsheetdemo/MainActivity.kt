package pyxis.uzuki.live.actionsheetdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import pyxis.uzuki.live.actionsheet.ActionSheet
import pyxis.uzuki.live.actionsheet.config.ActionSheetConfig
import pyxis.uzuki.live.actionsheet.listener.OnActionButtonClickListener
import pyxis.uzuki.live.actionsheet.listener.OnDismissListener

class MainActivity : AppCompatActivity() {
    var actionsheet: ActionSheet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener { showActionSheet() }
    }

    private fun showActionSheet() {
        val config = ActionSheetConfig.Builder()
                .addItem("Select from my Gallery", "Capture from Camera")
                .addItem("Delete Picture", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("Cancel")
                .setOnDismissListener(OnDismissListener { actionSheet, isCancel ->

                })
                .setOnActionButtonClickListener(OnActionButtonClickListener { actionSheet, actionButton, index ->
                    Toast.makeText(this@MainActivity, "clicked %s".format(actionButton.title), Toast.LENGTH_SHORT).show()
                })
                .build()


        actionsheet = ActionSheet.show(this, supportFragmentManager, config)
    }

    override fun onPause() {
        super.onPause()
        actionsheet?.dismiss()
    }
}
