package com.example.hospedajeplacerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import com.example.hospedajeplacerandroid.adapters.RoomAdaptador;
import com.example.hospedajeplacerandroid.helpers.QueueUtils;
import com.example.hospedajeplacerandroid.models.Room;

import java.util.ArrayList;

public class RoomsActivity extends AppCompatActivity {

    ListView roomsList;
    RoomAdaptador roomAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Room> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Room.injectRoomsFromCloud(queue, items, this);

        roomsList = findViewById(R.id.roomsList);
        roomAdaptador = new RoomAdaptador(this, items);
        roomsList.setAdapter(roomAdaptador);
        }

    public void refreshList(){
        if ( roomAdaptador!= null ) {
            roomAdaptador.notifyDataSetChanged();
        }
    }


    }

