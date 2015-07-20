#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "listeChainee.h"
#include "main.h"


void fermetureDuFichier(FILE* entree)
{
  if(fclose(entree) == EOF)
  {
    printf("Erreur lors de la fermeture du fichier.\n");
    exit(1);
  }
}

void sortieDurgenceDuFichier()
{
    printf("Erreur: %s\n", strerror(errno));
    exit(1);
}

int obtenirNombreDeMot(char* fichierDentree)
{
  char mot[81];
  int nombreDeMot = 0;
  FILE* entree = fopen(fichierDentree,"r");
  if(entree == NULL)
    sortieDurgenceDuFichier();
  while (fscanf(entree,"%s",mot) > 0)
    nombreDeMot++;
  fermetureDuFichier(entree);
  return nombreDeMot;
}

int obtenirNombreDeLigne(char* fichierDentree)
{
  char carac;
  int nombreDeLigne = 0;
  FILE* entree = fopen(fichierDentree,"r");
  if(entree == NULL)
    sortieDurgenceDuFichier();
  while ((carac = fgetc(entree)) != EOF)
  {
    if(carac == '\n')
      nombreDeLigne++;
  }
  fermetureDuFichier(entree);
  return nombreDeLigne;
}

struct cellule* recupererLesMotsDuFichier(char* fichierDentree, struct cellule* tete)
{
  char mot[81];
  int ajoute = 0;
  FILE* entree = fopen(fichierDentree,"r");
  if(entree == NULL)
    sortieDurgenceDuFichier();
  while(fscanf(entree,"%s",mot) > 0)
  {
    supprimerNonMajuscule(mot);
    if(contient(mot, &tete))
      incrementerValeurNoeud(mot, &tete);
    else
    {
      ajoute = ajouterMotALaListe(mot, &tete);
      if(!ajoute)
        ErreurAllocation();
    }
  }
  fermetureDuFichier(entree);
  return tete;
}

void genererFichierDeSortie(char* fichierDentree, struct cellule* tete, 
        char* fichierDeSortie)
{
  char* chaine = concatenerLesMotsDeLaListe(tete);
  int nbMotSansDoublons = obtenirNombreElementDeLaListe(tete);
  int nbMotAvecDoublons = obtenirNombreDeMot(fichierDentree) - 
  obtenirNombreElementDeLaListe(tete);
  int nbLigne = obtenirNombreDeLigne(fichierDentree);
  int nbLettre = obtenirNbLettresMotSansDoublons(tete);
  char lettre = obtenirLettrePlusFrequente(chaine);
  free(chaine);
  FILE* sortie = fopen(fichierDeSortie, "w");
  fprintf(sortie, "le nombre de mots sans doublons: %d\n", nbMotSansDoublons);
  fprintf(sortie, "le nombre de mots avec doublons: %d\n", nbMotAvecDoublons);
  fprintf(sortie, "le nombre de lignes dans le fichier d'entrée: %d\n", nbLigne);
  fprintf(sortie, "le nombre de lettres dans la liste de mots (sans doublons): %d\n", nbLettre);
  fprintf(sortie, "la lettre la plus fréquente (sans considérer les doublons): %c\n", lettre);
  fermetureDuFichier (sortie);
}

void afficherListeEtGenererStat(char* option, struct cellule** tete, 
        char* fichierDentree, char* fichierDeSortie)
{
  if(strcmp(option,"-S") == 0)
  {
    printf("Liste ordonnée des mots sans doublons:\n");
    *tete = recupererLesMotsDuFichier(fichierDentree, *tete);
    afficherListeChainee(*tete);
    genererFichierDeSortie(fichierDentree,*tete,fichierDeSortie );
  }
  else
    printf("Erreur: L'option indiquée, n'est pas valide\n");
}
