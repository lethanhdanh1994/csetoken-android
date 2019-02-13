package sg.cse.wallet.mvp.active_code;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.ActiveCodeReq;
import sg.cse.wallet.model.ActiveCodeRes;
import sg.cse.wallet.model.BaseRes;
import sg.cse.wallet.model.Remove2faRes;
import sg.cse.wallet.model.signin.SignInRes;

public class ActiveCodePresenter extends BaseAPI {

    private ActiveCodeView view;

    public ActiveCodePresenter(ActiveCodeView view) {
        this.view = view;
    }
    public void verityCodeEmail(String code,String email) {
        ActiveCodeReq req = new ActiveCodeReq();

        req.setEmail(email);
        req.setCodeVerify(code);


        apiInterfaceHeader.verityEmail(req).enqueue(new Callback<BaseRes>() {
            @Override
            public void onResponse(Call<BaseRes> call, Response<BaseRes> response) {
                BaseRes res = response.body();

                if (response.code() != 200) {
                    try {
                        res = new Gson().fromJson(response.errorBody().string(), ActiveCodeRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    res = response.body();
                }
                if(res!=null){
                    if(res.getSuccess()){
                        view.onActiveCodeSubmitSuccess();
                    }else {
                        view.onActiveCodeSubmitFails(res.getMessage());
                    }
                }// API error
                else {
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
    public void resendCodeEmail(String code) {
        final ActiveCodeReq req = new ActiveCodeReq();
        req.setEmail(code);
        apiInterfaceHeader.reSendVerifyEmail(req).enqueue(new Callback<BaseRes>() {
            @Override
            public void onResponse(Call<BaseRes> call, Response<BaseRes> response) {

                BaseRes res = response.body();

                if (response.code() != 200) {
                    try {
                        res = new Gson().fromJson(response.errorBody().string(), BaseRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    res = response.body();
                }
                if(res!=null){
                    if(res.getSuccess()){
                        view.onActiveCodeResendSuccess();
                    }else {
                        view.onActiveCodeResendFails();
                    }
                }else
                    view.onConnectionError();

                }// API error



            @Override
            public void onFailure(Call<BaseRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }
}
