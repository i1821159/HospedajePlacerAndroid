package com.example.hospedajeplacerandroid.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hospedajeplacerandroid.MainActivity;
import com.example.hospedajeplacerandroid.RoomsActivity;
import com.example.hospedajeplacerandroid.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Room {
    public String number_room;
    public String type;


    public Room(String _numberroom, String _type) {
        this.number_room = _numberroom;
        this.type = _type;
    }

    public static ArrayList getCollection() {
        ArrayList<Room> collection = new ArrayList<>();
        collection.add(new Room("H101", "Doble"));
        collection.add(new Room("H102", "Matrimonial"));
        collection.add(new Room("H103", "Simple"));
        return collection;
    }

    public static void injectRoomsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Room> rooms,
                                               final RoomsActivity _interface) {
        String url = "https://polar-thicket-71266.herokuapp.com/api/rooms";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONArray list = response.getJSONArray("data");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    rooms.add(new Room(o.getString("number_room"),
                                            o.getString("type")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }

}
