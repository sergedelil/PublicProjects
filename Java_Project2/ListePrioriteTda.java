package ca.uqam.inf2120.tp2.adt;


import java.util.Iterator;
import java.util.List;

/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP2
 * 
 * ListePrioriteTda : Cette interface définit les services d'une liste de
 * priorité dans laquelle les éléments sont organisés en ordre décroissant selon
 * leur priorité. les éléments de la plus grande priorité sont ajoutés au début
 * de la liste.
 * 
 * @author Ismael Doukoure
 * @version 15 juin 2014
 */
public interface ListePrioriteTda<T extends Priorite> extends Iterable<T>, Iterator<T>{

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
	public boolean ajouter(T elt);

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
	public void ajouter(List<T> liste);

	/**
	 * Supprime "elt" dans la liste existante. Aucune suppression si "elt" est
	 * nul ou s'il n'existe pas dans la liste. L'élément à supprimer doit être
	 * égal à celui passé en paramètre ("elt") avec la même priorité.
	 * 
	 * @param elt L'élément à supprimer
	 * @return Vrai si l'élément est supprimé
	 */
	public boolean supprimer(T elt);

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
	public List<T> supprimer(List<T> liste);
	
	
	/**
	 * Supprime tous les éléments dont la priorité est égale à celle passée en
	 * paramètre. Les éléments supprimés sont retournés dans un tableau liste.
	 * Une valeur nulle est retournée si aucun élément n'est supprimé.
	 * 
	 * @param priorite La priorité des éléments à supprimer
	 * @return Le tableau liste des éléments supprimés
	 */
	public List<T> supprimer(int priorite);

	
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
	public List<T> supprimer(int priorite, boolean plusPetit);
	
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
     public boolean remplacer(int anciennePriorite,int nouvellePriorite);


    /**
     * Vérifie l'existence de l'élément "elt" dans liste.
     * 
     * @param elt dont l'existence doit être vérifié. 
     * @return Vrai si elt existe, sinon faux. 
     */
      public boolean contient(T elt);
      
    /**
     * Retourne le nombre d'éléments dont la priorité est égale à celle passée en paramètre.
     * 
     * @param priorite La priorité 
     * @return Le nombre d'éléments dont la priorité = "priorite" 
     */
      public int obtenirNbElments(int priorite);
     
    /**
     * Retourne le nombre d'éléments dans la liste.
     * 
     * @return Le nombre d'éléments. 
     */
      public int obtenirNbElments();     
      
	/**
	 * Vérifie si la liste existante est vide.
	 * 
	 * @return Vrai si la liste est vide, sinon faux
	 */
	public boolean estVide();
	
	
	/* INFORMATIONS IMPORTANTES : Étant donné que ListePrioriteTda hérite des interfaces
     * Iterable<T>, Iterator<T>, vous devez définir toutes les méthodes qui sont
     * déclarées dans ces interfaces (SAUF la méthode remove de l'interface Iterator<T>)
     * dans votre classe ListePrioriteChaineeImpl. Voir ci-dessous pour les détails :
     *
     *
     * Iterable<T>
     * Cette interface contient une seule méthode "public Iterator<T> iterator()"
     * qui retourne un iterator sur la liste courante. Vous devez implanter cette
     * methode dans votre classe
     *
     *
     * Iterator<T>
     * Cette interface contient trois méthodes:
     *
     *   - public boolean hasNext() : Vérifie s'il y a un élément à partir de la
     *     position courante dans la liste
     *
     *   - public T next() : Retourner l'élément à la position courante et
     *     repositionne le curseur (l'itérateur) sur l'élément suivant.
     *
     *   - public void remove () : À NE PAS DÉFINIR. Donc cette méthode sera vide avec
     *     juste les accolades ouvrante et fermante.
     *
     */

}
