package ca.uqam.inf2120.tp2.adt;


/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP2
 * 
 * Priorite : Interface d�finissant les services de gestion de la priorit� 
 *            d'un �l�ment donn�. 
 *    
 * @author Ismael Doukoure
 * @version  15 juin 2014
 */
 public interface Priorite {
	
	/**
	 * Modifie la priorit� .
	 * 
	 * @param priorite La nouvelle priorit�.
	 */
	 public void modifierPriorite (int priorite);
	
	/**
	 * Retourne la priorti�
	 * 
	 * @return La priorit� 
	 */
	public int obtenirPriorite();
	
}
