package sg.cse.wallet.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.format.DateUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Support {
    public static Bitmap GenerateBarcodetoBitmap( String text){
    Bitmap bitmap = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
             bitmap = barcodeEncoder.createBitmap(bitMatrix);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String getStringAgo(String timeDate) {

        String result = "now";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        long time = 0;

        try {

            time = sdf.parse(timeDate).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //long now = System.currentTimeMillis();

        result = getTimeAgo(time);
        return result;
    }

    public static String convertTimeFormat(String timeDate) {

       String result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date newDate= null;
        try {
            newDate = sdf.parse(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat  spf= new SimpleDateFormat("HH:mm");
        result = spf.format(newDate);
        return result;
    }

    public static String convertDayFormat(String timeDate) {

        String result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date newDate= null;
        try {
            newDate = sdf.parse(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat  spf= new SimpleDateFormat("dd-MM-yyyy");
        result = spf.format(newDate);
        return result;
    } public static String convert12hFormat(String timeDate) {

        String result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date newDate= null;
        try {
            newDate = sdf.parse(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat  spf= new SimpleDateFormat("dd-MM-yyyy HH:mm a");
        result = spf.format(newDate);
        return result;
    }
    public static String getCuryFormat(Double cost)
    {
        Double mCost=cost;
        if(mCost<0){
            mCost = 0.0;
        }


        String value ="" ;

        NumberFormat formatter = new DecimalFormat("#,###.##");
        value= "$ "+formatter.format(mCost);

        return value;
    }

    public static String getValueFormat(Double cost)
    {
        Double mCost=cost;
        if(mCost<0){
            mCost = 0.0;
        }


        String value ="" ;

        NumberFormat formatter = new DecimalFormat("#,###.##");
        value= formatter.format(mCost);

        return value;
    }
    public static String getFormatNumber(Double cost)
    {
        Double mCost=cost;
        if(mCost<0||mCost.isNaN()){
            mCost = 0.00;
        }


        String value ="" ;

        NumberFormat formatter = new DecimalFormat("#,###.###");
        value= formatter.format(mCost);

        return value;
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

}
