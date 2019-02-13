package sg.cse.wallet.mvp.transection.transection_receiver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import sg.cse.wallet.R;
import sg.cse.wallet.adapter.TransectionAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.mvp.transection.Message;
import sg.cse.wallet.prefs.Const;


public class TransectionReceiverFragment extends BaseDialogFragment implements  TransectionReceiverView {

    private RecyclerView mRecyclerView;
    private TransectionAdapter adapter;
    private String wallAddress,type;
    private ArrayList<TransactionsHistoryRes.Result.Docs> transectionModelsList = new ArrayList<>();




    private TransectionReceiverPresenter transectionReceiverPresenter;
    public static TransectionReceiverFragment newInstance(String addressWallet,String type) {

        TransectionReceiverFragment myFragment = new TransectionReceiverFragment();

        Bundle args = new Bundle();
        args.putString("wallet", addressWallet);
        args.putString("type", type);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Log.d("DEBUG", getArguments().getString("wallet")); // This actually works!
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public int setView() {
        return R.layout.transection_transection_fragment;
    }

    @Override
    public void initView() {
          wallAddress = getArguments().getString("wallet");
          type = getArguments().getString("type");
        mRecyclerView = rootView.findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpRecyclerView();
//        transectionModels.add(new TransectionModel(1000,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));
//        transectionModels.add(new TransectionModel(1200,"1 day ago"));

        if(type.equals(Const.TRANS_TYPE_RECEIVE)){
            adapter = new TransectionAdapter(transectionModelsList,"Payment Received",R.drawable.receive_ic);
        }else {
            adapter = new TransectionAdapter(transectionModelsList,"Payment Sent",R.drawable.send_2_ic);
        }



        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initValue() {
        transectionReceiverPresenter = new TransectionReceiverPresenter(this);
    }

    @Override
    public void initAction() {
        showLoading();
        if(type.equals(Const.TRANS_TYPE_RECEIVE)){
            transectionReceiverPresenter.getTransactionsHistory(wallAddress, Const.TRANS_TYPE_RECEIVE);
        }else {
            transectionReceiverPresenter.getTransactionsHistory(wallAddress, Const.TRANS_TYPE_SENT);
        }


    }

    @Override
    public void onGetTransactionsSuccess(TransactionsHistoryRes.Result result) {
        dismissAll();
        transectionModelsList.clear();
        transectionModelsList.addAll(result.getDocs());
        adapter.notifyDataSetChanged();

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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        getArguments().clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Message event) {
        Log.e("EventBus ","Received"+event.getMessage());
        showLoading();
        if(type.equals(Const.TRANS_TYPE_RECEIVE)){
            transectionReceiverPresenter.getTransactionsHistory(event.getMessage(), Const.TRANS_TYPE_RECEIVE);
        }else {
            transectionReceiverPresenter.getTransactionsHistory(event.getMessage(), Const.TRANS_TYPE_SENT);
        }
    }

}