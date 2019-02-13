package sg.cse.wallet.mvp.changepassword;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.ChangePassReq;
import sg.cse.wallet.model.changepass.ChangePassRes;
import sg.cse.wallet.model.signin.InfoRes;

public class ChangePasswordPresenter extends BaseAPI {

    private ChangePasswordView view;

    public ChangePasswordPresenter(ChangePasswordView view) {
        this.view = view;
    }

    public void validateForm(String oldPassword, String newPassword, String confirmNewPassword){
        if (oldPassword.trim().isEmpty()){
            view.onOldPasswordEmpty();
        } else if (newPassword.trim().isEmpty()){
            view.onNewPasswordEmpty();
        } else if (newPassword.length() < 6 || newPassword.length() > 35) {
            view.onNewPasswordLengthInvalid();
        } else if (confirmNewPassword.trim().isEmpty()) {
            view.onConfirmNewPasswordEmpty();
        } else if (confirmNewPassword.length() < 6 || confirmNewPassword.length() > 35) {
            view.onConfirmNewPasswordLengthInvalid();
        } else if (!newPassword.equals(confirmNewPassword)) {
            view.onPasswordDoesNotMatch();
        } else {
            ChangePassReq changePassReq = new ChangePassReq();
            changePassReq.setOldPassword(oldPassword);
            changePassReq.setNewPassword(newPassword);
            changePassword(changePassReq);

        }
    }

    private void changePassword(ChangePassReq changePassReq){
        apiInterfaceHeader.changePass(changePassReq).enqueue(new Callback<ChangePassRes>() {
            @Override
            public void onResponse(Call<ChangePassRes> call, Response<ChangePassRes> response) {
                ChangePassRes changePassRes = null;
//                if (response.code() != 200) {
//                    try {
//                        changePassRes = new Gson().fromJson(response.errorBody().string(), ChangePassRes.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else
//                {
                    changePassRes = response.body();
//                }
                if (changePassRes != null){
                    if (changePassRes.isSuccess()){
                        view.onChangePassworrdSuccess(changePassRes);
                    } else {
                        view.onIncorrectOldPassword();
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<ChangePassRes> call, Throwable t) {
                call.cancel();
                view.onConnectionError();
            }
        });
    }
}
