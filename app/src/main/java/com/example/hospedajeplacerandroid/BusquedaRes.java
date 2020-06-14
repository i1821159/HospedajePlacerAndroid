package com.example.hospedajeplacerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class BusquedaRes extends AppCompatActivity {
    private Spinner spNumClient;
    EditText etCin;
    EditText etOut;
    DatePickerDialog.OnDateSetListener setListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_res);

        /*Muesta el spinner los datos*/
        spNumClient = (Spinner)findViewById(R.id.spNumClientxml);

        String[] opciones = {"1 Huesped", "2 Huespedes", "3 Huespedes", "4 Huespedes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        spNumClient.setAdapter(adapter);

        /*Para el ListView Date de CheckIn */

        etCin = findViewById(R.id.et_CheckIn);

        Calendar calendarIn = Calendar.getInstance();
        final int yearIn = calendarIn.get(Calendar.YEAR);
        final int monthIn = calendarIn.get(Calendar.MONTH);
        final int dayIn = calendarIn.get(Calendar.DAY_OF_MONTH);

        etCin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BusquedaRes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearI, int monthI, int dayI) {
                        monthI = monthI+1;
                        String dateI = dayI+"/"+monthI+"/"+yearI;
                        etCin.setText(dateI);
                    }
                },yearIn,monthIn,dayIn);
                datePickerDialog.show();
            }
        });


        /*Para el ListView Date de CheckOut */

        etOut = findViewById(R.id.et_CheckOut);

        Calendar calendarOut = Calendar.getInstance();
        final int yearOut = calendarOut.get(Calendar.YEAR);
        final int monthOut = calendarOut.get(Calendar.MONTH);
        final int dayOut = calendarOut.get(Calendar.DAY_OF_MONTH);

        etOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BusquedaRes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearO, int monthO, int dayO) {
                        monthO = monthO+1;
                        String dateO = dayO+"/"+monthO+"/"+yearO;
                        etOut.setText(dateO);
                    }
                },yearOut,monthOut,dayOut);
                datePickerDialog.show();
            }
        });







    }


}