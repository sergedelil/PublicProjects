


/**
* Affiche la liste des mots du fichier d’entrée 
* en ordre et sans doublons.

* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*   - tete: l’adresse de la tête de le liste..
*/
void afficherListeOrdonnee(char* fichierDentree, struct cellule** tete);

/**
* Affiche la liste des mots du fichier d’entrée en ordre 
* et sans doublons et génère le fichier des statistiques. 
*
* Paramètres :
*   - option : l’option.
*   - tete; l’adresse de la tête de la liste.
*   - fichierDentree : le fichier d’entrée.
*   - fichierDeSortie : le fichier de sortie.
*/
void afficherListeEtGenererStat(char* option, struct cellule** tete, 
        char* fichierDentree, char* fichierDeSortie);


