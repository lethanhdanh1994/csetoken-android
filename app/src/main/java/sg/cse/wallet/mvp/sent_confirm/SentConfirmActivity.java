package sg.cse.wallet.mvp.sent_confirm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;

public class SentConfirmActivity extends BaseDialogActivity implements SentConfirmView {
    @BindView(R.id.btn_backToolbar)
    ImageView btnBackToolbar;
    @BindView(R.id.tv_titleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tv_amountTool)
    TextView tvAmountTool;
    @BindView(R.id.tv_usdTool)
    TextView tvUsdTool;
    @BindView(R.id.confirmdetails)
    TextView confirmdetails;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_usd)
    TextView tvUsd;

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.edt_code)
    EditText edtCode;
    private String coin;
    private Double amount;
    String address;
    private SentConfirmPresenter presenter;

    @Override
    public int setView() {
        return R.layout.activity_sent_confirm;
    }

    @Override
    public void initView() {
        presenter = new SentConfirmPresenter(this);
    }

    @Override
    public void initValue() {
        coin = Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_FROM);
        tvAmountTool.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_WALLET_COIN));
        tvUsdTool.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_USD));
        tvFrom.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_FROM) + " Wallet");
        tvTo.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_TO));
        address = Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_TO);
        tvTotal.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_TOTAL));
        tvFee.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_FEE));
        amount=Double.valueOf(Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_TOTAL)));

    }

    @Override
    public void initAction() {

    }


    @OnClick({R.id.btn_backToolbar, R.id.btn_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_backToolbar:
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_confirm:
//                Intent intent = new Intent(this, GoogleAuthDisableActivity.class);
////
////                intent.putExtra(Extras.SENT_TOTAL, Double.valueOf(tvTotal.getText().toString()));
////                intent.putExtra(Extras.SENT_COIN, coin);
////                intent.putExtra(Extras.SENT_FEE, tvFee.getText().toString());
////
////                startActivity(intent);


                if (!edtCode.getText().toString().equals("")) {
                    showLoading();
                    presenter.onConfirmBtnClick(edtCode.getText().toString(), address, coin, amount);
                }else {
                    Toast.makeText(this, getResources().getString(R.string.err_2fa_not_emtry), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void sentSuccess() {
        dismissAll();
        Toast.makeText(this, getResources().getString(R.string.sent_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sentFails(String mess) {
        dismissAll();
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, getResources().getString(R.string.sent_fails), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionError() {
        dismissAll();
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }


}
