package sg.cse.wallet.mvp.signin;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;

import sg.cse.wallet.MainActivity;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.model.signin.InfoRes;
import sg.cse.wallet.model.signin.SignInRes;
import sg.cse.wallet.mvp.active_code.ActiveCodeActivity;
import sg.cse.wallet.mvp.active_code.ActiveCodeView;
import sg.cse.wallet.mvp.forgetpassword.ForgetPasswordActivity;
import sg.cse.wallet.mvp.signup.SignUpActivity;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.prefs.PrefKey;
import sg.cse.wallet.prefs.Prefs;

public class SignInActivity extends BaseDialogActivity implements View.OnClickListener, SignInView {

    @Override
    public int setView() {
        return R.layout.activity_sign_in;
    }

    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    public void initView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    private SignInPresenter signInPresenter;

    @Override
    public void initValue() {
        signInPresenter = new SignInPresenter(this);
        String accessToken = Prefs.getInstance().getStringValue(PrefKey.APP_TOKEN);
        if (accessToken != null && !accessToken.isEmpty()) {
            // goToActivity(MainActivity.class, true);
            showLoading();
            signInPresenter.getInfo();
        }
    }

    @Override
    public void initAction() {
        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnForgetPassword).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                showLoading();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                signInPresenter.validForm(email, password);
                break;
            case R.id.btnForgetPassword:
                goToActivity(ForgetPasswordActivity.class);
                break;
            case R.id.btnRegister:
                goToActivity(SignUpActivity.class);
                break;
        }
    }

    @Override
    public void onEmailEmpty() {
        showAlert(context.getString(R.string.err_email_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtEmail.requestFocus();
    }

    @Override
    public void onEmailInvalid() {
        showAlert(context.getString(R.string.err_email_is_invalid),
                context.getString(R.string.up_ok), null, null);
        edtEmail.requestFocus();
    }

    @Override
    public void onPasswordEmpty() {
        showAlert(context.getString(R.string.err_password_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtPassword.requestFocus();
    }

    @Override
    public void onPasswordLengthInvalid() {
        showAlert(context.getString(R.string.err_password_length_invalid), context.getString(R.string.up_ok),
                null, null);
        edtPassword.requestFocus();
    }

    @Override
    public void onSignInSuccess(SignInRes body) {

        Prefs.getInstance().storeValue(PrefKey.APP_TOKEN, body.getResult().getToken());
        String accessToken = Prefs.getInstance().getStringValue(PrefKey.APP_TOKEN);
        if (accessToken != null && !accessToken.isEmpty()) {
            // goToActivity(MainActivity.class, true);
            showLoading();
            signInPresenter.getInfo();
        }
        //signInPresenter.getInfo();
    }


    @Override
    public void onEmailDoesNotExist() {
        showAlert(context.getString(R.string.err_email_does_not_exist), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onIncorrectPassword() {
        showAlert(context.getString(R.string.err_password_is_incorrect), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onAccountIsBlocked() {
        showAlert(context.getString(R.string.err_your_account_is_blocked), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onConnectionError() {
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }

    @Override
    public void onInfoSuccess(final InfoRes body) {
        dismissAll();
        Const.INFO_OBJECT = body;

        if (body.getResult().getUser().getIsEmailVerified()) {
            goToActivity(MainActivity.class, true);
        } else {

            showAlert(context.getString(R.string.Please_active_this_email),
                    context.getString(R.string.yes),
                    context.getString(R.string.no),
                    new SAlert.OnActionClickListener() {
                        @Override
                        public void onPositiveClicked() {
                            Intent i = new Intent(SignInActivity.this, ActiveCodeActivity.class);
                            i.putExtra(Extras.EMAIL_USER, body.getResult().getUser().getEmail());
                            startActivity(i);
                        }


                        @Override
                        public void onNegativeClicked() {

                        }
                    });
        }
    }

    @Override
    public void onInfoFails() {
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
        dismissAll();
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
    }
}
