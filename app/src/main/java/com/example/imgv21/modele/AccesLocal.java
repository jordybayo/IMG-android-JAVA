package com.example.imgv21.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.imgv21.outils.MesOutils;
import com.example.imgv21.outils.MySQLLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    //proprities
    private String nomBase = "bdIMG.sqlite";
    private Integer versionBase = 1;
    private MySQLLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param contexte
     */
    public AccesLocal(Context contexte){
        accesBD = new MySQLLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    /**
     * Adding a profile in database
     * @param profil
     */
    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO profil(datemesure, poids, taille, age, sexe) values";
        req = req + " (\""+profil.getDateMesure()+"\", "+profil.getPoids()+", "+profil.getTaille()+", "+profil.getAge()+", "+profil.getSexe() +") ";
        bd.execSQL(req);
    }

    /**
     * Recuparation of the last profile of the database
     */
    public Profil recupDernier(){
        bd = accesBD.getReadableDatabase();
        Profil profil = null;
        String req = "SELECT * FROM profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date = MesOutils.convertStringToDate(curseur.getString(0));
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(date, poids, taille, age, sexe);
        }
        curseur.close();
        return profil;
    }
}
