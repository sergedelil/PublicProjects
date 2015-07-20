package ca.uqam.inf2120.tp3.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

import java.util.ListIterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ca.uqam.inf2120.tp1.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP3
 * 
 * Classe GestionUrgenceCliniqueVeterinaire : contient les services de gestion
 * des patients de la clinique v�t�rinaire.
 * 
 * @author Ismael Doukoure 
 * Compl�t� par : VOTRE NOM VOTRE PR�NOM - VOTRE CODE PERMANENT
 * 
 * @version 07 juillet 2014
 */
public class GestionUrgenceCliniqueVeterinaire {

	// La liste des patients
	private ListePrioriteTda<Patient> listePatients;

	/**
	 * Constructeur sans argument qui cr�e une liste de patients vide.
	 */
	public GestionUrgenceCliniqueVeterinaire() {
		listePatients = new ListePrioriteImpl<Patient>();
	}

	/**
	 * Ajoute un patient dans la liste des patients selon sa priorit�.
	 * 
	 * @param unPatient
	 *            Le patient � ajouter
	 */
	public void ajouterPatient(Patient unPatient) {

		listePatients.ajouter(unPatient);


	}

	/**
	 * Trouve le patient selon son identifiant et modifie-le selon les
	 * informations du patient pass� en param�tre.
	 * 
	 * @param unPatient Le patient � modifier
	 */
	public void modifierPatient(Patient unPatient) {

		boolean estModifie = false;
		Iterator<Patient> iter = listePatients.iterateur();

		while (iter.hasNext() && !estModifie){

			Patient lePatient = iter.next();

			if( unPatient.getIdentifiant().equals(lePatient.getIdentifiant())){

				listePatients.supprimer(lePatient);
				listePatients.ajouter(unPatient);
				estModifie = true;
			}

		}

	}

	/**
	 * Recherche les patients selon leur priorit�. La recherche se fait selon
	 * les crit�res suivants en fonction des param�tres "priorite" et
	 * "typeRecherche".
	 * 
	 * - Si le typeRecherche = 1, la m�thode retourne tous les patients dont la
	 *   priorit� est plus grande que la priorit� pass�e en param�tre.
	 * 
	 * - Si le typeRecherche = 0, la m�thode retourne tous les patients dont la
	 *   priorit� est �gale � la priorit� pass�e en param�tre.
	 * 
	 * - Si le typeRecherche = -1, la m�thode retourne tous les patients dont la
	 *    priorit� est plus petite que la priorit� pass�e en param�tre.
	 * 
	 * La m�thode retourne null si aucun patient ne r�pond aux crit�res.
	 * 
	 * @param priorite La Priorit� selon laquelle la recherche est faite.
	 * @param typeRecherche Le type de recherche dont les valeurs possibles: 1, 0, -1.
	 * @return Le tableau Liste (ArrayList) des patients qui r�pondent au
	 *         crit�re de recherche.
	 */
	public List<Patient> rechercherParPriorite(int priorite, int typeRecherche) {

		boolean trouve = false;
		List<Patient> listeRetour = new ArrayList<Patient>();

		if (typeRecherche == 1){

			listeRetour = listePatients.supprimer(priorite, false);
			listePatients.ajouter(listeRetour);
			trouve = true;

		}else if (typeRecherche == 0){

			listeRetour = listePatients.supprimer(priorite);
			listePatients.ajouter(listeRetour);
			trouve = true;

		}else if (typeRecherche == -1){

			listeRetour = listePatients.supprimer(priorite, true);
			listePatients.ajouter(listeRetour);
			trouve = true;
		}

		if(!trouve){

			listeRetour = null;
		}

		return listeRetour;

	}

