package sg.cse.wallet.pincode;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import sg.cse.wallet.R;
import sg.cse.wallet.prefs.Prefs;
import sg.cse.wallet.utils.Log;

public class PincodeFrag extends Fragment implements View.OnClickListener{
    private View view;

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
    private ImageView ivBackspace;

    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private ImageView ivFour;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_pincode, container, false);

        initView();
        setEventClick();

        return view;
    }

    private void initView() {
        btnOne = view.findViewById(R.id.btnOne);
        btnTwo = view.findViewById(R.id.btnTwo);
        btnThree = view.findViewById(R.id.btnThree);
        btnFour = view.findViewById(R.id.btnFour);
        btnFive = view.findViewById(R.id.btnFive);
        btnSix = view.findViewById(R.id.btnSix);
        btnSeven = view.findViewById(R.id.btnSeven);
        btnEight = view.findViewById(R.id.btnEight);
        btnNine = view.findViewById(R.id.btnNine);
        btnZero = view.findViewById(R.id.btnZero);
        ivBackspace = view.findViewById(R.id.ivBackspace);

        edtPincode = view.findViewById(R.id.edtPincode);

        ivOne = view.findViewById(R.id.ivOne);
        ivTwo = view.findViewById(R.id.ivTwo);
        ivThree = view.findViewById(R.id.ivThree);
        ivFour = view.findViewById(R.id.ivFour);

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
                        getActivity().finish();
                    } else {
                        edtPincode.getText().clear();
                    }
                }
                setEventChangePin();
            }
        });
    }

    public void setEventClick(){
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
        switch (v.getId()){
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

    public void setEventChangePin() {
        if (edtPincode.length()==1){
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.length()==2){
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.length()==3){
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        } else if (edtPincode.length()==4){
            ivOne.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
        }
        else if (edtPincode.length()==0){
            ivOne.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivTwo.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivThree.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            ivFour.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        }
    }
}
