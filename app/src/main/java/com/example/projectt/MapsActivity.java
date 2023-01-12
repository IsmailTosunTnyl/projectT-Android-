package com.example.projectt;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectt.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        try {
            API api = new API("mail60", Encriptions.encText("1234"), this);
            api.getNodes(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    Log.e("Success", "Success");
                    mapFragment.getMapAsync(MapsActivity.this);
                }

                @Override
                public void onFail() {
                    Log.e("Fail", "Fail");
                }
            });
        } catch (Exception e) {
            mapFragment.getMapAsync(MapsActivity.this);
            e.printStackTrace();
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String source = getIntent().getStringExtra("source");
        ArrayList<Node> nnodes;
        if (source.equals("routeCargo")){
            nnodes = Node.nodesForRoute;

        }else
            nnodes = Node.nodes;

        // Add a marker in Sydney and move the camera
        for (Node node : nnodes) {

            mMap.addMarker(new MarkerOptions().position(new LatLng(node.getLatitude(), node.getLongitude())).title(node.getNodeName()));
        }


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}