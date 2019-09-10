package com.example.imgv21.outils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametres;
    private String ret = null;
    public AsyncResponse delegate = null;

    /**
     * Constructor
     */
    public AccesHTTP() {
        parametres = new ArrayList<NameValuePair>();
    }

    /**
     * Adding of parameters post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){
        parametres.add(new BasicNameValuePair(nom, valeur));
    }

    /**
     * Connexion in backgoundtask with thread separate
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            //encoding parameters
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //connecton and sending parameters, wait for response
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            //tranforming response
            ret = EntityUtils.toString(reponse.getEntity());
        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage", "***********************"+e.toString());
        }catch (ClientProtocolException e){
            Log.d("Erreur protocole", "***********************"+e.toString());
        }catch (IOException e){
            Log.d("Erreur I/O", "***********************"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish((ret.toString()));
    }

}
