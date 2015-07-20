package ca.uqam.inf2120.tp1.adt;

import java.util.List;
import java.util.ListIterator;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP1
 * 
 * ListePrioriteTda : Cette interface d�finit les services d'une liste de
 * priorit� dans laquelle les �l�ments sont organis�s en ordre d�croissant selon
 * leur priorit�. les �l�ments de la plus grande priorit� sont ajout�s au d�but
 * de la liste.
 * 
 * @author Ismael Doukoure
 * @version 20 mai 2014
 */
public interface ListePrioriteTda<T extends Priorite> {

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
	public boolean ajouter(T elt);

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
     * Remplace la priorit� de tous les �l�ments de la liste existante dont la priorit�
     * est �gale � "anciennePriorite" par "nouvellePriorite". La priorit� "nouvellePriorite"
     * ne doit pas �tre n�gative. 
     * 
	 * @param anciennePriorite L'ancienne priorit� 
	 * @param nouvellePriorite La nouvelle priorit�
	 * 
	 * @return Vrai si au moins un changement de priorit� a �t� effectu�
	 */
     public boolean remplacer(int anciennePriorite,int nouvellePriorite);


  /**
   * Retourne le nombre d'�l�ments dont la priorit� est �gale � celle pass�e en param�tre.
   * 
   * @param priorite La priorit� 
   * @return Le nombre d'�l�ments dont la priorit� = "priorite" 
   */
     public int ObtenirNbElments(int priorite);
      
	/**
	 * V�rifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	public boolean estVide();

	/**
	 * Retourne un it�rateur sur la liste courante.
	 * 
	 * @return It�rateur sur la liste courante.
	 */
	public ListIterator<T> iterateur();	
	

}
