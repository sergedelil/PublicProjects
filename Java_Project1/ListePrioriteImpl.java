package ca.uqam.inf2120.tp1.adt.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ca.uqam.inf2120.tp1.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp1.adt.Priorite;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP1
 * 
 * ListePrioriteImpl : Impl�mentation de l'interface ListePrioriteTda.
 * 
 * @author DOGNY Gnagnely Serge ;  code permanent: DOGG03078104
 * @version 06 juin 2014
 */
public class ListePrioriteImpl<T extends Priorite> implements ListePrioriteTda<T> {

	private List<T> liste;
	
	// Constructeur

	public ListePrioriteImpl() {
		liste = new ArrayList<T>();
	}

	/**
	 * Ajoute l'�l�ment "elt" dans la liste selon sa priorit�. Les �l�ments de la
	 * plus grande priorit� sont ajout�s au d�but de la liste. L'�l�ment ne doit 
	 * pas �tre ajout� si une des conditions suivantes est vraie :
	 *   - L'�l�ment est null.
	 *   - L'�l�ment existe d�j� dans la liste (�l�ments identiques sans tenir compte 
	 *     de leur priorit�).
	 *   - La priorit� de l'�l�ment est inf�rieure ou �gale � 0.
	 *   
	 * Si l'�l�ment "elt" � ajouter a la m�me priorit� qu'un �l�ment qui existe dans 
	 * la liste, il doit �tre ajout� apr�s ce dernier.
	 *
	 * @param elt L'�l�ment � ajouter
	 * @return true si "elt" est ajout�, sinon false
	 */
	@Override
	public boolean ajouter(T elt) {
		
		boolean res = false;
		boolean inser = true;
		int priorite;
		int indice;	
		
		if (elt != null && elt.obtenirPriorite() > 0 ){
				// Ajoute l'�l�ment, si la liste est vide		
			
				if (liste.size() == 0){
					liste.add(elt);	
					res = true;
					
				}else {
					
					if (!liste.contains(elt)){
						priorite = elt.obtenirPriorite();
						
							for(int i = liste.size() - 1 ; i >= 0 && inser; i--){
								/*si la priorit� de l'�l�ment re�u est plus petite ou �gale � celle
								 *du dernier �l�ment de la liste, on l'ajoute � l'indice suivant.*/
								
								if (priorite <= (liste.get(i)).obtenirPriorite()){
									indice = liste.indexOf(liste.get(i));
									liste.add(indice + 1, elt);
									res = true;
									inser = false;										
								}
								/*si on est rendu au debut de la liste et que la priorit� de l'�l�ment 
								 *re�u est la plus grande, on l'ajoute � l'indice 0.*/
								
								if (i == 0 && priorite > (liste.get(i)).obtenirPriorite()){
									indice = liste.indexOf(liste.get(i));
									liste.add(indice, elt);
									res = true;
									inser = false;										
								}					
							}
					}
				}			
		}
		
		return res;
	}

	/**
	 * Ajoute tous les �l�ments de la liste pass�e en param�tre dans la
	 * liste existante. Tous les �l�ments sont ajout�s dans la liste selon leur
	 * priorit�. Les �l�ments de la plus grande priorit� sont ajout�s au d�but de 
	 * la liste. Aucun ajout si une des conditions suivantes est vraie :
	 *   - La liste est nulle.
	 *   - L'�l�ment � ajouter est null.
	 *   - L'�l�ment existe d�j� dans la liste (�l�ments identiques sans tenir compte
	 *     de leur priorit�).
	 *   - La priorit� de l'�l�ment est inf�rieure ou �gale � 0.
	 * 
	 * Si l'�l�ment � ajouter a la m�me priorit� qu'un �l�ment qui existe dans la liste,
	 * il doit �tre ajout� apr�s ce dernier.
	 * 
	 * @param liste Le tableau liste (ArrayList) dont les �l�ments doivent �tre ajout�s
	 */
	@Override
	public void ajouter(List<T> liste) {
						
		if (liste != null && liste.size() != 0){
									
			for (int i = 0; i < liste.size(); i++){
				// passe chaque �l�ment de la liste re�u � la m�thode ajouer pour l'ajout.			 
				
				ajouter(liste.get(i));
				
			}
				
		}				
	}

