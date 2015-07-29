/**
* Structure d'un noeud avec les champs: 
*    - valeur   : qui indique le nombre d'occurrence du mot.
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
* S’il survient une erreur d’allocation, elle retourne NULL sinon elle 
* retourne un pointeur sur la structure ainsi créée.
*
* Paramètre: 
*    - chaine; une chaîne de caractères pour initialiser le champ mot.
*/
struct cellule* creerLeNoeud(char* chaine);

/**
* Ajoute le noeud contenant le mot à la liste par ordre croissant sans doublons. 
* Elle reçoit en paramètre la tête de la liste et le mot à placer dans le 
* noeud. Si le noeud est ajouté, elle
* retourne 1 sinon 0.
*	 
* Paramètres :
*    - chaine; la chaîne à placer dans le noeud.
*    - tete; l’adresse de la tête de la liste.
*/
int ajouterMotALaListe(char* chaine, struct cellule** tete);

/**
* Reçoit en paramètre la tête de la liste chaînée et affiche le champ mot 
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
* Vérifie si un noeud dans la liste contient la chaîne de caractères reçu en 
* paramètre. Retourne 1 si oui et 0 sinon.
*
* Paramètres:
*   - chaine; la chaîne de caractères
*   - tete; l’adresse de la tête de la liste.
*/
int contient(char* chaine, struct cellule** tete);

/**
* Incrémente la valeur du champ valeur, si le noeud qui contient la chaîne
* reçu en paramètre. Elle retourne 1 si une incrémentation a été faite sinon 0.
*	 
*Paramètres :
*   - chaine; la chaîne de caractères
*   - tete; l’adresse de la tête de la liste.
*/
int incrementerValeurNoeud(char* chaine, struct cellule** tete);

/**
* Retourne le nombre total d’élément contenu dans la liste.
* de caractères reçu en paramètre.
*
*Paramètre:
*   - tete; référence de la tête de la liste.
*/
int obtenirNbDesMotsSansDoublons(struct cellule* tete);

/**
* Retourne le nombre de lettres des mots sans doublons.
*
*Paramètre:
*   - tete; référence de la tête de la liste.
*/
int obtenirNbLettresDesMotSansDoublons(struct cellule* tete);


/**
* Effectue la concaténation des mots de la liste sans doublons et  
* retourne la chaîne ainsi obtenue.
*
*Paramètres :
*   - tete; référence de la tête de la liste.
*/
char* concatenerLesMotsSansDoublons(struct cellule* tete);

/**
* Libère la mémoire réservée à la liste chaînée. 
*
* Paramètres :
*   - tete; l’adresse de la tête de la liste.
*/
void libererLaListeChainee(struct cellule** tete);


