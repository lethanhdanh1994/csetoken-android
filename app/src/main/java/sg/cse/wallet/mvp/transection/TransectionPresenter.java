package sg.cse.wallet.mvp.transection;

import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.dashboard.WalletView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransectionPresenter extends BaseAPI {

    private TransectionView view;

    public TransectionPresenter(TransectionView view) {
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

    /**
     * Get transactions history
     *
     * @param walletAddress
     */
    public void getTransactionsHistory(String walletAddress, String typeWallet) {
        apiInterfaceHeader.getTransactionsHistory(walletAddress, 1, 10, typeWallet).enqueue(new Callback<TransactionsHistoryRes>() {
            @Override
            public void onResponse(Call<TransactionsHistoryRes> call, Response<TransactionsHistoryRes> response) {
                TransactionsHistoryRes tranRes = null;
                if (response.code() != 200) {
                    try {
                        tranRes = new Gson().fromJson(response.errorBody().string(), TransactionsHistoryRes.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    tranRes = response.body();
                }
                if (tranRes != null) {
                    if (tranRes.getSuccess()) {
                        view.onGetTransactionsSuccess(tranRes.getResult());
                    } else {
                        view.onGetTransactionsError(tranRes.getMessage());
                    }
                } else {
                    view.onConnectionError();
                }
            }

            @Override
            public void onFailure(Call<TransactionsHistoryRes> call, Throwable t) {
                call.cancel();
                view.onConnectionError();
            }
        });
    }
}