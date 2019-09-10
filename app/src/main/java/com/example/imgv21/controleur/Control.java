package com.example.imgv21.controleur;

import android.content.Context;

import com.example.imgv21.modele.AccesDistant;
import com.example.imgv21.modele.Profil;
import com.example.imgv21.outils.Serializer;
import com.example.imgv21.vue.CalculActivity;

import org.json.JSONArray;

import java.util.Date;

public final class Control {
    private static Control instance = null;
    private static Profil profil;
    private static String nomFIc = "saveProfil";
//    private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context contexte;
    /**
     * the Constructor not taking something
     */
    private Control(){
        super();
    }

    /**
     *
     * @return an Intsance
     */
    public static final Control getInstance(Context contexte){
        if(contexte != null){
            Control.contexte = contexte;
        }
        if(Control.instance == null){
            Control.instance = new Control();
//            accesLocal = new AccesLocal(contexte);
//            profil = accesLocal.recupDernier();
//            recupSerialise(contexte);
            accesDistant = new AccesDistant();
            accesDistant.envoi("dernier", new JSONArray());
        }
        return  Control.instance;
    }

    /**
     *create a new profil of the user
     * @param poids weight of user
     * @param taille in cm
     * @param age 1 for man and 0 for woman
     * @param sexe sex
     */
    public void creeProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(new Date(), poids, taille, age, sexe);
//        accesLocal.ajout(profil);
//        Serializer.Serialize(nomFIc, profil, context);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
    }

    public void setProfil(Profil profil){
        Control.profil = profil;
        ((CalculActivity)contexte).recupProfil();
    }

    /**
     * float come from the calculating of the body img
     * @return the img
     */
    public float getImg(){
        return profil.getImg();
    }

    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * Recuparation of object serialize (the profile)
     * @param contexte
     */
    private static void recupSerialise(Context contexte){
        profil = (Profil)Serializer.deserialize(nomFIc, contexte);
    }

    public Integer getSexe(){
        if(profil == null){
            return  null;
        } else{
            return profil.getSexe();
        }
    }

    public Integer getPoids(){
        if(profil == null){
            return  null;
        } else{
            return profil.getPoids();
        }
    }

    public Integer getAge(){
        if(profil == null){
            return  null;
        } else{
            return profil.getAge();
        }
    }


    public Integer getTaille(){
        if(profil == null){
            return  null;
        } else{
            return profil.getTaille();
        }
    }

}
