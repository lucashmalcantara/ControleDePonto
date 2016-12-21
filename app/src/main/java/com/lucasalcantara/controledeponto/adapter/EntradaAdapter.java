package com.lucasalcantara.controledeponto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Lucas on 18/12/2016.
 */
public class EntradaAdapter extends BaseAdapter {

    private Context context;
    private List<Entrada> entradaList;

    public EntradaAdapter(Context context, List<Entrada> productList) {
        this.context = context;
        this.entradaList = productList;
    }

    @Override
    public int getCount() {
        return entradaList.size();
    }

    @Override
    public Object getItem(int position) {
        //Fazer tratamento de exceções
        return entradaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entrada entrada = entradaList.get(position);
        View layout;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Não queremos vicular este layout ao ViewGroup passado como parametro, por isso null
            layout = layoutInflater.inflate(R.layout.entrada, null);
        } else {
            layout = convertView;
        }

        TextView horarioTextView = (TextView) layout.findViewById(R.id.horarioTextView);
        horarioTextView.setText(entrada.getHorario().toString());

        TextView descricaoTextView = (TextView) layout.findViewById(R.id.descricaoTextView);
        descricaoTextView.setText(entrada.getDescricao());

        // Retorna o layout personalizado para ser a nossa linha
        return layout;
    }//getView
}
