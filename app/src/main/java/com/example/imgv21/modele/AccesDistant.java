package com.example.imgv21.modele;

import android.util.Log;

import com.example.imgv21.controleur.Control;
import com.example.imgv21.outils.AccesHTTP;
import com.example.imgv21.outils.AsyncResponse;
import com.example.imgv21.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant implements AsyncResponse {
    //constants
    private static final String SERVERADDR = "http://192.168.8.1/img/serveurimg.php";
    private Control controle;

    public AccesDistant(){
        controle = Control.getInstance(null);
    }
    /**
     * Back of the distant server
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "******************************"+output);
        //decoupage of receive message
        String[] message = output.split("%");
        //in the message[0]: "enreg", "dernier", "erreu !"
        //in meesage[1] : rest of message

        //if therre is 2 case
        if(message.length>1){
            if(message[0].equals("enreg")){
                Log.d("enreg", "******************************"+message[1]);
            }else{
                if(message[0].equals("dernier")){
                    Log.d("dernier", "******************************"+message[1]);
                    try{
                        JSONObject info = new JSONObject(message[1]);
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        String dateMesure = info.getString("datemesure");
                        Date date = MesOutils.converStringToDate(dateMesure, "yyyy-MM-dd hh:mm:ss");
                        Log.d("date mysql", "******************************retourMysql"+date);
                        Profil profil = new Profil(date, poids, taille, age, sexe);
                        controle.setProfil(profil);
                    }catch (JSONException e){
                        Log.d("Erreur !", "Conversion JSON impossible"+e.toString());
                    }

                }else{
                    if (message[0].equals("Erreur !")) {
                        Log.d("Erreur !", "******************************"+message[1]);
                    }
                }
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        //delegation link
        accesDonnees.delegate = this;
        //add parameters
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        //appel au serveur
        accesDonnees.execute(SERVERADDR);
    }
}
