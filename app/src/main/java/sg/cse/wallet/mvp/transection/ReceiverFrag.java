package sg.cse.wallet.mvp.transection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.CardStackViewReceiveAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;

public class ReceiverFrag extends BaseDialogFragment implements TransectionView {


    @BindView(R.id.icon_wallet)
    ImageView iconWallet;
    @BindView(R.id.tv_coinAsset)
    TextView tvCoinAsset;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.activity_main_card_stack_view)
    CardStackView cardStackView;
    Unbinder unbinder;
    private TransectionPresenter transectionPresenter;

    private CardStackViewReceiveAdapter cardStackViewAdapter;
    private List<WalletRes.Doc> listCardStackViewCoinWallet;


    private void createView() {
        listCardStackViewCoinWallet = new ArrayList<>();


        cardStackViewAdapter = new CardStackViewReceiveAdapter(getContext());

        cardStackView.setAdapter(cardStackViewAdapter);

        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {

            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                if (cardStackView.getTopIndex() == listCardStackViewCoinWallet.size()) {
                    paginateCardStackView();

                    tvTotal.setText(String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()));
                    tvCoinAsset.setText(String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));
                    EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress(),
                            String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()),

                            listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));
                } else {
                    tvTotal.setText(String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()));
                    tvCoinAsset.setText(String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));
                    // setupPieChart(walletList, cardStackView.getTopIndex());
                    // updateTransectionData(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress());
                    EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress(),
                            String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()),

                            listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));
                }

            }

            @Override
            public void onCardReversed() {

            }

            @Override
            public void onCardMovedToOrigin() {

            }

            @Override
            public void onCardClicked(int index) {

            }
        });

    }


    //    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessage(List<WalletRes.Doc> walletList) {
//
//
//
//    }
    private void paginateCardStackView() {
        cardStackView.setPaginationReserved();
        cardStackViewAdapter.clear();
        cardStackViewAdapter.addAll(listCardStackViewCoinWallet);
        cardStackViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int setView() {
        return R.layout.frag_receiver;
    }

    @Override
    public void initView() {
        createView();
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {
        transectionPresenter = new TransectionPresenter(this);
        transectionPresenter.getWallet();
        showLoading();
    }

    @Override
    public void onGetWalletSuccess(List<WalletRes.Doc> walletList) {
        dismissAll();
        listCardStackViewCoinWallet.clear();
        listCardStackViewCoinWallet.addAll(walletList);

        for (int i = 0; i < listCardStackViewCoinWallet.size(); i++) {
            tvCoinAsset.setText(listCardStackViewCoinWallet.get(0).getCoinAsset());
            tvTotal.setText(String.valueOf(listCardStackViewCoinWallet.get(0).getAvailableAmount()));
            cardStackViewAdapter.add(listCardStackViewCoinWallet.get(i));
        }
        cardStackViewAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(0).getAddress(), String.valueOf(listCardStackViewCoinWallet.get(0).getAvailableAmount()), listCardStackViewCoinWallet.get(0).getCoinAsset()));

    }

    @Override
    public void onGetWalletError(String message) {

    }

    @Override
    public void onGetTransactionsSuccess(TransactionsHistoryRes.Result result) {

    }

    @Override
    public void onGetTransactionsError(String message) {

    }

    @Override
    public void onConnectionError() {

    }
}