	/**
	 * Supprime "elt" dans la liste existante. Aucune suppression si "elt" est
	 * nul ou s'il n'existe pas dans la liste. L'�l�ment � supprimer doit �tre
	 * �gal � celui pass� en param�tre ("elt") avec la m�me priorit�.
	 * 
	 * @param elt L'�l�ment � supprimer
	 * @return Vrai si l'�l�ment est supprim�
	 */
	@Override
	public boolean supprimer(T elt) {
		
		boolean res = false;
		boolean trouve = true;		
		int i = 0;
		
		if (elt != null && elt.obtenirPriorite() > 0){		
		
			while ( i < liste.size() && trouve){
				
				if(elt.equals(liste.get(i)) ){
					
					if(elt.obtenirPriorite() == (liste.get(i)).obtenirPriorite());{
						
						liste.remove(i);
						res = true;	
						trouve = false;
					}					
				}
				
			    i++;								
			}				
		}
		return res;
	}

	/**
	 * Supprime tous les �l�ments de la liste pass�e en param�tre dans la liste
	 * existante. Tous les �l�ments non supprim�s de la liste pass�e en param�tre
	 * sont retourn�s dans un tableau liste (ArrayList). L'�l�ment � supprimer doit
	 * �tre �gal � "elt" avec la m�me priorit�.
	 * 
	 * @param liste Le tableau liste (ArrayList) dont les �l�ments doivent �tre supprim�s
	 * @return Le tableau liste (ArrayList) des �l�ments non supprim�s, nul si tous les �l�ments
	 *         sont supprim�s.
	 */
	@Override
	public List<T> supprimer(List<T> liste) {
				
		int comptElNonSupprime = 0;		
		boolean res;
		List<T> listeRetour = null;	
		
		if (liste != null && liste.size() != 0){
			
			listeRetour = new ArrayList<T>();
			
			for (int i = 0; i < liste.size(); i++){	
				
				  res = supprimer(liste.get(i));
				  
				  if (!res){
					  //si l'�l�ment n'a pas �t� supprim�, l'ajouter � la liste de retour
					  listeRetour.add(liste.get(i));
					  comptElNonSupprime++;					  
				  }
			}
			// si tous les �l�ments ont �t� supprim�s, retourner la valeur nulle	
			if (comptElNonSupprime == 0){
				
				listeRetour = null;
			}
		}
		return listeRetour;
	}

	/**
	 * Supprime tous les �l�ments dont la priorit� est �gale � celle pass�e en
	 * param�tre. Les �l�ments supprim�s sont retourn�s dans un tableau liste.
	 * Une valeur nulle est retourn�e si aucun �l�ment n'est supprim�.
	 * 
	 * @param priorite La priorit� des �l�ments � supprimer
	 * @return Le tableau liste des �l�ments supprim�s
	 */
	@Override
	public List<T> supprimer(int priorite) {
				
		int i = 0;
		int j = 0;
		int taille = liste.size();		
		List<T> listeRetour = new ArrayList<T>();
		
		while ( i < liste.size()){
			
			j = i; // conserve la position de l'indice.
			
			if(liste.get(i).obtenirPriorite() == priorite){
				listeRetour.add(liste.get(i));	
				liste.remove(i);				
				i = j;  // reprend le parcours, � partir de l'indice ou la suppression a eu lieu.
				
			}else{
				
				i++;
			}
		}
		
		if (i == taille){ // si aucun �l�ment n'a �t� supprim�, retourne une valeur nulle.
			
			listeRetour = null;
		}
		
		return listeRetour;
	}

