package com.example.imgv21.vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgv21.R;
import com.example.imgv21.controleur.Control;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    //proprities
    private EditText txtPoids;
    private  EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Control controle;

    /**
     * initialisation of links with graphics objects
     */
    private void init(){
        txtPoids = (EditText)findViewById(R.id.txtPoids);
        txtTaille = (EditText)findViewById(R.id.txtTaille);
        txtAge =   (EditText)findViewById(R.id.txtAge);
        rdHomme = (RadioButton)findViewById(R.id.rdHomme);
        rdFemme = (RadioButton)findViewById(R.id.rdFemme);
        lblIMG = (TextView)findViewById(R.id.lblIMG);
        imgSmiley = (ImageView)findViewById(R.id.imgSmiley);
        this.controle = Control.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
//        recupProfil();
    }

    /**
     * Listen to button Calculate event
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(CalculActivity.this, "test", Toast.LENGTH_SHORT).show();
                //Log.d("message", "vous avez clicker sur calculer**********************");

                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;
                //taking all the value
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e){ }
                if(rdHomme.isChecked()){
                    sexe = 1;
                }
                //verifying data
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(CalculActivity.this, "Saisi Incorrect", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Display text and Image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe){
        //creating new profil and data recuparation
        this.controle.creeProfil(poids, taille, age, sexe, this);
        float img = this.controle.getImg();
        String message = this.controle.getMessage();
        //displaying
        if(message == "Normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }else{
            lblIMG.setTextColor(Color.RED);
            if(message == "Trop faible"){
                imgSmiley.setImageResource(R.drawable.maigre);
            }else{
                imgSmiley.setImageResource(R.drawable.graise);
            }
        }
        lblIMG.setText(String.format("%.01f", img)+": IMG "+message);
    }

    /**
     * recuparation of of object if it has been serialised
     */
    public  void recupProfil(){
        if(controle.getPoids() != null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe() == 1){
                rdHomme.setChecked(true);
            }
        }
        //simmule le click sur le boutton calcul
//        ((Button)findViewById(R.id.btnCalc)).performClick();
    }

    /**
     * Back to menu
     */
    private void ecouteRetourMenu(){
        ((ImageButton) findViewById(R.id.btnRetourDeCalcul )).setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
