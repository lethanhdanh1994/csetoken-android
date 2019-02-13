package sg.cse.wallet.mvp.active_code;

import sg.cse.wallet.base.BaseConnectionView;

public interface ActiveCodeView extends BaseConnectionView {
    void onActiveCodeSubmitSuccess();
    void onActiveCodeSubmitFails(String error);


    void onActiveCodeResendSuccess();
    void onActiveCodeResendFails();
}
