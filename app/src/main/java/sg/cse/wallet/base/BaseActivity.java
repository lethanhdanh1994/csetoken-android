package sg.cse.wallet.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import sg.cse.wallet.helper.SystemHelper;
import butterknife.ButterKnife;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseAction {

    protected Context context;
    protected boolean backPressEnable = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        ButterKnife.bind(this);
        context = this;


    }

    /**
     * Init action
     */
    public void init() {
        initView();
        initValue();
        initAction();
    }

    /**
     * Hide keyboard when click outside EditText
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    SystemHelper.hideKeyboard(context);
                }
            }
        }


        return super.dispatchTouchEvent(event);
    }

    /**
     * Go to another activity and don't finish current activity
     *
     * @param clazz
     */
    protected void goToActivity(Class clazz) {
        goToActivity(clazz, false);
    }

    /**
     * Go to another activity and finish current activity
     *
     * @param clazz
     * @param isFinish
     */
    protected void goToActivity(Class clazz, boolean isFinish) {
        startActivity(new Intent(context, clazz));
        if (isFinish) {
            finish();
        }
    }

    /**
     * Set back press enable
     *
     * @param backPressEnable
     */
    public void setBackPressEnable(boolean backPressEnable) {
        this.backPressEnable = backPressEnable;
    }

    /**
     * On activity back pressed
     */
    public void onActivityBackPressed() {
    }

    @Override
    public void onBackPressed() {
        onActivityBackPressed();
        if (backPressEnable) {
            super.onBackPressed();
        }
    }
}
