package com.newaphasiatalkhelper.newaphasiatalkhelper.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.newaphasiatalkhelper.newaphasiatalkhelper.R;
import com.newaphasiatalkhelper.newaphasiatalkhelper.models.FirebaseModel;

/**
 * Created by Tum on 10/29/2017 AD.
 */
//Create Toolbar

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    View logo, btnSos, userProfile;
    FirebaseModel fb;

    public void initToolbar() {
        fb = new FirebaseModel(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        userProfile = findViewById(R.id.btn_user_profile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Activity a = this;
        logo = findViewById(R.id.iv_toolbar_logo);
        btnSos = findViewById(R.id.btn_sos);
        logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                fb.logoaphasiaActivity();
            }
        });

        btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, SosActivities.class);
                startActivity(intent);
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

        public void hideUserProfileIcon(){
            userProfile.setVisibility(View.GONE);
    }


}
