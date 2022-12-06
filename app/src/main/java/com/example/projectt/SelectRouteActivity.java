package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.projectt.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SelectRouteActivity extends AppCompatActivity  {
    Spinner sourceNodeSpinner, destinationNodeSpinner;
    Button selectRoute_btn;

    SupportMapFragment mapFragment;
    private ArrayAdapter<String> sourceAdapter, destinationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_route);
        init();




        API.getNodes(new VolleyCallBack() {
            @Override
            public void onSuccess() {

                String[] nodeNames = new String[Node.nodes.size()];
                for(int i = 0; i < Node.nodes.size(); i++){
                    nodeNames[i] = Node.nodes.get(i).getID()+"."+Node.nodes.get(i).getNodeName();
                }
                destinationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nodeNames);
                destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destinationNodeSpinner.setAdapter(destinationAdapter);
                sourceNodeSpinner.setAdapter(destinationAdapter);



            }

            @Override
            public void onFail() {
                //TODO
            }
        });

        destinationNodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mapRoute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });

        selectRoute_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destination = destinationNodeSpinner.getSelectedItem().toString();
                String source = sourceNodeSpinner.getSelectedItem().toString();

                String[] dest = destination.split("\\.");
                String[] sour = source.split("\\.");
                int destID = Integer.parseInt(dest[0]);
                int sourID = Integer.parseInt(sour[0]);



            }
        });






    }



    private void mapRoute(){
        String destination = destinationNodeSpinner.getSelectedItem().toString();
        String source = sourceNodeSpinner.getSelectedItem().toString();

        String[] dest = destination.split("\\.");
        String[] sour = source.split("\\.");
        int destID = Integer.parseInt(dest[0]);
        int sourID = Integer.parseInt(sour[0]);




        try {
            Node.nodes = new ArrayList<>();
            API.getNodes(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    Log.e("Success", "Success");


                }

                @Override
                public void onFail() {
                    Log.e("Fail", "Fail");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    private void init(){
        sourceNodeSpinner = findViewById(R.id.sourceNode_spinner);
        destinationNodeSpinner = findViewById(R.id.destinationNode_spinner);
        selectRoute_btn = findViewById(R.id.routeGo_btn);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


    }
}