#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "gestionDesStatistiques.h"
#include "listeChainee.h"
#include "gestionFichier.h"
#include "main.h"

struct cellule* creerLeNoeud(char* chaine)
{
  struct cellule* ptr = malloc(sizeof(struct cellule));
  if(ptr == NULL)
    return NULL;
  ptr->valeur = 1;
  strcpy(ptr->mot, chaine);
  ptr->suivant = NULL;
  return ptr;
}

int ajouterMotALaListe(char* chaine, struct cellule** tete)
{
  int estAjoute = 0;
  if(*tete == NULL)// La liste set vide
  {
    *tete = creerLeNoeud(chaine);
    if(*tete == NULL)
      return estAjoute;
    estAjoute = 1;
  }
  else
  {
    struct cellule* precedant = NULL;
    struct cellule* courant = *tete;
    while(courant != NULL && ((strcmp(chaine, courant ->mot)) >= 0))
    {
      precedant = courant;
      courant = courant ->suivant;
    }
    if(precedant == NULL)//S'il s'agit du premier de la liste
    {
      struct cellule* ptr = creerLeNoeud (chaine);
      if(ptr == NULL)
        return estAjoute;
      ptr ->suivant = *tete;
      *tete = ptr;
      estAjoute = 1;
    }
    else
    {
      struct cellule* ptr = creerLeNoeud(chaine);
      if(ptr == NULL)
        return estAjoute;
      precedant ->suivant = ptr;
      ptr ->suivant = courant;
      estAjoute = 1;
    }
  }
  return estAjoute;
}

void afficherListeChainee(struct cellule* tete)
{
  struct cellule* courant = tete;
  while(courant != NULL)
  {
    printf("%s\n", courant ->mot);
    courant = courant ->suivant;
  }
}

void ErreurAllocation()
{
  printf("Erreur d'allocation dans la création du noeud\n");
  exit(1);
}

int contient(char* chaine, struct cellule** tete)
{
  struct cellule* courant = *tete;
  int existe = 0;
  if(*tete != NULL)
  {
    while(courant != NULL && !existe)
    {
      if(strcmp(chaine, courant ->mot) == 0)
        existe = 1;
      courant = courant ->suivant;
    }
  }
  return existe;
}

int incrementerValeurNoeud(char* chaine, struct cellule** tete)
{
  struct cellule* courant = *tete;
  int estCompte = 0;
  while(courant != NULL && !estCompte)
  {
    if(strcmp(chaine, courant ->mot) == 0)
    {
      courant ->valeur = courant ->valeur + 1;
      estCompte = 1;
    }
    courant = courant ->suivant;
  }
  return estCompte;
}

int obtenirNbDesMotsSansDoublons(struct cellule* tete)
{
  struct cellule* courant = tete;
  int compteur = 0;
  while(courant != NULL)
  {
    compteur++;
    courant = courant ->suivant;
  }
  return compteur ;
}

int obtenirNbLettresDesMotSansDoublons(struct cellule* tete)
{
  struct cellule* courant = tete;
  int compteur = 0;
  while(courant != NULL)
  {
    compteur = compteur + strlen(courant ->mot);
    courant = courant ->suivant;
  }
  return compteur;
}

char* concatenerLesMotsSansDoublons(struct cellule* tete)
{
  int taille = obtenirNbLettresDesMotSansDoublons(tete);
  char* chaine = calloc(taille+1, sizeof(char));
  if(chaine == NULL)
    ErreurAllocation();
  struct cellule* courant = tete;
  while(courant != NULL)
  {
    strcat(chaine, courant ->mot ); 
    courant = courant ->suivant;
  }
  return chaine;
}

void libererLaListeChainee(struct cellule** tete)
{
  struct cellule* courant = *tete;
  struct cellule* tmp;
  while (courant != NULL)
  {
    tmp = courant;
    courant = courant->suivant;
    free(tmp);
  }
}
