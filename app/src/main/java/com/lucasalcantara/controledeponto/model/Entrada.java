package com.lucasalcantara.controledeponto.model;

import java.util.Date;

/**
 * Created by Lucas on 18/12/2016.
 */
public class Entrada {

    private long ID;
    private Date horario;
    private String descricao;

    //region ** Construtores **

    public Entrada() {
    }

    public Entrada(Date horario) {
        this.horario = horario;
    }

    public Entrada(Date horario, String descricao) {
        this.descricao = descricao;
        this.horario = horario;
    }

    public Entrada(long ID,  Date horario, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
        this.horario = horario;
    }
    //endregion

    //region ** Get e Set **

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //endregion
}
