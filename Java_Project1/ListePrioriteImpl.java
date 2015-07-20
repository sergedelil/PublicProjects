package ca.uqam.inf2120.tp1.adt.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ca.uqam.inf2120.tp1.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp1.adt.Priorite;

/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP1
 * 
 * ListePrioriteImpl : Implémentation de l'interface ListePrioriteTda.
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
	 * Ajoute l'élément "elt" dans la liste selon sa priorité. Les éléments de la
	 * plus grande priorité sont ajoutés au début de la liste. L'élément ne doit 
	 * pas être ajouté si une des conditions suivantes est vraie :
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
		
		boolean res = false;
		boolean inser = true;
		int priorite;
		int indice;	
		
		if (elt != null && elt.obtenirPriorite() > 0 ){
				// Ajoute l'élément, si la liste est vide		
			
				if (liste.size() == 0){
					liste.add(elt);	
					res = true;
					
				}else {
					
					if (!liste.contains(elt)){
						priorite = elt.obtenirPriorite();
						
							for(int i = liste.size() - 1 ; i >= 0 && inser; i--){
								/*si la priorité de l'élément reçu est plus petite ou égale à celle
								 *du dernier élément de la liste, on l'ajoute à l'indice suivant.*/
								
								if (priorite <= (liste.get(i)).obtenirPriorite()){
									indice = liste.indexOf(liste.get(i));
									liste.add(indice + 1, elt);
									res = true;
									inser = false;										
								}
								/*si on est rendu au debut de la liste et que la priorité de l'élément 
								 *reçu est la plus grande, on l'ajoute à l'indice 0.*/
								
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
	 * Ajoute tous les éléments de la liste passée en paramètre dans la
	 * liste existante. Tous les éléments sont ajoutés dans la liste selon leur
	 * priorité. Les éléments de la plus grande priorité sont ajoutés au début de 
	 * la liste. Aucun ajout si une des conditions suivantes est vraie :
	 *   - La liste est nulle.
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
				// passe chaque élément de la liste reçu à la méthode ajouer pour l'ajout.			 
				
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
				
		int comptElNonSupprime = 0;		
		boolean res;
		List<T> listeRetour = null;	
		
		if (liste != null && liste.size() != 0){
			
			listeRetour = new ArrayList<T>();
			
			for (int i = 0; i < liste.size(); i++){	
				
				  res = supprimer(liste.get(i));
				  
				  if (!res){
					  //si l'élément n'a pas été supprimé, l'ajouter à la liste de retour
					  listeRetour.add(liste.get(i));
					  comptElNonSupprime++;					  
				  }
			}
			// si tous les éléments ont été supprimés, retourner la valeur nulle	
			if (comptElNonSupprime == 0){
				
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
				
		int i = 0;
		int j = 0;
		int taille = liste.size();		
		List<T> listeRetour = new ArrayList<T>();
		
		while ( i < liste.size()){
			
			j = i; // conserve la position de l'indice.
			
			if(liste.get(i).obtenirPriorite() == priorite){
				listeRetour.add(liste.get(i));	
				liste.remove(i);				
				i = j;  // reprend le parcours, à partir de l'indice ou la suppression a eu lieu.
				
			}else{
				
				i++;
			}
		}
		
		if (i == taille){ // si aucun élément n'a été supprimé, retourne une valeur nulle.
			
			listeRetour = null;
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
     * Remplace la priorité de tous les éléments de la liste existante dont la priorité
     * est égale à "anciennePriorite" par "nouvellePriorite". La priorité "nouvellePriorite"
     * ne doit pas être négative. 
     * 
	 * @param anciennePriorite L'ancienne priorité 
	 * @param nouvellePriorite La nouvelle priorité
	 * 
	 * @return Vrai si au moins un changement de priorité a été effectué
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
	   * Retourne le nombre d'éléments dont la priorité est égale à celle passée en paramètre.
	   * 
	   * @param priorite La priorité 
	   * @return Le nombre d'éléments dont la priorité = "priorite" 
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
	 * Vérifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	@Override
	public boolean estVide() {
			
		return liste.isEmpty();
	}

	/**
	 * Retourne un itérateur sur la liste courante.
	 * 
	 * @return Itérateur sur la liste courante.
	 */	
	@Override
	public ListIterator<T> iterateur() {
		
		ListIterator<T> iterateur = liste.listIterator();
		
		return iterateur;
	}	
	
}
