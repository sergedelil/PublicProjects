#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "gestionDesStatistiques.h"
#include "listeChainee.h"
#include "gestionFichier.h"
#include "main.h"


FILE* ouvertureDuFichier(char* fichierDentree)
{
  return fopen(fichierDentree,"r");;
}

int validerOuvertureDuFichier(FILE* entree)
{
  int reponse = 1;
  if(entree == NULL)
    reponse = 0;
  return reponse;
}

void afficherMessageErreurOuvertureFichier()
{
  printf("Erreur: %s\n", strerror(errno));
  exit(1);
}

FILE* ouvrirFichier(char* fichierDentree)
{
  FILE* entree = ouvertureDuFichier(fichierDentree);
  int resultat = validerOuvertureDuFichier(entree);
  if(!resultat)
    afficherMessageErreurOuvertureFichier();
  return entree;
}

void afficherMessageErreurDeFermeture()
{
  printf("\nErreur lors de la fermeture du fichier.\n");
  exit(1);
}

void fermerFichier(FILE* entree)
{
  if(fclose(entree) == EOF)
    afficherMessageErreurDeFermeture(); 
}

struct cellule* recupererLesMotsDuFichier(char* fichierDentree, struct cellule* tete)
{
  char mot[81];
  int ajoute = 0;
  FILE* entree = ouvrirFichier(fichierDentree);
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
  fermerFichier(entree);
  return tete;
}

void genererFichierDeSortie(char* fichierDentree, struct cellule* tete, 
        char* fichierDeSortie)
{
  char* chaine = concatenerLesMotsSansDoublons(tete);
  char* texte1 = "le nombre de mots sans doublons: %d\n";
  char* texte2 = "le nombre de mots avec doublons: %d\n";
  char* texte3 = "le nombre de lignes dans le fichier d'entrée: %d\n";
  char* texte4 = "le nombre de lettres dans la liste de mots (sans doublons): %d\n";
  char* texte5 = "la lettre la plus fréquente (sans considérer les doublons): %c\n";
  FILE* sortie = fopen(fichierDeSortie, "w");
  fprintf(sortie, texte1, obtenirNbDesMotsSansDoublons(tete));
  fprintf(sortie, texte2, obtenirNombreDeMot(fichierDentree));
  fprintf(sortie, texte3, obtenirNombreDeLigne(fichierDentree));
  fprintf(sortie, texte4, obtenirNbLettresDesMotSansDoublons(tete));
  fprintf(sortie, texte5, obtenirLettrePlusFrequente(chaine));
  free(chaine);
  fermerFichier (sortie);
}

void supprimerNonMajuscule (char tab[81])
{
   int i;
   int j = 0;
   for(i = 0; tab[i] != '\0'; i++)
   {
      if(tab[i] >= 'A' && tab[i] <= 'Z')
      {
         tab[j] = tab[i];
         j++;
      }
   }
   tab[j] = '\0';
}

