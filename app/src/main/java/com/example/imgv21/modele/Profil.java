package com.example.imgv21.modele;

import android.view.ViewTreeObserver;

import com.example.imgv21.outils.MesOutils;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable {

    //constantes
    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;



    //proprities
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;


    //Constructor
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    //getter
    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG(){
        float tailleM = ((float)taille)/100;
        this.img = (float)( (1.2*poids/(tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4);
    }

    private void resultIMG(){
        Integer min;
        Integer max;
        if(sexe==0){ //cest une femme
            min = minFemme;
            max = maxFemme;
        }else{
            min = minHomme;
            max = maxHomme;
        }
        //message correspondant
        message = "Normal";
        if(img<min){
            message = "Trop faible";
        }else{
            if(img>max){
                message = "Trop Eleve";
            }
        }
    }

    /**
     * Conversion of profile in JSONArray format
     * @return
     */
    public JSONArray convertToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(MesOutils.convertDateToString(dateMesure));
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);
        return new JSONArray(laListe);
    }


}
