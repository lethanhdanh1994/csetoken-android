package sg.cse.wallet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.dialog.SLoading;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public abstract class BaseDialogActivity extends BaseActivity {

    private SLoading sLoading;
    private SAlert sAlert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sLoading = new SLoading(context);
        sAlert = new SAlert(context);
        init();
    }

    /**
     * Show loading
     */
    public void showLoading() {
        dismissAll();
        sLoading.show();
    }

    /**
     * Dismiss loading
     */
    public void dismissLoading() {
        if (sLoading != null)
            sLoading.dismiss();
    }

    /**
     * Show alert
     *
     * @param message
     */
    public void showAlert(String message) {
        dismissAll();
        sAlert.show(message);
    }

    /**
     * Show alert with custom action
     *
     * @param message
     * @param positiveText
     * @param negativeText
     * @param listener
     */
    public void showAlert(String message, String positiveText, String negativeText, SAlert.OnActionClickListener listener) {
        dismissAll();
        sAlert.show(message, positiveText, negativeText, listener);
    }

    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        if (sAlert != null)
            sAlert.dismiss();
    }

    /**
     * Dismiss all dialog
     */
    public void dismissAll() {
        dismissLoading();
        dismissAlert();
    }
}
