package sg.cse.wallet.mvp.changepassword;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import sg.cse.wallet.MainActivity;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.model.changepass.ChangePassRes;
import sg.cse.wallet.model.signin.InfoRes;
import sg.cse.wallet.mvp.active_code.ActiveCodeActivity;
import sg.cse.wallet.mvp.signin.SignInActivity;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.prefs.PrefKey;
import sg.cse.wallet.prefs.Prefs;

public class ChangePasswordActivity extends BaseDialogActivity implements View.OnClickListener, ChangePasswordView {

    private EditText edtOldPassword;
    private EditText edtNewPassword;
    private EditText edtConfirmNewPassword;


    @Override
    public int setView() {
        return R.layout.activity_change_pass;
    }

    @Override
    public void initView() {
        edtOldPassword = findViewById(R.id.edt_oldPass);
        edtNewPassword = findViewById(R.id.edt_newPass);
        edtConfirmNewPassword = findViewById(R.id.edt_confirmNewPass);
    }

    private ChangePasswordPresenter changePasswordPresenter;
    @Override
    public void initValue() {
        changePasswordPresenter = new ChangePasswordPresenter(this);
    }

    @Override
    public void initAction() {
        findViewById(R.id.iv_backBtn).setOnClickListener(this);
        findViewById(R.id.btn_changePassword).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_backBtn:
                finish();
                break;
            case R.id.btn_changePassword:
                showLoading();
                String oldPassword = edtOldPassword.getText().toString();
                String newPassword = edtNewPassword.getText().toString();
                String confirmNewPassword = edtConfirmNewPassword.getText().toString();
                changePasswordPresenter.validateForm(oldPassword, newPassword, confirmNewPassword);
                break;
        }
    }

    @Override
    public void onOldPasswordEmpty() {
        showAlert(context.getString(R.string.err_password_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtOldPassword.requestFocus();
    }

    @Override
    public void onIncorrectOldPassword() {
        showAlert(context.getString(R.string.err_password_is_incorrect), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onNewPasswordEmpty() {
        showAlert(context.getString(R.string.err_password_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtNewPassword.requestFocus();
    }

    @Override
    public void onNewPasswordLengthInvalid() {
        showAlert(context.getString(R.string.err_password_length_invalid), context.getString(R.string.up_ok),
                null, null);
        edtNewPassword.requestFocus();
    }

    @Override
    public void onConfirmNewPasswordEmpty() {
        showAlert(context.getString(R.string.err_confirm_password_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtConfirmNewPassword.requestFocus();
    }

    @Override
    public void onConfirmNewPasswordLengthInvalid() {
        showAlert(context.getString(R.string.err_confirm_password_length_invalid), context.getString(R.string.up_ok),
                null, null);
        edtConfirmNewPassword.requestFocus();
    }

    @Override
    public void onPasswordDoesNotMatch() {
        showAlert(context.getString(R.string.err_password_does_not_match),
                context.getString(R.string.up_ok), null, null);
        edtNewPassword.requestFocus();
    }

    @Override
    public void onChangePassworrdSuccess(ChangePassRes body) {
        dismissAll();
        Prefs.getInstance().storeValue(PrefKey.APP_TOKEN, body.getResult());
        String accessToken = Prefs.getInstance().getStringValue(PrefKey.APP_TOKEN);
        if (accessToken != null && !accessToken.isEmpty()) {
            showAlert("Change password success", "OK", null, null);
            edtOldPassword.setText("");
            edtConfirmNewPassword.setText("");
            edtNewPassword.setText("");
        }
    }


    @Override
    public void onConnectionError() {
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }
}
