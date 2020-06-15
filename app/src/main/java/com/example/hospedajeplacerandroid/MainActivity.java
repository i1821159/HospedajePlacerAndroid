/*INTEGRANTES*/
/*
LAVADO CHUCO RENZO
ORTEGA SANTIVAÑEZ DANIEL
PORTUGAL ROBLES BRUCEE
*/

package com.example.hospedajeplacerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button irALogin = findViewById(R.id.btnirLogin);
        irALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(o);
            }
        });

        Button irARegister = findViewById(R.id.btnirRegister);
        irARegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(o);
            }
        });

        Button irABusquedaRes = findViewById(R.id.btnirBusquedaRes);
        irABusquedaRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(MainActivity.this, BusquedaRes.class);
                startActivity(o);
            }
        });

        Button irARooms = findViewById(R.id.btnirRooms);
        irARooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(MainActivity.this, RoomsActivity.class);
                startActivity(o);
            }
        });

    }
}
