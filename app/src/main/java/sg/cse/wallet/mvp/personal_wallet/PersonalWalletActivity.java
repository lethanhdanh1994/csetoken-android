package sg.cse.wallet.mvp.personal_wallet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sg.cse.wallet.R;
import sg.cse.wallet.adapter.ViewPagerAdapter;
import sg.cse.wallet.base.BaseDialogActivity;
import sg.cse.wallet.mvp.sent.SentFrag;
import sg.cse.wallet.mvp.transection.ReceiverFrag;
import sg.cse.wallet.mvp.transection.TransectionFrag;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalWalletActivity extends BaseDialogActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int setView() {
        return R.layout.activity_per_wallet;
    }

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public void initValue() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle("Personal wallet");
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

        adapter.addFragment(new ReceiverFrag());
        adapter.addFragment(new TransectionFrag());
        adapter.addFragment(new SentFrag());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        if (getIntent() != null) {
            viewPager.setCurrentItem(getIntent().getIntExtra("IN", 0));
        }


    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setCustomView(R.layout.view_tab1_receiver);
        tabLayout.getTabAt(1).setCustomView(R.layout.view_tab1_transection);
        tabLayout.getTabAt(2).setCustomView(R.layout.view_tab1_send);
    }

}
