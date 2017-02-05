package com.lucasalcantara.controledeponto.view;

import android.content.Context;
import android.content.Intent;
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

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.dbutils.ControleDePontoDBOpenHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUISICAO_ACTIVITY_EDITAR = 1;

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
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mToolbar.setTitle(R.string.meus_horarios);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        contexto = getApplicationContext();
        try {
            dbHelper = new ControleDePontoDBOpenHelper(this);
            entradaController = new EntradaController(dbHelper);
            if (savedInstanceState == null) {
                listaEntradaFragment = new ListaEntradaFragment();
                listaEntradaFragment.setAtributos(entradaController, contexto);
                editarEntradaFragment = new EditarEntradaFragment();
                editarEntradaFragment.setAtributos(entradaController, contexto);
                // Trabalhar sempre o mais local possível, para não dar proplema. Pois depois que ele da o commit não é possível dar outro.
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.rl_fragment_container, listaEntradaFragment);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarEditarEntradaActivity();
            }
        });

        //exibirEntradas();
    }


    private void carregarEditarEntradaActivity() {
        Intent intent = new Intent(this, EditarEntradaActivity.class);
        startActivityForResult(intent, REQUISICAO_ACTIVITY_EDITAR);
    }// loadProdutcLayout

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

        /*try {
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
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUISICAO_ACTIVITY_EDITAR) {
            if (data != null) {
                Bundle params = data.getExtras();

                if (params != null) {
                    if (params.getBoolean("salvou")) {
                        listaEntradaFragment.exibirEntradas();
                    }
                }
            }
        }
    }
}
