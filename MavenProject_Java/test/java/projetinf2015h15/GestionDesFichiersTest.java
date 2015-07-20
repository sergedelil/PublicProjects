
package projetinf2015h15;

import java.io.IOException;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;


public class GestionDesFichiersTest {
    
    JSONObject objetStat;
    String textJson;
    JSONObject objetFichier;
    String fichierEntree;
    
    @Before
    public void setUp() throws IOException {
        fichierEntree = "resources/input.json";
        objetStat = GestionDesFichiers.preparerLefichierStat();
        textJson = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        objetFichier = (JSONObject) JSONSerializer.toJSON(textJson);
        
    }
    
    @After
    public void tearDown() {
        objetStat = null;
        objetFichier = null;
        textJson = null;
        fichierEntree = null;
    }

    
    @org.junit.Test
    public void testChargerFichier() throws Exception {
        assertEquals(textJson, GestionDesFichiers.chargerFichier(fichierEntree, objetStat));
    }

    @org.junit.Test
    public void testEcrireFichierSurDisque() throws Exception {
        System.out.println("ecrireFichierSurDisque");
        String nomFichier = "";
        String objet = "";
        GestionDesFichiers.ecrireFichierSurDisque(nomFichier, objet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creationFichierSortie method, of class GestionDesFichiers.
     */
    @org.junit.Test
    public void testCreationFichierSortie() throws Exception {
        System.out.println("creationFichierSortie");
        JSONObject objet = null;
        List<JSONObject> liste = null;
        String total = "";
        String expResult = "";
        String result = GestionDesFichiers.creationFichierSortie(objet, liste, total);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of traitementReclamations method, of class GestionDesFichiers.
     */
    @org.junit.Test
    public void testTraitementReclamations() throws Exception {
        System.out.println("traitementReclamations");
        String fichierEntree = "";
        String fichierSortie = "";
        String fichierStat = "";
        GestionDesFichiers.traitementReclamations(fichierEntree, fichierSortie, fichierStat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gererErreur method, of class GestionDesFichiers.
     */
    @org.junit.Test
    public void testGererErreur() throws Exception {
        System.out.println("gererErreur");
        String message = "";
        boolean res = false;
        JSONObject objetStat = null;
        GestionDesFichiers.gererErreur(message, res, objetStat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gestionDesStatistiques method, of class GestionDesFichiers.
     */
    @org.junit.Test
    public void testGestionDesStatistiques() throws Exception {
        System.out.println("gestionDesStatistiques");
        String[] args = null;
        String afficheStat = "";
        String fichierStat = "";
        String initStat = "";
        GestionDesFichiers.gestionDesStatistiques(args, afficheStat, fichierStat, initStat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of traiterLademande method, of class GestionDesFichiers.
     */
    @org.junit.Test
    public void testTraiterLademande() throws Exception {
        System.out.println("traiterLademande");
        String[] args = null;
        String fichierStat = "";
        GestionDesFichiers.traiterLademande(args, fichierStat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
