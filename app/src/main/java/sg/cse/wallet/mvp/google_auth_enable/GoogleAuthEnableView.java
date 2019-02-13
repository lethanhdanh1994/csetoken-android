package sg.cse.wallet.mvp.google_auth_enable;

import sg.cse.wallet.base.BaseConnectionView;

public interface GoogleAuthEnableView extends BaseConnectionView {
    void onGoogleConfirmSuccess();
    void onGoogleConfirmFails();
}
