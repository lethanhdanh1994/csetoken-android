package sg.cse.wallet.mvp.signin;

import sg.cse.wallet.model.signin.InfoRes;
import sg.cse.wallet.model.signin.SignInRes;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public interface SignInView {
    void onEmailEmpty();

    void onEmailInvalid();

    void onPasswordEmpty();

    void onSignInSuccess(SignInRes body);

    void onEmailDoesNotExist();

    void onIncorrectPassword();

    void onAccountIsBlocked();

    void onPasswordLengthInvalid();

    void onConnectionError();

    void onInfoSuccess(InfoRes body);
    void onInfoFails();
}
