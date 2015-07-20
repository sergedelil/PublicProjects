
package ca.uqam.inf2120.tp2.adt.impl;

import java.util.*;

import ca.uqam.inf2120.tp2.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp2.adt.Priorite;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP2
 * 
 * ListePrioriteTda : Cette interface d�finit les services d'une liste de
 * priorit� dans laquelle les �l�ments sont organis�s en ordre d�croissant selon
 * leur priorit�. les �l�ments de la plus grande priorit� sont ajout�s au d�but
 * de la liste.
 * 
 * @author Serge Dogny
 * @version 20 Juin 2014
 */
public class ListePrioriteChaineeImpl<T extends Priorite> implements ListePrioriteTda<T> {	
	
	private Noeud<T> tete;
	private Noeud<T> courant;
	
	// Constructeur

	public ListePrioriteChaineeImpl() {	
		
		tete = null;
		courant = null;
	}
	
	/**
	 * retourne un iterateur sur la liste courante
	 */
	@Override
	public Iterator<T> iterator() {
		
		courant = tete;
				
		return this;
	}
	
	/**
	 * V�rifie s'il y a un �l�ment � partir de la
     * position courante dans la liste
	 */
	@Override
	public boolean hasNext() {
		
		return (courant != null);
	}
	
	/**
	 * Retourner l'�l�ment � la position courante et
     * repositionne le curseur (l'it�rateur) sur l'�l�ment suivant.
	 */
	
	@Override
	public T next() {
		
		T element = courant.getElement();		
		courant = courant.getSuivant();
		
		return element;
	}

	@Override
	public void remove() {
		
		// Pas � d�finir		
	}
	
	/**
	 * Ajoute l'�l�ment "elt" dans la liste selon sa priorit�. Les �l�ments de la
	 * plus grande priorit� sont ajout�s au d�but de la liste. L'�l�ment ne doit 
	 * pas �tre ajout� si l'une des conditions suivantes est vraie :
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
		
		boolean estAjoute = false;			
		
		if (elt != null && elt.obtenirPriorite() > 0 && !contient(elt) ){				
			
			if (tete == null){//si la liste est vide
				
				tete = new Noeud<T>(elt);
				estAjoute = true;
				
			}else {	// Recherche l'�l�ment dans la liste
				
				int priorite = elt.obtenirPriorite();
				Noeud<T> precedant = null;
				courant = tete;
				
				while (courant != null && priorite <= courant.getElement().obtenirPriorite()){
					
					precedant = courant;								
					courant = courant.getSuivant();						
				}		
							
				if(precedant == null){ //s'il s'agit du premier de la liste								
							
					tete = new Noeud<T>(elt,tete);
					estAjoute = true;
								
				}else{// sinon, l'inserrer avant l'�l�ment
																					
					precedant.setSuivant(new Noeud<T>(elt,courant));								
					estAjoute = true;
				}				
			}
		}	
		
		return estAjoute;
	}	
	
	/**
	 * Ajoute tous les �l�ments de la liste pass�e en param�tre dans la
	 * liste existante. Tous les �l�ments sont ajout�s dans la liste selon leur
	 * priorit�. Les �l�ments de la plus grande priorit� sont ajout�s au d�but de 
	 * la liste. Aucun ajout si la liste pass�e en param�tre est nulle ou vide.
	 * L'�l�ment de la liste pass� en param�tre ne doit pas �tre ajout� si l'une 
	 * des conditions suivantes est vraie :
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
		
		Noeud<T> precedant = null;
		boolean estSupprime = false;		
	
		if (elt != null && elt.obtenirPriorite() > 0 && contient(elt)){	
			
			courant = tete;			
			while(courant != null && !estSupprime){
							
				if(elt.equals(courant.getElement()) && elt.obtenirPriorite()
						== courant.getElement().obtenirPriorite()){
						
					if(precedant == null){// si c'est le premier �l�ment
							
						tete = tete.getSuivant();						
						estSupprime = true;
							
					}else{ //sinon supprime l'�l�ments courant
							
						precedant.setSuivant(courant.getSuivant());							
						estSupprime = true;
					}
					
				}else{
					
					precedant = courant;
					courant = courant.getSuivant();					
				}				
			}			
		}
		
		return estSupprime;
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
		
		int comptEltNonSupprime = 0;		
		boolean eltSupprime = false;
		List<T> listeRetour = null;	
		
		if (liste != null && liste.size() != 0){	
		
			listeRetour = new ArrayList<T>();	
		
			for (int i = 0; i < liste.size() && !eltSupprime; i++){
				
				eltSupprime = supprimer(liste.get(i));
				
				if (!eltSupprime){//si l'�l�ment n'a pas �t� supprim�.
										
					listeRetour.add(liste.get(i));
					comptEltNonSupprime++;
				}
				
				eltSupprime = false;
			}			
				
			if (comptEltNonSupprime == 0){//si tous les �l�ments ont �t� supprim�s.	
				
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
			
		List<T> listeRetour  = null;				
		
		if (priorite > 0){
			
			courant = tete;
			listeRetour = new ArrayList<T>();
			
			while( courant != null ){
				
				if(priorite == courant.getElement().obtenirPriorite()){	
					
					listeRetour.add(courant.getElement());	
				}
				
				courant = courant.getSuivant();
			}
				
			listeRetour = extraction(listeRetour);			
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
		
		List<T> listeRetour = null;		
		courant = tete;
		
		if(priorite > 0){
			
			listeRetour = new ArrayList<T>();
		
			if(plusPetit){ //si le param�tre plusPetit est vrai		
			
				while(courant != null){
				
					if(courant.getElement().obtenirPriorite() < priorite){
					
						listeRetour.add(courant.getElement());					
					}
				
					courant = courant.getSuivant();			
				}
			
				listeRetour = extraction(listeRetour);
			
			}else{//si le param�tre plusPetit est faux.			
			
				while(courant != null){
				
					if(courant.getElement().obtenirPriorite() > priorite){
					
						listeRetour.add(courant.getElement());					
					}
				
					courant = courant.getSuivant();	
				}			
			
				listeRetour = extraction(listeRetour);			
			}
		}
		
		return listeRetour;
	}

	private List<T> extraction(List<T> listeRetour) {
		
		if(!listeRetour.isEmpty()){
		
			supprimer(listeRetour);
		
		}else{
		
			listeRetour = null;
		}
		
		return listeRetour;
	}
	
	/**
     * Remplace la priorit� de tous les �l�ments de la liste existante dont leur priorit�
     * est �gale � "anciennePriorite" par "nouvellePriorite". La priorit� "nouvellePriorite"
     * doit �tre sup�rieure � 0. Les �l�ments, dont la priorit� a chang�, doivent 
     * �tre replac�s dans la liste selon leur nouvelle priorit�. 
     * 
	 * @param anciennePriorite L'ancienne priorit� 
	 * @param nouvellePriorite La nouvelle priorit�
	 * 
	 * @return Vrai si au moins un changement de priorit� a �t� effectu�
	 */
	@Override
	public boolean remplacer(int anciennePriorite, int nouvellePriorite) {		
		
		boolean changement = false;		
		
		if(nouvellePriorite > 0){
			
			List<T> maListe = new ArrayList<T>();
			courant = tete;	
			
			while(courant != null){
			
				if(courant.getElement().obtenirPriorite() == anciennePriorite){
				
					courant.getElement().modifierPriorite(nouvellePriorite);					
					changement = maListe.add(courant.getElement());
				}
				
				courant = courant.getSuivant();			
			}
		
			if (!maListe.isEmpty()){
			
				supprimer(maListe);	
				ajouter(maListe);
			}
		}
		
		return changement;
	}
	
