package com.example.hospedajeplacerandroid.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hospedajeplacerandroid.MainActivity;
import com.example.hospedajeplacerandroid.RoomDescriptionActivity;
import com.example.hospedajeplacerandroid.RoomsActivity;
import com.example.hospedajeplacerandroid.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Room {
    public int id;
    public String number_room;
    public String type;
    public String urlImage;


    public Room(int _id, String _number_room, String _type, String _urlImage) {
        this.id = _id;
        this.number_room = _number_room;
        this.type = _type;
        this.urlImage = _urlImage;
    }

    public static ArrayList getCollection() {
        ArrayList<Room> collection = new ArrayList<>();
        collection.add(new Room(0,"H101", "Doble" , ""));
        collection.add(new Room(0,"H102", "Matrimonial", ""));
        collection.add(new Room(0,"H103", "Simple", ""));
        return collection;
    }

    public static void injectRoomFromCloud(final QueueUtils.QueueObject o,
                                           final Room room,
                                           final RoomDescriptionActivity _interface){
        String url = "https://polar-thicket-71266.herokuapp.com/api/rooms/" + room.id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONObject objeto = response.getJSONObject("data");
                                room.number_room = objeto.getString("number_room");
                                room.type = objeto.getString("type");
                                room.urlImage = objeto.getString("urlImages");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refresh(); // Esta función debemos implementarla
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

    public static void injectRoomsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Room> rooms,
                                               final RoomsActivity _interface) {
        String url = "https://polar-thicket-71266.herokuapp.com/api/rooms/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONArray list = response.getJSONArray("data");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    rooms.add(new Room(o.getInt("id"),  o.getString("number_room"),
                                            o.getString("type"), o.getString("urlImages")));
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