	/**
	 * Recherche le patient selon son identifiant. La m�thode retourne le
	 * patient dont l'identifiant qui est �gal � l'identifiant pass� en param�tre.
	 * La m�thode retourne null si aucun candidat trouv� avec cet identifiant.
	 * 
	 * @param identifiant L'identifiant du patient recherch�.
	 * @return Le patient dont l'identifiant est �gal � l'identifiant pass� en
	 *         param�tre.
	 */
	public Patient rechercherParIdentifiant(String identifiant) {

		Patient monPatient = null;
		boolean existe = false;
		Iterator<Patient> iter = listePatients.iterateur();

		while (iter.hasNext() && !existe){

			monPatient = iter.next();

			if(monPatient.getIdentifiant().equals(identifiant)){				

				existe = true;
			}			
		}

		return monPatient;
	}

	/**
	 * Recherche tous les patients. La m�thode retourne tous les patients.
	 * 
	 * @return Le tableau Liste (ArrayList) des patients.
	 */
	public List<Patient> rechercherTousLesPatients() {

		List<Patient> listeRetour = new ArrayList<Patient>();
		Iterator<Patient> iter = listePatients.iterateur();

		while (iter.hasNext()){

			Patient monPatient = iter.next();
			listeRetour.add(monPatient);			
		}

		return listeRetour;

	}

	/**
	 * Supprime le patient pass� en param�tre de la liste des patients. Le
	 * patient supprim� doit avoir le m�me identifiant et la m�me priorit� que
	 * celui pass� en param�tre.
	 * 
	 * @param unPatient Le patient � supprimer.
	 * @return Vrai si la suppression a �t� faite, sinon faux.
	 */
	public boolean supprimerPatient(Patient patient) {

		boolean estSupprime = listePatients.supprimer(patient);		

		return estSupprime;
	}


	/* INFORMATIONS IMPORTANTES : 
	 * 
	 * Ajoutez toute autre m�thode que vous jugez n�cessaire pour accomplir ce travail.
	 *
	 */
	/**
	 * V�rifie l'existence de l'�l�ment "elt" dans liste.
	 * 
	 * @param elt dont l'existence doit �tre v�rifi�. 
	 * @return Vrai si elt existe, sinon faux. 
	 */

	public boolean contient(String chaine) {

		boolean existe = false;
		Iterator<Patient> iter = listePatients.iterateur();

		while (iter.hasNext() && !existe){

			Patient patient = iter.next();

			if (chaine.equals(patient.getIdentifiant())){

				existe = true;				
			}					
		}

		return existe;		
	}
	
	/**
	 * Cr�er le mod�le avec les colonnes non �ditables.
	 * 
	 * @return LE mod�le de la table.
	 */
	@SuppressWarnings({ "serial" })
	public DefaultTableModel creerModeleAvecColonnesNonEditables(List<Patient> liste) {


		Vector<String> nomsColonnes = new Vector<String>();
		nomsColonnes.add("Identifiant");
		nomsColonnes.add("Nom");
		nomsColonnes.add("Priorit�");
		nomsColonnes.add("Date/Heure d'arriv�e");
		nomsColonnes.add("Nom et Pr�nom du propri�taire");

		Vector<Vector<Object>> donne = new Vector<Vector<Object>>();

		ListIterator<Patient> it = liste.listIterator();
		int i = 0;
		while (it.hasNext()) {
			Patient unPa = it.next();

			donne.add(new Vector<Object>());
			donne.elementAt(i).add(unPa.getIdentifiant());
			donne.elementAt(i).add(unPa.getNom());
			donne.elementAt(i).add(unPa.getPriorite());
			SimpleDateFormat formater = null;		   
			formater = new SimpleDateFormat("yyyy-MM-yy HH:mm:ss");	 		    
			donne.elementAt(i).add(formater.format(unPa.getDateHeureArrivee()));
			donne.elementAt(i).add(unPa.getProprietaire().getNom()+" "+ unPa.getProprietaire().getPrenom());
			i++;
		}		

		return new DefaultTableModel( donne, nomsColonnes) {

			boolean[] columnEditables = new boolean[] { false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};		
	}	

	public boolean estVide(){

		return (listePatients.estVide());
	}

}
