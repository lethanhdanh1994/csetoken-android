package sg.cse.wallet.mvp.request_send;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.CardStackViewAdapter;
import sg.cse.wallet.adapter.ViewPagerAdapter;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.model.PriceAndFreeRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.mvp.scan_barcode.ScanBarcodeActivity;
import sg.cse.wallet.mvp.sent.SentFrag;
import sg.cse.wallet.mvp.sent.SentPresenter;
import sg.cse.wallet.mvp.sent_confirm.SentConfirmActivity;
import sg.cse.wallet.mvp.transection.ReceiverFrag;
import sg.cse.wallet.mvp.transection.TransectionFrag;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;

public class RequestSendActivity extends BaseDialogActivity {
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public int setView() {
        return R.layout.activity_request_send;
    }

    @Override
    public void initView() {
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    @Override
    public void initValue() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle("Request Send");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initAction() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RequestSendFrag());
        viewPager.setAdapter(adapter);
    }
}
