package ca.uqam.inf2120.tp2.adt.impl;

/**
 * Titre : Liste Cha�n�e 
 * Description: La classe Noeud repr�sente l'unit� de base
 * d'une liste cha�n�e.
 * 
 * UQAM - INF2120
 * 
 * @author Ismael Doukoure
 * @version Mars 2008 
 * Modifi� le 12 Mars 2014
 */

public class Noeud<T> {
	
	private T element; // r�f�rence de l'information contenu dans le noeud
	private Noeud<T> suivant; // r�f�rence vers le noeud suivant

	/**
	 * Construire un noeud � vide. Les champs element et suivant seront
	 * initialis�s � null.
	 */
	public Noeud() {
		this(null, null); // appel d'un autre constructeur
	}

	/**
	 * Construire un noeud avec une donn�e. la r�f�rence du prochain 
	 * noeud est mise � nul.
	 * 
	 * @param element La donn�e � stocker dans le noeud
	 */
	public Noeud(T element) {
		this(element, null); // appel d'un autre constructeur
	}

	/**
	 * Construire un noeud avec une donn�e et le suivant du prochain.
	 * 
	 * @param element La donn�e � stocker dans le noeud
	 * @param suivant La r�f�rence vers le noeud suivant
	 */
	public Noeud(T element, Noeud<T> suivant) {
		this.element = element;
		this.suivant = suivant;
	}

	/**
	 * Obtenir la donn�e stock�e dans le noeud
	 * 
	 * @return La r�f�rence vers l'objet element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Obtenir la r�f�rence du noeud suivant
	 * 
	 * @return La r�f�rence vers le noeud suivant
	 */
	public Noeud<T> getSuivant() {
		return suivant;

	}

	/**
	 * Permet d'initialiser ou modifier le noeud suivant.
	 * 
	 * @param la r�f�rence vers le noeud
	 */
	public void setSuivant(Noeud<T> unNoeud) {
		suivant = unNoeud;
	}

	/**
	 * Permet d'initialiser ou modifier la donn�e
	 * 
	 * @param element La donn�e � stocker
	 */
	public void setElement(T element) {
		this.element = element;

	}

} // Noeud
