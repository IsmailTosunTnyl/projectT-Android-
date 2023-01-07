package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        String takeordelivery = getIntent().getStringExtra("takeordelivery");
        Log.e("kind", kind);
        openBox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kind.equals("destination") || kind.equals("node")){
                    Log.e("kind", "USER");
                API.closeBox( new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        message_txt.setText("Box closed");
                    }

                    @Override
                    public void onFail() {
                        message_txt.setText("Box not closed");
                    }
                },CargoID, NodeID,kind);}
                else{
                    Log.e("kind", "DRIVER");
                API.opendriverbox( new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        message_txt.setText("Box closed");
                        Log.e("Box closed, node Id", ""+NodeID);
                    }

                    @Override
                    public void onFail() {
                        message_txt.setText("Box not closed");
                    }
                },CargoID, NodeID,"close",takeordelivery);}

            // remove page from stack but wait for the response





            }



        });



    }


    public void init(){
        openBox_btn = findViewById(R.id.openbox_btn);
        message_txt = findViewById(R.id.openboxmessage_txt);
    }
}