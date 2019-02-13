package sg.cse.wallet.mvp.setting;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPresenter extends BaseAPI {

    private SettingView view;

    public SettingPresenter(SettingView view) {
        this.view = view;
    }

    /**
     * Get wallet
     */
    public void enableGoogleAuth() {

        apiInterfaceHeader.googleAuthEnable().enqueue(new Callback<Generate2faRes>() {
            @Override
            public void onResponse(Call<Generate2faRes> call, Response<Generate2faRes> response) {
                Generate2faRes.Result result = null;
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                       // result = response.body().getResult();
                        view.onGoogleAuthSuccess(result);
                    } else {
                        view.onGoogleAuthFails(response.message());
                    }
                }// API error
                else {
                    view.onGoogleAuthFails(response.message());
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
