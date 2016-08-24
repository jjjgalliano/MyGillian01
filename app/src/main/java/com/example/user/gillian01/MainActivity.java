package com.example.user.gillian01;

import android.content.Context;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,EditText.OnEditorActionListener{

    static final String[] pokemonNames ={"傑尼龜","妙蛙種子","可達鴨"};
    TextView infoText;
    EditText nameEditText;
    RadioGroup optionGroup;
    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main); //enter

        infoText =(TextView)findViewById(R.id.infoText);
        nameEditText =(EditText)findViewById(R.id.nameEditText);
        nameEditText.setOnEditorActionListener(MainActivity.this);
        nameEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        optionGroup =(RadioGroup)findViewById(R.id.optionGroup);
        confirmBtn = (Button)findViewById(R.id.confirmbutton);
        confirmBtn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.confirmbutton)
        {
            //confirm

            Log.d("buttonTest","confirm button was clicked");
            String name = nameEditText.getText().toString();

            int  selectedradioButtonID = optionGroup.getCheckedRadioButtonId();
           View selectedRadioButtonView = optionGroup.findViewById(selectedradioButtonID);
           // int selectedIndex = optionGroup.indexOfChild(selectedRadioButtonView);

            RadioButton selectedRadioButton =(RadioButton)selectedRadioButtonView;
            String radionBtnText = selectedRadioButton.getText().toString();

            String welcomemessages = String.format("你好,訓練者 %s ,歡迎你來到這個世界,你的第一個夥伴是%s",name,radionBtnText );
            infoText.setText(welcomemessages);

        }


    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE)
        {
            InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            // setup android setting > keyboard language > current keyboard > hardware  show method keyboard > on
            inm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            //simulate button clicked
            confirmBtn.performClick();
            return true;

        }

        return false;
    }
}
