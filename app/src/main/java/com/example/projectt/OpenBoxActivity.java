package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OpenBoxActivity extends AppCompatActivity {
    Button openBox_btn;
    TextView message_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_box_msg);

        init();
        int CargoID = getIntent().getIntExtra("CargoID", 0);
        int NodeID = getIntent().getIntExtra("NodeID", 0);
        String kind = getIntent().getStringExtra("kind");
        Log.e("kind", kind);
        openBox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.closeBox( new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        message_txt.setText("Box closed");
                    }

                    @Override
                    public void onFail() {
                        message_txt.setText("Box not closed");
                    }
                },CargoID, NodeID,kind);
            }
        });



    }


    public void init(){
        openBox_btn = findViewById(R.id.openbox_btn);
        message_txt = findViewById(R.id.openboxmessage_txt);
    }
}