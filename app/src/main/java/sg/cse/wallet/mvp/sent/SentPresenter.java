package sg.cse.wallet.mvp.sent;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentPresenter extends BaseAPI {

    private SentView view;

    public SentPresenter(SentView view) {
        this.view = view;
    }

    /**
     * Get wallet
     */
    public void getWallet() {
        apiInterfaceHeader.getWallet().enqueue(new Callback<WalletRes>() {
            @Override
            public void onResponse(Call<WalletRes> call, Response<WalletRes> response) {
                WalletRes walletRes = null;
                if (response.code() != 200) { // API error
                    try {
                        walletRes = new Gson().fromJson(response.errorBody().string(), WalletRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    walletRes = response.body();
                }

                if (walletRes != null) {
                    if (walletRes.isSuccess()) {
                        view.onGetWalletSuccess(walletRes.getResult().getDocs());
                    } else {
                        view.onGetWalletError(walletRes.getMessage());
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<WalletRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }

    public void getFeeAndPriceUSD(String coin) {
        apiInterfaceHeader.getFeeByCoin(coin).enqueue(new Callback<PriceAndFreeRes>() {
            @Override
            public void onResponse(Call<PriceAndFreeRes> call, Response<PriceAndFreeRes> response) {
                PriceAndFreeRes walletRes = null;
                if (response.code() != 200) { // API error
                    try {
                        walletRes = new Gson().fromJson(response.errorBody().string(), PriceAndFreeRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    walletRes = response.body();
                }

                if (walletRes != null) {
                    if (walletRes.getSuccess()) {
                        view.getPriceAndFeeSuccess(walletRes);
                    } else {
                        view.getPriceAndFeeFails(walletRes.getMessage());
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<PriceAndFreeRes> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                view.onConnectionError();
            }
        });
    }

}