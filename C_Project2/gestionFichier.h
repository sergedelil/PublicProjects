
/**
* ouvre un flot de lecture sur le fichier d’entrée 
* et retourne le flot.
*
* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*/
FILE* ouvertureDuFichier(char* fichierDentree);


/**
* Retourne 1 pour valider que l'ouverture est 
* réalisée avec succès, sinon retourne 0.
*
* Paramètres :
*   - entree : le flot sur le fichier d’entrée.
*/
int validerOuvertureDuFichier(FILE* entree);

/**
* Détecte s'il s'est produit une erreur lors de 
* l'ouverture du fichier et affiche un message correspondant.
*
*/
void afficherMessageErreurOuvertureFichier();

/**
* Ouvre un flot de lecture sur le fichier et gère
* les éventuelles erreurs occasionnées en retournant le flot. 
*
* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*/
FILE* ouvrirFichier(char* fichierDentree);


/**
* Affiche un message d'erreur à la fermeture du fichier.
*
*/
void afficherMessageErreurDeFermeture();

/**
* Ferme le flot de lecture ouvert sur le fichier et affiche
* un message en cas d'erreur.
*
* Paramètres :
*   - entree : le flot sur le fichier d’entrée.
*/
void fermerFichier(FILE* entree);

/**
* Récupère chaque mot du fichier et l’ajoute à la liste chainée. Elle retourne 
* un pointeur sur la tête de la liste.
*	 
* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*   - tete; l’adresse de la tête de la liste.
*/
struct cellule* recupererLesMotsDuFichier(char* fichierDentree, struct cellule* tete);

/**
* Calcul les statistiques et génère le fichier de sortie. 
* Elle retourne un pointeur sur la tête de la liste.
*	 
* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*   - tete; l’adresse de la tête de la liste.
*   - fichierDeSortie : le fichier de sortie.
*/
void genererFichierDeSortie(char* fichierDentree, struct cellule* tete, char* fichierDeSortie);

/**
* Supprime tous les caractères autres que des caractères alphabétiques 
* majuscules dans la chaine de caractères reçu en paramètre.
* de caractères reçu en paramètre.
*
* Paramètre:
*   - tab[81]; la chaine de caractères à nettoyer.
*/
void supprimerNonMajuscule(char tab[81]);
