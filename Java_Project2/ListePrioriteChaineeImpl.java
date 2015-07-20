
package ca.uqam.inf2120.tp2.adt.impl;

import java.util.*;

import ca.uqam.inf2120.tp2.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp2.adt.Priorite;

/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP2
 * 
 * ListePrioriteTda : Cette interface définit les services d'une liste de
 * priorité dans laquelle les éléments sont organisés en ordre décroissant selon
 * leur priorité. les éléments de la plus grande priorité sont ajoutés au début
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
	 * Vérifie s'il y a un élément à partir de la
     * position courante dans la liste
	 */
	@Override
	public boolean hasNext() {
		
		return (courant != null);
	}
	
	/**
	 * Retourner l'élément à la position courante et
     * repositionne le curseur (l'itérateur) sur l'élément suivant.
	 */
	
	@Override
	public T next() {
		
		T element = courant.getElement();		
		courant = courant.getSuivant();
		
		return element;
	}

	@Override
	public void remove() {
		
		// Pas à définir		
	}
	
	/**
	 * Ajoute l'élément "elt" dans la liste selon sa priorité. Les éléments de la
	 * plus grande priorité sont ajoutés au début de la liste. L'élément ne doit 
	 * pas être ajouté si l'une des conditions suivantes est vraie :
	 *   - L'élément est null.
	 *   - L'élément existe déjà dans la liste (éléments identiques sans tenir compte 
	 *     de leur priorité).
	 *   - La priorité de l'élément est inférieure ou égale à 0.
	 *   
	 * Si l'élément "elt" à ajouter a la même priorité qu'un élément qui existe dans 
	 * la liste, il doit être ajouté après ce dernier.
	 *
	 * @param elt L'élément à ajouter
	 * @return true si "elt" est ajouté, sinon false
	 */
	@Override
	public boolean ajouter(T elt) {			
		
		boolean estAjoute = false;			
		
		if (elt != null && elt.obtenirPriorite() > 0 && !contient(elt) ){				
			
			if (tete == null){//si la liste est vide
				
				tete = new Noeud<T>(elt);
				estAjoute = true;
				
			}else {	// Recherche l'élément dans la liste
				
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
								
				}else{// sinon, l'inserrer avant l'élément
																					
					precedant.setSuivant(new Noeud<T>(elt,courant));								
					estAjoute = true;
				}				
			}
		}	
		
		return estAjoute;
	}	
	
	/**
	 * Ajoute tous les éléments de la liste passée en paramètre dans la
	 * liste existante. Tous les éléments sont ajoutés dans la liste selon leur
	 * priorité. Les éléments de la plus grande priorité sont ajoutés au début de 
	 * la liste. Aucun ajout si la liste passée en paramètre est nulle ou vide.
	 * L'élément de la liste passé en paramètre ne doit pas être ajouté si l'une 
	 * des conditions suivantes est vraie :
	 *   - L'élément à ajouter est null.
	 *   - L'élément existe déjà dans la liste (éléments identiques sans tenir compte
	 *     de leur priorité).
	 *   - La priorité de l'élément est inférieure ou égale à 0.
	 * 
	 * Si l'élément à ajouter a la même priorité qu'un élément qui existe dans la liste,
	 * il doit être ajouté après ce dernier.
	 * 
	 * @param liste Le tableau liste (ArrayList) dont les éléments doivent être ajoutés
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
	 * nul ou s'il n'existe pas dans la liste. L'élément à supprimer doit être
	 * égal à celui passé en paramètre ("elt") avec la même priorité.
	 * 
	 * @param elt L'élément à supprimer
	 * @return Vrai si l'élément est supprimé
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
						
					if(precedant == null){// si c'est le premier élément
							
						tete = tete.getSuivant();						
						estSupprime = true;
							
					}else{ //sinon supprime l'éléments courant
							
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
	 * Supprime tous les éléments de la liste passée en paramètre dans la liste
	 * existante. Tous les éléments non supprimés de la liste passée en paramètre
	 * sont retournés dans un tableau liste (ArrayList). L'élément à supprimer doit
	 * être égal à "elt" avec la même priorité.
	 * 
	 * @param liste Le tableau liste (ArrayList) dont les éléments doivent être supprimés
	 * @return Le tableau liste (ArrayList) des éléments non supprimés, nul si tous les éléments
	 *         sont supprimés.
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
				
				if (!eltSupprime){//si l'élément n'a pas été supprimé.
										
					listeRetour.add(liste.get(i));
					comptEltNonSupprime++;
				}
				
				eltSupprime = false;
			}			
				
			if (comptEltNonSupprime == 0){//si tous les éléments ont été supprimés.	
				
				listeRetour = null;
			}				
		}
		
		return listeRetour;
	}
	
	/**
	 * Supprime tous les éléments dont la priorité est égale à celle passée en
	 * paramètre. Les éléments supprimés sont retournés dans un tableau liste.
	 * Une valeur nulle est retournée si aucun élément n'est supprimé.
	 * 
	 * @param priorite La priorité des éléments à supprimer
	 * @return Le tableau liste des éléments supprimés
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
	 * Supprime les éléments selon les conditions suivantes :
	 *   - Si le paramètre "plusPetit" est vrai, les éléments à supprimer doivent
	 *     avoir la priorité plus petite que celle passée en paramètre.
	 *   - Si le paramètre "plusPetit" est faux, les éléments à supprimer doivent
	 *     avoir la priorité plus grande que celle passée en paramètre.
	 *     
	 * Les éléments supprimés sont retournés dans un tableau liste. Une valeur nulle
	 * est retournée si aucun élément n'est supprimé.
	 * 
	 * @param priorite La priorité
	 * @return Le tableau liste des éléments supprimés
	 */
	@Override
	public List<T> supprimer(int priorite, boolean plusPetit) {
		
		List<T> listeRetour = null;		
		courant = tete;
		
		if(priorite > 0){
			
			listeRetour = new ArrayList<T>();
		
			if(plusPetit){ //si le paramètre plusPetit est vrai		
			
				while(courant != null){
				
					if(courant.getElement().obtenirPriorite() < priorite){
					
						listeRetour.add(courant.getElement());					
					}
				
					courant = courant.getSuivant();			
				}
			
				listeRetour = extraction(listeRetour);
			
			}else{//si le paramètre plusPetit est faux.			
			
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
     * Remplace la priorité de tous les éléments de la liste existante dont leur priorité
     * est égale à "anciennePriorite" par "nouvellePriorite". La priorité "nouvellePriorite"
     * doit être supérieure à 0. Les éléments, dont la priorité a changé, doivent 
     * être replacés dans la liste selon leur nouvelle priorité. 
     * 
	 * @param anciennePriorite L'ancienne priorité 
	 * @param nouvellePriorite La nouvelle priorité
	 * 
	 * @return Vrai si au moins un changement de priorité a été effectué
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
     * Vérifie l'existence de l'élément "elt" dans liste.
     * 
     * @param elt dont l'existence doit être vérifié. 
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
     * Retourne le nombre d'éléments dont la priorité est égale à celle passée en paramètre.
     * 
     * @param priorite La priorité 
     * @return Le nombre d'éléments dont la priorité = "priorite" 
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
     * Retourne le nombre d'éléments dans la liste.
     * 
     * @return Le nombre d'éléments. 
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
	 * Vérifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	@Override
	public boolean estVide() {
		
		return (tete == null);
	}
	
}
