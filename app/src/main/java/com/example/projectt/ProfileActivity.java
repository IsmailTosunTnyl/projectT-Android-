package com.example.projectt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button DTS_btn,TFS_btn,carry_cargo_btn,choose_cargo_btn,add_cargo_btn,temp_btn,driver_take_cargo_btn,driver_drop_cargo_btn,driver_map_btn;
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
                intent.putExtra("source","DTS" );
                startActivity(intent);
            }
        });

        TFS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                // get the own cargo activity
                Intent intent = new Intent(ProfileActivity.this, OwnCargoActivity.class);
                intent.putExtra("source","TFS" );
                startActivity(intent);
            }
        });



        choose_cargo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Intent intent = new Intent(ProfileActivity.this, SelectRouteActivity.class);
                startActivity(intent);
            }
        });

        add_cargo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Intent intent = new Intent(ProfileActivity.this, CargoAddActivity.class);
                startActivity(intent);
            }
        });
        temp_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Intent intent = new Intent(ProfileActivity.this, MapsActivity.class);
                intent.putExtra("source","map" );
                startActivity(intent);
            }
        });


        driver_take_cargo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Log.e("driver_take_cargo_btn","driver_take_cargo_btn");
                Intent intent = new Intent(ProfileActivity.this, OwnCargoActivity.class);
                intent.putExtra("source","DriverTakeCargo" );
                startActivity(intent);
            }
        });

        driver_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Log.e("driver_map_btn","driver_map_btn");
                Intent intent = new Intent(ProfileActivity.this, MapsActivity.class);
                intent.putExtra("source","routeCargo" );
                startActivity(intent);
            }
        });

        driver_drop_cargo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the own cargo activity
                Log.e("driver_drop_cargo_btn","driver_drop_cargo_btn");
                Intent intent = new Intent(ProfileActivity.this, OwnCargoActivity.class);
                intent.putExtra("source","DriverDropCargo" );
                startActivity(intent);
            }
        });


    }
    private void init() {
        DTS_btn = findViewById(R.id.DTS_btn);
        TFS_btn = findViewById(R.id.TFS_btn);
        add_cargo_btn = findViewById(R.id.addCargo_btn);
        balance_text = findViewById(R.id.ProfilePayment_txt);
        star_text = findViewById(R.id.Star_txt);
        fullname_text = findViewById(R.id.profileFullName_txt);
        choose_cargo_btn = findViewById(R.id.chooseCargo_btn);
        temp_btn = findViewById(R.id.temp_btn);
        driver_take_cargo_btn = findViewById(R.id.driver_takeCargo_btn);
        driver_drop_cargo_btn = findViewById(R.id.driver_dropCargo_btn);
        driver_map_btn = findViewById(R.id.driver_map_btn);

    }

    private void placeData() {
      fullname_text.setText(API.user.getName() + " " + API.user.getLastname());
        balance_text.setText(API.user.getBalance() + " TL");
        star_text.setText(API.user.getStar()+" ");
    }

}
