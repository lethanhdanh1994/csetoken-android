package sg.cse.wallet.base;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public interface BaseAction {

    /**
     * Set contentView for Activity/Fragment
     * @return - view
     */
    int setView();

    /**
     * Init view
     */
    void initView();

    /**
     * Init value
     */
    void initValue();

    /**
     * Init action
     */
    void initAction();
}
