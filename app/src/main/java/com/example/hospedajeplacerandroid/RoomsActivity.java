package com.example.hospedajeplacerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.hospedajeplacerandroid.adapters.RoomAdaptador;
import com.example.hospedajeplacerandroid.helpers.QueueUtils;
import com.example.hospedajeplacerandroid.models.Room;

import java.util.ArrayList;

public class RoomsActivity extends AppCompatActivity {

    ListView roomsList;
    RoomAdaptador roomAdaptador;
    QueueUtils.QueueObject queue = null;
    ImageLoader queueImage = null;
    ArrayList<Room> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        queue = QueueUtils.getInstance(this.getApplicationContext());
        queueImage = queue.getImageLoader();
        items = new ArrayList<>();
        Room.injectRoomsFromCloud(queue, items, this);

        roomsList = findViewById(R.id.roomsList);
        roomAdaptador = new RoomAdaptador(this, items, queueImage);
        roomsList.setAdapter(roomAdaptador);

        roomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //AQUI SE SOLICITA MAS DATOS EN LA NUBE
                
          Room registro = items.get(position);
          showDetails(registro);

            }
        });

        }

    public void showDetails(Room item){
        Intent o = new Intent(this, RoomDescriptionActivity.class);
        o.putExtra("roomId", item.id);
        startActivity(o);

    }

    public void refreshList(){
        if ( roomAdaptador!= null ) {
            roomAdaptador.notifyDataSetChanged();
        }
    }


    }

