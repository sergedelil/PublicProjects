
package projetinf2015h15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;


public class GestionDesObjetsJsonTest {
    
    JSONObject objetStat;
    String textJson;
    JSONObject objetFichier;
    String mois;
    String fichierEntree;
    String montant;
    String montant2;
    String montant3;
    String formatDeLaDate;
    String date;
    String numeroSoin;
    String numeroDossier;
    String message;
    double remboursement;
    String reclamation;
    JSONArray tableauReclamation;
    List<JSONObject> liste;
    int soin;
    
    @Before
    public void setUp() throws IOException {
        objetStat = GestionDesFichiers.preparerLefichierStat();
        textJson = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        objetFichier = (JSONObject) JSONSerializer.toJSON(textJson);
        fichierEntree = "resources/input.json";
        mois = "2015-01";
        montant = "234.54$";
        montant2 = "234,54$";
        montant3 = "23454,45$";
        formatDeLaDate = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        date = "2015-01-11";
        numeroSoin = "100";
        numeroDossier = "A100323";
        remboursement = 256.24;
        reclamation = objetFichier.getString("reclamations");
        tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        liste = new ArrayList<>();
        message = "Le dossier est invalide";
        soin = 175;
    }
    
    @After
    public void tearDown() {
        objetStat = null;
        objetFichier = null;
        textJson = null;
        mois = null;
        montant = null;
        montant2 = null;
        montant3 = null;
        date = null;
        formatDeLaDate = null;
        numeroSoin = null;
        numeroDossier = null;
        remboursement = 0.0;
        reclamation = null;
        tableauReclamation = null;
        liste = null;
        soin = 0;
    }

    @org.junit.Test
    public void testFormaterObjet() throws Exception {
        JSONObject resultat = GestionDesObjetsJson.formaterObjet(textJson, objetStat);
        assertEquals(objetFichier, resultat);
    }

    @org.junit.Test
    public void testGetNumeroDossier() throws Exception {
        String resultat = GestionDesObjetsJson.getNumeroDossier(objetFichier);
        assertEquals(numeroDossier, resultat);
    }

    @org.junit.Test
    public void testGetMois() throws Exception {
        String resultat = GestionDesObjetsJson.getMois(objetFichier);
        assertEquals(mois, resultat);
    }

    /**
     * Test of modifierLeSoin method, of class GestionDesObjetsJson.
     */
    @org.junit.Test
    public void testModifierLeSoin() {
        GestionDesObjetsJson.modifierLeSoin(remboursement, tableauReclamation.getJSONObject(0));
    }

    @org.junit.Test
    public void testListerLesReclamations() {
        for(int i = 0; i < tableauReclamation.size(); i++){
            liste.add(tableauReclamation.getJSONObject(i));
        }
        List<JSONObject> resultat = GestionDesObjetsJson.listerLesReclamations(objetFichier);
        assertEquals(liste, resultat);
    }

    @org.junit.Test
    public void testCreationMessageJson() {
         GestionDesObjetsJson.creationMessageJson(message);
    }

    @org.junit.Test
    public void testTransformerMontant() {
        assertEquals("234.54$", GestionDesObjetsJson.transformerMontant(montant2));
    }
    @org.junit.Test
    public void test2TransformerMontant() {
        assertEquals("23454.45$", GestionDesObjetsJson.transformerMontant(montant3));
    }

    @org.junit.Test
    public void testInitialiserLesStats() throws Exception {
        GestionDesObjetsJson.initialiserLesStats();
    }

    @org.junit.Test
    public void testAfficherStatistiques() throws Exception {
        GestionDesObjetsJson.afficherStatistiques();
    }
    
    @org.junit.Test
    public void testeffacerAnciennesStatistiques() throws Exception {
        GestionDesObjetsJson.effacerAnciennesStatistiques(objetStat);
    }

    @org.junit.Test
    public void testCompterLeSoin() {
        for(int i = 0; i < tableauReclamation.size(); i++){
            liste.add(tableauReclamation.getJSONObject(i));
        }
        assertEquals(4, GestionDesObjetsJson.compterLeSoin(liste, soin));
    }

    @org.junit.Test
    public void testMiseAJourStatistique() throws Exception {
       GestionDesObjetsJson.miseAJourStatistique(objetFichier, fichierEntree, objetStat);
    }
    
}