	/**
	 * Supprime les �l�ments selon les conditions suivantes :
	 *   - Si le param�tre "plusPetit" est vrai, les �l�ments � supprimer doivent
	 *     avoir la priorit� plus petite que celle pass�e en param�tre.
	 *   - Si le param�tre "plusPetit" est faux, les �l�ments � supprimer doivent
	 *     avoir la priorit� plus grande que celle pass�e en param�tre.
	 *     
	 * Les �l�ments supprim�s sont retourn�s dans un tableau liste. Une valeur nulle
	 * est retourn�e si aucun �l�ment n'est supprim�.
	 * 
	 * @param priorite La priorit�
	 * @return Le tableau liste des �l�ments supprim�s
	 */
	@Override
	public List<T> supprimer(int priorite, boolean plusPetit) {
			
		int i = 0;
		int j = 0;
		int taille = liste.size();
		List<T> listeRetour = null;
		if (plusPetit){
			
			listeRetour = new ArrayList<T>();
			
			while ( i < liste.size()){
				
				j = i;	
				
				if(liste.get(i).obtenirPriorite() < priorite){
					
					listeRetour.add(liste.get(i));	
					liste.remove(i);
					i = j;
					
				}else{
					
					i++;
				}
			}
			
		}else {	
			
			listeRetour = new ArrayList<T>();
			
			while ( i < liste.size()){
				
				j = i;	
				
				if(liste.get(i).obtenirPriorite() > priorite){
					listeRetour.add(liste.get(i));	
					liste.remove(i);
					i = j;
					
				}else{
					
					i++;
				}
			}
		}
		
		if (i == taille){
			
			listeRetour = null;
		}
		
		return listeRetour;
	}

	/**
     * Remplace la priorit� de tous les �l�ments de la liste existante dont la priorit�
     * est �gale � "anciennePriorite" par "nouvellePriorite". La priorit� "nouvellePriorite"
     * ne doit pas �tre n�gative. 
     * 
	 * @param anciennePriorite L'ancienne priorit� 
	 * @param nouvellePriorite La nouvelle priorit�
	 * 
	 * @return Vrai si au moins un changement de priorit� a �t� effectu�
	 */
	@Override
	public boolean remplacer(int anciennePriorite, int nouvellePriorite) {
		
		int i = 0;
		int j = 0;
		List<T> maListe = new ArrayList<T>();
		boolean res = false;		
		
		if(nouvellePriorite > 0){
			
			while (i < liste.size()){
				
				j = i;
				
				if(liste.get(i).obtenirPriorite() == anciennePriorite){	
					
					liste.get(i).modifierPriorite(nouvellePriorite);
					maListe.add(liste.get(i));
					liste.remove(i);
					res = true;	
					i = j;
					
				}else{
					
					i++;
					
				}
			}
			
			ajouter(maListe);
		}
		
		return res;
	}

	/**
	   * Retourne le nombre d'�l�ments dont la priorit� est �gale � celle pass�e en param�tre.
	   * 
	   * @param priorite La priorit� 
	   * @return Le nombre d'�l�ments dont la priorit� = "priorite" 
	   */
	@Override
	public int ObtenirNbElments(int priorite) {
		
		int compteur = 0;
		
		for(int i = 0; i < liste.size(); i++){
			
			if(liste.get(i).obtenirPriorite() == priorite){
				
				compteur++;
			}
		}
		
		return compteur;
	}

	/**
	 * V�rifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	@Override
	public boolean estVide() {
			
		return liste.isEmpty();
	}

	/**
	 * Retourne un it�rateur sur la liste courante.
	 * 
	 * @return It�rateur sur la liste courante.
	 */	
	@Override
	public ListIterator<T> iterateur() {
		
		ListIterator<T> iterateur = liste.listIterator();
		
		return iterateur;
	}	
	
}
