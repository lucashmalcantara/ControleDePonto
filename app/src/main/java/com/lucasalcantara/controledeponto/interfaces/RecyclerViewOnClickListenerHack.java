package com.lucasalcantara.controledeponto.interfaces;

import android.view.View;

/**
 * Created by Lucas on 28/01/2017.
 *
 * Interface utilizada para auxiliar o OnClick do RecyclerView.
 */

public interface RecyclerViewOnClickListenerHack {
    public void onClickListener(View view, int position);
    public void onLongPressClickListener(View view, int position);
}
