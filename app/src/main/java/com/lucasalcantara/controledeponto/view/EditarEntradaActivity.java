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
import android.widget.Toast;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.controller.EntradaController;

import java.text.DecimalFormat;
import java.util.Calendar;

public class EditarEntradaActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private EntradaController entradaController = null;

    TextView horaTextView = null;
    TextView dataTextView = null;
    Button feitoButton = null;

    int hora, minuto;
    int dia, mes, ano;

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
        preencherTextViewData(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        preencherTextViewHora(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        feitoButton = (Button) findViewById(R.id.feitoButton);
        feitoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEntrada();
            }
        });
    }

    //region Referente aos dialogs

    public void mostrarTimePickerDialog(View v) {
        Bundle params = new Bundle();
        params.putString("hora", String.valueOf(hora));
        params.putString("minuto", String.valueOf(minuto));

        DialogFragment fragmento = new TimePickerFragment();
        fragmento.setArguments(params);
        fragmento.show(getSupportFragmentManager(), "timePicker");
    }

    public void mostrarDatePickerDialog(View v) {
        Bundle params = new Bundle();
        params.putString("dia", String.valueOf(dia));
        params.putString("mes", String.valueOf(mes));
        params.putString("ano", String.valueOf(ano));

        DialogFragment fragmento = new DatePickerFragment();
        fragmento.setArguments(params);
        fragmento.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        preencherTextViewHora(hourOfDay, minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        preencherTextViewData(dayOfMonth, monthOfYear, year);
    }
    //endregion

    private void preencherTextViewData(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;

        DecimalFormat df = new DecimalFormat("00");
        String data = String.valueOf(String.format("%1$s/%2$s/%3$s",
                df.format(dia), df.format(mes + 1), ano));
        dataTextView.setText(data);
    }

    private void preencherTextViewHora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;

        DecimalFormat df = new DecimalFormat("00");
        String horario = String.valueOf(df.format(hora)) + ":" + String.valueOf(df.format(minuto));
        horaTextView.setText(horario);
    }

    private void salvarEntrada() {
        EntradaController.ERRO retval = EntradaController.ERRO.ERRO_INTERNO;

        try {
            retval = entradaController.inserirEntrada("","");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String mag = "";
            switch (retval) {
                case SUCESSO:
                    mag = "Entrada incluida com sucesso.";
                    break;
                case VALOR_DE_HORARIO_INVALIDO:
                    mag = "Horario invalido.";
                    break;
                case ERRO_INTERNO:
                    mag = "Erro interno.";
                    break;
            }

            // Exibe uma mensagem na tela
            Toast t = Toast.makeText(this, mag, Toast.LENGTH_LONG);
            t.show();
            //  expenseListFragment.showExpenses();
        }
    }

    public void setAtributos(EntradaController entradaController) {
        this.entradaController = entradaController;
    }
}
