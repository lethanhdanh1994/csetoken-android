package sg.cse.wallet.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by thongph on 2/1/18
 * <p>
 * Last modified on 2/1/18
 */

public abstract class BaseFragment extends Fragment implements BaseAction {
    Unbinder unbinder;
    protected Context context;
    protected View rootView;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
            unbinder.unbind();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setView(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        initView();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initValue();
        initAction();
    }


    public FragmentNavigator getFragmentNavigator() {
        return ((BaseFragmentActivity) context).fragmentNavigator;
    }

    /**
     * Get active fragment
     *
     * @return
     */
    protected Fragment getActiveFragment() {
        return getFragmentNavigator().getActiveFragment();
    }

    /**
     * Get previous fragment
     *
     * @return
     */
    protected Fragment getPreviousFragment() {
        return getFragmentNavigator().getPreviousFragment();
    }

    /**
     * Set backPress enable
     *
     * @param enable
     */
    protected void setBackPressEnable(boolean enable) {
        if (context == null) {
            context = getActivity();
        }
        if (context != null) {
            ((BaseActivity) context).setBackPressEnable(enable);
        }
    }

    /**
     * On Fragment backPressed
     */
    public void onFragmentBackPressed() {
    }
}
