package sg.cse.wallet.mvp.active_code;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sg.cse.wallet.MainActivity;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.prefs.Extras;

public class ActiveCodeActivity extends BaseDialogActivity implements ActiveCodeView {
    @BindView(R.id.edtCode1)
    EditText etDigit1;
    @BindView(R.id.edtCode2)
    EditText etDigit2;
    @BindView(R.id.edtCode3)
    EditText etDigit3;
    @BindView(R.id.edtCode4)
    EditText etDigit4;
    @BindView(R.id.edtCode5)
    EditText etDigit5;
    @BindView(R.id.edtCode6)
    EditText etDigit6;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnResend)
    TextView btnResend;
    @BindView(R.id.btnSignIn)
    TextView btnSignIn;

    ActiveCodePresenter mPresenter;
    private String emailUser;

    @Override
    public int setView() {
        return R.layout.activity_active_code;
    }

    @Override
    public void initView() {
        handleInputEditTextCode();
        setButtonContinueClickbleOrNot();
    }

    @Override
    public void initValue() {
        if (getIntent().getStringExtra(Extras.EMAIL_USER) != null) {
            emailUser = getIntent().getStringExtra(Extras.EMAIL_USER);
        }

        mPresenter = new ActiveCodePresenter(this);
    }

    @Override
    public void initAction() {
        showLoading();
        mPresenter.resendCodeEmail(emailUser);
    }

    private void handleInputEditTextCode() {
        etDigit1.requestFocus();

        etDigit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                    etDigit2.requestFocus();
                }
            }
        });
        etDigit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                    etDigit3.requestFocus();
                } else {
                    etDigit1.requestFocus();
                }
            }
        });
        etDigit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                    etDigit4.requestFocus();
                } else {
                    etDigit2.requestFocus();
                }
            }
        });
        etDigit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                    etDigit5.requestFocus();
                } else {
                    etDigit3.requestFocus();
                }
            }
        });
        etDigit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                    etDigit6.requestFocus();
                } else {
                    etDigit4.requestFocus();
                }
            }
        });
        etDigit6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setButtonContinueClickbleOrNot();
                if (editable.toString().length() == 1) {
                } else {
                    etDigit5.requestFocus();
                }
            }
        });

        etDigit1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                } else {
                    if (etDigit1.getText().toString().trim().length() == 1) {
                        etDigit2.requestFocus();
                    }
                }
                return false;
            }
        });
        etDigit2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etDigit2.getText().toString().trim().length() == 0)
                        etDigit1.requestFocus();
                } else {
                    if (etDigit2.getText().toString().trim().length() == 1) {
                        etDigit3.requestFocus();
                    }
                }
                return false;
            }
        });
        etDigit3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etDigit3.getText().toString().trim().length() == 0)
                        etDigit2.requestFocus();
                } else {
                    if (etDigit3.getText().toString().trim().length() == 1) {
                        etDigit4.requestFocus();
                    }
                }
                return false;
            }
        });
        etDigit4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etDigit4.getText().toString().trim().length() == 0)
                        etDigit3.requestFocus();
                } else {
                    if (etDigit4.getText().toString().trim().length() == 1) {
                        etDigit5.requestFocus();
                    }
                }
                return false;
            }
        });
        etDigit5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etDigit5.getText().toString().trim().length() == 0)
                        etDigit4.requestFocus();
                } else {
                    if (etDigit5.getText().toString().trim().length() == 1) {
                        etDigit6.requestFocus();
                    }
                }
                return false;
            }
        });
        etDigit6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etDigit6.getText().toString().trim().length() == 0)
                        etDigit5.requestFocus();
                }
                return false;
            }
        });
    }

    private void setButtonContinueClickbleOrNot() {
        if (!validate()) {
            btnSubmit.setAlpha(.2f);
            btnSubmit.setClickable(false);
        } else {
            btnSubmit.setAlpha(1.0f);
            btnSubmit.setClickable(true);
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etDigit1.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(etDigit2.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(etDigit3.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(etDigit4.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(etDigit5.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(etDigit6.getText().toString().trim())) {
            return false;
        }
        return true;
    }

    @OnClick({R.id.btnSubmit, R.id.btnResend, R.id.btnSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                String code =
                        etDigit1.getText().toString() +
                                etDigit2.getText().toString() +
                                etDigit3.getText().toString() +
                                etDigit4.getText().toString() +
                                etDigit5.getText().toString() +
                                etDigit6.getText().toString();
                showLoading();
                mPresenter.verityCodeEmail(code, emailUser);
                break;
            case R.id.btnResend:
                showLoading();
                mPresenter.resendCodeEmail(emailUser);
                break;
            case R.id.btnSignIn:
                finish();
                break;
        }
    }

    @Override
    public void onActiveCodeSubmitSuccess() {
dismissAll();
        goToActivity(MainActivity.class, true);
    }

    @Override
    public void onActiveCodeSubmitFails(String error) {
        dismissAll();

        switch (error){
            case "CODE_VERIFY_NOT_MATCH":
           showAlert(getResources().getString(R.string.CODE_VERIFY_NOT_MATCH));
                break;
            case "NOT_FOUND_EMAIL":
                showAlert(getResources().getString(R.string.NOT_FOUND_EMAIL));
                break;


        }


    }

    @Override
    public void onActiveCodeResendSuccess() {
        dismissAll();

    }

    @Override
    public void onActiveCodeResendFails() {
        dismissAll();


        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }

    @Override
    public void onConnectionError() {
        dismissAll();
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);

    }


}
