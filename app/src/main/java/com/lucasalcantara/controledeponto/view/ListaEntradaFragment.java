package com.lucasalcantara.controledeponto.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.adapter.EntradaAdapter;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaEntradaFragment extends Fragment {

    private EntradaController mEntradaController = null;
    private Context mContexto = null;
    private RecyclerView mEntradaRecyclerView;
    private List<Entrada> mEntradaList = null;

    public ListaEntradaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_entrada, container, false);

        mEntradaRecyclerView = (RecyclerView) view.findViewById(R.id.rv_lista_entradas);
        // Com isso o tamanho do RecyclerView não muda.
        mEntradaRecyclerView.setHasFixedSize(true);
        mEntradaRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

              /*  LinearLayoutManager llm = (LinearLayoutManager) mEntradaRecyclerView.getLayoutManager();
                EntradaAdapter mAdapter = (EntradaAdapter) mEntradaRecyclerView.getAdapter();

                // Se a posição for igual, significa que estou exibindo o ultimo item, então
                // quero exibir mais alguns itens.
                if (mEntradaList.size() == llm.findLastCompletelyVisibleItemPosition() + 1){
                     List<Entrada> listAux = null; // Aqui é onde devemos adicionar mais entradas...

                    for (int i = 0; i < listAux.size(); i++) {
                        mAdapter.addListItem(listAux.get(i), mEntradaList.size());
                    }
                }*/
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mEntradaRecyclerView.setLayoutManager(llm);

        try {
            mEntradaList = mEntradaController.obterTodasEntradas();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EntradaAdapter mAdapter = new EntradaAdapter(getActivity(), mEntradaList);
        mEntradaRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        exibirEntradas();
    }

    public void setAtributos(EntradaController entradaController, Context contexto) {
        this.mEntradaController = entradaController;
        this.mContexto = contexto;
    }

    private void exibirEntradas() {

   /*     List<Entrada> entradas = null;
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
        }*/
    }
}
