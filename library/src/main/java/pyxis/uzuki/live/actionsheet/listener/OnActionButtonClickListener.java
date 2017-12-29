package pyxis.uzuki.live.actionsheet.listener;

import java.io.Serializable;

import pyxis.uzuki.live.actionsheet.ActionSheet;
import pyxis.uzuki.live.actionsheet.model.ActionButton;

/**
 * ActionSheet
 * Class: OnActionButtonClickListener
 * Created by Pyxis on 2017-12-28.
 * <p>
 * Description:
 */

public interface OnActionButtonClickListener extends Serializable {
    void onActionButtonClick(ActionSheet actionSheet, ActionButton actionButton, int index);
}
