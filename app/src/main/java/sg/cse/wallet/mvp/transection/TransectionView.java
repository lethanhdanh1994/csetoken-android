package sg.cse.wallet.mvp.transection;

import java.util.List;

import sg.cse.wallet.base.BaseConnectionView;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;

public  interface TransectionView extends BaseConnectionView{

    void onGetWalletSuccess(List<WalletRes.Doc> walletList);
    void onGetWalletError(String message);
    void onGetTransactionsSuccess(TransactionsHistoryRes.Result result);
    void onGetTransactionsError(String message);


}
