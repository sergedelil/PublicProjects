package projetinf2015h15;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesObjetsJson.*;

public class GestionDesCalculs {
   
    public static Double appliquerLesContrat(JSONObject uneReclamation, String dossier) {
        Double remboursement = 0.0;
        
        int numeroSoin = uneReclamation.getInt("soin");
        String chaineMontant = uneReclamation.getString("montant");
        chaineMontant = transformerMontant(chaineMontant);
        int tailleDuMontant = chaineMontant.trim().length();
        Double montant = Double.parseDouble(chaineMontant.substring(0, tailleDuMontant - 1));
        switch (dossier.substring(0, 1)) {
            case "A":
                remboursement = rembourserContratA(numeroSoin, montant);
                break;
            case "B":
                remboursement = rembourserContratB(numeroSoin, montant);
                break;
            case "C":
                remboursement = rembourserContratC(numeroSoin, montant);
                break;
            case "D":
                remboursement = rembourserContratD(numeroSoin, montant);
                break;
            case "E":
                remboursement = rembourserContratE(numeroSoin, montant);
        }
        return remboursement;
    }

    public static Double rembourserContratE(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 0 || numSoin == 600 || numSoin == 150) {
            remboursement = montant * 0.15;
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant * 0.6;
        } else if (numSoin == 100) {
            remboursement = montant * 0.25;
        } else if (numSoin == 200) {
            remboursement = montant * 0.12;
        } else if (numSoin == 400) {
            remboursement = montant * 0.25;
            if (remboursement > 15) {
                remboursement = 15.0;
            }
        } else if (numSoin == 500) {
            remboursement = montant * 0.30;
            if (remboursement > 20) {
                remboursement = 20.0;
            }
        } else if (numSoin == 175) {
            remboursement = montant * 0.25;
            if (remboursement > 20) {
                remboursement = 20.0;
            }
        } else {
            remboursement = montant * 0.22;
        }
        return remboursement;
    }

    public static Double rembourserContratD(int numSoin, Double montant) {
        Double remboursement = 0.0;
        if (numSoin == 200 || numSoin == 600) {
            remboursement = montant;
            if (remboursement > 100) {
                remboursement = 100.0;
            }
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant;
        } else if (numSoin == 100) {
            remboursement = montant;
            if (remboursement > 75) {
                remboursement = 75.0;
            }
        } else if (numSoin == 0) {
            remboursement = montant;
            if (remboursement > 85) {
                remboursement = 85.0;
            }
        } else if (numSoin == 400) {
            remboursement = montant;
            if (remboursement > 65) {
                remboursement = 65.0;
            }
        } else if (numSoin == 700) {
            remboursement = montant;
            if (remboursement > 90) {
                remboursement = 90.0;
            }
        } else if (numSoin == 150) {
            remboursement = montant;
            if (remboursement > 150) {
                remboursement = 150.0;
            }
        } else if (numSoin == 175) {
            remboursement = montant * 0.95;
        } else if (numSoin == 500) {
            remboursement = montant;
        }
        return remboursement;
    }

    public static Double rembourserContratC(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 150) {
            remboursement = montant * 0.85;
        } else if (numSoin == 100) {
            remboursement = montant * 0.95;
        } else if (numSoin == 600) {
            remboursement = montant * 0.75;
        } else {
            remboursement = montant * 0.9;
        }
        return remboursement;
    }

    public static Double rembourserContratB(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 100 || numSoin == 500) {
            remboursement = montant * 0.5;
            if (remboursement > 50) {
                remboursement = 50.0;
            }
        } else if (numSoin == 0) {
            remboursement = montant * 0.5;
            if (remboursement > 40) {
                remboursement = 40.0;
            }
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant * 0.5;
        } else if (numSoin == 200) {
            remboursement = montant;
        } else if (numSoin == 175) {
            remboursement = montant * 0.75;
        } else if (numSoin == 400 || numSoin == 150) {
            remboursement = 0.0;
        } else if (numSoin == 600) {
            remboursement = montant;
        } else {
            remboursement = montant * 0.7;
        }
        return remboursement;
    }

    public static Double rembourserContratA(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 0 || numSoin == 200 || numSoin == 500) {
            remboursement = montant * 0.25;
        } else if ((numSoin >= 300 && numSoin <= 399) || (numSoin == 400)
                || (numSoin == 700) || (numSoin == 150)) {
            remboursement = 0.0;
        } else if (numSoin == 175) {
            remboursement = montant * 0.5;
        } else if (numSoin == 100) {
            remboursement = montant * 0.35;
        } else {
            remboursement = montant * 0.4;
        }
        return remboursement;
    }

    public static String additionnerLesRemboursements(List<JSONObject> listeReclamation) {
        DecimalFormat df = formaterEnDecimal();
        JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(listeReclamation);
        Double totalRemboursement = 0.0;
        for (int i = 0; i < tableauReclamation.size(); i++) {
            JSONObject soinCourant = tableauReclamation.getJSONObject(i);
            totalRemboursement = ajouterMontantAuTotal(soinCourant, totalRemboursement);
        }
        return df.format(totalRemboursement);
    }

    public static Double ajouterMontantAuTotal(JSONObject soinCourant, Double totalRemboursement)
            throws NumberFormatException {
        String montantantRembourse = soinCourant.getString("montant");
        montantantRembourse = transformerMontant(montantantRembourse);
        int tailleDuMontant = montantantRembourse.trim().length();
        Double montant = Double.parseDouble(montantantRembourse.substring(0, tailleDuMontant - 1));
        totalRemboursement = totalRemboursement + montant;
        return totalRemboursement;
    }

    public static List<JSONObject> calculerRemboursementDuSoin(JSONObject objet)
            throws IOException {
        Double leRembourssement;
        double cumuleSoin100 = 0.0;
        double cumuleSoin175 = 0.0;
        double cumuleSoin200 = 0.0;
        double cumuleSoin500 = 0.0;
        double cumuleSoin600 = 0.0;
        List<JSONObject> listeReclamation = listerLesReclamations(objet);
        for (JSONObject uneReclamation : listeReclamation) {
            leRembourssement = appliquerLesContrat(uneReclamation, getNumeroDossier(objet));
            if(admetUnMontantMax(uneReclamation.getString("soin"))){
                if (uneReclamation.getInt("soin") == 100){
                    cumuleSoin100 = cumuleSoin100 + leRembourssement;
                    if(cumuleSoin100 > 250){
                        leRembourssement = leRembourssement - (cumuleSoin100 - 250);
                    }
                }else if (uneReclamation.getInt("soin") == 175){
                    cumuleSoin175 = cumuleSoin175 + leRembourssement;
                    if(cumuleSoin175 > 200){
                        leRembourssement = leRembourssement - (cumuleSoin175 - 200);
                    }
                }else if (uneReclamation.getInt("soin") == 200){
                    cumuleSoin200 = cumuleSoin200 + leRembourssement;
                    if(cumuleSoin200 > 250){
                        leRembourssement = leRembourssement - (cumuleSoin200 - 250);
                    }
                }else if (uneReclamation.getInt("soin") == 500){
                    cumuleSoin500 = cumuleSoin500 + leRembourssement;
                    if(cumuleSoin500 > 150){
                        leRembourssement = leRembourssement - (cumuleSoin500 - 150);
                    }
                }else if (uneReclamation.getInt("soin") == 600){
                    cumuleSoin600 = cumuleSoin600 + leRembourssement;
                    if(cumuleSoin600 > 300){
                        leRembourssement = leRembourssement - (cumuleSoin600 - 300);
                    }
                }
            }
            modifierLeSoin(leRembourssement, uneReclamation);
        }
        return listeReclamation;
    }

    public static DecimalFormat formaterEnDecimal() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);
        return df;
    }

    public static boolean admetUnMontantMax(String numeroSoin){
        boolean resultat = false;
        if (numeroSoin.matches("^(100|175|200|500|600)$")){
            resultat = true;
        }
        return resultat;
    }
}