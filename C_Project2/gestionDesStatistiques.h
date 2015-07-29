

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
* Reçoit une chaîne et un caractère en paramètre, compte le nombre 
* d’occurrence du caractère dans la chaîne et retourne le résultat.
*
* Paramètres :
*   - chaine; la chaîne de caractères.
*   - lettre;le caractère.
*/
int compterNombreOccurrence(char* chaine, char lettre);

/**
* Retourne la lettre de la chaîne reçu en paramètre qui apparaît le plus souvent. 
*	
* Paramètres :
*   - chaine; la chaîne de caractères.
*/
char obtenirLettrePlusFrequente(char* chaine);



