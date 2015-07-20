package ca.uqam.inf2120.tp2.adt;


/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP2
 * 
 * Priorite : Interface définissant les services de gestion de la priorité 
 *            d'un élément donné. 
 *    
 * @author Ismael Doukoure
 * @version  15 juin 2014
 */
 public interface Priorite {
	
	/**
	 * Modifie la priorité .
	 * 
	 * @param priorite La nouvelle priorité.
	 */
	 public void modifierPriorite (int priorite);
	
	/**
	 * Retourne la priortié
	 * 
	 * @return La priorité 
	 */
	public int obtenirPriorite();
	
}
