package sg.cse.wallet.mvp.sent_confirm;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.TransReq;
import sg.cse.wallet.model.BaseRes;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.setting.SettingView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentConfirmPresenter  extends BaseAPI

{

    private SentConfirmView view;

    public SentConfirmPresenter(SentConfirmView view) {
        this.view = view;
    }

    public void onConfirmBtnClick(String code, String address,String coin,Double amount){

        TransReq event = new TransReq();
        event.setAddress(address);
        event.setAmount(amount);
        event.setCoinAsset(coin);
        event.setCode2FA(code);

        apiInterfaceHeader.sentTransaction(event).enqueue(new Callback<BaseRes>() {
            @Override
            public void onResponse(Call<BaseRes> call, Response<BaseRes> response) {

                BaseRes walletRes = null;
                if (response.code() != 200) { // API error
                    try {
                        walletRes = new Gson().fromJson(response.errorBody().string(), BaseRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    walletRes = response.body();
                }

                if (walletRes != null) {
                    if (walletRes.getSuccess()) {
                        view.sentSuccess();
                    } else {
                        view.sentFails(walletRes.getMessage());
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<BaseRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }
}
