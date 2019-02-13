package sg.cse.wallet.api;


import retrofit2.http.PUT;
import sg.cse.wallet.api.request.ActiveCodeReq;
import sg.cse.wallet.api.request.AvatarReq;
import sg.cse.wallet.api.request.ChangePassReq;
import sg.cse.wallet.api.request.Confirm2faReq;
import sg.cse.wallet.api.request.SignInReq;
import sg.cse.wallet.api.request.SignUpReq;
import sg.cse.wallet.api.request.TransReq;
import sg.cse.wallet.model.ActiveCodeRes;
import sg.cse.wallet.model.BaseRes;

import sg.cse.wallet.api.request.ChartReq;
import sg.cse.wallet.api.request.SignInReq;
import sg.cse.wallet.api.request.SignUpReq;
import sg.cse.wallet.model.ChartRes;

import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.Remove2faRes;
import sg.cse.wallet.model.changepass.ChangePassRes;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import sg.cse.wallet.model.signin.InfoRes;
import sg.cse.wallet.model.signin.SignInRes;
import sg.cse.wallet.model.signup.SignUpRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public interface APIInterface {

    @POST("auth/login")
    Call<SignInRes> login(@Body SignInReq signInReq);

    @POST("auth/register")
    Call<SignInRes> register(@Body SignUpReq signUpReq);

    @GET("wallet")
    Call<WalletRes> getWallet();

    @GET("wallet/getTransactionHistories/{input}")
    Call<TransactionsHistoryRes> getTransactionsHistory(
            @Path("input") String walletAddress,
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("type") String type);

    @POST("auth/generate2fa")
    Call<Generate2faRes> googleAuthEnable();

    @FormUrlEncoded
    @POST("auth/confirm2Fa")
    Call<Generate2faRes> googleAuthConfirm(
            @Body Confirm2faReq code
    );

    @FormUrlEncoded
    @POST("auth/remove2fa")
    Call<Remove2faRes> googleAuthRemove(
            @Body Confirm2faReq code
            );



    @POST("wallet/transfer")
    Call<BaseRes> sentTransaction(
            @Body TransReq trans
    );

    @GET("auth/me")
    Call<InfoRes> getProfile();


    @PUT("auth/updateAvatarByUserId")
    Call<BaseRes> uploadAvatar(@Body AvatarReq url);


    @POST("pricechart")
    Call<ChartRes> getDataChart(@Body ChartReq req );

    @GET("price/{coin}")
    Call<PriceAndFreeRes> getFeeByCoin(@Path("coin") String coin );


    @POST("auth/verifyEmail")
    Call<BaseRes> verityEmail(
            @Body ActiveCodeReq req
    );

    @POST("auth/reSendVerifyEmail")
    Call<BaseRes> reSendVerifyEmail(
            @Body ActiveCodeReq req
    );

    @PUT("auth/updatePasswordByUserId")
    Call<ChangePassRes> changePass(@Body ChangePassReq changePassReq);
}
