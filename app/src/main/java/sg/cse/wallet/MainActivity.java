package sg.cse.wallet;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.base.BaseFragmentActivity;
import sg.cse.wallet.mvp.personal_wallet.PersonalWalletActivity;
import sg.cse.wallet.mvp.profile.ProfileActivity;
import sg.cse.wallet.mvp.request_send.RequestSendActivity;
import sg.cse.wallet.mvp.setting.SettingActivity;
import sg.cse.wallet.mvp.signin.SignInActivity;
import sg.cse.wallet.pincode.PinCode;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.PrefKey;
import sg.cse.wallet.prefs.Prefs;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.transaction_lay)
    LinearLayout transactionLay;
    @BindView(R.id.receive_lay)
    LinearLayout receiveLay;
    @BindView(R.id.sent_lay)
    LinearLayout sentLay;
    @BindView(R.id.request_sent_lay)
    LinearLayout requestSentLay;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    private static int PROFILE_CODE = 123;

    @Override
    public int setView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void initValue() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
      tvUserName.setText(Const.INFO_OBJECT.getResult().getUser().getFullName());
      tvEmail.setText(Const.INFO_OBJECT.getResult().getUser().getEmail());

       if(!Const.INFO_OBJECT.getResult().getUser().getAvatarUrl().equals("")){
           Picasso.get().load(Const.INFO_OBJECT.getResult().getUser().getAvatarUrl()).into(ivAvatar);
       }


    }

    @Override
    public void initAction() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        ActivityCompat.finishAffinity(this);
    }


    @OnClick({R.id.iv_profile, R.id.iv_setting, R.id.transaction_lay, R.id.receive_lay, R.id.sent_lay, R.id.request_sent_lay, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.transaction_lay:
                Intent ITrans = new Intent(this, PersonalWalletActivity.class);
                ITrans.putExtra("IN", 1);
                startActivity(ITrans);

                break;
            case R.id.receive_lay:
                Intent IR = new Intent(this, PersonalWalletActivity.class);
                IR.putExtra("IN", 0);
                startActivity(IR);
                break;
            case R.id.sent_lay:
                Intent IProfile = new Intent(this, PersonalWalletActivity.class);
                IProfile.putExtra("IN", 2);
                startActivity(IProfile);
                break;
            case R.id.request_sent_lay:
                Intent IRQSent = new Intent(this, RequestSendActivity.class);
                startActivity(IRQSent);
                break;
            case R.id.btn_logout:
                Prefs.getInstance().init(this);
                Prefs.getInstance().storeValue(PrefKey.APP_TOKEN, "");
                Prefs.getInstance().removePinCode();
                Intent ILogout = new Intent(this, SignInActivity.class);
                startActivity(ILogout);
                finish();
                break;
            case R.id.iv_profile:
                startActivityForResult(new Intent(this, ProfileActivity.class), PROFILE_CODE);
                break;
            case R.id.iv_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == PROFILE_CODE && resultCode == RESULT_OK) {
            reload();
        }

    }
}
