package sg.cse.wallet.utils;

/**
 * Created by thongph on 2/2/18
 * <p>
 * Last modified on 2/2/18
 */

public class Log {

    /**
     * Enable log
     */
    private static final boolean enableLog = true;

    /**
     * Debug log
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (enableLog) {
            android.util.Log.d(tag, msg);
        }
    }

    /**
     * Error log
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (enableLog) {
            android.util.Log.e(tag, msg);
        }
    }

    /**
     * Warning log
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (enableLog) {
            android.util.Log.w(tag, msg);
        }
    }

    /**
     * Information log
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (enableLog) {
            android.util.Log.i(tag, msg);
        }
    }
}
