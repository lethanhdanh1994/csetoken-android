package sg.cse.wallet.mvp.signup;

import sg.cse.wallet.model.signin.SignInRes;
import sg.cse.wallet.model.signup.SignUpRes;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public interface SignUpView {
    void onUserNameEmpty();

    void onUserNameLengthInvalid();

    void onEmailEmpty();

    void onEmailInValid();

    void onPasswordEmpty();

    void onPasswordLengthInvalid();

    void onConfirmPasswordEmpty();

    void onConfirmPasswordLengthInvalid();

    void onPasswordDoesNotMatch();

    void onSignUpSuccess(SignInRes signInRes);

    void onEmailExist();

    void onUserNameExist();

    void onConnectionError();
}
