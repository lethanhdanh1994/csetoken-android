package sg.cse.wallet.mvp.google_auth_enable;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;
import butterknife.BindView;
import butterknife.OnClick;

public class GoogleAuthEnableActivity extends BaseDialogActivity implements GoogleAuthEnableView {

    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.iv_barCode)
    ImageView ivBarCode;
    @BindView(R.id.edt_generateBarCode)
    EditText edtGenerateBarCode;
    @BindView(R.id.btn_copy)
    LinearLayout btnCopy;
    @BindView(R.id.edt_google2faCode)
    EditText edtGoogle2faCode;
    @BindView(R.id.cb_writeCode)
    CheckBox cbWriteCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    GoogleAuthEnablePresenter presenter;

    @Override
    public int setView() {
        return R.layout.dialog_googleauth_enable;
    }

    @Override
    public void initView() {
        Picasso.get().load(getIntent().getExtras().getString(Extras.URL_BARCODE)).into(ivBarCode);
        edtGenerateBarCode.setText(getIntent().getExtras().getString(Extras.SECRET_KEY));
    }

    @Override
    public void initValue() {
        presenter = new GoogleAuthEnablePresenter(this);


    }

    @Override
    public void initAction() {

    }


    @OnClick({R.id.btn_close, R.id.btn_confirm, R.id.btn_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_confirm:

                showLoading();
                presenter.enableGoogleAuth(edtGoogle2faCode.getText().toString());

                break;
            case R.id.btn_copy:
                Support.setClipboard(this, edtGenerateBarCode.getText().toString());
                Toast.makeText(this, context.getResources().getString(R.string.copy_toast), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onGoogleConfirmSuccess() {
        dismissAll();
        Toast.makeText(this, context.getResources().getString(R.string.google_auth_success), Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onGoogleConfirmFails() {
        dismissAll();
        Toast.makeText(this, context.getResources().getString(R.string.google_auth_fails), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionError() {
        dismissAlert();
    }
}
