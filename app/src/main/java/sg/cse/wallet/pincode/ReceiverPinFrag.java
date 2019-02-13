package sg.cse.wallet.pincode;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.ReceiverPinAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.transection.TransectionPresenter;
import sg.cse.wallet.mvp.transection.TransectionView;

public class ReceiverPinFrag extends BaseDialogFragment implements TransectionView {
    @BindView(R.id.activity_main_card_stack_view)
    RecyclerView recyclerView;
    private List<WalletRes.Doc> listCardStackViewCoinWallet;

    @Override
    public int setView() {
        return R.layout.frag_receiver_pin;
    }

    @Override
    public void initView() {
        createView();
    }

    private void createView() {
        listCardStackViewCoinWallet = new ArrayList<>();
        //Config RECYCLERVIEW
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        //PAGE
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {
        TransectionPresenter transectionPresenter = new TransectionPresenter(this);
        showLoading();
        transectionPresenter.getWallet();

    }

    @Override
    public void onGetWalletSuccess(List<WalletRes.Doc> walletList) {
        dismissAll();
        listCardStackViewCoinWallet.clear();
        listCardStackViewCoinWallet.addAll(walletList);
        //Set adapter
        recyclerView.setAdapter(new ReceiverPinAdapter(listCardStackViewCoinWallet));
    }

    @Override
    public void onGetWalletError(String message) {
        dismissAll();
    }

    @Override
    public void onGetTransactionsSuccess(TransactionsHistoryRes.Result result) {

    }

    @Override
    public void onGetTransactionsError(String message) {

    }

    @Override
    public void onConnectionError() {
        dismissAll();
    }
}
