package sg.cse.wallet.mvp.sent;

import java.util.List;

import sg.cse.wallet.base.BaseConnectionView;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;

public  interface SentView extends BaseConnectionView{

    void onGetWalletSuccess(List<WalletRes.Doc> walletList);
    void onGetWalletError(String message);
    void getPriceAndFeeSuccess(PriceAndFreeRes priceAndFreeRes);
    void getPriceAndFeeFails(String message);
}
