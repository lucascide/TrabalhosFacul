package com.example.sistemadeecommerce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tempo {

    public static boolean eUmaDataValida(String inDate) {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data.setLenient(false);
        try {
            data.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;

    }

    public static String setDataAtual(){

        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date atualidade = Calendar.getInstance().getTime();

        String dataAtual = data.format(atualidade);

        return dataAtual;

    }

}