
package projetinf2015h15;

import java.io.IOException;
import static projetinf2015h15.GestionDesFichiers.*;

public class ProgrammePrincipal {
    
    public static void main(String[] args) throws IOException {
        String afficheStat = "-S";
        String initStat = "-SR";
        String fichierStat = "resources/statistiques.json";
        if (args.length == 0){
            System.out.println("Argument(s) manquant(s)");
        }else if(args.length == 1){    
            gestionDesStatistiques(args, afficheStat, fichierStat, initStat);
        } else if(args.length == 2){
            traiterLademande(args, fichierStat);
        }
    }
}
