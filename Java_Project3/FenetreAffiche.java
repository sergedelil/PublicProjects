package ca.uqam.inf2120.tp3.interfacegraphiques;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import ca.uqam.inf2120.tp3.modele.*;

/**
 * Fenêtre Client.
 * 
 * Université du Québec à Montréal 
 * 
 * INF2120 - Programmation II
 * 
 * @author Ismael Doukoure
 * @version Mars 2008 
 * Modifié en Mars 2014
 * 
 */
@SuppressWarnings("serial")
public class FenetreAffiche extends JDialog implements ActionListener{

	JPanel panneauHaut;	
	JRadioButton btnFelin;
	JRadioButton btnCanin;
	JRadioButton btnAutre;
	ButtonGroup groupeClient;


	JPanel panneauMilieu;
	GridLayout gestionnairePanneauMilieu;

	JLabel ident;
	JTextField saisieId;

	JLabel heure;
	JTextField saisieHeure;

	JLabel nom;
	JTextField saisieNom;
	JLabel age;
	JTextField saisieAge;
	JLabel raison;
	JTextArea saisieRaison;
	JLabel priorite;
	JComboBox<String> champPriorite;

	JLabel nomP;
	JTextField saisieNomP;
	JLabel prenom;
	JTextField saisiePrenom;
	JLabel telephone;
	JTextField saisieTelephone;
	JLabel adresse;
	JTextArea saisieAdresse;

	Proprietaire proprio;
	Patient unPatient;
	private GestionUrgenceCliniqueVeterinaire urgence;
	private String element;




	JPanel panneauBas;	
	JButton btnFermer;
	FlowLayout gestionnairePanneauBas;



	public FenetreAffiche(GestionUrgenceCliniqueVeterinaire urgence, String element) {
		this.urgence = urgence;
		this.element = element;
		init();
	}	

	private void init() {

		Patient unPatient = urgence.rechercherParIdentifiant(element);

		panneauHaut = new JPanel( new GridLayout(1, 3));
		panneauHaut.setBorder(new TitledBorder(null, "Espèce",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnFelin = new JRadioButton("Félin");
		btnCanin = new JRadioButton("Canin");
		btnAutre  = new JRadioButton("Autre");

		if(unPatient.getEspece().equals("Félin")){

			btnFelin.setSelected(true);

		}else if(unPatient.getEspece().equals("Canin")){

			btnCanin.setSelected(true);

		}else if(unPatient.getEspece().equals("Autre")){

			btnAutre.setSelected(true);

		}		

		groupeClient = new ButtonGroup();
		groupeClient.add(btnFelin);
		groupeClient.add(btnCanin);
		groupeClient.add(btnAutre);

		panneauHaut.add(btnFelin);
		panneauHaut.add(btnCanin);
		panneauHaut.add(btnAutre);

		panneauMilieu = new JPanel(new GridLayout(6, 2));
		panneauMilieu.setBorder(new TitledBorder(null,
				"Informations générales du patient", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));


		ident = new JLabel("Identifiant :");
		saisieId = new JTextField();
		panneauMilieu.add(ident);
		panneauMilieu.add(saisieId);
		saisieId.setText(unPatient.getIdentifiant());
		saisieId.setEditable(false);

		nom = new JLabel("Nom - Patient");
		saisieNom = new JTextField();
		panneauMilieu.add(nom);
		panneauMilieu.add(saisieNom);
		saisieNom.setText(unPatient.getNom());
		saisieNom.setEditable(false);

		age = new JLabel("Age :");
		saisieAge = new JTextField();
		panneauMilieu.add(age);
		panneauMilieu.add(saisieAge);
		saisieAge.setText(unPatient.getAge());
		saisieAge.setEditable(false);

		raison = new JLabel("Raison de l'urgence");
		saisieRaison = new JTextArea(2, 12);		
		panneauMilieu.add(raison);
		panneauMilieu.add(saisieRaison);
		saisieRaison.setText(unPatient.getRaisonUrgence());
		saisieRaison.setEditable(false);

		priorite = new JLabel("Priorité");
		champPriorite = new JComboBox<String>();			
		panneauMilieu.add(priorite);
		JTextField prio = new JTextField();
		panneauMilieu.add(prio);
		prio.setEditable(false);
		prio.setText(unPatient.getPriorite()+"");

		heure = new JLabel("Date / Heure d'arrivée :");
		saisieHeure = new JTextField();
		panneauMilieu.add(heure);
		panneauMilieu.add(saisieHeure);
		SimpleDateFormat formater = null;		   
		formater = new SimpleDateFormat("yyyy-MM-yy HH:mm:ss");		
		saisieHeure.setText(formater.format(unPatient.getDateHeureArrivee())+"");
		saisieHeure.setEditable(false);

		JPanel panneauMilieu2 = new JPanel(new GridLayout(4, 2));


		panneauMilieu2.setBorder(new TitledBorder(null,
				"Informations du prorpiétaire", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		nomP = new JLabel("Nom");
		saisieNomP = new JTextField();
		panneauMilieu2.add(nomP);
		panneauMilieu2.add(saisieNomP);
		saisieNomP.setText(unPatient.getProprietaire().getNom());
		saisieNomP.setEditable(false);

		prenom = new JLabel("Prénom");
		saisiePrenom = new JTextField();
		panneauMilieu2.add(prenom);
		panneauMilieu2.add(saisiePrenom);
		saisiePrenom.setText(unPatient.getProprietaire().getPrenom());
		saisiePrenom.setEditable(false);

		telephone = new JLabel("Téléphone");
		saisieTelephone = new JTextField();
		panneauMilieu2.add(telephone);
		panneauMilieu2.add(saisieTelephone);
		saisieTelephone.setText(unPatient.getProprietaire().getTelephone());
		saisieTelephone.setEditable(false);

		adresse = new JLabel("Adresse");
		saisieAdresse = new JTextArea(2, 12);
		panneauMilieu2.add(adresse);
		panneauMilieu2.add(saisieAdresse);		
		saisieAdresse.setText(unPatient.getProprietaire().getAdresse());
		saisieAdresse.setEditable(false);

		// Création du panneau du bas avec ses composants.
		panneauBas = new JPanel();
		gestionnairePanneauBas = new FlowLayout(FlowLayout.RIGHT);
		panneauBas.setLayout(gestionnairePanneauBas);

		btnFermer = new JButton("Fermer");		
		panneauBas.add(btnFermer);

		setTitle("            STP - Affichage  d'un patient");		
		setBounds(400, 200, 390, 450);

		JPanel panneauInter = new JPanel(new GridLayout(2, 1));

		panneauInter.add(panneauMilieu);
		panneauInter.add(panneauMilieu2);

		add(panneauHaut, BorderLayout.NORTH);
		add(panneauInter, BorderLayout.CENTER);
		add(panneauBas , BorderLayout.SOUTH);

		// Définir le type de fermetire
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);


		btnFermer.addActionListener(this);		

	}

	@Override
	public void actionPerformed(ActionEvent e) {		

		if ((e.getSource() == btnFermer)){			

			this.dispose();				
		}		
	}
}
