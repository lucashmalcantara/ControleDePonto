package com.lucasalcantara.controledeponto.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.adapter.EntradaAdapter;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaEntradaFragment extends Fragment {

    private EntradaController entradaController = null;
    private Context contexto = null;
    ListView entradasListView = null;

    public ListaEntradaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_entrada, container, false);

        // Obtendo referencia ao elemento ListView
        entradasListView = (ListView) view.findViewById(R.id.entradasListView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        exibirEntradas();
    }

    public void setAtributos(EntradaController entradaController, Context contexto) {
        this.entradaController = entradaController;
        this.contexto = contexto;
    }

    private void exibirEntradas() {

        List<Entrada> entradas = null;
        try {
            entradas = entradaController.obterTodasEntradas();
            entradasListView = (ListView) getActivity().findViewById(R.id.entradasListView);
            entradasListView.setAdapter(new EntradaAdapter(contexto, entradas));
            entradasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //carregarLayoutEditarEntrada();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
