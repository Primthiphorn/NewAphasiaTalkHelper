package com.newaphasiatalkhelper.newaphasiatalkhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.newaphasiatalkhelper.newaphasiatalkhelper.R;
import com.newaphasiatalkhelper.newaphasiatalkhelper.helpers.Speaker;
import com.newaphasiatalkhelper.newaphasiatalkhelper.models.FavoriteModel;
import com.newaphasiatalkhelper.newaphasiatalkhelper.models.FirebaseModel;
import com.newaphasiatalkhelper.newaphasiatalkhelper.models.FrequencyModel;

public class TypeTextActivity extends BaseActivity {

    View btnClear, btnSpeech, btnHome,btnAddFav;
    EditText etType;
    FavoriteModel favoriteModel;
    FrequencyModel frequencyModel;
    View area;

    FirebaseModel fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_text);
        initToolbar();

        fb = new FirebaseModel(this);
        fb.enterTypeTextActivity();

        frequencyModel = new FrequencyModel(this);
        favoriteModel = new FavoriteModel(this);
        btnClear = findViewById(R.id.btn_clear);
        btnSpeech = findViewById(R.id.btn_speech);
        btnHome = findViewById(R.id.btn_home);
        etType = (EditText) findViewById(R.id.et_type);
        btnAddFav = findViewById(R.id.btn_add_fav);
        area = findViewById(R.id.area);


        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etType.setText("");
            }
        });
        fb.clearTypeTextActivity();

       btnHome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
       fb.backhomeActivity();

       btnSpeech.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String tts = etType.getText().toString();
               Speaker.speak(tts);
               fb.speechTypeTextActivity(tts);

              // frequencyModel.incrementFreq(tts);

           }
       });

        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentence = etType.getText().toString();
                if(favoriteModel.search(sentence)== null)
                {
                favoriteModel.add(sentence);
                    Toast.makeText(TypeTextActivity.this,"เพิ่มไปยังรายการโปรดเรียบร้อย" , Toast.LENGTH_SHORT).show();
                    fb.addfavTypeTextActivity(sentence);
                }
                else{
                    Toast.makeText(TypeTextActivity.this,"มีในรายการโปรดอยู่แล้ว" , Toast.LENGTH_SHORT).show();
                    fb.addfavTypeTextActivity(sentence);
                }
            }
        });

    }

    void  hideKeyboard(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Speaker.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Speaker.onStop();
    }

}
