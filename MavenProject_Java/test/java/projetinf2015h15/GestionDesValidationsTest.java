
package projetinf2015h15;

import java.io.IOException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;


public class GestionDesValidationsTest {
    
    JSONObject objetStat;
    String textJson;
    JSONObject objetFichier;
    String mois;
    String fichierEntree = "resources/input.json";
    String montant;
    String formatDeLaDate;
    String date;
    String numeroSoin;
    String numeroDossier;
    @Before
    public void setUp() throws IOException {
        
        objetStat = GestionDesFichiers.preparerLefichierStat();
        textJson = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        objetFichier = (JSONObject) JSONSerializer.toJSON(textJson);
        mois = "2015-01";
        montant = "234.54$";
        formatDeLaDate = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        date = "2015-01-11";
        numeroSoin = "100";
        numeroDossier = "A127456";
    }
    
    @After
    public void tearDown() {
        objetStat = null;
        objetFichier = null;
        textJson = null;
        mois = null;
        montant = null;
        date = null;
        formatDeLaDate = null;
        numeroSoin = null;
        numeroDossier = null;
    }

   
    @Test
    public void testValiderLesSoins() throws Exception {
        assertTrue(GestionDesValidations.validerLesSoins(objetFichier, mois, objetStat));
    }
    @Test
    public void testValiderNumeroDossier() throws Exception {
       assertTrue(GestionDesValidations.validerNumeroDossier(numeroDossier, objetStat));
    }

    @Test
    public void testValiderFormatMois() throws Exception {
        assertTrue(GestionDesValidations.validerFormatMois(mois, objetStat));
    }

    @org.junit.Test
    public void testValiderNumeroSoin() throws Exception {
        assertTrue(GestionDesValidations.validerNumeroSoin(numeroSoin, objetStat));
    }

    @org.junit.Test
    public void testValiderLaDate() throws Exception {
        assertTrue(GestionDesValidations.validerLaDate(date, mois, objetStat));
    }
    @org.junit.Test
    public void testidentifierLaDateAuMoisEnCours() throws IOException {
        assertTrue(GestionDesValidations.identifierLaDateAuMoisEnCours(date, mois, objetStat));
    }
    @org.junit.Test
    public void testcomparerDateMois() throws IOException {
        assertTrue(GestionDesValidations.comparerDateMois(date,formatDeLaDate, mois, objetStat));
    }
    @org.junit.Test
    public void testValiderMontant() throws Exception {
        assertTrue(GestionDesValidations.validerMontant(montant, objetStat));
    }
    @org.junit.Test
    public void testValiderProprietesReclamation() throws Exception {
        String reclamation = objetFichier.getString("reclamations");
        GestionDesValidations.validerProprietesReclamation(reclamation, objetStat);
    }

    @org.junit.Test
    public void testValiderLesProprietesJson() throws Exception {
        GestionDesValidations.validerLesProprietesJson(objetFichier, objetStat);
    }
    
    @org.junit.Test
    public void testTesterValiditerDeLobjetJson() throws Exception {
        assertEquals(objetFichier, GestionDesValidations.testerValiditerDeLobjetJson(fichierEntree));
    }
}
