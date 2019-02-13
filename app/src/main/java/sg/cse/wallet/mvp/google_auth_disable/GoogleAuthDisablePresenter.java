package sg.cse.wallet.mvp.google_auth_disable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.Confirm2faReq;
import sg.cse.wallet.model.Remove2faRes;
import sg.cse.wallet.model.generate2fa.Generate2faRes;

public class GoogleAuthDisablePresenter extends BaseAPI {

    private GoogleAuthDisableView view;

    public GoogleAuthDisablePresenter(GoogleAuthDisableView view) {
        this.view = view;
    }

    public void removeGoogleAuth(String code) {
        Confirm2faReq event = new Confirm2faReq();
        event.setCode2FA(code);
        apiInterfaceHeader.googleAuthRemove(event).enqueue(new Callback<Remove2faRes>() {
            @Override
            public void onResponse(Call<Remove2faRes> call, Response<Remove2faRes> response) {
                if (response.isSuccessful()) {
                    if(response.body().getSuccess()){
                        view.onGoogleRemoveSuccess();
                    }else {
                        view.onGoogleRemoveFails();
                    }
                }// API error
                else {
                    view.onGoogleRemoveFails();
                }
            }

            @Override
            public void onFailure(Call<Remove2faRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }

}
