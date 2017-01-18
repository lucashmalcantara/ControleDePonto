package com.lucasalcantara.controledeponto.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.adapter.EntradaAdapter;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.dbutils.ControleDePontoDBOpenHelper;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ControleDePontoDBOpenHelper dbHelper = null;
    EntradaController entradaController = null;
    EditarEntradaFragment editarEntradaFragment = null;
    ListaEntradaFragment listaEntradaFragment = null;
    Context contexto = null;

    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        contexto = getApplicationContext();
        try {
            //DESCOBRIR PQ ESTÁ DANDO ERRO
            dbHelper = new ControleDePontoDBOpenHelper(this);
            entradaController = new EntradaController(dbHelper);
            if (savedInstanceState == null) {
                listaEntradaFragment = new ListaEntradaFragment();
                listaEntradaFragment.setAtributos(entradaController, contexto);
                editarEntradaFragment = new EditarEntradaFragment();
                editarEntradaFragment.setAtributos(entradaController, contexto);
                // Trabalhar sempre o mais local possível, para não dar proplema. Pois depois que ele da o commit não é possivel dar outro.
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.drawer_layout, listaEntradaFragment);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft1 = fm.beginTransaction();
                ft1.replace(R.id.drawer_layout, editarEntradaFragment);
                ft1.addToBackStack(null);
                ft1.commit();
            }
        });

        exibirEntradas();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exibirEntradas() {
        try {
            List<Entrada> entradaList = entradaController.obterTodasEntradas();
            ListView productsListView = (ListView) findViewById(R.id.entradasListView);
            productsListView.setAdapter(new EntradaAdapter(this, entradaList));
            productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   // carregarLayoutEditarEntrada();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
