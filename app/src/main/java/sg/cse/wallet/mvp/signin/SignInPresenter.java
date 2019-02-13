package sg.cse.wallet.mvp.signin;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.SignInReq;
import sg.cse.wallet.helper.ValidateHelper;
import sg.cse.wallet.model.signin.InfoRes;
import sg.cse.wallet.model.signin.SignInRes;
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
public class SignInPresenter extends BaseAPI {

    private static final String TAG = "SignInPresenter";

    private SignInView view;

    public SignInPresenter(SignInView view) {
        this.view = view;
    }

    /**
     * Valid sign in form
     *
     * @param email
     * @param password
     */
    public void validForm(String email, String password) {
        if (email.trim().isEmpty()) {
            view.onEmailEmpty();
        } else if (!ValidateHelper.isEmailValid(email)) {
            view.onEmailInvalid();
        } else if (password.trim().isEmpty()) {
            view.onPasswordEmpty();
        } else if (password.length() < 6 || password.length() > 35) {
            view.onPasswordLengthInvalid();
        } else {
            SignInReq signInReq = new SignInReq();
            signInReq.setEmail(email);
            signInReq.setPassword(password);
            signIn(signInReq);
        }
    }


    /**
     * Sign in
     *
     * @param signInReq
     */
    private void signIn(final SignInReq signInReq) {
        apiInterface.login(signInReq).enqueue(new Callback<SignInRes>() {
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
                        view.onSignInSuccess(signInRes);
                    } else {
                        switch (signInRes.getMessage()) {
                            case "EMAIL_DOES_NOT_EXIST":
                                view.onEmailDoesNotExist();
                                break;
                            case "INCORRECT_PASSWORD":
                                view.onIncorrectPassword();
                                break;
                            case "YOUR_ACCOUNT_IS_BLOCKED":
                                view.onAccountIsBlocked();
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

    public void getInfo() {
        apiInterfaceHeader.getProfile().enqueue(new Callback<InfoRes>() {
            @Override
            public void onResponse(Call<InfoRes> call, Response<InfoRes> response) {
                InfoRes infoRes = null;
//                if (response.code() != 200) {
//                    try {
//                        infoRes = new Gson().fromJson(response.errorBody().string(), InfoRes.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
                    infoRes = response.body();
               // }
                if (infoRes != null) {
                    if (infoRes.getSuccess()) {
                    view.onInfoSuccess(infoRes);
                    } else {
                       view.onInfoFails();
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<InfoRes> call, Throwable t) {
                call.cancel();
                view.onConnectionError();
            }
        });
    }
}
