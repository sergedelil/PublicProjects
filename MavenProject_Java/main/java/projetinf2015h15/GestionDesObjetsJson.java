package projetinf2015h15;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesCalculs.*;
import static projetinf2015h15.GestionDesFichiers.*;

public class GestionDesObjetsJson {

    public static JSONObject formaterObjet(String texteJson, JSONObject objetStat) throws JSONException, IOException {
        JSONObject objActuel = null;
        if (texteJson.trim().length() != 0) {
            try{
                objActuel = (JSONObject) JSONSerializer.toJSON(texteJson);
            }catch (JSONException e){
                String messageSortie = "Le fichier JSON n'est pas valide.";
                gererErreur(messageSortie, true, objetStat);
            }
        }else {
            String messageSortie = "Le fichier d'entrée est vide.";
            gererErreur(messageSortie, false, objetStat);
        }
        return objActuel;
    }

    public static String getNumeroDossier(JSONObject objActuel) throws IOException {
        return objActuel.getString("dossier");
    }

    public static String getMois(JSONObject objActuel) throws IOException {
        return objActuel.getString("mois");
    }

    public static String getSoin(JSONObject objActuel) throws IOException {
        return objActuel.getString("soin");
    }

    public static void modifierLeSoin(Double remboursement, JSONObject uneReclamation) {
        DecimalFormat df = formaterEnDecimal();
        String leMontant = df.format(remboursement) + "$";
        uneReclamation.discard("montant");
        uneReclamation.accumulate("montant", leMontant);
    }

    public static List<JSONObject> listerLesReclamations(JSONObject objetJson) {
        List<JSONObject> listeReclamation = new ArrayList<>();
        String reclamation = objetJson.getString("reclamations");
        JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        for (int i = 0; i < tableauReclamation.size(); i++) {
            JSONObject objetCourant = tableauReclamation.getJSONObject(i);
            listeReclamation.add(objetCourant);
        }
        return listeReclamation;
    }

    public static String creationMessageJson(String message) {
        JSONObject objetJson = new JSONObject();
        objetJson.accumulate("message", message);
        return objetJson.toString();
    }

    public static String transformerMontant(String montant) {
        String montantTransformer = "";
        for (int i = 0; i < montant.length(); i++) {
            char caractereCourant = montant.charAt(i);
            if (caractereCourant != ',') {
                montantTransformer = montantTransformer + caractereCourant;
            } else {
                montantTransformer = montantTransformer + ".";
            }
        }
        return montantTransformer;
    }
    public static int obtenirValeurReclamTraitee(JSONObject objetStat){
        return objetStat.getInt("Reclamations valides traitees");
    }

    public static int obtenirValeurReclamRejetee(JSONObject objetStat){
        return objetStat.getInt("Reclamations rejetees");
    }

    public static int obtenirValeurMassotherapie(JSONObject objetStat){
        return objetStat.getInt("Massotherapie");
    }

    public static int obtenirValeurOsteopathie(JSONObject objetStat){
        return objetStat.getInt("Osteopathie");
    }

    public static int obtenirValeurKinesitherapie(JSONObject objetStat){
        return objetStat.getInt("Kinesitherapie");
    }

    public static int obtenirValeurGeneralistePrive(JSONObject objetStat){
        return objetStat.getInt("Medecin generaliste prive");
    }

    public static int obtenirValeurPsycoIndividuelle(JSONObject objetStat){
        return objetStat.getInt("Psychologie individuelle");
    }

    public static int obtenirValeurSoinsDentaires(JSONObject objetStat){
        return objetStat.getInt("Soins dentaires");
    }

    public static int obtenirValeurNaturopathieAcuponcture(JSONObject objetStat){
        return objetStat.getInt("Naturopathie, acuponcture");
    }

    public static int obtenirValeurChiropratie(JSONObject objetStat){
        return objetStat.getInt("Chiropractie");
    }

    public static int obtenirValeurPhysiotherapie(JSONObject objetStat){
        return objetStat.getInt("Physiotherapie");
    }

