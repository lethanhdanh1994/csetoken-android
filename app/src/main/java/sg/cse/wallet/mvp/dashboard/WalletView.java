package sg.cse.wallet.mvp.dashboard;

import java.util.ArrayList;
import java.util.List;

import sg.cse.wallet.model.ChartRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public interface WalletView {
    void onGetWalletSuccess(List<WalletRes.Doc> walletList,Double totalAmountChart);

    void onGetWalletError(String message);

    void onGetTransactionsSuccess(TransactionsHistoryRes.Result result);

    void onGetTransactionsError(String message);

    void onConnectionError();

    void onGetDataChartSuccess(ArrayList<ChartRes.Result> listItem, String type);
    void onGetDataChartFails();
}
