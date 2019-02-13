package sg.cse.wallet.mvp.request_send;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.CardStackViewAdapter;
import sg.cse.wallet.adapter.CardStackViewRequestSendAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.scan_barcode.ScanBarcodeActivity;
import sg.cse.wallet.mvp.sent.SentPresenter;
import sg.cse.wallet.mvp.sent_confirm.SentConfirmActivity;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;

public class RequestSendFrag extends BaseDialogFragment implements RequestSendView {
    @BindView(R.id.btn_requestSend)
    Button btnRequestSend;
    String walletFrom, walletCoin, walletUSD, walletAddress;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt_usd)
    EditText edtUsd;
    @BindView(R.id.edt_coin)
    EditText edtCoin;

    @BindView(R.id.bottom)
    RelativeLayout bottom;

    @BindView(R.id.tv_coinAsset)
    TextView tvCoinAsset;
    @BindView(R.id.tv_Fee)
    TextView tvFee;
    @BindView(R.id.tv_coinSample)
    TextView tvCoinSample;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    int isEdtCoin = 0;

    private CardStackView cardStackView;
    private RequestSendPresenter transectionPresenter;
    private CardStackViewRequestSendAdapter cardStackViewRequestSendAdapter;
    private List<WalletRes.Doc> listCardStackViewCoinWallet;
    private double priceUSD = 0;
    private double feeSent=0;

    @Override
    public void onGetWalletSuccess(List<WalletRes.Doc> walletList) {
        dismissAll();
        listCardStackViewCoinWallet.clear();
        listCardStackViewCoinWallet.addAll(walletList);

        for (int i = 0; i < listCardStackViewCoinWallet.size(); i++) {
            cardStackViewRequestSendAdapter.add(listCardStackViewCoinWallet.get(i));
        }
        cardStackViewRequestSendAdapter.notifyDataSetChanged();
        WalletRes.Doc firstCoinWallet = listCardStackViewCoinWallet.get(0);
        setDataWalletSent(firstCoinWallet);


    }

    private void setDataWalletSent(WalletRes.Doc firstCoinWallet) {
        walletCoin = String.valueOf(firstCoinWallet.getAvailableAmount());
        walletFrom = String.valueOf(firstCoinWallet.getCoinAsset());
        walletUSD = String.valueOf(firstCoinWallet.getEstimatedUSDT() + " USD");
        walletAddress = String.valueOf(firstCoinWallet.getAddress());

        transectionPresenter.getFeeAndPriceUSD(walletFrom);

        tvCoinSample.setText(walletFrom);
        tvCoinAsset.setText(walletFrom);
        tvAddress.setText(walletAddress);


        showLoading();
    }

    @Override
    public void onGetWalletError(String message) {
        dismissAll();
    }

    @Override
    public void getPriceAndFeeSuccess(PriceAndFreeRes priceAndFreeRes) {
        dismissAll();

        tvFee.setText(String.valueOf(priceAndFreeRes.getResult().getFee()));
        priceUSD = priceAndFreeRes.getResult().getPriceUSD();
        feeSent = priceAndFreeRes.getResult().getFee();

    }

    @Override
    public void getPriceAndFeeFails(String message) {
        dismissAll();

    }

    @Override
    public void onConnectionError() {
        dismissAll();

    }

    @Override
    public int setView() {
        return R.layout.frag_request_send;
    }

    @Override
    public void initView() {


        cardStackView = (CardStackView) rootView.findViewById(R.id.activity_main_card_stack_view);

        cardStackViewRequestSendAdapter = new CardStackViewRequestSendAdapter(getContext(), "Current Balance");

        cardStackView.setAdapter(cardStackViewRequestSendAdapter);

        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {

            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                if (cardStackView.getTopIndex() == listCardStackViewCoinWallet.size()) {
                    paginateCardStackView();
//                    EventBus.getDefault().post(new Message(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress(),
//                            String.valueOf(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAvailableAmount()),
//
//                            listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getCoinAsset()));

                    WalletRes.Doc firstCoinWallet = listCardStackViewCoinWallet.get(cardStackView.getTopIndex());
                    setDataWalletSent(firstCoinWallet);
                } else {
                    // setupPieChart(walletList, cardStackView.getTopIndex());
                    // updateTransectionData(listCardStackViewCoinWallet.get(cardStackView.getTopIndex()).getAddress());
                    WalletRes.Doc firstCoinWallet = listCardStackViewCoinWallet.get(cardStackView.getTopIndex());
                    setDataWalletSent(firstCoinWallet);

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

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isEdtCoin++;
                //isEdtCoin+=isEdtCoin;

                try {
                    if (isEdtCoin <= 1) {
                        if (editable == edtCoin.getEditableText()) {
                            double value = Double.valueOf(edtCoin.getText().toString()) * priceUSD;

                            edtUsd.setText(Support.getFormatNumber(value));
                        } else {
                            double value = Double.valueOf(edtUsd.getText().toString()) / priceUSD;
                            edtCoin.setText(Support.getFormatNumber(value));
                        }
                    } else {
                        isEdtCoin = 0;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    isEdtCoin = 0;
                }


            }


        };


        edtUsd.addTextChangedListener(textWatcher);

        edtCoin.addTextChangedListener(textWatcher);


    }


    @Override
    public void initValue() {
        listCardStackViewCoinWallet = new ArrayList<>();
    }

    @Override
    public void initAction() {
        transectionPresenter = new RequestSendPresenter(this);
        transectionPresenter.getWallet();
        showLoading();
    }

    private void paginateCardStackView() {
        cardStackView.setPaginationReserved();
        cardStackViewRequestSendAdapter.clear();
        cardStackViewRequestSendAdapter.addAll(listCardStackViewCoinWallet);
        cardStackViewRequestSendAdapter.notifyDataSetChanged();
    }


    private Boolean isCheckValueConfirm() {

        if (edtCoin.getText().toString().equals("")){
            Toast.makeText(getActivity(),getResources().getString(R.string.err_amount_not_emtry),Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    @OnClick({R.id.btn_requestSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_requestSend:
                if (isCheckValueConfirm()) {
                    Intent intent = new Intent(getActivity(), QrCodeRequestActivity.class);
                    intent.putExtra(Extras.SENT_FROM, walletFrom);
                    intent.putExtra(Extras.SENT_WALLET_COIN, walletCoin);
                    intent.putExtra(Extras.SENT_USD, walletUSD);
                    intent.putExtra(Extras.SENT_TOTAL, edtCoin.getText().toString());
                    intent.putExtra(Extras.SENT_ADDRESS, tvAddress.getText().toString());
                    intent.putExtra(Extras.SENT_FEE, String.valueOf(feeSent)+" "+ walletFrom);
                    startActivity(intent);
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == Const.SCAN_RESULT && resultCode == getActivity().RESULT_OK) {

        }
    }
}
