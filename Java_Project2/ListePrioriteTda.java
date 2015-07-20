package ca.uqam.inf2120.tp2.adt;


import java.util.Iterator;
import java.util.List;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP2
 * 
 * ListePrioriteTda : Cette interface d�finit les services d'une liste de
 * priorit� dans laquelle les �l�ments sont organis�s en ordre d�croissant selon
 * leur priorit�. les �l�ments de la plus grande priorit� sont ajout�s au d�but
 * de la liste.
 * 
 * @author Ismael Doukoure
 * @version 15 juin 2014
 */
public interface ListePrioriteTda<T extends Priorite> extends Iterable<T>, Iterator<T>{

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
	public boolean ajouter(T elt);

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
	public void ajouter(List<T> liste);

	/**
	 * Supprime "elt" dans la liste existante. Aucune suppression si "elt" est
	 * nul ou s'il n'existe pas dans la liste. L'�l�ment � supprimer doit �tre
	 * �gal � celui pass� en param�tre ("elt") avec la m�me priorit�.
	 * 
	 * @param elt L'�l�ment � supprimer
	 * @return Vrai si l'�l�ment est supprim�
	 */
	public boolean supprimer(T elt);

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
	public List<T> supprimer(List<T> liste);
	
	
	/**
	 * Supprime tous les �l�ments dont la priorit� est �gale � celle pass�e en
	 * param�tre. Les �l�ments supprim�s sont retourn�s dans un tableau liste.
	 * Une valeur nulle est retourn�e si aucun �l�ment n'est supprim�.
	 * 
	 * @param priorite La priorit� des �l�ments � supprimer
	 * @return Le tableau liste des �l�ments supprim�s
	 */
	public List<T> supprimer(int priorite);

	
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
	public List<T> supprimer(int priorite, boolean plusPetit);
	
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
     public boolean remplacer(int anciennePriorite,int nouvellePriorite);


    /**
     * V�rifie l'existence de l'�l�ment "elt" dans liste.
     * 
     * @param elt dont l'existence doit �tre v�rifi�. 
     * @return Vrai si elt existe, sinon faux. 
     */
      public boolean contient(T elt);
      
    /**
     * Retourne le nombre d'�l�ments dont la priorit� est �gale � celle pass�e en param�tre.
     * 
     * @param priorite La priorit� 
     * @return Le nombre d'�l�ments dont la priorit� = "priorite" 
     */
      public int obtenirNbElments(int priorite);
     
    /**
     * Retourne le nombre d'�l�ments dans la liste.
     * 
     * @return Le nombre d'�l�ments. 
     */
      public int obtenirNbElments();     
      
	/**
	 * V�rifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	public boolean estVide();
	
	
	/* INFORMATIONS IMPORTANTES : �tant donn� que ListePrioriteTda h�rite des interfaces
     * Iterable<T>, Iterator<T>, vous devez d�finir toutes les m�thodes qui sont
     * d�clar�es dans ces interfaces (SAUF la m�thode remove de l'interface Iterator<T>)
     * dans votre classe ListePrioriteChaineeImpl. Voir ci-dessous pour les d�tails :
     *
     *
     * Iterable<T>
     * Cette interface contient une seule m�thode "public Iterator<T> iterator()"
     * qui retourne un iterator sur la liste courante. Vous devez implanter cette
     * methode dans votre classe
     *
     *
     * Iterator<T>
     * Cette interface contient trois m�thodes:
     *
     *   - public boolean hasNext() : V�rifie s'il y a un �l�ment � partir de la
     *     position courante dans la liste
     *
     *   - public T next() : Retourner l'�l�ment � la position courante et
     *     repositionne le curseur (l'it�rateur) sur l'�l�ment suivant.
     *
     *   - public void remove () : � NE PAS D�FINIR. Donc cette m�thode sera vide avec
     *     juste les accolades ouvrante et fermante.
     *
     */

}
