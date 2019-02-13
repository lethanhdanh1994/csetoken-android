package sg.cse.wallet.mvp.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.listener.DexterError;
import com.squareup.picasso.Picasso;
import com.yuyakaido.android.cardstackview.CardStackView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.CardStackViewProfileAdapter;
import sg.cse.wallet.base.BaseCropImageActivity;
import sg.cse.wallet.dialog.SAlert;
import sg.cse.wallet.helper.PickImageHelper;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.utils.Support;

public class ProfileActivity extends BaseCropImageActivity implements ProfileView {
    CardStackView cardStackView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_fullName)
    TextView tvFullName;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    ProfilePresenter mPresenter;
    @BindView(R.id.iv_editBtn)
    ImageView ivEditBtn;
    @BindView(R.id.iv_backBtn)
    ImageView ivBackBtn;

    private Boolean isAvtUpdate = false;


    @Override
    public int setView() {
        return R.layout.activity_profile;
    }

    @Override
    public void initView() {
        cardStackView = (CardStackView) findViewById(R.id.activity_main_card_stack_view);
        CardStackViewProfileAdapter adapter1 = new CardStackViewProfileAdapter(getApplicationContext());
        adapter1.add(new Coin("Paker", 100, "2222200K usd"));
        adapter1.add(new Coin("asd", 100, "2222200K usd"));
        adapter1.add(new Coin("Paker", 100, "2222200K usd"));
        adapter1.add(new Coin("asd", 100, "2222200K usd"));
        cardStackView.setAdapter(adapter1);
    }

    @Override
    public void initValue() {
        mPresenter = new ProfilePresenter(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle(R.string.profile);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // what do you want here
                if(isAvtUpdate){
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }else
                    finish();

            }
        });

        if (!Const.INFO_OBJECT.getResult().getUser().getAvatarUrl().equals("")) {
            Picasso.get().load(Const.INFO_OBJECT.getResult().getUser().getAvatarUrl()).into(ivAvatar);
        }

        tvFullName.setText(Const.INFO_OBJECT.getResult().getUser().getFullName());
    }

    @Override
    public void initAction() {


    }



    @OnClick(R.id.iv_avatar)
    public void onViewClicked() {
        new PickImageHelper(context, new PickImageHelper.OnPickImageListener() {
            @Override
            public void onAnyPermissionsDenied() {
                showAlert(context.getString(R.string.msg_permission_pick_photo_not_granted),
                        context.getString(R.string.upcase_go_to_settings),
                        context.getString(R.string.upcase_cancel),
                        new SAlert.OnActionClickListener() {
                            @Override
                            public void onPositiveClicked() {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 101);
                            }

                            @Override
                            public void onNegativeClicked() {

                            }
                        });
            }

            @Override
            public void onPermissionError(DexterError error) {
                dismissAll();
                showAlert(context.getString(R.string.msg_an_error_has_occurred) + "\n" + error.toString());
            }

            @Override
            public void onPickImageSuccess(Uri imageUri, String imageBase64) {
                dismissLoading();
                ivAvatar.setBackground(null);
                Picasso.get().load(imageUri).fit().into(ivAvatar);

                FirebaseStorage storage = FirebaseStorage.getInstance("gs://rock-terra-212709.appspot.com");
                StorageReference storageRef = storage.getReference();
                //StorageReference avatarRef = storageRef.child("Avatar");


                final StorageReference riversRef = storageRef.child("Avatar/" + imageUri.getLastPathSegment());
                UploadTask uploadTask = riversRef.putFile(imageUri);
                showLoading();

                // Register observers to listen for when the download is done or if it fails
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        exception.printStackTrace();
                        dismissAll();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...


                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                dismissAll();
                                mPresenter.uploadAvatar(uri.toString());
                            }
                        });


                    }
                });

            }

            @Override
            public void onPickImageFail() {
                dismissAll();
                showAlert(context.getString(R.string.msg_photo_not_updated));
            }
        }).requestPermissionsAndPickImage();
    }

    @Override
    public void uploadAvatarSuccess(String url) {
      Const.INFO_OBJECT.getResult().getUser().setAvatarUrl(url);
       isAvtUpdate =true;

    }

    @Override
    public void uploadAvatarFails() {

    }

    @Override
    public void onConnectionError() {

    }

    @OnClick({R.id.iv_editBtn, R.id.iv_backBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_editBtn:
                break;
            case R.id.iv_backBtn:
                if(isAvtUpdate){
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }else
                    finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if(isAvtUpdate){
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }else
            finish();
    }
}