package ca.uqam.inf2120.tp3.modele;

/**
 * UQAM - �t� 2014 - INF2120 - Groupe 20 - TP3 
 * 
 * Classe Proprietaire : contient les informations du propri�taire
 * du patient (F�lin, canin, autre).
 *    
 * @author Ismael Doukoure
 * Compl�t� par : VOTRE NOM  VOTRE PR�NOM - VOTRE CODE PERMANENT
 * 
 * @version 07 juillet 2014
 */
public class Proprietaire {
	
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	
	/**
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param telephone
	 */
	public Proprietaire(String nom, String prenom, String adresse,
			String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	


	/* INFORMATIONS IMPORTANTES : 
	 * 
	 * Ajoutez les getters, les setters et toute autre m�thode que vous jugez 
	 * n�cessaire pour accomplir ce travail.
     *
     */	
	

}

