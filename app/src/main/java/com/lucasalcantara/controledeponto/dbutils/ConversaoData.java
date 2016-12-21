package com.lucasalcantara.controledeponto.dbutils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lucas on 18/12/2016.
 */
public class ConversaoData {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String converteDeTimestamp(long timestamp){
        // TimeStamp são as datas em long desde 1 de janeiro de 1970, é muito grande.
        return df.format(new Date(timestamp));
    }

    public static String converteDeDate(Date date){
        return df.format(date);
    }

    public static long converteDeString(String date){
        try{
            Date d = df.parse(date);
            return d.getTime();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return -1;
    }
}
