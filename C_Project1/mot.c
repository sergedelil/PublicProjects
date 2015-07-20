/* 
 * File:   mot.c
 * Author: sergedelil
 *Code permanent : DOGG03078104
 * Created on 30 mai 2015, 15:55
 */

#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "mot.h"


int main(int argc, char ** argv)
{
   char mat[12][15];
   char matCopie[12][15];
   char matMot[255][15];
   
   validerArgument(argc);
   int nbMot = chargerFichier(mat, matCopie, matMot, argv[1]);
   rechercheMot(mat, matMot, matCopie, nbMot);
   afficherMatrice(matCopie);
   return 0;
}


void validerArgument(int argc)
{
   if (argc != 2)
   {
      printf("\n Erreur : Un argument manque au programme.\n\n");
      exit(1);
   }
}

void rechercheMot(char mat[12][15], char matMot[255][15],char matCopie[12][15],
        int nbMot)
{
   char colonne[13];
   int reponse;
   int i,j;
   char mot[14];
   char motInverse[14];
   for (i = 0; i < nbMot; i++)
   {
      strcpy(mot, matMot[i]);
      supprimerEspace (mot);
      inverserMot(mot, motInverse);
      reponse = verifierParLigne(mot, motInverse, mat, matCopie);
      if (!reponse)
      {
         for (j = 0; j < 12 && !reponse; j++)
         {
            extraireColonne (mat, colonne, j);
            reponse = verifierParColonne(mot, motInverse, colonne, matCopie, j);
         }  
      }
   } 
}

int verifierParLigne(char mot[14], char motInverse[14], char mat[12][15],
        char matCopie[12][15])
{
   int i;
   int resultat = 0;
   for (i = 0; i < 12 && !resultat; i++)
   {
      resultat = repererMotSurLigneEtSupprimer(mot, motInverse, mat, matCopie,i);
   }
   return resultat;
}

void extraireColonne (char mat[12][15], char colonne[13], int noColonne)
{
   int j;
   for (j = 0; j< 12; j++)
   {
      colonne[j] = mat[j][noColonne];
   }
   colonne[j] = '\0';
}

void inverserMot(char mot[14], char motInverse[14])
{
   int tailleChaine = strlen(mot);
   int i; int j = 0;
   for(i = tailleChaine - 1; i >= 0; i--)
   {
      motInverse[j] = mot[i];
      j++;
   }
   motInverse[j] = '\0';
   
}
int verifierParColonne(char mot[14], char motInverse[14], 
        char colonne[13], char matCopie[12][15], int noColonne)
{
   int trouve = 0;
   if (strstr(colonne, mot) == NULL)
   {
      if (strstr(colonne, motInverse) != NULL)
      {
         trouve = 1;
         eliminerMotSurColonne(colonne,matCopie, motInverse, noColonne);
      }
   }
   else
   {
      trouve = 1;
      eliminerMotSurColonne(colonne, matCopie, mot, noColonne);
   }
   return trouve;
}

void supprimerEspace (char tab[14])
{
   int i;
   int j = 0;
   for(i = 0; tab[i] != '\0'; i++)
   {
      if(tab[i] != ' ' && tab[i] != '\t' && tab[i] >= 'A' && tab[i] <= 'Z')
      {
         tab[j] = tab[i];
         j++;
      }
   }
   tab[j] = '\0';
}

void afficherMatrice(char matCopie[12][15])
{
   int i;
   for (i = 0; i < 12; i++)
   {
      supprimerEspace (matCopie[i]);
      printf("%s", matCopie[i]);
   }
   printf("\n");
}

void eliminerMotSurColonne(char colonne[13], char matCopie[12][15], 
        char mot[14], int noColonne)
 {
   int i,j,k,s;
   int egal;
   int trouve = 0;
   for(i = 0; i < 12 && !trouve; i++)
   {
      s = i;
      k = 0;
      egal = 0;
      while(k < strlen(mot) && !egal)
      { 
         if (matCopie[s][noColonne] == ' ')
         {
            if(colonne[s] == mot[k])
            {
               s++; k++;
            }
            else 
               egal = 1;
         }
         else if(colonne[s] == mot[k])
         {
            s++; k++;
         }
         else
            egal = 1;
      }
      if (k == strlen(mot) )
      {
         trouve = 1;
         for (j = i, k = 0; k < strlen(mot); k++,j++)
         {
            matCopie[j][noColonne] = ' ';
         }
      }
   }
} 

void eliminerMotsurligne(char ligne[13], char mot[14], char matCopie[12][15],
        int noLigne)
{
   int i,j,k,s;
   int egal;
   int trouve = 0;
   for(i = 0; i < 12 && !trouve; i++)
   {
      s = i;
      k = 0;
      egal = 0;
      while(k < strlen(mot) && !egal)
      { 
         if (matCopie[noLigne][s] == ' ')
         {
            if(ligne[s] == mot[k])
            {
               s++; k++;
            }
            else 
               egal = 1;
         }
         else if(ligne[s] == mot[k])
         {
            s++; k++;
         }
         else
            egal = 1;
      }
      if (k == strlen(mot) )
      {
         trouve = 1;
         for (j = i, k = 0; k < strlen(mot); k++,j++)
         {
            matCopie[noLigne][j] = ' ';
         }
      }
   }
   
}
   
int chargerFichier(char mat[12][15], char matCopie[12][15], char matMot[255][15], 
        char nomFichier[50])
{
   FILE * entree;
   char tampon[14];
   int noLigne = 0;
   int nbMot = 0;
   entree = fopen(nomFichier, "r");
   if (entree == NULL)
   {
      printf("Erreur: %s\n", strerror(errno));
      exit(1);
   }
   while (fgets(tampon, 14, entree) != NULL)
   {
      if(noLigne < 12)
      {
         strcpy(mat[noLigne], tampon);
         strcpy(matCopie[noLigne], tampon);
      }
      else if(noLigne > 12)
      {
         supprimerEspace (tampon);
         if(strlen(tampon) != 0)
         {
            strcpy(matMot[nbMot], tampon);
            nbMot++;
         }
      }
      noLigne++;
   }
   if (fclose(entree) == EOF)
   {
      printf("Erreur lors de la fermeture du fichier.\n");
      exit(1);
   }
   return nbMot;
}

int repererMotSurLigneEtSupprimer(char mot[14], char motInverse[14], 
           char mat[12][15], char matCopie[12][15], int i)
{
   int trouve = 0;
   if (strstr(mat[i], mot) == NULL)
   {
      if (strstr(mat[i], motInverse) != NULL)
      {
         trouve = 1;
         eliminerMotsurligne(mat[i], motInverse, matCopie, i);
      }
   }
   else 
   {
      trouve = 1;
      eliminerMotsurligne(mat[i], mot, matCopie, i);
   }
   return trouve;
}
   
 

