package com.example.projectt;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class API {
    static String mail;
    static String password_encrypted;
    static Context context;
    static String baseURL;
    static RequestQueue queue;
    static boolean  isSignedIn;
    static String firstName;
    static String lastName;
    static String phone;
    static String address;
    static String nationalID;
    static User user = new User();

    public API(String mail, String password_encrypted, Context context) {
        this.mail = mail;
        this.password_encrypted = password_encrypted;
        this.context = context;
        this.baseURL = "http://192.168.1.30/";
        this.queue = Volley.newRequestQueue(context);


    }

    public static void getNodes(){


        //String url ="http://
        String url = baseURL + "nodeall/" + mail +"/"+ password_encrypted;

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textViewResult.setText("Response is: "+ response.toString());
                        Log.e("Response Request", response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("node");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String name = jsonObject.getString("nodeName");
                                int ID = jsonObject.getInt("ID");
                                double latitude = jsonObject.getDouble("latitude");
                                double longitude = jsonObject.getDouble("longitude");
                                new Node(ID, name, latitude, longitude);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textViewResult.setText("That didn't work!"+ error.toString());
                Log.e("Error Response ", error.toString());
            }
        });

        queue.add(jsonObjectRequest);

    }

    public static void getAllCargos(){

        //String url ="http://
        String url = baseURL+"cargoall/" + mail +"/"+ password_encrypted;

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textViewResult.setText("Response is: "+ response.toString());
                        Log.e("Response Request", response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("Cargos");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                int ID = jsonObject.getInt("ID");
                                String type = jsonObject.getString("Type");
                                double weight = jsonObject.getDouble("Weight");
                                double volume = jsonObject.getDouble("Volume");
                                double value = jsonObject.getDouble("Value");
                                new Cargo(ID, type, weight, volume, value);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textViewResult.setText("That didn't work!"+ error.toString());
                Log.e("Error Response ", error.toString());
            }
        });

        queue.add(jsonObjectRequest);

    }

    public static void signUp(final VolleyCallBack callBack,String firstName,String lastName,String password,String email,String address,String phone,String nationalID ){
        try {
            API.password_encrypted = Encriptions.encText(password);
            API.firstName = firstName;
            API.lastName = lastName;
            API.address = address;
            API.phone = phone;
            API.nationalID = nationalID;
            API.mail = email;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //String url ="http://
        String url = baseURL+"signup/"+firstName+"/"+lastName+"/"+password_encrypted+"/"+email+"/"+address+"/"+phone+"/"+nationalID;
        Log.e("SignUp Request","Request Sent");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textViewResult.setText("Response is: "+ response.toString());
                        Log.e("SignUp Request", response.toString());
                        try {
                            JSONObject jsonArray = response.getJSONObject("ok");
                            //TODO print ok message
                            Log.e("SignUp Request","User Created");
                            callBack.onSuccess();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("SignUp Request",e.toString());
                            Log.e("SignUp Request","Something Worng I can feel It");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding

                try {
                callBack.onFail();

                if(error.networkResponse.data!=null) {
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        Log.e("Sigup Error",body+" CODE: "+statusCode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }}

                }catch (Exception e){
                    Log.e("error response",e.toString());

                }

                //TODO print error message USER EXİSTS
            }
        });
        queue.add(jsonObjectRequest);


    }

    public static void addCargo(int ownerID,int reciverID,String type,double weight,double volume,int nodeID,String status){


        String url = baseURL+"cargoadd/"+ mail +"/"+ password_encrypted +"/"+ownerID+"/"+reciverID+"/"+type+"/"+weight+"/"+volume+"/"+nodeID+"/"+status;
        Log.e("AddCargo Request","Request Sent");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textViewResult.setText("Response is: "+ response.toString());
                        Log.e("AddCargo Request", response.toString());
                        try {
                            JSONObject jsonArray = response.getJSONObject("ok");
                            //TODO print ok message
                            Log.e("AddCargo Request", "Cargo Created");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("AddCargo Request", e.toString());
                            Log.e("AddCargo Request", "Something Worng I can feel It");
                        }}},new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
                //get status code here

                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        String statusCode = String.valueOf(error.networkResponse.statusCode);
                        body = new String(error.networkResponse.data,"UTF-8");
                        Log.e("Add Cargo Error",body+" CODE: "+statusCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("AddCargo Request", e.toString());
                    }
                }

                //TODO print error message USER EXİSTS
            }
        });
        queue.add(jsonObjectRequest);






    }


    public static boolean signIn(final VolleyCallBack callBack) {

        String url = baseURL+"login/"+ mail +"/"+ password_encrypted;
        Log.e("signIn Request","Request Sent");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        API.isSignedIn = true;
                        try {
                            JSONObject jsonArray = response.getJSONObject("user");
                            user.setId(jsonArray.getInt("ID"));
                            user.setName(jsonArray.getString("Name"));
                            user.setLastname(jsonArray.getString("LastName"));
                            user.setAdress(jsonArray.getString("Adress"));
                            user.setPhone(jsonArray.getString("Phone"));
                            user.setNationalID(jsonArray.getString("NationalID"));
                            user.setMail(jsonArray.getString("Mail"));
                            user.setBalance(jsonArray.getDouble("Balance"));
                            user.setStar(jsonArray.getDouble("Star"));

                            Log.e("Sigın Request", user.getName()+" "+user.getLastname());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        //textViewResult.setText("Response is: "+ response.toString());
                        Log.e("Login Request", response.toString());
                        callBack.onSuccess();
                       }},new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                API.isSignedIn = false;
                String body;
                //get status code here
                String errorstr =  error.toString();
                Log.e("Volley error",errorstr);
                //get response body and parse with appropriate encoding
                try {
                    if(error.networkResponse.data!=null) {
                        String statusCode = String.valueOf(error.networkResponse.statusCode);
                        body = new String(error.networkResponse.data,"UTF-8");
                        Log.e("Login Error",body+" CODE: "+statusCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Login Request", e.toString());
                }



                //TODO print error message USER EXİSTS
            }
        });
        queue.add(jsonObjectRequest);




        return isSignedIn;
    }
}









