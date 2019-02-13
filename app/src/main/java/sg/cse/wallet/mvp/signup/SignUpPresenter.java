package sg.cse.wallet.mvp.signup;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.SignUpReq;
import sg.cse.wallet.helper.ValidateHelper;
import sg.cse.wallet.model.signin.SignInRes;
import sg.cse.wallet.model.signup.SignUpRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class SignUpPresenter extends BaseAPI {

    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    /**
     * Validate sign up form
     *
     * @param userName
     * @param email
     * @param password
     * @param confirmPassword
     */
    public void validateForm(String userName, String email, String password, String confirmPassword) {
        if (userName.trim().isEmpty()) {
            view.onUserNameEmpty();
        } else if (userName.length() < 6 || userName.length() > 35) {
            view.onUserNameLengthInvalid();
        } else if (email.trim().isEmpty()) {
            view.onEmailEmpty();
        } else if (!ValidateHelper.isEmailValid(email)) {
            view.onEmailInValid();
        } else if (password.trim().isEmpty()) {
            view.onPasswordEmpty();
        } else if (password.length() < 6 || password.length() > 35) {
            view.onPasswordLengthInvalid();
        } else if (confirmPassword.trim().isEmpty()) {
            view.onConfirmPasswordEmpty();
        } else if (confirmPassword.length() < 6 || confirmPassword.length() > 35) {
            view.onConfirmPasswordLengthInvalid();
        } else if (!password.equals(confirmPassword)) {
            view.onPasswordDoesNotMatch();
        } else {
            SignUpReq signUpReq = new SignUpReq();
            signUpReq.setEmail(email);
            signUpReq.setUsername(userName);
            signUpReq.setPassword(password);
            register(signUpReq);
        }
    }

    /**
     * Register
     *
     * @param signUpReq
     */
    private void register(SignUpReq signUpReq) {
        apiInterface.register(signUpReq)
                .enqueue(new Callback<SignInRes>() {
                    @Override
                    public void onResponse(Call<SignInRes> call, Response<SignInRes> response) {
                        SignInRes signInRes = null;
                        if (response.code() != 200) {
                            try {
                                signInRes = new Gson().fromJson(response.errorBody().string(), SignInRes.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            signInRes = response.body();
                        }
                        if (signInRes != null) {
                            if (signInRes.isSuccess()) {
                                view.onSignUpSuccess(signInRes);
                            } else {
                                switch (signInRes.getMessage()) {
                                    case "EMAIL_EXIST":
                                        view.onEmailExist();
                                        break;
                                    case "USERNAME_EXIST":
                                        view.onUserNameExist();
                                        break;
                                }
                            }
                        } else {
                            view.onConnectionError();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInRes> call, Throwable t) {
                        call.cancel();
                        view.onConnectionError();
                    }
                });
    }
}
