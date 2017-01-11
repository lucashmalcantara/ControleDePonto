package com.lucasalcantara.controledeponto.view;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lucasalcantara.controledeponto.R;

import java.text.DecimalFormat;

public class EditarEntradaActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener {
    TextView horaTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_entrada);

        horaTextView = (TextView) findViewById(R.id.horaTextView);
        horaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePickerDialog(v);
            }
        });
    }

    public void mostrarTimePickerDialog(View v) {
        DialogFragment fragmento = new TimePickerFragment();
        fragmento.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        DecimalFormat df = new DecimalFormat("00");
        String hora = String.valueOf(df.format(hourOfDay)) + ":" + String.valueOf(df.format(minute));
        horaTextView.setText(hora);
    }
}
