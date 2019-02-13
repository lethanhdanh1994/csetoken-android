package sg.cse.wallet.mvp.setting;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.language.SelectLanguageActivity;
import sg.cse.wallet.model.generate2fa.Generate2faRes;
import sg.cse.wallet.mvp.changepassword.ChangePasswordActivity;
import sg.cse.wallet.mvp.google_auth_disable.GoogleAuthDisableActivity;
import sg.cse.wallet.mvp.google_auth_enable.GoogleAuthEnableActivity;
import sg.cse.wallet.pincode.CreatePinActivity;
import sg.cse.wallet.pincode.DisablePinActivity;
import sg.cse.wallet.pincode.PinCode;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.prefs.Prefs;

public class SettingActivity extends BaseDialogActivity implements SettingView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_enableGoogleAuth)
    TextView tvEnableGoogleAuth;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.switchPincode)
    Switch switchPincode;
    @BindView(R.id.ivChangePassword)
    ImageView ivChangePassword;
    @BindView(R.id.iv_language)
    ImageView ivLanguage;
    private SettingPresenter settingPresenter;
    boolean isHaveCode;
    private int REQ_CODE_REMOVE=1234;
    private int REQ_CODE_ENABLE=1235;
    @Override
    public int setView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle("Settings");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // what do you want here
                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchPincode.setOnCheckedChangeListener(null); //Tắt lắng nghe thay đổi của switchPincode
        checkPinCode(); //Kiểm tra Pin Code: Bật/Tắt
        setListenerPinCode(); //Bật Lắng Nghe.
    }


    private void setListenerPinCode() {
        switchPincode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Open Create
                    goToActivity(CreatePinActivity.class);
                } else {
                    //Remove Code
                    goToActivity(DisablePinActivity.class);
                }
            }
        });
    }

    private void checkPinCode() {
        if (Prefs.getInstance().getPinCode().equals("")){
            switchPincode.setChecked(false);
        } else {
            switchPincode.setChecked(true);
        }
    }

    @Override
    public void initValue() {
        isHaveCode = Const.INFO_OBJECT.getResult().getUser().getIsHave2Fa();
        settingPresenter = new SettingPresenter(this);
        if (!Const.INFO_OBJECT.getResult().getUser().getEmail().equals("")) {
            tvEmail.setText(Const.INFO_OBJECT.getResult().getUser().getEmail());
        }

        if (isHaveCode) {
            tvEnableGoogleAuth.setText(getResources().getString(R.string.disable));
        }else {
            tvEnableGoogleAuth.setText(getResources().getString(R.string.enable));
        }
    }

    @Override
    public void initAction() {


    }

    @OnClick({R.id.ivChangePassword, R.id.iv_language})
    public void onViewClickedChange(View view){
        switch (view.getId()){
            case R.id.ivChangePassword:
                Intent intentChangePass = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePass);
                break;
            case R.id.iv_language:
                Intent intentChangeLanguage = new Intent(this, SelectLanguageActivity.class);
                startActivity(intentChangeLanguage);
                break;
        }
    }

    @OnClick(R.id.tv_enableGoogleAuth)
    public void onViewClicked() {
        if (isHaveCode) {
         Intent i = new Intent(this,GoogleAuthDisableActivity.class);
         startActivityForResult(i,REQ_CODE_REMOVE);
        }else {
            showLoading();
            settingPresenter.enableGoogleAuth();
        }
    }

    @Override
    public void onGoogleAuthSuccess(Generate2faRes.Result result) {
        dismissAll();
        Intent googleAuthIntent = new Intent(this, GoogleAuthEnableActivity.class);
        googleAuthIntent.putExtra(Extras.SECRET_KEY, result.getSecretKey());
        googleAuthIntent.putExtra(Extras.URL_BARCODE, result.getQr());
        startActivity(googleAuthIntent);


    }

    @Override
    public void onGoogleAuthFails(String message) {
        dismissAll();
    }

    @Override
    public void onConnectionError() {
        dismissAll();
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE_REMOVE){
            if(resultCode==RESULT_OK){
                Const.INFO_OBJECT.getResult().getUser().setIsHave2Fa(false);
                isHaveCode = false;
                tvEnableGoogleAuth.setText(getResources().getString(R.string.enable));
            }
        }else if(requestCode==REQ_CODE_ENABLE){
            if(resultCode==RESULT_OK){
                Const.INFO_OBJECT.getResult().getUser().setIsHave2Fa(true);
                isHaveCode = true;
                tvEnableGoogleAuth.setText(getResources().getString(R.string.disable));
            }
        }

    }
}
