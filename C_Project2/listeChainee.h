/**
* Structure d'un noeud avec les champs: 
*    - valeur   : qui indique le nombre d'occurence du mot.
*    - mot      : qui reçoit le mot extrait du fichier.
*    - suivant  : contient la référence de la tête de la structure suivante.
*/
struct cellule
{
  int valeur;
  char mot[81];
  struct cellule* suivant;
};
/**
* Créé une structure par allocation de mémoire et initialise ces champs.
* S’il survient une erreur d’allocation, elle return NULL sinon elle 
* retourne un pointeur sur la structure ainsi créée.
*
* Paramètre: 
*    - chaine; une chaine de caractères pour initialiser le champ mot.
*/
struct cellule* creerLeNoeud(char* chaine);

/**
* Ajoute un noeud(une structure) à la liste. Elle reçoit en paramètre la tête de
* la liste et la chaine à placer dans le noeud. Si le noeud est ajouté, elle
* retourne 1 sinon 0.
*	 
* Paramètres :
*    - chaine; la chaine à placer dans le noeud.
*    - tete; l’adresse de la tête de la liste.
*/
int ajouterMotALaListe(char* chaine, struct cellule** tete);

/**
* Reçoit en paramètre la tête de la liste chainée et affiche le champ mot 
* de chacun de ces noeuds.
*
* Paramètres :
*    - tete; référence de la tête de la liste.
*/
void afficherListeChainee(struct cellule* tete);

/**
* Affiche un message d’erreur d’allocation et interrompt 
*le programme.
*/
void ErreurAllocation();

/**
* Vérifie si un noeud dans la liste contient la chaine de caractères reçu en 
* paramètre. Retourne 1 si oui et 0 sinon.
*
* Paramètres:
*   - chaine; la chaine de caractères
*   - tete; l’adresse de la tête de la liste.
*/
int contient(char* chaine, struct cellule** tete);

/**
* Incrémente la valeur du champ valeur, si le noeud qui contient la chaine
* reçu en paramètre. Elle retourne 1 si une incrémentation a été faite sinon 0.
*	 
*Paramètres :
*   - chaine; la chaine de caractères
*   - tete; l’adresse de la tête de la liste.
*/
int incrementerValeurNoeud(char* chaine, struct cellule** tete);

/**
* Retourne le nombre d’élément contenu dans la liste.
* de caractères reçu en paramètre.
*
*Paramètre:
*   - tete; référence de la tête de la liste.
*/
int obtenirNombreElementDeLaListe(struct cellule* tete);

/**
* Retourne le nombre de lettres des mots sans doublons.
*
*Paramètre:
*   - tete; référence de la tête de la liste.
*/
int obtenirNbLettresMotSansDoublons(struct cellule* tete);

/**
* Effectue la concaténation des mots de la liste sans doublons et  
* retourne la chaine ainsi obtenue.
*
*Paramètres :
*   - tete; référence de la tête de la liste.
*/
char* concatenerLesMotsDeLaListe(struct cellule* tete);

/**
* Reçoit une chaine et un caractère en paramètre, compte le nombre 
* d’occurence du caractère dans la chaine et retourne le resultat.
*
* Paramètres :
*   - chaine; la chaine de caractères.
*   - lettre;le caractère.
*/
int compterNombreOccurence(char* chaine, char lettre);

/**
* Retourne la lettre de la chaine reçu en paramètre qui apparait le plus souvent. 
*	
* Paramètres :
*   - chaine; la chaine de caractères.
*/
char obtenirLettrePlusFrequente(char* chaine);

/**
* Reçoit un fichier en entrée, ordonne son contenu et l’affiche à la console. 
*
* Paramètres :
*   - fichierDentree; le nom du fichier d’entrée.
*   - tete; l’adresse de la tête de le liste.
*/
void afficherListeOrdonnee(char* fichierDentree, struct cellule** tete);

/**
* Libère la mémoire réservée à la liste chainée. 
*
* Paramètres :
*   - tete; l’adresse de la tête de la liste.
*/
void libererLaListeChainee(struct cellule** tete);

/**
* Supprime tous les caractères autres que des caractères alphabétiques 
* majuscules dans la chaine de caractères reçu en paramètre.
* de caractères reçu en paramètre.
*
* Paramètre:
*   - tab[81]; la chaine de caractères à nettoyer.
*/
void supprimerNonMajuscule(char tab[81]);
