package ca.uqam.inf2120.tp1.adt;

/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP1
 * 
 * Priorite : Interface définissant les services de gestion de la priorité 
 *            d'un élément donné. 
 *    
 * @author Ismael Doukoure
 * @version  20 mai 2014
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
