package sg.cse.wallet.mvp.sent_confirm;

import sg.cse.wallet.base.BaseConnectionView;

public interface SentConfirmView extends BaseConnectionView {
    void sentSuccess();
    void sentFails(String mess);
}
