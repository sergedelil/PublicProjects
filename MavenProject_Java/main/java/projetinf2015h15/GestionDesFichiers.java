package projetinf2015h15;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesCalculs.*;
import static projetinf2015h15.GestionDesObjetsJson.*;
import static projetinf2015h15.GestionDesValidations.*;

public class GestionDesFichiers {

    public static String chargerFichier(String nomDuFichier, JSONObject objetStat) 
            throws IOException {
        String texteJson = null;
        try {
            texteJson = FileReader.loadFileIntoString(nomDuFichier, "UTF-8");
        } catch (FileNotFoundException e) {
            String messageSortie = "Le fichier d'entrée n'existe pas.";
            gererErreur(messageSortie, false, objetStat);
        }
        return texteJson;
    }

    public static void ecrireFichierSurDisque(String nomFichier, String objet)
            throws IOException {
        try (FileWriter fichierJson = new FileWriter(nomFichier)) {
            fichierJson.write(objet);
            fichierJson.flush();
            fichierJson.close();
        }
    }

    public static String creationFichierSortie(JSONObject objet,
            List<JSONObject> liste, String total) throws IOException {
        JSONObject objetJson = new JSONObject();
        objetJson.accumulate("dossier", getNumeroDossier(objet));
        objetJson.accumulate("mois", getMois(objet));
        objetJson.accumulate("reclamations", liste);
        objetJson.accumulate("total", total + "$");
        return objetJson.toString();
    }

    public static void traitementReclamations(String fichierEntree, 
            String fichierSortie, String fichierStat) 
            throws IOException {
        JSONObject objetStat = preparerLefichierStat();
        JSONObject objet = testerValiditerDeLobjetJson(fichierEntree);
        List<JSONObject> listeReclamation = calculerRemboursementDuSoin(objet);
        String totalrembourser = additionnerLesRemboursements(listeReclamation);
        String objetJson = creationFichierSortie(objet, listeReclamation, totalrembourser);
        ecrireFichierSurDisque(fichierSortie, objetJson);
        String objetStatistique = miseAJourStatistique(objet, fichierEntree, objetStat);
        ecrireFichierSurDisque(fichierStat, objetStatistique);
        System.out.println(objetJson);
    }

    public static void gererErreur(String message,boolean res, JSONObject objetStat) 
            throws IOException {
        String fichier = "resources/output.json";
        System.out.println(message);
        ecrireFichierSurDisque(fichier, creationMessageJson(message));
        if(res){
            String fichierStat = "resources/statistiques.json";
            int nombreRejetee = obtenirValeurReclamRejetee(objetStat) + 1;
            objetStat.discard("Reclamations rejetees");
            objetStat.accumulate("Reclamations rejetees", nombreRejetee);
            ecrireFichierSurDisque(fichierStat, objetStat.toString());
        }
        System.exit(0);
    }

    public static void gestionDesStatistiques(String[] args, String afficheStat, String fichierStat, String initStat) throws IOException {
        if (args[0].equals(afficheStat)) {
            afficherStatistiques();
        } else if (args[0].equals(initStat)) {
            ecrireFichierSurDisque(fichierStat, initialiserLesStats());
            System.out.println("Le fichier statistique a été initialis\u00e9.");
        }else {
            System.out.println("Argument(s) manquant(s)");
        }
    }

    public static void traiterLademande(String[] args, String fichierStat) throws IOException {
        String fichierEntree = args[0];
        String fichierSortie = args[1];
        traitementReclamations(fichierEntree, fichierSortie, fichierStat);
    }

    public static JSONObject preparerLefichierStat() throws IOException {
        String statJson = FileReader.loadFileIntoString("resources/statistiques.json", "UTF-8");
        JSONObject objetStat = (JSONObject) JSONSerializer.toJSON(statJson);
        return objetStat;
    }
    
   
}
