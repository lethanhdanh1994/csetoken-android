package sg.cse.wallet.mvp.dashboard;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import sg.cse.wallet.api.BaseAPI;
import sg.cse.wallet.api.request.ChartReq;
import sg.cse.wallet.model.ChartRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class WalletPresenter extends BaseAPI {

    private WalletView view;

    public WalletPresenter(WalletView view) {
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
                        view.onGetWalletSuccess(walletRes.getResult().getDocs(),walletRes.getResult().getTotalEstimatedUSDT());
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
    public void getTransactionsHistory(String walletAddress) {
        apiInterfaceHeader.getTransactionsHistory(walletAddress,1,10,"ALL").enqueue(new Callback<TransactionsHistoryRes>() {
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
    public void getChart(String coin, final String time) {
        ChartReq event = new ChartReq();
        event.setCoinAsset(coin);
        event.setTime(time);

        apiInterfaceHeader.getDataChart(event).enqueue(new Callback<ChartRes>() {
            @Override
            public void onResponse(Call<ChartRes> call, Response<ChartRes> res) {
                ChartRes chartRes = res.body();

                if(chartRes.getSuccess()){
                    view.onGetDataChartSuccess((ArrayList<ChartRes.Result>) chartRes.getResult(),time);
                }else {
                    view.onGetDataChartFails();
                }
            }

            @Override
            public void onFailure(Call<ChartRes> call, Throwable t) {
                call.cancel();
                view.onConnectionError();
            }
        });
    }
}
