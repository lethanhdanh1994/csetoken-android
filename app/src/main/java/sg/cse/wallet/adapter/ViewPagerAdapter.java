package sg.cse.wallet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
    FragmentManager mManager;
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            this.mManager = manager;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

public  void clear(){
            mManager.beginTransaction().remove(mFragmentList.get(0)).commit();
      mManager.beginTransaction().remove(mFragmentList.get(1)).commit();


    //  mFragmentList.clear();
}


    }