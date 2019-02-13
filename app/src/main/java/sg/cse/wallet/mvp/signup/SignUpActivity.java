package sg.cse.wallet.mvp.signup;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import sg.cse.wallet.MainActivity;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.model.signin.SignInRes;
import sg.cse.wallet.model.signup.SignUpRes;
import sg.cse.wallet.mvp.active_code.ActiveCodeActivity;
import sg.cse.wallet.mvp.signin.SignInActivity;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.prefs.PrefKey;
import sg.cse.wallet.prefs.Prefs;

public class SignUpActivity extends BaseDialogActivity implements View.OnClickListener, SignUpView {

    @Override
    public int setView() {
        return R.layout.activity_sign_up;
    }

    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    @Override
    public void initView() {
        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
    }

    private SignUpPresenter signUpPresenter;

    @Override
    public void initValue() {
        signUpPresenter = new SignUpPresenter(this);
    }

    @Override
    public void initAction() {
        findViewById(R.id.btnSignUp).setOnClickListener(this);
        findViewById(R.id.btnSignIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                showLoading();
                String userName = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();
                signUpPresenter.validateForm(userName, email, password, confirmPassword);
                break;
            case R.id.btnSignIn:
                goToActivity(SignInActivity.class, true);
                break;
        }
    }

    @Override
    public void onUserNameEmpty() {
        showAlert(context.getString(R.string.err_full_name_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtUserName.requestFocus();
    }

    @Override
    public void onUserNameLengthInvalid() {
        showAlert(context.getString(R.string.err_user_name_length_invalid), context.getString(R.string.up_ok),
                null, null);
        edtUserName.requestFocus();
    }

    @Override
    public void onEmailEmpty() {
        showAlert(context.getString(R.string.err_email_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtEmail.requestFocus();
    }

    @Override
    public void onEmailInValid() {
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
    public void onConfirmPasswordEmpty() {
        showAlert(context.getString(R.string.err_confirm_password_is_empty),
                context.getString(R.string.up_ok), null, null);
        edtConfirmPassword.requestFocus();
    }

    @Override
    public void onConfirmPasswordLengthInvalid() {
        showAlert(context.getString(R.string.err_confirm_password_length_invalid), context.getString(R.string.up_ok),
                null, null);
        edtConfirmPassword.requestFocus();
    }

    @Override
    public void onPasswordDoesNotMatch() {
        showAlert(context.getString(R.string.err_password_does_not_match),
                context.getString(R.string.up_ok), null, null);
        edtPassword.requestFocus();
    }

    @Override
    public void onSignUpSuccess(SignInRes signUpRes) {
        dismissAll();
        Prefs.getInstance().storeValue(PrefKey.APP_TOKEN, signUpRes.getResult().getToken());

        Intent i = new Intent(SignUpActivity.this, ActiveCodeActivity.class);
        i.putExtra(Extras.EMAIL_USER, edtEmail.getText().toString());
        startActivity(i);
       // goToActivity(MainActivity.class, true);
    }

    @Override
    public void onEmailExist() {
        showAlert(context.getString(R.string.err_email_exist), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onUserNameExist() {
        showAlert(context.getString(R.string.err_user_name_exist), context.getString(R.string.up_ok),
                null, null);
    }

    @Override
    public void onConnectionError() {
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }
}
