package com.lucasalcantara.controledeponto.view;


import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Lucas on 05/01/2017.
 */
public class TimePickerFragment extends DialogFragment {

    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;

        // Este erro lembrará de implementar OnTimeSetListener
        // na sua Activity caso esqueça.
        try {
            mListener = (TimePickerDialog.OnTimeSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hora, minuto;

        if (getArguments().isEmpty() == false) {
            hora = Integer.parseInt(getArguments().getString("hora"));
            minuto = Integer.parseInt(getArguments().getString("minuto"));
        } else {
            // Pega a data atual.
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);
        }

        // Cria uma instância do TimePickerDialog e retorna.
        return new TimePickerDialog(mActivity, mListener, hora, minuto,
                DateFormat.is24HourFormat(mActivity));
    }
}