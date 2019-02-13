package sg.cse.wallet.mvp.google_auth_enable;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.Confirm2faReq;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.cse.wallet.model.signin.InfoRes;

public class GoogleAuthEnablePresenter extends BaseAPI {

    private GoogleAuthEnableView view;

    public GoogleAuthEnablePresenter(GoogleAuthEnableView view) {
        this.view = view;
    }

    public void enableGoogleAuth(String code) {
        Confirm2faReq event = new Confirm2faReq();
        event.setCode2FA(code);
        apiInterfaceHeader.googleAuthConfirm(event).enqueue(new Callback<Generate2faRes>() {
            @Override
            public void onResponse(Call<Generate2faRes> call, Response<Generate2faRes> response) {
                Generate2faRes infoRes = null;
                if (response.code() != 200) {
                    try {
                        infoRes = new Gson().fromJson(response.errorBody().string(), Generate2faRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                infoRes = response.body();
                 }
                if (infoRes != null) {
                    if (infoRes.getSuccess()) {
                        view.onGoogleConfirmSuccess();
                    } else {
                        view.onGoogleConfirmFails();
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<Generate2faRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }

}
