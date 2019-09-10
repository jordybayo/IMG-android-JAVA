package com.example.imgv21.modele;

        import org.junit.Assert;
        import org.junit.Test;

        import static org.junit.Assert.*;

public class ProfilTest {

    //creation profil
    private Profil profil = new Profil(67, 165, 35, 0);
    //result img
    private float img = (float)32.2;
    //meesage
    String message = "Trop eleve";

    @Test
    public void getImg() throws Exception {
        Assert.assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() throws Exception {
        //Assert.assertEquals( message, profil.getMessage() ) ;
    }
}