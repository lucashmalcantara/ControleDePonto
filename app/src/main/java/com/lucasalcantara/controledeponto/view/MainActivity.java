package com.lucasalcantara.controledeponto.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.SimpleAdapter;

import com.lucasalcantara.controledeponto.R;
import com.lucasalcantara.controledeponto.adapter.EntradaAdapter;
import com.lucasalcantara.controledeponto.controller.EntradaController;
import com.lucasalcantara.controledeponto.dbutils.ControleDePontoDBOpenHelper;
import com.lucasalcantara.controledeponto.dbutils.ConversaoData;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ControleDePontoDBOpenHelper dbHelper = null;
    EntradaController entradaController = null;
    Context contexto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarLayoutEditarEntrada();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        contexto = getApplicationContext();
        dbHelper = new ControleDePontoDBOpenHelper(this);
        entradaController = new EntradaController(dbHelper);
      /*  try {
            //DESCOBRIR PQ ESTÁ DANDO ERRO
            dbHelper = new ControleDePontoDBOpenHelper(this);
            entradaController = new EntradaController(dbHelper);
            if (savedInstanceState == null) {
                expenseListFragment = new ExpenseListFragment();
                expenseListFragment.setAtributos(expenseController, myContext);
                newExpenseFragment = new NewExpenseFragment();
                newExpenseFragment.setAtributos(expenseController, myContext);
                // Trabalhar sempre o mais local possível, para não dar proplema. Pois depois que ele da o commit não é possivel dar outro.
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.layout_activity_expenses, expenseListFragment, LIST_TAG);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //region POPULANDO O LISTVIEW

      /*  List<Entrada> products = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            products.add(new Entrada(new Date("18/12/2016 17:00:00"), "Entrada " + i));
        }//for

        ListView productsListView = (ListView) findViewById(R.id.entradasListView);
        productsListView.setAdapter(new EntradaAdapter(this, products));
        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                carregarLayoutEditarEntrada();
            }
        });*/

        exibirEntradas();

        //endregion
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

    private void carregarLayoutEditarEntrada() {
        Intent intent = new Intent(this, EditarEntradaActivity.class);
        startActivity(intent);
    }

    public void exibirEntradas() {
        try {
          /*  List<Map<String, String>> values = new ArrayList<>();
            List<Entrada> entradaList = entradaController.obterTodasEntradas();
            for (Entrada e : entradaList) {
                Map<String, String> row = new HashMap<>();
                row.put("id", Long.toString(e.getID()));
                row.put("horario", ConversaoData.converteDeDate(e.getHorario()));
                row.put("descricao", e.getDescricao());
                values.add(row);
            }
            if (values.size() > 0) {
                // VERIFICAR POR QUE OS ITENS ESTÃO FICANDO EM BRANCO
                // Classe que permite popular um ListView de forma simplificada
                SimpleAdapter sa = new SimpleAdapter(contexto, values, R.layout.expense_row, new String[]{ "horario", "descricao"}, new int[]{ R.id.txtAmountList, R.id.txtDescList});
                expenseLV.setAdapter(sa);
            }*/

            List<Entrada> entradaList = entradaController.obterTodasEntradas();
            ListView productsListView = (ListView) findViewById(R.id.entradasListView);
            productsListView.setAdapter(new EntradaAdapter(this, entradaList));
            productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    carregarLayoutEditarEntrada();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