    public static int obtenirValeurOrthophonieErgo(JSONObject objetStat){
        return objetStat.getInt("Ortophonie, ergatherapie");

    }

    public static String initialiserLesStats() throws IOException{
        JSONObject objetStat = GestionDesFichiers.preparerLefichierStat();
        effacerAnciennesStatistiques(objetStat);
        objetStat.accumulate("Reclamations valides traitees", 0);
        objetStat.accumulate("Reclamations rejetees", 0);
        objetStat.accumulate("Massotherapie", 0);
        objetStat.accumulate("Osteopathie", 0);
        objetStat.accumulate("Kinesitherapie", 0);
        objetStat.accumulate("Medecin generaliste prive", 0);
        objetStat.accumulate("Psychologie individuelle", 0);
        objetStat.accumulate("Soins dentaires", 0);
        objetStat.accumulate("Naturopathie, acuponcture", 0);
        objetStat.accumulate("Chiropractie", 0);
        objetStat.accumulate("Physiotherapie", 0);
        objetStat.accumulate("Ortophonie, ergatherapie", 0);
        return objetStat.toString();
    }
     public static void afficherStatistiques() throws IOException{
        JSONObject objetStat = GestionDesFichiers.preparerLefichierStat();
        System.out.println("-------------------------------------");
        System.out.println("Reclamations valides traitees :"+objetStat.getInt("Reclamations valides traitees"));
        System.out.println("Reclamations rejetees         :"+objetStat.getInt("Reclamations rejetees"));
        System.out.println("Massotherapie                 :"+objetStat.getInt("Massotherapie"));
        System.out.println("Osteopathie                   :"+objetStat.getInt("Osteopathie"));
        System.out.println("Kinesitherapie                :"+objetStat.getInt("Kinesitherapie"));
        System.out.println("Medecin generaliste prive     :"+objetStat.getInt("Medecin generaliste prive"));
        System.out.println("Psychologie individuelle      :"+objetStat.getInt("Psychologie individuelle"));
        System.out.println("Soins dentaires               :"+objetStat.getInt("Soins dentaires"));
        System.out.println("Naturopathie, acuponcture     :"+objetStat.getInt("Naturopathie, acuponcture"));
        System.out.println("Chiropractie                  :"+objetStat.getInt("Chiropractie"));
        System.out.println("Physiotherapie                :"+objetStat.getInt("Physiotherapie"));
        System.out.println("Ortophonie, ergatherapie      :"+objetStat.getInt("Ortophonie, ergatherapie"));
        System.out.println("-------------------------------------");
    }

    public static void effacerAnciennesStatistiques(JSONObject objetStat) {
        objetStat.discard("Reclamations valides traitees");
        objetStat.discard("Reclamations rejetees");
        objetStat.discard("Massotherapie");
        objetStat.discard("Osteopathie");
        objetStat.discard("Kinesitherapie");
        objetStat.discard("Medecin generaliste prive");
        objetStat.discard("Psychologie individuelle");
        objetStat.discard("Soins dentaires");
        objetStat.discard("Naturopathie, acuponcture");
        objetStat.discard("Chiropractie");
        objetStat.discard("Physiotherapie");
        objetStat.discard("Ortophonie, ergatherapie");
    }
    
    public static int compterLeSoin(List<JSONObject> liste, int numero) {
	int compteur = 0;
        for (int i = 0; i < liste.size(); i++){
            if(numero == liste.get(i).getInt("soin")){
                compteur++;
            }
        }
	return compteur;
    }  

    public static String miseAJourStatistique(JSONObject objet, String fichierEntree, 
            JSONObject objetStat) throws IOException {
        List<JSONObject> liste = listerLesReclamations(objet);
        List<Integer> listeValidation = new ArrayList<>();
        int valeurActuelle = obtenirValeurReclamTraitee(objetStat);
        objetStat.discard("Reclamations valides traitees");
        objetStat.accumulate("Reclamations valides traitees", valeurActuelle + 1);
        for (int i = 0; i < liste.size(); i++) {
            int numSoinCourant = liste.get(i).getInt("soin");
            if (!(listeValidation.contains(numSoinCourant))){
                calculerLesStatistiques(numSoinCourant, objetStat, liste);
            }
            listeValidation.add(numSoinCourant);
        }
        return objetStat.toString();
    }          

