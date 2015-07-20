package ca.uqam.inf2120.tp1.adt.test;

import ca.uqam.inf2120.tp1.adt.Priorite;

/**
 * ElementT : Cette classe correspond à l'élément T et sera utilisé pour faire
 * les tests unitaires
 * 
 * @author Ismael Doukoure - INF2120 - Groupe 20
 * @version 20 mai 2014
 */
public class ElementT implements Priorite {

	// Déclaration des attributs
	private String identifiant;
	private int priorite;

	/**
	 * Constructeur avec 1 argument.
	 * 
	 * @param identifiant
	 *            L'identifiant de "ElementT"
	 */
	public ElementT(String identifiant) {
		super();
		this.identifiant = identifiant;
	}

	/**
	 * Constructeur avec 2 arguments
	 * 
	 * @param identifiant
	 *            L'identifiant de "ElementT"
	 * @param priorite
	 *            La prorité
	 */
	public ElementT(String identifiant, int priorite) {
		this.identifiant = identifiant;
		this.priorite = priorite;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.uqam.inf2120.tp1.adt.Priorite#modifier(int)
	 */
	@Override
	public void modifierPriorite(int priorite) {
		this.priorite = priorite;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.uqam.inf2120.tp1.adt.Priorite#obtenir()
	 */
	@Override
	public int obtenirPriorite() {
		return priorite;
	}
	
	

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object unAutreObjet) {

		// Si les 2 objets sont identiques, on doit retourner
		// vrai comme résultat.
		if (this == unAutreObjet) {
			return true;
		}

		// Si unAutreObjet vaut null, on doit retourner
		// faux comme résultat.
		if (unAutreObjet == null) {
			return false;
		}

		// Si les 2 objets (this, unAutreObjet) ne sont pas de même
		// classe, ils ne peuvent pas être égaux, on doit retourner
		// faux comme résultat.
		if (this.getClass() != unAutreObjet.getClass()) {
			return false;
		}

		// Sachant que unAutreObjet n'est pas null et que unAutreObjet
		// et l'objet courant sont de même type, on peut se permettre
		// la conversion de UnAutreObjet en ElementT
		ElementT elt = (ElementT) unAutreObjet;

		// Tester si les attributs ont les mêmes valeurs
		// SAUF l'attribut priorite.
		return (identifiant.equals(elt.getIdentifiant()));

	}	

}