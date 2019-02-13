package sg.cse.wallet.mvp.setting;

import java.util.List;

import sg.cse.wallet.base.BaseConnectionView;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;

public interface SettingView  extends BaseConnectionView{
        void onGoogleAuthSuccess(Generate2faRes.Result result);
        void onGoogleAuthFails(String message);
        }