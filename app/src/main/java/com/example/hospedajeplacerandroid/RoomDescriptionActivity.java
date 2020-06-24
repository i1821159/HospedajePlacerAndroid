package com.example.hospedajeplacerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.hospedajeplacerandroid.helpers.QueueUtils;
import com.example.hospedajeplacerandroid.models.Room;

public class RoomDescriptionActivity extends AppCompatActivity {
    QueueUtils.QueueObject queue = null;
    int roomId;
    Room roomObject = new Room(0,"","","");
    ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_description);

        Intent o = getIntent();
        roomId =  o.getIntExtra("roomId", -1);
        if(roomId <= -1){
            Toast.makeText(this, "No se selecciono una Habitación", Toast.LENGTH_SHORT).show();
        }
        // CONSUMIR INFO DE HABITACION DE LA NUBE
        roomObject.id  = roomId;
        queue = QueueUtils.getInstance(this.getApplicationContext());
        imageLoader = queue.getImageLoader(); //imagen
        Room.injectRoomFromCloud(queue, roomObject, this);

        //LLAMAR A OTRO ACTIVITY DE LOS BOTONES
        Button btnComprar = findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(RoomDescriptionActivity.this, RegisterDataClienteActivity.class);
                startActivity(o);
            }
        });
    }
    public void refresh(){
        TextView txtHabitacionName = findViewById(R.id.txtHabitacionName);
        txtHabitacionName.setText(roomObject.number_room);

        NetworkImageView imgFoto = findViewById(R.id.imgFoto);
        imgFoto.setImageUrl(roomObject.urlImage, imageLoader);
    }
}