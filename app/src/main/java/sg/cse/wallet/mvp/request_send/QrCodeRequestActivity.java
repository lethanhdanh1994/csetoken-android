package sg.cse.wallet.mvp.request_send;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.prefs.Extras;
import sg.cse.wallet.utils.Support;

public class QrCodeRequestActivity extends Activity {
    private ImageView ivQrCode;
    private Button btnSave;
    private LinearLayout layoutQrCode;
    private TextView tvAmount;
    private TextView tvTotal;
    private TextView tvAddressRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qr_code_request);

        ivQrCode = findViewById(R.id.iv_qrCode);
        btnSave = findViewById(R.id.btnSave);
        layoutQrCode = findViewById(R.id.layout_QrCode);
        tvAmount = findViewById(R.id.tv_amount);
        tvTotal = findViewById(R.id.tv_total);
        tvAddressRequest = findViewById(R.id.tv_addressRequest);



        tvAmount.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_TOTAL));
        tvTotal.setText(Objects.requireNonNull(getIntent().getExtras()).getString(Extras.SENT_FROM));
        ivQrCode.setImageBitmap(Support.GenerateBarcodetoBitmap(tvAddressRequest.getText().toString()+tvAmount.getText().toString()));
        tvAddressRequest.setText(Objects.requireNonNull(getIntent().getExtras().getString(Extras.SENT_ADDRESS)));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSave();
            }
        });

    }

    public static Bitmap viewToBitmap(View view, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void startSave(){
        FileOutputStream fileOutputStream = null;
        File file = getDisc();
        if (!file.exists() && !file.mkdirs()){
            Toast.makeText(this, "Can't create directory to save image", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img" + date + ".jpg";
        String file_name = file.getAbsolutePath() + "/" + name;
        File new_file = new File(file_name);

        try{
            fileOutputStream = new FileOutputStream(new_file);
            Bitmap bitmap = viewToBitmap(layoutQrCode, layoutQrCode.getWidth(), layoutQrCode.getHeight());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Toast.makeText(this, "Save image success", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        refreshGallery(new_file);
    }

    public void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    private File getDisc(){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return  new File(file, "Image Demo");
    }
}
