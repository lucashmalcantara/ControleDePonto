package com.lucasalcantara.controledeponto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.interfaces.RecyclerViewOnClickListenerHack;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lucas on 18/12/2016.
 */
public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.MeuViewHolder> {

    private static final String TAG =  EntradaAdapter.class.getName();

    private List<Entrada> mEntradaList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public EntradaAdapter(Context c, List<Entrada> l) {
        this.mEntradaList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // É chamado quando há a necessidade de criar uma nova view.
    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.entrada, parent, false);
        MeuViewHolder mvh = new MeuViewHolder(v);
        return mvh;
    }

    @Override
    public int getItemCount() {
        return mEntradaList.size();
    }

    // Este método é chamado toda hora.
    // Ele que vincula os dados da nossa lista à view, mesmo que a view esteja sendo reutilizada.
    @Override
    public void onBindViewHolder(MeuViewHolder holder, int position) {
        DateFormat df = new SimpleDateFormat("dd/MM - HH:mm");
        holder.tvHorario.setText(df.format(mEntradaList.get(position).getHorario()));
        holder.tvDescricao.setText(mEntradaList.get(position).getDescricao());

        try {
            // Responsável pela animação da view.
            YoYo.with(Techniques.Landing)
                    .duration(700)
                    .playOn(holder.itemView);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao animar o item da view.", e);
        }
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Entrada e, int position) {
        mEntradaList.add(e);
        notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        mEntradaList.remove(position);
        notifyItemRemoved(position);
    }

    // Trabalha chache. Guarda a view para ser reutilizada.
    public class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvHorario;
        public TextView tvDescricao;

        public MeuViewHolder(View itemView) {
            super(itemView);

            tvHorario = (TextView) itemView.findViewById(R.id.tv_horario);
            tvDescricao = (TextView) itemView.findViewById(R.id.tv_descricao);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }
}
