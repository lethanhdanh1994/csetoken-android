package sg.cse.wallet.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.system.ErrnoException;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thongph on 12/30/2017
 */

public abstract class BaseCropImageActivity extends BaseDialogActivity {

    public static final int CROP_TYPE_OVAL = 0;
    public static final int CROP_TYPE_SQUARE = 1;
    public static final int CROP_TYPE_RECTANGLE = 2;
    public static final int CROP_TYPE_FREE = 3;

    private int cropType;

    public BaseCropImageActivity setCropType(int cropType) {
        this.cropType = cropType;
        return this;
    }

    private OnImageListener onImageListener;

    public void pickCropImage(OnImageListener onImageListener) {

        this.onImageListener = onImageListener;
        startPickImageActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE) {
                    Uri imageUri = getPickImageResultUri(data);
                    if (imageUri != null) {
                        beginCrop(imageUri);
                    }
                } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    if (onImageListener != null) {
                        onImageListener.onImageResult(result.getUri());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beginCrop(Uri uri) {
        switch (cropType) {
            case CROP_TYPE_OVAL:
                CropImage.activity(uri)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setAspectRatio(1, 1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(true)
                        .setAllowFlipping(false)
                        .start(this);
                break;
            case CROP_TYPE_SQUARE:
                CropImage.activity(uri)
                        .setAspectRatio(1, 1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(true)
                        .setAllowFlipping(false)
                        .start(this);
                break;
            case CROP_TYPE_RECTANGLE:
                CropImage.activity(uri)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(true)
                        .setAllowFlipping(false)
                        .start(this);
                break;
            case CROP_TYPE_FREE:
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(true)
                        .setAllowFlipping(false)
                        .start(this);
                break;
        }
    }

    public interface OnImageListener {

        void onImageResult(Uri imageUri);

        void onFail();
    }

    /**
     * Start pick image activity
     *
     * @param activity
     */
    private void startPickImageActivity(@NonNull Activity activity) {
        activity.startActivityForResult(getPickImageChooserIntent(), CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }


    /**
     * Create a chooser intent to select the  source to get image from.<br>
     * The source can be camera's  (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br>
     * All possible sources are added to the intent chooser.<br>
     * Use "pick_image_intent_chooser_title" string resource to override chooser title.
     */
    private Intent getPickImageChooserIntent() {

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getCaptureImageOutputUri());
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        allIntents.add(galleryIntent);
//        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
//        for (ResolveInfo res : listGallery) {
//            Intent intent = new Intent(galleryIntent);
//            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            intent.setPackage(res.activityInfo.packageName);
//            allIntents.add(intent);
//        }

        // the main intent is the last in the  list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
//        for (Intent childAllIntent : allIntents) {
//            if (childAllIntent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
//                mainIntent = childAllIntent;
//                break;
//            }
//        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main  intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture  by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    /**
     * Get the URI of the selected image from  {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera  and gallery image.
     *
     * @param data the returned data of the  activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
