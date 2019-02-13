package sg.cse.wallet.mvp.transection.transection_receiver;


import com.google.gson.Gson;

import java.io.IOException;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransectionReceiverPresenter extends BaseAPI {

    private TransectionReceiverView view;

    public TransectionReceiverPresenter(TransectionReceiverView view) {
        this.view = view;
    }

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
