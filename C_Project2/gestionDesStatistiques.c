#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "gestionDesStatistiques.h"
#include "listeChainee.h"
#include "gestionFichier.h"
#include "main.h"


int obtenirNombreDeMot(char* fichierDentree)
{
  char mot[81];
  int nombreDeMot = 0;
  FILE* entree = ouvrirFichier(fichierDentree);
  while (fscanf(entree,"%s",mot) > 0)
    nombreDeMot++;
  fermerFichier(entree);
  return nombreDeMot;
}

int obtenirNombreDeLigne(char* fichierDentree)
{
  char carac;
  int nombreDeLigne = 0, fin = EOF;
  FILE* entree = ouvrirFichier(fichierDentree);
  while((carac = fgetc(entree)) != EOF)
  {
    if(carac == '\n')
      nombreDeLigne++;
    fin = carac;
  }
  if(fin != EOF && fin != '\n')
    nombreDeLigne++;
  else if (fin != EOF && fin == '\n')
    nombreDeLigne++;
  fermerFichier(entree);
  return nombreDeLigne;
}

int compterNombreOccurrence(char* chaine, char lettre)
{
  int i, compteur = 0;
  for(i = 0; i < strlen(chaine); i++)
  {
    if(chaine[i] == lettre)
      compteur++;
  }
  return compteur;
}

char obtenirLettrePlusFrequente(char* chaine)
{
  int i, nombre, max = 0;
  char lettre;
  for(i = 0; i < strlen(chaine); i++)
  {
    nombre = compterNombreOccurrence (chaine, chaine[i]);
    if(nombre > max)
    {
      max = nombre;
      lettre = chaine[i];
    }
  }
  return lettre;
}
