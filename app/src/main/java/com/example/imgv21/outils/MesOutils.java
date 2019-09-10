package com.example.imgv21.outils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {



    /**
     * Conversion chaine sous format Tue Feb 09:16:52 GMT 2018 (EEE MMM dd hhmm:ss 'GMT'  yyyy) ver date
     * @param uneDate
     * @return
     */
    public  static Date convertStringToDate(String uneDate){
        return converStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'GMT' yyyy");
    }


    /**
     * Conversion chaine sous format Tue Feb 09:16:52 GMT 2018 (EEE MMM dd hhmm:ss 'GMT'  yyyy) ver date
     * @param uneDate
     * @return
     */
    public static Date converStringToDate(String uneDate){
        String formatAttendu = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try{
            Date date = formatter.parse(uneDate);
            return date;
        }catch(Exception e) {
            Log.d("erreur", "parse de la date impossible"+e.toString());
        }
        return  null;
    }

    /**
     * Conversion chaine sous format recue en parametre vers date
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public  static Date converStringToDate(String uneDate, String formatAttendu){
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try{
            Date date = formatter.parse(uneDate);
            return date;
        }catch(Exception e) {
            Log.d("erreur", "parse de la date impossible"+e.toString());
        }
        return  null;
    }

    /**
     * COnvert the date in format yyyy-MM-dd hh:mm:ss
     * @param uneDate
     */
    public static String convertDateToString (Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(uneDate);
    }
}
