package com.example.projectt;

import android.util.Log;

import java.util.ArrayList;

public class Node {
    private int ID;
    private String nodeName;
    private double latitude;
    private double longitude;
    public static ArrayList<Node> nodes = new ArrayList<>();

    public Node(int ID, String nodeName, double latitude, double longitude) {
        this.ID = ID;
        this.nodeName = nodeName;
        this.latitude = latitude;
        this.longitude = longitude;
        Node.nodes.add(this);
        Log.e("Node", "Node created "+this.nodeName+nodes.size());

    }

    public int getID() {
        return ID;
    }

    public String getNodeName() {
        return nodeName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
