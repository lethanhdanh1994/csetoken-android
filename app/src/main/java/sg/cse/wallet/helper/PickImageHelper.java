package sg.cse.wallet.helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.Preconditions;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.util.List;

import sg.cse.wallet.base.BaseCropImageActivity;
import sg.cse.wallet.utils.BitmapUtils;

import static android.support.v4.util.Preconditions.*;




/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class PickImageHelper {

    private Context context;
    private OnPickImageListener listener;

    @SuppressLint("RestrictedApi")
    public PickImageHelper(Context context, OnPickImageListener listener) {
        this.context = context;
        this.listener = checkNotNull(listener, "OnPickImageListener cannot null!");
    }

    /**
     * Request permissions and start pick image
     */
    public void requestPermissionsAndPickImage() {
        Dexter.withActivity(((BaseCropImageActivity) context))
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(final MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            startPickImage();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            listener.onAnyPermissionsDenied();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        listener.onPermissionError(error);
                    }
                })
                .check();
    }

    /**
     * Pick image
     */
    private void startPickImage() {
        ((BaseCropImageActivity) context).setCropType(BaseCropImageActivity.CROP_TYPE_SQUARE);
        ((BaseCropImageActivity) context).pickCropImage(new BaseCropImageActivity.OnImageListener() {
            @Override
            public void onImageResult(Uri imageUri) {
                Bitmap avtBitmap = BitmapUtils.fromUri(imageUri);
                avtBitmap = BitmapUtils.resizeBitmap(avtBitmap, 500);
                String imageBase64 = BitmapUtils.encodeToBase64(avtBitmap);
                // upload avatar base64
                listener.onPickImageSuccess(imageUri, imageUri.getPath());
            }

            @Override
            public void onFail() {
                listener.onPickImageFail();
            }
        });
    }

    public interface OnPickImageListener {
        void onAnyPermissionsDenied();

        void onPermissionError(DexterError error);

        void onPickImageSuccess(Uri imageUri, String imageBase64);

        void onPickImageFail();
    }
}
