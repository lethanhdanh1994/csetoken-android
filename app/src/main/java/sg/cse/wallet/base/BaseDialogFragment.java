package sg.cse.wallet.base;

import sg.cse.wallet.dialog.SAlert;

/**
 * Created by thongph on 2/2/18
 * <p>
 * Last modified on 2/2/18
 */

public abstract class BaseDialogFragment extends BaseFragment {

    /**
     * Show error dialog
     *
     * @param message
     */
    public void showAlert(String message) {
        ((BaseDialogActivity) context).showAlert(message);
    }

    /**
     * Show error dialog with action
     *
     * @param message
     * @param positiveText
     * @param negativeText
     * @param onActionClickListener
     */
    public void showAlert(String message, String positiveText, String negativeText,
                          SAlert.OnActionClickListener onActionClickListener) {
        ((BaseDialogActivity) context).showAlert(message, positiveText, negativeText,
                onActionClickListener);
    }

    /**
     * Dismiss error dialog
     */
    public void dismissAlert() {
        ((BaseDialogActivity) context).dismissAlert();
    }

    /**
     * Show loading dialog
     */
    public void showLoading() {
        ((BaseDialogActivity) context).showLoading();
    }

    /**
     * Dismiss loading dialog
     */
    public void dismissLoading() {
        ((BaseDialogActivity) context).dismissLoading();
    }

    /**
     * Dismiss all dialog
     */
    public void dismissAll() {
        ((BaseDialogActivity) context).dismissAll();
    }
}
