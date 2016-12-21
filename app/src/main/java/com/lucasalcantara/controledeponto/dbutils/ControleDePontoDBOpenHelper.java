package com.lucasalcantara.controledeponto.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucasalcantara.controledeponto.model.EntradaDAO;

/**
 * Created by Lucas on 18/12/2016.
 */
public class ControleDePontoDBOpenHelper  extends SQLiteOpenHelper{

    private SQLiteDatabase myDataBase = null;
    private Context myContext = null;
    private static final String DB_NAME = "controledeponto.db";
    private static final int DB_VERSION = 1;
    private EntradaDAO expenseDAO = null;

    public ControleDePontoDBOpenHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
        myContext = ctx;
        myDataBase = this.getWritableDatabase();

        expenseDAO = new EntradaDAO(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null){
            db = getWritableDatabase();
        }
        // Criar as tabelas aqui.
        expenseDAO.createTable(db);
    }

    @Override // Utilizado quando se ha atualização do BD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // atualiza a estrutura do BD. Normalmente se tem que guardar os dados do usuário em um
        // outro lugar antes de simplesmente apagar as tabelas. Este exemplo é mais simples.
        expenseDAO.dropTable(db);
        onCreate(db);
    }

    public SQLiteDatabase getDB() {
        return myDataBase;
    }// getDB

    // Inicia a transação.
    public void beginTransaction() throws  Exception{
        myDataBase.beginTransaction();
    }

    // finaliza a transação.
    public void endTransaction()throws  Exception{
        myDataBase.endTransaction();
    }

    public void setTransactionSuccessful(){
        myDataBase.setTransactionSuccessful();
    }

    public EntradaDAO getExpenseDAO(){
        return expenseDAO;
    }
}
