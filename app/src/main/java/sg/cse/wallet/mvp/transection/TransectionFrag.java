package sg.cse.wallet.mvp.transection;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import sg.cse.wallet.R;
import sg.cse.wallet.adapter.CardStackViewAdapter;
import sg.cse.wallet.adapter.ViewPagerAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.transection.transection_receiver.TransectionReceiverFragment;
import sg.cse.wallet.prefs.Const;

public class TransectionFrag extends BaseDialogFragment implements TransectionView{
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private CardStackView cardStackView;
    private TransectionPresenter transectionPresenter;
    private CardStackViewAdapter cardStackViewAdapter;
    private List<WalletRes.Doc> listCardStackViewCoinWallet;

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setCustomView(R.layout.view_tab2_received);
        tabLayout.getTabAt(1).setCustomView(R.layout.view_tab2_send);
    }

    @Override
    public void onGetWalletSuccess(List<WalletRes.Doc> walletList) {
        dismissAll();
      listCardStackViewCoinWallet.clear();
        listCardStackViewCoinWallet.addAll(walletList);

        for (int i=0;i<listCardStackViewCoinWallet.size();i++){
            cardStackViewAdapter.add(listCardStackViewCoinWallet.get(i));
        }
        cardStackViewAdapter.notifyDataSetChanged();

       updateTransectionData(walletList.get(0).getAddress());
        EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(0).getAddress(), String.valueOf(listCardStackViewCoinWallet.get(0).getAvailableAmount()), listCardStackViewCoinWallet.get(0).getCoinAsset()));
    }

    @Override
    public void onGetWalletError(String message) {
        dismissAll();
    }

    @Override
    public void onGetTransactionsSuccess(TransactionsHistoryRes.Result result) {


        dismissAll();


    }
    private void updateTransectionData(String addressWallet){



//       if(adapter!=null){
//           adapter.clear();
//           adapter.notifyDataSetChanged();
//
//       }

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(TransectionReceiverFragment.newInstance(addressWallet,Const.TRANS_TYPE_RECEIVE));
        adapter.addFragment(TransectionReceiverFragment.newInstance(addressWallet,Const.TRANS_TYPE_SENT));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

//        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(2);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
    //    viewPager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onGetTransactionsError(String message) {
        dismissAll();
    }

    @Override
    public void onConnectionError() {
        dismissAll();

    }

    @Override
    public int setView() {
        return R.layout.frag_transection;
    }

    @Override
    public void initView() {
        tabLayout =rootView.findViewById(R.id.tabs);
        viewPager =rootView.findViewById(R.id.viewpager);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#f6c360"));
                }else tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#6dcef5"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        cardStackView = (CardStackView) rootView.findViewById(R.id.activity_main_card_stack_view);

        cardStackViewAdapter = new CardStackViewAdapter(getContext(),"Current Balance");

        cardStackView.setAdapter(cardStackViewAdapter);

        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {

            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                if (cardStackView.getTopIndex() == listCardStackViewCoinWallet.size()) {
                    paginateCardStackView();
                    EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress(),
                            String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()),

                            listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));
                } else {
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

    @Override
    public void initValue() {
        listCardStackViewCoinWallet= new ArrayList<>();
    }

    @Override
    public void initAction() {
        transectionPresenter = new TransectionPresenter(this);
        transectionPresenter.getWallet();
        showLoading();
    }

    private void paginateCardStackView() {
        cardStackView.setPaginationReserved();
        cardStackViewAdapter.clear();
        cardStackViewAdapter.addAll(listCardStackViewCoinWallet);
        cardStackViewAdapter.notifyDataSetChanged();
    }
}