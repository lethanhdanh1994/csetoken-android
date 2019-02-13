package sg.cse.wallet.mvp.request_send;

import java.util.List;

import sg.cse.wallet.base.BaseConnectionView;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.WalletRes;

public interface RequestSendView extends BaseConnectionView {
    void onGetWalletSuccess(List<WalletRes.Doc> walletList);
    void onGetWalletError(String message);
    void getPriceAndFeeSuccess(PriceAndFreeRes priceAndFreeRes);
    void getPriceAndFeeFails(String message);
}
