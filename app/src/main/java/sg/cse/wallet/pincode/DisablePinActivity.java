package sg.cse.wallet.pincode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import sg.cse.wallet.R;
import sg.cse.wallet.prefs.Prefs;

public class DisablePinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtPincode;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private TextView tvTitle;
    private TextView tvTitlePin;
    private ImageView ivBackspace;

    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private ImageView ivFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
        initView();
        setEventClick();
        tvTitlePin.setText("Disable Pincode");
    }

    private void initView() {
        tvTitlePin = findViewById(R.id.tv_titlePin);
        tvTitle = findViewById(R.id.tv_title);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        ivBackspace = findViewById(R.id.ivBackspace);

        edtPincode = findViewById(R.id.edtPincode);

        ivOne = findViewById(R.id.ivOne);
        ivTwo = findViewById(R.id.ivTwo);
        ivThree = findViewById(R.id.ivThree);
        ivFour = findViewById(R.id.ivFour);

        //Lắng nghe thay đổi của edtPinCode.
        edtPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String myCode = edtPincode.getText().toString();
                if (myCode.length() == 4) {
                    if (myCode.equals(Prefs.getInstance().getPinCode())) {
                        finish();
                        Prefs.getInstance().removePinCode();
                    } else {
                        edtPincode.getText().clear();
                    }
                }
                setEventChangePin();
            }
        });
    }

    public void setEventClick() {
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        ivBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                edtPincode.append("1");
                break;
            case R.id.btnTwo:
                edtPincode.append("2");
                break;
            case R.id.btnThree:
                edtPincode.append("3");
                break;
            case R.id.btnFour:
                edtPincode.append("4");
                break;
            case R.id.btnFive:
                edtPincode.append("5");
                break;
            case R.id.btnSix:
                edtPincode.append("6");
                break;
            case R.id.btnSeven:
                edtPincode.append("7");
                break;
            case R.id.btnEight:
                edtPincode.append("8");
                break;
            case R.id.btnNine:
                edtPincode.append("9");
                break;
            case R.id.btnZero:
                edtPincode.append("0");
                break;
            case R.id.ivBackspace:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtPincode, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
        }
    }

    private void setEventChangePin() {
        if (edtPincode.getText().toString().length() == 1) {
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.getText().toString().length() == 2) {
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.getText().toString().length() == 3) {
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.getText().toString().length() == 4) {
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
        } else if (edtPincode.getText().toString().length() == 0) {
            ivOne.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        }
    }
}
