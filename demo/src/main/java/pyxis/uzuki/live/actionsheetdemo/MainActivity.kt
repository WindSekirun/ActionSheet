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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener { showActionSheet() }
    }

    private fun showActionSheet() {
        val config = ActionSheetConfig.Builder()
                .addItem("내 앨범에서 선택", "카메라로 촬영 등록")
                .addItem("등록 사진 삭제", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("취소")
                .setOnDismissListener(OnDismissListener { actionSheet, isCancel ->

                })
                .setOnActionButtonClickListener(OnActionButtonClickListener { actionSheet, actionButton, index ->
                    Toast.makeText(this@MainActivity, "clicked %s".format(actionButton.title), Toast.LENGTH_SHORT).show()
                })
                .build()

        ActionSheet.show(this, supportFragmentManager, config)
    }
}
