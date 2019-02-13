package sg.cse.wallet.mvp.google_auth_disable;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.mvp.sent_confirm.SentConfirmPresenter;
import sg.cse.wallet.mvp.sent_confirm.SentConfirmView;

public class GoogleAuthDisableActivity extends BaseDialogActivity implements GoogleAuthDisableView {
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private Double amount;
    String address, coin, note;
    private GoogleAuthDisablePresenter presenter;


    @Override
    public int setView() {
        return R.layout.activity_sent_confirm_2fa;
    }

    @Override
    public void initView() {

        presenter = new GoogleAuthDisablePresenter(this);
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {

    }


    @Override
    public void onGoogleRemoveSuccess() {
        dismissAll();
        Toast.makeText(this, getResources().getString(R.string.remove_success), Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        finish();

    }

    @Override
    public void onGoogleRemoveFails() {
        dismissAll();
        Toast.makeText(this, getResources().getString(R.string.remove_fails), Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED,returnIntent);
        finish();
    }

    @Override
    public void onConnectionError() {
        dismissAll();
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);

    }



    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (!etCode.getText().toString().equals("")) {
            showLoading();
            presenter.removeGoogleAuth(etCode.getText().toString());
        }else {
            Toast.makeText(this, getResources().getString(R.string.err_2fa_not_emtry), Toast.LENGTH_SHORT).show();
        }

    }


}
