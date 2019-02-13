package sg.cse.wallet.mvp.changepassword;

import sg.cse.wallet.model.changepass.ChangePassRes;
import sg.cse.wallet.model.signin.InfoRes;

public interface ChangePasswordView {
    void onOldPasswordEmpty();

    void onIncorrectOldPassword();

    void onNewPasswordEmpty();

    void onNewPasswordLengthInvalid();

    void onConfirmNewPasswordEmpty();

    void onConfirmNewPasswordLengthInvalid();

    void onPasswordDoesNotMatch();

    void onChangePassworrdSuccess(ChangePassRes body);

    void onConnectionError();
}
