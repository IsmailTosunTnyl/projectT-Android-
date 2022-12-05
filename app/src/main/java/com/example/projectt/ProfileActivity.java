package com.example.projectt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button DTS_btn,deliver_cargo_btn,carry_cargo_btn;
    TextView balance_text,star_text, fullname_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        init();
        placeData();

        DTS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // get the own cargo activity
                Intent intent = new Intent(ProfileActivity.this, OwnCargoActivity.class);
                startActivity(intent);
            }
        });




    }
    private void init() {
        DTS_btn = findViewById(R.id.DTS_btn);
        deliver_cargo_btn = findViewById(R.id.deliverCargo_btn);
        carry_cargo_btn = findViewById(R.id.carryCargo_btn);
        balance_text = findViewById(R.id.ProfilePayment_txt);
        star_text = findViewById(R.id.Star_txt);
        fullname_text = findViewById(R.id.profileFullName_txt);

    }

    private void placeData() {
      fullname_text.setText(API.user.getName() + " " + API.user.getLastname());
        balance_text.setText(API.user.getBalance() + " TL");
        star_text.setText(API.user.getStar()+" ");
    }

}
