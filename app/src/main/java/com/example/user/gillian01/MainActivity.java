package com.example.user.gillian01;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class MainActivity extends CustomizedActivity implements View.OnClickListener,EditText.OnEditorActionListener{
//public class MainActivity extends AppCompatActivity implements View.OnClickListener,EditText.OnEditorActionListener{
    public static final String selectedPokemonIndexKey = "selectedPokemonIndexKey"; // put to intent  : hashmap key value
    static final String[] pokemonNames ={"傑尼龜","妙蛙種子","可達鴨"};
    TextView infoText;
    EditText nameEditText;
    RadioGroup optionGroup;
    Button confirmBtn;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main); //enter

        handler = new Handler(this.getMainLooper()); // user state loop interacte


        avtivityName = this.getClass().getSimpleName();

        infoText =(TextView)findViewById(R.id.infoText);
        nameEditText =(EditText)findViewById(R.id.nameEditText);
        nameEditText.setOnEditorActionListener(MainActivity.this);
        nameEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        optionGroup =(RadioGroup)findViewById(R.id.optionGroup);
        confirmBtn = (Button)findViewById(R.id.confirmbutton);
        confirmBtn.setOnClickListener(MainActivity.this);
    }




    //  dont want to create another runable class
    Runnable jumpToNewActivityTask = new Runnable() {
        @Override
        public void run() {
            jumpToNewActivity();
        }
    };

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
            handler.postDelayed(jumpToNewActivityTask,3*1000); // delay 3 seconds
            //jumpToNewActivity();
        }


    }

    public int getSelectedPokemonIndex(){
        int  selectedradioButtonID = optionGroup.getCheckedRadioButtonId();
        View selectedRadioButtonView = optionGroup.findViewById(selectedradioButtonID);
        return optionGroup.indexOfChild(selectedRadioButtonView);

    }

    // jump activity
    private void jumpToNewActivity(){
        Intent intent =  new Intent();
        intent.setClass(MainActivity.this, PokemonListActivity.class);
        intent.putExtra(selectedPokemonIndexKey,getSelectedPokemonIndex()); // put to intent  key value
        startActivity(intent);

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
