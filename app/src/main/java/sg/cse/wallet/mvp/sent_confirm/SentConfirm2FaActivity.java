//package sg.cse.wallet.mvp.sent_confirm;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import sg.cse.wallet.R;
//import sg.cse.wallet.base.BaseDialogActivity;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class SentConfirm2FaActivity extends BaseDialogActivity implements SentConfirmView {
//    @BindView(R.id.et_code)
//    EditText etCode;
//    @BindView(R.id.btn_confirm)
//    Button btnConfirm;
//    private Double amount;
//    String address, coin, note;
//    private SentConfirmPresenter presenter;
//
//
//    @Override
//    public int setView() {
//        return R.layout.activity_sent_confirm_2fa;
//    }
//
//    @Override
//    public void initView() {
//
//        presenter = new SentConfirmPresenter(this);
//    }
//
//    @Override
//    public void initValue() {
//
//    }
//
//    @Override
//    public void initAction() {
//
//    }
//
//
//    @Override
//    public void sentSuccess() {
//        dismissAll();
//        Toast.makeText(this, getResources().getString(R.string.sent_success), Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void sentFails() {
//        dismissAll();
//        Toast.makeText(this, getResources().getString(R.string.sent_fails), Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onConnectionError() {
//        dismissAll();
//        showAlert(context.getString(R.string.err_connection_fail),
//                context.getString(R.string.up_ok), null, null);
//
//    }
//
//
//
//    @OnClick(R.id.btn_confirm)
//    public void onViewClicked() {
//        if (etCode.getText().toString().equals("")) {
//            showLoading();
//            presenter.onConfirmBtnClick(etCode.getText().toString(), address, coin, amount);
//        }
//
//    }
//
//
//}
