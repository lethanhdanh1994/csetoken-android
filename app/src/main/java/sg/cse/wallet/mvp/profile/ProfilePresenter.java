package sg.cse.wallet.mvp.profile;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.AvatarReq;
import sg.cse.wallet.model.BaseRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.sent.SentView;

public class ProfilePresenter extends BaseAPI {

    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }
    public void uploadAvatar(final String url) {
        AvatarReq avatarReq = new AvatarReq();
        avatarReq.setAvatarUrl(url);

        apiInterfaceHeader.uploadAvatar(avatarReq).enqueue(new Callback<BaseRes>() {
            @Override
            public void onResponse(Call<BaseRes> call, Response<BaseRes> response) {

            if(response.isSuccessful()){
                view.uploadAvatarSuccess(url);
            }else {
                view.uploadAvatarFails();
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
