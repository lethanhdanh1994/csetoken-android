package sg.cse.wallet.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by thongph on 12/30/2017
 */
public class BitmapUtils {

    public static Bitmap fromUri(Uri uri) {

        File file = new File(uri.getPath());
        return BitmapFactory.decodeFile(file.getPath());
    }

    public static String toString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public static Bitmap fromString(String input) {

        try {
            byte[] encodeByte = Base64.decode(input, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String encodeToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOS);
        return "data:image/jpeg;base64," + Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Bitmap compressBitmap(Bitmap source, int size /* kilobytes */) {

        size = size * 1000;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        source.compress(Bitmap.CompressFormat.JPEG, 100, baos);

//        if (imageBytes.length <= size) {
//            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        }
//
//        int rate = imageBytes.length / size;
//
//        baos = new ByteArrayOutputStream();
//        source.compress(Bitmap.CompressFormat.JPEG, 100 / rate, baos);
//        imageBytes = baos.toByteArray();
//
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        int rate = 50;

        while (baos.toByteArray().length > size) {

            baos = new ByteArrayOutputStream();
            source.compress(Bitmap.CompressFormat.JPEG, rate, baos);
            rate -= 10;
        }

        return BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
    }

    public static Bitmap resizeBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        Log.d("Bitmap", "Width: " + width + " - Height: " + height);

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
