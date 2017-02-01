package com.lucasalcantara.controledeponto.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lucasalcantara.controledeponto.dbutils.ControleDePontoDBOpenHelper;

/**
 * Created by Lucas on 18/12/2016.
 */
public class EntradaDAO {

    private ControleDePontoDBOpenHelper dbHelper = null;
    private static final String ENTRADA_TBL = "entry";
    private static final String ID_ENTRADA = "_id";
    private static final String DATA_ENTRADA = "date";
    private static final String DESCRICAO_ENTRADA = "description";
    private static final String[] ALL_COLUMNS = {ID_ENTRADA, DATA_ENTRADA, DESCRICAO_ENTRADA};

    public EntradaDAO(ControleDePontoDBOpenHelper helper) {
        this.dbHelper = helper;
    }

    private static final String EXPENSE_CREATE_QUERY = "create table " + ENTRADA_TBL + " (" +
            ID_ENTRADA + " integer primary key autoincrement," + // olhar esse auto increment
            DESCRICAO_ENTRADA + " text, " +
            DATA_ENTRADA + " long " +
            " );";

    public static void createTable(SQLiteDatabase db) {
        db.execSQL(EXPENSE_CREATE_QUERY);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ENTRADA_TBL);
    }

    // Métodos de banco de dados, em geral, podem lançar exceções.
    public void insertEntrada(Entrada e) throws Exception {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("insert into " + ENTRADA_TBL + " (" + DESCRICAO_ENTRADA + "," +
                 DATA_ENTRADA + ") values ('" + e.getDescricao() + "', " +
                 e.getHorario().getTime() + ");"
        );
    }

    public void deleteEntrada(long id) throws Exception {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("delete from " + ENTRADA_TBL + "where" + ID_ENTRADA + " =" + id);
    }

    public List<Entrada> getAllEntradas() throws Exception {
        SQLiteDatabase db = dbHelper.getDB();
        List<Entrada> expenseList = new ArrayList<Entrada>();
        Cursor cursor = db.query(ENTRADA_TBL, ALL_COLUMNS, null, null, null, null, DATA_ENTRADA + " desc");

        // Começa antes da primeira
        while (cursor.moveToNext()) {
            Entrada e = new Entrada(cursor.getLong(0), new Date(cursor.getLong(1)), cursor.getString(2));
            expenseList.add(e);
        }// para cada registro

        return expenseList;
    }
}
