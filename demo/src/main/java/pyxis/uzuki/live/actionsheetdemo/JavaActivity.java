package pyxis.uzuki.live.actionsheetdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import pyxis.uzuki.live.actionsheet.ActionSheet;
import pyxis.uzuki.live.actionsheet.config.ActionSheetConfig;
import pyxis.uzuki.live.actionsheet.listener.OnActionButtonClickListener;
import pyxis.uzuki.live.actionsheet.listener.OnDismissListener;

/**
 * ActionSheet
 * Class: JavaActivity
 * Created by Pyxis on 2017-12-29.
 * <p>
 * Description:
 */

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> showActionSheet());
    }

    private void showActionSheet() {
        ActionSheetConfig config = new ActionSheetConfig.Builder()
                .addItem("내 앨범에서 선택", "카메라로 촬영 등록")
                .addItem("등록 사진 삭제", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("취소")
                .setOnDismissListener((OnDismissListener) (actionSheet, isCancel) -> {

                })
                .setOnActionButtonClickListener((OnActionButtonClickListener) (actionSheet, actionButton, index) -> {
                    Toast.makeText(this, String.format("clicked %s", actionButton.getTitle()), Toast.LENGTH_SHORT).show();
                })
                .build();

        ActionSheet.show(this, getSupportFragmentManager(), config);
    }
}
