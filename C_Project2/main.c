#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "listeChainee.h"
#include "gestionDesStatistiques.h"
#include "gestionFichier.h"
#include "main.h"

int main(int argc, char** argv) 
{
  struct cellule* tete = NULL;
  if(argc == 1 || argc == 3 || argc > 4)
    printf("Argument(s) manquant(s)\n");
  else if(argc == 2)
    afficherListeOrdonnee(argv[1],&tete);
  else if(argc == 4)
    afficherListeEtGenererStat(argv[2],&tete, argv[1], argv[3]);
  libererLaListeChainee(&tete);
  return 0;
}


void afficherListeOrdonnee(char* fichierDentree, struct cellule** tete)
{
  *tete = recupererLesMotsDuFichier(fichierDentree, *tete);
  if(*tete != NULL)
  {
    printf("\nListe ordonnée des mots sans doublons:\n");
    afficherListeChainee(*tete);
  }
  else
    printf("La liste est vide\n");
}

void afficherListeEtGenererStat(char* option, struct cellule** tete, 
        char* fichierDentree, char* fichierDeSortie)
{
  if(strcmp(option,"-S") == 0)
  {
    *tete = recupererLesMotsDuFichier(fichierDentree, *tete);
    if(*tete != NULL)
    {
      printf("Liste ordonnée des mots sans doublons:\n");
      afficherListeChainee(*tete);
      genererFichierDeSortie(fichierDentree,*tete,fichierDeSortie );
    }
    else
      printf("La liste est vide: Aucune nouvelle statistique n'est générée.\n");
  }
  else
    printf("Erreur: L'option indiquée, n'est pas valide\n");
}
