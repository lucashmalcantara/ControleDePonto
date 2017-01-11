package com.lucasalcantara.controledeponto.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lucasalcantara.controledeponto.R;

import java.util.Calendar;

public class EditarEntradaActivity extends AppCompatActivity  implements OnCompleteListener {
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

    public void onComplete(String time) {
        // After the dialog fragment completes, it calls this callback.
        // use the string here
        String teste = time;
    }
}
