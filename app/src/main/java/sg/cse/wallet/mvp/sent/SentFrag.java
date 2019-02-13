package sg.cse.wallet.mvp.sent;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import sg.cse.wallet.base.BaseCropImageActivity;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.scan_barcode.ScanBarcodeActivity;
import sg.cse.wallet.mvp.sent_confirm.SentConfirmActivity;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;

public class SentFrag extends BaseDialogFragment implements SentView {


    @BindView(R.id.btn_sentConfirm)
    Button btnSentConfirm;
    String walletFrom, walletCoin, walletUSD;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.edt_scan)
    EditText edtScan;
    @BindView(R.id.scan)
    ImageView scan;
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

    int isEdtCoin = 0;


    private CardStackView cardStackView;
    private SentPresenter transectionPresenter;
    private CardStackViewAdapter cardStackViewAdapter;
    private List<WalletRes.Doc> listCardStackViewCoinWallet;
    private double priceUSD = 0;
    private double feeSent=0;


    @Override
    public void onGetWalletSuccess(List<WalletRes.Doc> walletList) {
        dismissAll();
        listCardStackViewCoinWallet.clear();
        listCardStackViewCoinWallet.addAll(walletList);

        for (int i = 0; i < listCardStackViewCoinWallet.size(); i++) {
            cardStackViewAdapter.add(listCardStackViewCoinWallet.get(i));
        }
        cardStackViewAdapter.notifyDataSetChanged();
        WalletRes.Doc firstCoinWallet = listCardStackViewCoinWallet.get(0);
        setDataWalletSent(firstCoinWallet);


    }

    private void setDataWalletSent(WalletRes.Doc firstCoinWallet) {
        walletCoin = String.valueOf(firstCoinWallet.getAvailableAmount());
        walletFrom = String.valueOf(firstCoinWallet.getCoinAsset());
        walletUSD = String.valueOf(firstCoinWallet.getEstimatedUSDT() + " USD");

        transectionPresenter.getFeeAndPriceUSD(walletFrom);

        tvCoinSample.setText(walletFrom);
        tvCoinAsset.setText(walletFrom);


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
        return R.layout.frag_sent;
    }

    @Override
    public void initView() {


        cardStackView = (CardStackView) rootView.findViewById(R.id.activity_main_card_stack_view);

        cardStackViewAdapter = new CardStackViewAdapter(getContext(), "Current Balance");

        cardStackView.setAdapter(cardStackViewAdapter);

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
        transectionPresenter = new SentPresenter(this);
        transectionPresenter.getWallet();
        showLoading();
    }

    private void paginateCardStackView() {
        cardStackView.setPaginationReserved();
        cardStackViewAdapter.clear();
        cardStackViewAdapter.addAll(listCardStackViewCoinWallet);
        cardStackViewAdapter.notifyDataSetChanged();
    }


    private Boolean isCheckValueConfirm() {
        if (edtScan.getText().toString().equals("")) {
            Toast.makeText(getActivity(), getResources().getString(R.string.err_address_not_emtry), Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (edtCoin.getText().toString().equals("")){
            Toast.makeText(getActivity(),getResources().getString(R.string.err_amount_not_emtry),Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    @OnClick({R.id.scan, R.id.btn_sentConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:

                Dexter.withActivity(getActivity())
                        .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(final MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    Intent i = new Intent(getActivity(), ScanBarcodeActivity.class);
                                    startActivityForResult(i, Const.SCAN_RESULT);
                                }
                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showAlert(context.getString(R.string.msg_permission_pick_photo_not_granted),
                                            context.getString(R.string.upcase_go_to_settings),
                                            context.getString(R.string.upcase_cancel),
                                            new SAlert.OnActionClickListener() {
                                                @Override
                                                public void onPositiveClicked() {
                                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                                    intent.setData(uri);
                                                    startActivityForResult(intent, 101);
                                                }

                                                @Override
                                                public void onNegativeClicked() {

                                                }
                                            });
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .withErrorListener(new PermissionRequestErrorListener() {
                            @Override
                            public void onError(DexterError error) {
                                dismissAll();
                                showAlert(context.getString(R.string.msg_an_error_has_occurred) + "\n" + error.toString());
                            }
                        })
                        .check();







                break;
            case R.id.btn_sentConfirm:
                if (isCheckValueConfirm()) {
                    Intent intent = new Intent(getActivity(), SentConfirmActivity.class);
                    intent.putExtra(Extras.SENT_FROM, walletFrom);
                    intent.putExtra(Extras.SENT_TO, edtScan.getText().toString());
                    intent.putExtra(Extras.SENT_WALLET_COIN, walletCoin);
                    intent.putExtra(Extras.SENT_USD, walletUSD);
                     intent.putExtra(Extras.SENT_TOTAL, edtCoin.getText().toString());
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
            edtScan.setText(data.getExtras().getString(Extras.SCAN_BARCODE));
        }

    }
}