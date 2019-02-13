package sg.cse.wallet.mvp.google_auth_disable;

import sg.cse.wallet.base.BaseConnectionView;

public interface GoogleAuthDisableView extends BaseConnectionView {
    void onGoogleRemoveSuccess();
    void onGoogleRemoveFails();
}
