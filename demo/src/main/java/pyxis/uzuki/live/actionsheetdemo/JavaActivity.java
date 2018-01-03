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
    private ActionSheet actionSheet = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> showActionSheet());
    }

    private void showActionSheet() {
        ActionSheetConfig config = new ActionSheetConfig.Builder()
                .addItem("Select from my Gallery", "Capture from Camera")
                .addItem("Delete Picture", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("Cancel")
                .setOnDismissListener((OnDismissListener) (actionSheet, isCancel) -> {

                })
                .setOnActionButtonClickListener((OnActionButtonClickListener) (actionSheet, actionButton, index) -> {
                    Toast.makeText(this, String.format("clicked %s", actionButton.getTitle()), Toast.LENGTH_SHORT).show();
                })
                .build();

        actionSheet = ActionSheet.show(this, getSupportFragmentManager(), config);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (actionSheet != null) {
            actionSheet.dismiss();
        }
    }
}
