package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.projectt.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectRouteActivity extends AppCompatActivity  {
    Spinner sourceNodeSpinner, destinationNodeSpinner;
    Button selectRoute_btn;
    ListView routeListView;
    ProgressBar progressBar;
    int destID, sourID;

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
                progressBar.setVisibility(View.VISIBLE);

                String destination = destinationNodeSpinner.getSelectedItem().toString();
                String source = sourceNodeSpinner.getSelectedItem().toString();

                String[] dest = destination.split("\\.");
                String[] sour = source.split("\\.");
                destID = Integer.parseInt(dest[0]);
                sourID = Integer.parseInt(sour[0]);
                CargoItems.cargoItems = new ArrayList<>();
                API.route(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {

                        progressBar.setVisibility(View.GONE);
                        routeListView.setVisibility(View.VISIBLE);
                        String[] routes = new String[2];
                        //routes[0] = "Source: "+ Node.nodesForRoute.get(0).getNodeName()+" \nDestination: "+Node.nodesForRoute.get(Node.nodesForRoute.size()-1).getNodeName();
                        String temp ="Nodes To Visit (Advanced Route) : \n";
                        for (int i = 0; i < Node.nodesForRoute.size(); i++) {
                            temp += Node.nodesForRoute.get(i).getNodeName()+"\n";
                        }

                        //temp += "Destination: "+Node.nodesForRoute.get(Node.nodesForRoute.size()-1).getNodeName();
                        routes[1] = temp;
                        temp="";
                        routes[0] = "\nNodes To Visit (Base Route) : \n"+ sour[1]+"\n";
                        Log.e("Route", routes[1]);

                        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, routes);

                        //(C) adımı
                        routeListView.setAdapter(veriAdaptoru);




                    }

                    @Override
                    public void onFail() {

                    }
                },sourID,destID);

            }
        });

        routeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    CargoItems.routeItems = new ArrayList<>();
                    for (CargoItems cargoItem : CargoItems.cargoItems) {
                        if(cargoItem.getDestNodeID() == destID && cargoItem.getNodeID() == sourID){
                            CargoItems.routeItems.add(cargoItem);
                        }
                    }
                 //CargoItems.cargoItems = temp;







                Intent intent = new Intent(getApplicationContext(), OwnCargoActivity.class);
                intent.putExtra("route", position);
                Log.e("Route Position ", position+"");
                intent.putExtra("source", "SelectRouteActivity");
                startActivity(intent);
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
        routeListView = findViewById(R.id.route_listview);
        progressBar = findViewById(R.id.route_progressBar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


    }
}