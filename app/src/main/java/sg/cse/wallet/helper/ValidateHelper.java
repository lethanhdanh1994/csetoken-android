package sg.cse.wallet.helper;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */

public class ValidateHelper {

    public static boolean isPhoneValid(String phoneNumber) {
        if (phoneNumber.startsWith("09")) {
            return phoneNumber.length() == 10;
        } else if (phoneNumber.startsWith("01")) {
            return phoneNumber.length() == 11 && (phoneNumber.startsWith("012")
                    || phoneNumber.startsWith("016") || phoneNumber.startsWith("018"));
        } else
            return phoneNumber.startsWith("08") && phoneNumber.length() == 10;
    }

    public static boolean isEmailValid(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String parsephoneNumber(String phoneNumber) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i < 4)
                result.append(phoneNumber.charAt(i));
            else
                result.append('*');
        }
        return result.toString();
    }

    /**
     * Valid ho ten
     *
     * @param fullName
     * @return
     */
    public static boolean isFullNameValid(String fullName) {
        if (fullName.length() > 0) {
            if (fullName.contains(" ")) {
                String[] fullNameArray = fullName.split(" ");
                for (String value : fullNameArray) {
                    if (value.length() > 7) {
                        return false;
                    }
                }
            } else {
                return fullName.length() <= 7;
            }
        }
        return true;
    }
}
