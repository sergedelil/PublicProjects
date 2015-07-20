package projetinf2015h15;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesFichiers.*;
import static projetinf2015h15.GestionDesObjetsJson.*;

public class GestionDesValidations {

    public static boolean validerLesSoins(JSONObject objet, String mois, JSONObject objetStat) 
                  throws IOException {
            int compteurSoins = 0;
            String reclamation = objet.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
            for (int i = 0; i < tableauReclamation.size(); i++) {
                JSONObject objetCourant = tableauReclamation.getJSONObject(i);
                compteurSoins = validerSoinDateMontant(objetCourant, mois, compteurSoins, objetStat);
            }
            return compteurSoins == tableauReclamation.size();
        }

    public static int validerSoinDateMontant(JSONObject objetCourant, String mois, 
            int compteurSoins,JSONObject objetStat) throws IOException {
        if (validerNumeroSoin(objetCourant.getString("soin"), objetStat)
                && validerLaDate(objetCourant.getString("date"), mois, objetStat)
                && validerMontant(objetCourant.getString("montant"), objetStat)) {
            compteurSoins++;
        }
        return compteurSoins;
    }

    public static boolean validerNumeroDossier(String numero, JSONObject objetStat) 
            throws IOException {
        boolean reponse = false;
        if (numero != null && numero.matches("^[ABCDE]\\d{6}$")) {
            reponse = true;
        }else {
            String messageSortie = "Le numero du dossier n'est pas valide.";
            gererErreur(messageSortie, true, objetStat);
        }
        return reponse;
    }

    public static boolean validerFormatMois(String mois, JSONObject objetStat) 
            throws IOException {
        boolean reponse = false;
        if (mois != null && mois.matches("\\d{4}-(0[1-9]|1[0-2])")) {
            reponse = true;
        }else {
            String messageSortie = "Le format ou la valeur du mois n'est pas valide.";
            gererErreur(messageSortie, true, objetStat);
        }
        return reponse;
    }
    
    public static boolean validerNumeroSoin(String numeroSoin, JSONObject objetStat) 
            throws IOException {
        boolean reponse = false;
            if (numeroSoin.matches("^(0|150|175|[1-7]0{2}|3[0-9]{2})$")){
                reponse = true;
            }else {
                String messageSortie = "Le numero d'un soin n'est pas valide.";
                gererErreur(messageSortie, true, objetStat);
            }
        return reponse;
    }
    
    public static boolean validerLaDate(String date, String mois, JSONObject objetStat) 
            throws IOException {
        boolean reponse = identifierLaDateAuMoisEnCours(date, mois, objetStat);
        if (reponse == false){
                String messageSortie = "La date d'un soin n'est pas valide.";
                gererErreur(messageSortie, true, objetStat);
        }
        return reponse;
    }

    public static boolean identifierLaDateAuMoisEnCours(String date, String mois, 
            JSONObject objetStat) throws IOException {
        boolean reponse;
        String formatDeLaDate = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"; 
        reponse = comparerDateMois(date, formatDeLaDate, mois, objetStat);
        return reponse;
    }

    public static boolean comparerDateMois(String date, String formatDeLaDate, String mois,
            JSONObject objetStat) throws IOException {
        boolean valide;
        boolean uneReponse = false;
        if (date != null && date.matches(formatDeLaDate)){
            valide = validerFormatMois(date.trim().substring(0, 7), objetStat);
            if (valide) {
                int i = 0;
                while (i < mois.trim().length() && (date.trim().charAt(i) 
                        == mois.trim().charAt(i))) {
                    i++;
                }
                uneReponse = i == mois.trim().length();
            }
        }
        return uneReponse;
    }

    public static boolean validerMontant(String montant, JSONObject objetStat) throws IOException {
        boolean montantEstValide = false;
        if (montant != null && montant.matches("^\\d+[.,]?\\d{2}\\$$")) {
            montantEstValide = true;
        }else {
            String messageSortie = "Le montant d'un soin est invalide.";
            gererErreur(messageSortie, true, objetStat);
        }
        return montantEstValide;
    }

    public static void validerProprietesReclamation(String reclamation, JSONObject objetStat) 
            throws IOException {
        JSONArray listeReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        if(listeReclamation.isEmpty()){
            String messageSortie = "Aucune réclamation n'est portée au dossier.";
            gererErreur(messageSortie, true, objetStat);
        }else{
        for (int i = 0; i < listeReclamation.size(); i++) {
            JSONObject objetCourant = listeReclamation.getJSONObject(i);
            verifierNumeroSoin(objetCourant,objetStat);
            verifierLaDate(objetCourant, objetStat);
            verifierLeMontant(objetCourant, objetStat);
        }
        }
    }

    public static void validerLesProprietesJson(JSONObject objet,JSONObject objetStat) 
            throws IOException {
        testerDossier(objet, objetStat);
        testerMois(objet, objetStat);
        testerReclamation(objet, objetStat);
    }

    public static void verifierNumeroSoin(JSONObject objetCourant,JSONObject objetStat) 
            throws IOException {
        try {
            objetCourant.getString("soin");
        } catch (JSONException e) {
            String messageSortie = "Le numero d'un soin est manquant.";
            gererErreur(messageSortie, true, objetStat);
        }
    }

    public static void verifierLeMontant(JSONObject objetCourant, JSONObject objetStat) 
            throws IOException {
        try {
            objetCourant.getString("montant");
        } catch (JSONException e) {
            String messageSortie = "Le montant d'un soin est manquant.";
            gererErreur(messageSortie, true, objetStat);
        }
    }

    public static void verifierLaDate(JSONObject objetCourant, JSONObject objetStat) 
            throws IOException {
        try {
            objetCourant.getString("date");
        } catch (JSONException e) {
            String messageSortie = "La date d'un soin est manquante.";
            gererErreur(messageSortie, true, objetStat);
        }
    }

    public static void testerReclamation(JSONObject objet, JSONObject objetStat) throws IOException {
        try {
            objet.getString("reclamations");
        } catch (JSONException e) {
            String messageSortie = "La propriété «reclamations» est manquante.";
            gererErreur(messageSortie, true, objetStat);
        }
    }

    public static void testerMois(JSONObject objet, JSONObject objetStat) throws IOException {
        try {
            objet.getString("mois");
        } catch (JSONException e) {
            String messageSortie = "La propriété «mois» est manquante.";
            gererErreur(messageSortie, true, objetStat);
        }
    }

    public static void testerDossier(JSONObject objet, JSONObject objetStat) throws IOException {
        try {
            objet.getString("dossier");
        } catch (JSONException e) {
            String messageSortie = "La propriété «dossier» est manquante.";
            gererErreur(messageSortie, true, objetStat);
        }
    }
    public static JSONObject testerValiditerDeLobjetJson(String fichierEntree) 
            throws IOException, JSONException {
        JSONObject objetStat = preparerLefichierStat();
        JSONObject objet = formaterObjet(chargerFichier(fichierEntree, objetStat), objetStat);
        validerLesProprietesJson(objet, objetStat);
        validerProprietesReclamation(objet.getString("reclamations"), objetStat);
        validerNumeroDossier(getNumeroDossier(objet), objetStat);
        validerFormatMois(getMois(objet), objetStat);
        validerLesSoins(objet,getMois(objet), objetStat);
        return objet;
    }
}