	/**
     * V�rifie l'existence de l'�l�ment "elt" dans liste.
     * 
     * @param elt dont l'existence doit �tre v�rifi�. 
     * @return Vrai si elt existe, sinon faux. 
     */
	@Override
	public boolean contient(T elt) {
		
		courant = tete;
		boolean existe = false;
		
		if(elt != null && elt.obtenirPriorite() > 0){
			
			while (courant != null && !existe){		
			
				if(courant.getElement().equals(elt)){
					
					existe = true;					
				}
				
				courant = courant.getSuivant();				
			}			
		}
		
		return existe;
	}
	
	/**
     * Retourne le nombre d'�l�ments dont la priorit� est �gale � celle pass�e en param�tre.
     * 
     * @param priorite La priorit� 
     * @return Le nombre d'�l�ments dont la priorit� = "priorite" 
     */
	@Override
	public int obtenirNbElments(int priorite) {		
				
		int nbElementPriorite = 0;
		
		if(priorite > 0){
			
			courant = tete;
			while(courant != null){
				
				if(priorite == courant.getElement().obtenirPriorite()){
					
					nbElementPriorite++;
				}	
				
				courant = courant.getSuivant();				
			}			
		}
		
		return nbElementPriorite;		
	}
	
	/**
     * Retourne le nombre d'�l�ments dans la liste.
     * 
     * @return Le nombre d'�l�ments. 
     */
	@Override
	public int obtenirNbElments() {
		
		int nbTotalElement = 0;
		courant = tete;
		 
		while (courant != null){
			
			nbTotalElement++;				
			courant = courant.getSuivant();			
		}		
	
		return nbTotalElement;
	}
	
	/**
	 * V�rifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	@Override
	public boolean estVide() {
		
		return (tete == null);
	}
	
}
