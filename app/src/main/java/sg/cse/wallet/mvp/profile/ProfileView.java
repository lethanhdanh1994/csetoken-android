package sg.cse.wallet.mvp.profile;

import sg.cse.wallet.base.BaseConnectionView;


public interface ProfileView extends BaseConnectionView {

    void uploadAvatarSuccess(String url);

    void uploadAvatarFails();
}
