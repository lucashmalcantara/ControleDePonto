package com.lucasalcantara.controledeponto.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lucasalcantara.controledeponto.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class EditarEntradaActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    TextView horaTextView = null;
    TextView dataTextView = null;
    Button feitoButton = null;

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

        dataTextView = (TextView) findViewById(R.id.dataTextView);
        dataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog(v);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        preencherTextViewData(calendar.get(Calendar.DAY_OF_MONTH), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR));
        preencherTextViewHora(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        feitoButton = (Button) findViewById(R.id.feitoButton);
        feitoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //region Referente aos dialogs

    public void mostrarTimePickerDialog(View v) {
        DialogFragment fragmento = new TimePickerFragment();
        fragmento.show(getSupportFragmentManager(), "timePicker");
    }

    public void mostrarDatePickerDialog(View v) {
        DialogFragment fragmento = new DatePickerFragment();
        fragmento.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        preencherTextViewHora(hourOfDay, minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        preencherTextViewData((dayOfMonth + 1), monthOfYear, year);
    }
    //endregion

    private void preencherTextViewData(int dia, int mes, int ano) {
        DecimalFormat df = new DecimalFormat("00");
        String data = String.valueOf(String.format("%1$s/%2$s/%3$s",
                df.format(dia), df.format(mes), ano));
        dataTextView.setText(data);
    }

    private void preencherTextViewHora(int hora, int minuto) {
        DecimalFormat df = new DecimalFormat("00");
        String horario = String.valueOf(df.format(hora)) + ":" + String.valueOf(df.format(minuto));
        horaTextView.setText(horario);
    }

    private void salvarEntrada(){

    }
}
