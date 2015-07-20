package ca.uqam.inf2120.tp3.modele;

import java.util.Date;

import ca.uqam.inf2120.tp1.adt.Priorite;
//import ca.uqam.inf2120.tp1.adt.test.ElementT;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP3 
 * 
 * Classe Patient (F�lin, Canin,autre): contient les informations 
 * d'un patient de la Clinique v�t�rinaire.
 *    
 * @author Ismael Doukoure
 * Compl�t� par : VOTRE NOM  VOTRE PR�NOM - VOTRE CODE PERMANENT
 * 
 * @version 07 juillet 2014
 */
public class Patient implements Priorite {


	private String identifiant;
	private String espece;
	private String nom;
	private String age;
	private Date dateHeureArrivee;
	private String raisonUrgence;
	private int priorite;
	private Proprietaire proprietaire;
	private static int nbSequentiel = 0;



	/**
	 * 
	 * @param nom
	 * @param age
	 * @param espece
	 * @param raisonUrgence
	 * @param priorite
	 * @param proprietaire
	 */
	public Patient(String nom, String age, String espece, String raisonUrgence,
			int priorite, Proprietaire proprietaire) {

		this.nom = nom;
		this.age = age;
		this.espece = espece;
		this.raisonUrgence = raisonUrgence;
		this.priorite = priorite;
		this.proprietaire = proprietaire;
		identifiant = construireIdentifiant();
		dateHeureArrivee = new Date();

	}


	/**
	 * Construit l'identifiant en concatenant les trois (3) premiers caract�res 
	 * du nom du patient et un num�ro s�quentiel . le num�ro s�quentiel doit �tre 
	 * un attribut statique de type int qui s'incr�mente chaque fois qu'on construit
	 * un identifiant. Cet attribut doit �tre d�clar� dans la classe Patient.
	 * 
	 * Si le nombre de caract�res composant le nom du patient est moins que trois (3)
	 * caract�res, le caract�re 'X' est utilis� pour les caract�res manquants.
	 * 
	 * Exemple : 
	 *   - nom du 1er patient  = Isidor,   l'identifiant = "ISI1
	 *   - nom du 2e patient  =  Betty,    l'identifiant = "BET2
	 *   - nom du 3e patient  =  Ya,       l'identifiant = "YAX3
	 * 
	 */
	public String construireIdentifiant() {

		nbSequentiel++;
		String identifiant;

		if (nom.trim().length() == 1){

			identifiant = nom.trim() + "X" + "X" + nbSequentiel;

		}else if (nom.trim().length() == 2){

			identifiant = nom.trim() + "X" + nbSequentiel;			

		}else {

			identifiant = nom.trim().substring(0, 3) + nbSequentiel;

		}

		return identifiant.toUpperCase();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object unAutrePatient) {

		if (this == unAutrePatient) {
			return true;
		}

		if (unAutrePatient == null) {
			return false;
		}

		// Si les 2 objets (this, unAutrePatient) ne sont pas de m�me
		// classe, ils ne peuvent pas �tre �gaux, on doit retourner
		// faux comme r�sultat.
		if (this.getClass() != unAutrePatient.getClass()) {
			return false;
		}

		// Sachant que unAutrePatient n'est pas null et que unAutrePatient
		// et l'objet courant sont de m�me type, on peut se permettre
		// la conversion de UnAutreObjet en ElementT
		Patient patient = (Patient) unAutrePatient;

		// Tester si les attributs ont les m�mes valeurs
		// SAUF l'attribut priorite.
		return (identifiant.equals(patient.getIdentifiant()));		

	}


	/* (non-Javadoc)
	 * @see ca.uqam.inf2120.tp1.adt.Priorite#modifierPriorite(int)
	 */
	@Override
	public void modifierPriorite(int priorite) {

		this.priorite = priorite;

	}
	/* (non-Javadoc)
	 * @see ca.uqam.inf2120.tp1.adt.Priorite#obtenirPriorite()
	 */
	@Override
	public int obtenirPriorite() {

		return priorite;
	}


	public String getIdentifiant() {
		return identifiant;
	}


	public String getEspece() {
		return espece;
	}


	public String getNom() {
		return nom;
	}


	public String getAge() {
		return age;
	}


	public Date getDateHeureArrivee() {
		return dateHeureArrivee;
	}


	public String getRaisonUrgence() {
		return raisonUrgence;
	}	


	public Proprietaire getProprietaire() {
		return proprietaire;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public void setEspece(String espece) {
		this.espece = espece;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public void setDateHeureArrivee(Date dateHeureArrivee) {
		this.dateHeureArrivee = dateHeureArrivee;
	}


	public void setRaisonUrgence(String raisonUrgence) {
		this.raisonUrgence = raisonUrgence;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}


	public int getPriorite() {
		return priorite;
	}


	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
}
