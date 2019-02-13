package sg.cse.wallet.pincode;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sg.cse.wallet.R;
import sg.cse.wallet.adapter.ViewPagerAdapter;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.mvp.sent.SentFrag;
import sg.cse.wallet.mvp.transection.ReceiverFrag;
import sg.cse.wallet.mvp.transection.TransectionFrag;
import sg.cse.wallet.utils.Log;

public class PinCode extends BaseDialogActivity {
    ViewPager viewPager;
    public static boolean active = false;

    @Override
    public int setView() {
        return R.layout.activity_pin_code;
    }

    @Override
    public void initView() {
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PincodeFrag());
        adapter.addFragment(new ReceiverPinFrag());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initAction() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }
}
