package com.lucasalcantara.controledeponto.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.adapter.EntradaAdapter;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.interfaces.RecyclerViewOnClickListenerHack;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaEntradaFragment extends Fragment implements RecyclerViewOnClickListenerHack {
    private static final String TAG =  ListaEntradaFragment.class.getName();

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
        mEntradaRecyclerView.addOnItemTouchListener(new ReclyclerViewOnTouchListener(getActivity(), mEntradaRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mEntradaRecyclerView.setLayoutManager(llm);

        try {
            mEntradaList = mEntradaController.obterTodasEntradas();
        } catch (Exception e) {
            Log.e(TAG, "Erro ao listar todas as entradas.", e);
        }

        EntradaAdapter mAdapter = new EntradaAdapter(getActivity(), mEntradaList);
        // "This" porque é o nosso fragment que está implementando RecyclerViewOnClickListenerHack.
        //mAdapter.setRecyclerViewOnClickListenerHack(this);
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

    public void exibirEntradas() {

        try {
            mEntradaList = mEntradaController.obterTodasEntradas();
        } catch (Exception e) {
            Log.e(TAG, "Erro ao listar todas as entradas.", e);
        }

        EntradaAdapter mAdapter = (EntradaAdapter) mEntradaRecyclerView.getAdapter();
        mAdapter.setList(mEntradaList);
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onClickListener()\nPosição do item: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onLongPressClickListener()\nPosição do item: " + position, Toast.LENGTH_SHORT).show();
    }

    public static class ReclyclerViewOnTouchListener implements RecyclerView.OnItemTouchListener {

        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public ReclyclerViewOnTouchListener(Context context, final RecyclerView rv, RecyclerViewOnClickListenerHack reclyclerViewOnTouchListener) {
            mContext = context;
            mRecyclerViewOnClickListenerHack = reclyclerViewOnTouchListener;
            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View childView = rv.findChildViewUnder(e.getX(), e.getY());

                    if (childView != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(childView,
                                rv.getChildAdapterPosition(childView));
                        // [OLHAR] PODE SER QUE DÊ ERRO AQUI /\
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View childView = rv.findChildViewUnder(e.getX(), e.getY());

                    if (childView != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onClickListener(childView,
                                rv.getChildAdapterPosition(childView));
                        // [OLHAR] PODE SER QUE DÊ ERRO AQUI /\
                    }
                    // Quando colocamos como true, significa que interceptamos.
                    // Significa que trabalhamos com o evento que ocorreu.
                    // Fala para o Android que ele não precisa fazer mais nada, pois já estamos
                    // fazendo.
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            // Se não fizermos isto, os eventos de toque na tela não serão chamados.
            mGestureDetector.onTouchEvent(e);
            // Se colocarmos como true, o layout root do button "rouba" o evento do botão.
            // É como se ao clicar no button, quem respondesse fosse o layout em que ele se encontra.
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