    public static void calculerLesStatistiques(int numSoinCourant, JSONObject objetStat, 
            List<JSONObject> liste) {
        if (numSoinCourant == 0) {
            statistiqueSoin0(objetStat, liste);
        }else if (numSoinCourant == 100) {
            statistiqueSoin100(objetStat, liste);
        }if (numSoinCourant == 150) {
            statistiqueSoin150(objetStat, liste);
        }else if (numSoinCourant == 175) {
            statistiqueSoin175(objetStat, liste);
        }else if (numSoinCourant == 200) {
            statistiqueSoin200(objetStat, liste);
        }else if (numSoinCourant >= 300 && numSoinCourant <= 399) {
            statistiqueSoin300(objetStat, liste, numSoinCourant);
        }else if (numSoinCourant == 400) {
            statistiqueSoin400(objetStat, liste);
        }else if (numSoinCourant == 500) {
            statistiqueSoin500(objetStat, liste);
        }else if (numSoinCourant == 600) {
            statistiqueSoin600(objetStat, liste);
        }else if (numSoinCourant == 700) {
            statistiqueSoin700(objetStat, liste);
        }
    }

    private static void statistiqueSoin700(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurOrthophonieErgo(objetStat);
        objetStat.discard("Ortophonie, ergatherapie");
        objetStat.accumulate("Ortophonie, ergatherapie", ancienNb + compterLeSoin(liste, 700));
    }

    private static void statistiqueSoin600(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurPhysiotherapie(objetStat);
        objetStat.discard("Physiotherapie");
        objetStat.accumulate("Physiotherapie", ancienNb + compterLeSoin(liste, 600));
    }

    private static void statistiqueSoin500(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurChiropratie(objetStat);
        objetStat.discard("Chiropractie");
        objetStat.accumulate("Chiropractie", ancienNb + compterLeSoin(liste, 500));
    }

    private static void statistiqueSoin400(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurNaturopathieAcuponcture(objetStat);
        objetStat.discard("Naturopathie, acuponcture");
        objetStat.accumulate("Naturopathie, acuponcture", ancienNb + compterLeSoin(liste, 400));
    }

    private static void statistiqueSoin300(JSONObject objetStat, List<JSONObject> liste, 
            int numSoinCourant) {
        int ancienNb = obtenirValeurSoinsDentaires(objetStat);
        objetStat.discard("Soins dentaires");
        objetStat.accumulate("Soins dentaires", ancienNb + compterLeSoin(liste, numSoinCourant));
    }

    private static void statistiqueSoin200(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurPsycoIndividuelle(objetStat);
        objetStat.discard("Psychologie individuelle");
        objetStat.accumulate("Psychologie individuelle", ancienNb + compterLeSoin(liste, 200));
    }

    private static void statistiqueSoin175(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurGeneralistePrive(objetStat);
        objetStat.discard("Medecin generaliste prive");
        objetStat.accumulate("Medecin generaliste prive", ancienNb + compterLeSoin(liste, 175));
    }

    private static void statistiqueSoin150(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurKinesitherapie(objetStat);
        objetStat.discard("Kinesitherapie");
        objetStat.accumulate("Kinesitherapie", ancienNb + compterLeSoin(liste, 150));
    }

    private static void statistiqueSoin100(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurOsteopathie(objetStat);
        objetStat.discard("Osteopathie");
        objetStat.accumulate("Osteopathie", ancienNb + compterLeSoin(liste, 100));
    }

    private static void statistiqueSoin0(JSONObject objetStat, List<JSONObject> liste) {
        int ancienNb = obtenirValeurMassotherapie(objetStat);
        objetStat.discard("Massotherapie");
        objetStat.accumulate("Massotherapie", ancienNb + compterLeSoin(liste, 0));
    }
}
