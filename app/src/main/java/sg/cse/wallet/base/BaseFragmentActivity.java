package sg.cse.wallet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import sg.cse.wallet.R;
import sg.cse.wallet.mvp.dashboard.DashboardFragment;
import butterknife.ButterKnife;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public abstract class BaseFragmentActivity extends BaseDialogActivity {

    public FragmentNavigator fragmentNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), R.id.viewContainer);
        fragmentNavigator.setRootFragment(new DashboardFragment());
        fragmentNavigator.setOnStackChanged(new FragmentNavigator.onStackChanged() {
            @Override
            public void onChanged(Fragment fragment) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressEnable) {
            Fragment activeFragment = fragmentNavigator.getActiveFragment();
            if (activeFragment instanceof BaseFragment) {
                ((BaseFragment) fragmentNavigator.getActiveFragment()).onFragmentBackPressed();
            }
        }
        super.onBackPressed();
    }
}
