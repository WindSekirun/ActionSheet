package pyxis.uzuki.live.actionsheet.listener;

import java.io.Serializable;

import pyxis.uzuki.live.actionsheet.ActionSheet;

/**
 * ActionSheet
 * Class: OnDismissListener
 * Created by Pyxis on 2017-12-28.
 * <p>
 * Description:
 */

public interface OnDismissListener extends Serializable {
    void onDismiss(ActionSheet actionSheet, boolean isCancel);
}
