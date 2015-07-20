#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "listeChainee.h"
#include "gestionDesStatistiques.h"
#include "main.h"

int main(int argc, char** argv) 
{
  struct cellule* tete = NULL;
  if(argc == 1)
    printf("Argument(s) manquant(s)\n");
  else if(argc == 2)
    afficherListeOrdonnee(argv[1],&tete);
  else if(argc == 4)
    afficherListeEtGenererStat(argv[2],&tete, argv[1], argv[3]);
  libererLaListeChainee(&tete);
  return 0;
}