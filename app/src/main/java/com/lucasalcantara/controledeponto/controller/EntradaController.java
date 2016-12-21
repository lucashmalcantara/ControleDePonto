package com.lucasalcantara.controledeponto.controller;

import com.lucasalcantara.controledeponto.dbutils.ControleDePontoDBOpenHelper;
import com.lucasalcantara.controledeponto.model.Entrada;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lucas on 18/12/2016.
 */
public class EntradaController {

    public enum ERRO {
        SUCESSO,
        VALOR_DE_HORARIO_INVALIDO,
        ERRO_INTERNO
    }

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ControleDePontoDBOpenHelper dbHelper = null;
    // Se der errado retornamos um erro interno. Não se sabe o que pode dar erro, talvez um acesso ao banco ou algo do tipo.
    public ERRO insertExpense(String txtHorario, String txtDesc) throws Exception{
        Date horario = null;
        String descricao = null;

        descricao = txtDesc;
        // Regras de negócio
        try {
            horario = df.parse(txtHorario);
        } catch (Exception e) {
            return ERRO.VALOR_DE_HORARIO_INVALIDO;
        }

        ERRO retval = ERRO.SUCESSO;

        try {
            dbHelper.beginTransaction();
            dbHelper.getExpenseDAO().insertEntrada(new Entrada(horario, descricao));
            dbHelper.setTransactionSuccessful();
        } catch (Exception e) {
            return ERRO.ERRO_INTERNO;
        } finally {
            dbHelper.endTransaction();
        }

        return retval;
    }

    public ERRO removeExpense(long id) throws Exception{
        try{
            dbHelper.getExpenseDAO().deleteEntrada(id);
        }catch (Exception e){
            return ERRO.ERRO_INTERNO;
        }
        return ERRO.SUCESSO;
    }

    public List<Entrada> getAllExpenses() throws Exception{
        return dbHelper.getExpenseDAO().getAllEntradas();
    }

    public EntradaController(ControleDePontoDBOpenHelper db){
        dbHelper = db;
    }

}
