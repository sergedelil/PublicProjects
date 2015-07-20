
/**
* Reçoit le stream d’ouverture du fichier en paramètre et assure
* sa fermeture avec gestion des possibles les erreurs. 
*	
* Paramètres :
*   - entree; stream d’entrée du fichier.
*/
void fermetureDuFichier(FILE* entree);

/**
* Affiche un message d’erreur et provoque l’arrêt du programme.
*/
void sortieDurgenceDuFichier();

/**
* Reçoit le fichier d’entrée en paramètre et retourne le nombre
* de mot que celui contient avec gestion des possibles les erreurs
* d’ouverture de fichier. 
*	
* Paramètres :
*   - fichierDentree; le fichier d’entrée.
*/
int obtenirNombreDeMot(char* fichierDentree);

/**
* Reçoit le fichier d’entrée en paramètre et retourne le nombre
* de ligne que celui contient avec gestion des possibles les erreurs
* d’ouverture de fichier. 
*	
* Paramètres :
*   - fichierDentree : le fichier d’entrée.
*/
int obtenirNombreDeLigne(char* fichierDentree);

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
* Affiche la liste chainée à la console et génère le fichier de sortie.
*	 
* Paramètres :
*   - option : l’option.
*   - tete: l’adresse de la tête de la liste.
*   - fichierDentree : le fichier d’entrée.
*   - fichierDeSortie : le fichier de sortie.
*/
void afficherListeEtGenererStat(char* option, struct cellule** tete, 
        char* fichierDentree, char* fichierDeSortie);
