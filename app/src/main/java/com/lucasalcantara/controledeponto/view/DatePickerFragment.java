package com.lucasalcantara.controledeponto.view;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by Lucas on 05/01/2017.
 */
public class DatePickerFragment extends DialogFragment {

    private Activity mActivity;
    private DatePickerDialog.OnDateSetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;

        // Este erro lembrará de implementar OnDateSetListener
        // na sua Activity caso esqueça.
        try {
            mListener = (DatePickerDialog.OnDateSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int dia, mes, ano;

        if (getArguments().isEmpty() == false) {
            dia = Integer.parseInt(getArguments().getString("dia"));
            mes = Integer.parseInt(getArguments().getString("mes"));
            ano = Integer.parseInt(getArguments().getString("ano"));
        } else {
            final Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);
        }

        return new DatePickerDialog(mActivity, mListener, ano, mes, dia);
    }
}