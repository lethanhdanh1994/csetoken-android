package sg.cse.wallet.mvp.transection.transection_receiver;

import sg.cse.wallet.base.BaseConnectionView;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;

public interface TransectionReceiverView extends BaseConnectionView {
    void onGetTransactionsSuccess(TransactionsHistoryRes.Result result);
    void onGetTransactionsError(String message);
}
