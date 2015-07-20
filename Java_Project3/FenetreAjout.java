package ca.uqam.inf2120.tp3.interfacegraphiques;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
 * @author Serge Dogny
 * @version Juillet 2014 2008  
 * 
 */
@SuppressWarnings("serial")
public class FenetreAjout extends JDialog implements ActionListener{

	// Déclarations des attributs

	// Panneau de haut et ses composants
	JPanel panneauHaut;	
	JRadioButton btnFelin;
	JRadioButton btnCanin;
	JRadioButton btnAutre;
	ButtonGroup groupeClient;

	private GestionUrgenceCliniqueVeterinaire urgence ;

	// Panneau du milieu et ses composants
	JPanel panneauMilieu;
	GridLayout gestionnairePanneauMilieu;
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
	private FenetreRecherche fenRech = new FenetreRecherche();



	// Panneau du bas et ses composants
	JPanel panneauBas;
	JButton btnAjouter;
	JButton btnAnnuler;
	FlowLayout gestionnairePanneauBas;


	// Constructeur sans arguments
	public FenetreAjout(GestionUrgenceCliniqueVeterinaire urgence) {
		this.urgence = urgence;
		init();
	}


	private void init() {

		// Création du panneau de haut avec ses composants.
		panneauHaut = new JPanel( new GridLayout(1, 3));
		panneauHaut.setBorder(new TitledBorder(null, "Espèce",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//panneauHaut.setLayout(gestionnairePanneauHaut);

		btnFelin = new JRadioButton("Félin");
		btnFelin.setSelected(true);		

		btnCanin = new JRadioButton("Canin");

		btnAutre  = new JRadioButton("Autre");

		groupeClient = new ButtonGroup();
		groupeClient.add(btnFelin);
		groupeClient.add(btnCanin);
		groupeClient.add(btnAutre);

		panneauHaut.add(btnFelin);
		panneauHaut.add(btnCanin);
		panneauHaut.add(btnAutre);

		// Création du panneau du milieu avec ses composants.
		panneauMilieu = new JPanel(new GridLayout(4, 2));
		panneauMilieu.setBorder(new TitledBorder(null,
				"Informations générales du patient", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		nom = new JLabel("Nom - Patient");
		saisieNom = new JTextField();
		panneauMilieu.add(nom);
		panneauMilieu.add(saisieNom);

		age = new JLabel("Age :");
		saisieAge = new JTextField();
		panneauMilieu.add(age);
		panneauMilieu.add(saisieAge);

		raison = new JLabel("Raison de l'urgence");
		saisieRaison = new JTextArea(2, 12);		
		panneauMilieu.add(raison);
		panneauMilieu.add(saisieRaison);

		priorite = new JLabel("Priorité");
		champPriorite = new JComboBox<String>();
		champPriorite.addItem("5");
		champPriorite.addItem("4");
		champPriorite.addItem("3");
		champPriorite.addItem("2");
		champPriorite.addItem("1");		
		panneauMilieu.add(priorite);
		panneauMilieu.add(champPriorite);

		JPanel panneauMilieu2 = new JPanel(new GridLayout(4, 2));


		panneauMilieu2.setBorder(new TitledBorder(null,
				"Informations du prorpiétaire", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		nomP = new JLabel("Nom");
		saisieNomP = new JTextField();
		panneauMilieu2.add(nomP);
		panneauMilieu2.add(saisieNomP);

		prenom = new JLabel("Prénom");
		saisiePrenom = new JTextField();
		panneauMilieu2.add(prenom);
		panneauMilieu2.add(saisiePrenom);

		telephone = new JLabel("Téléphone");
		saisieTelephone = new JTextField();
		panneauMilieu2.add(telephone);
		panneauMilieu2.add(saisieTelephone);

		adresse = new JLabel("Adresse");
		saisieAdresse = new JTextArea(2, 12);
		panneauMilieu2.add(adresse);
		panneauMilieu2.add(saisieAdresse);		

		// Création du panneau du bas avec ses composants.
		panneauBas = new JPanel();
		gestionnairePanneauBas = new FlowLayout(FlowLayout.RIGHT);
		panneauBas.setLayout(gestionnairePanneauBas);

		btnAjouter = new JButton("Ajouter");
		btnAnnuler = new JButton("Annuler");

		panneauBas.add(btnAjouter);
		panneauBas.add(btnAnnuler);

		setTitle("            STP - Ajout d'une patient");		
		setBounds(400, 200, 390, 400);

		JPanel panneauInter = new JPanel(new GridLayout(2, 1));

		panneauInter.add(panneauMilieu);
		panneauInter.add(panneauMilieu2);

		add(panneauHaut, BorderLayout.NORTH);
		add(panneauInter, BorderLayout.CENTER);
		add(panneauBas , BorderLayout.SOUTH);

		// Définir le type de fermetire
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btnAjouter.addActionListener(this);
		btnAnnuler.addActionListener(this);			

	}

	@Override
	public void actionPerformed(ActionEvent e) {		

		String espece = null;

		if(e.getSource() == btnAjouter){

			if(saisieNomP.getText().trim().length() != 0 && saisiePrenom.getText().trim().length() != 0 
					&& saisieAdresse.getText().trim().length() != 0 && saisieTelephone.getText().trim().length() != 0
					&& saisieNom.getText().trim().length() != 0	&& saisieAge.getText().trim().length() != 0 
					&& saisieRaison.getText().trim().length() != 0){

				if(btnFelin.isSelected()){					
					espece = "Félin";
				}else if(btnCanin.isSelected()){				
					espece = "Canin";				
				}else if(btnAutre.isSelected()){				
					espece = "Autre";				
				}

				proprio  = new Proprietaire(saisieNomP.getText(),saisiePrenom.getText(),saisieAdresse.getText(),saisieTelephone.getText());

				unPatient = new Patient (saisieNom.getText(),saisieAge.getText(),espece,saisieRaison.getText(),
						Integer.parseInt((String)champPriorite.getSelectedItem().toString()), proprio);		


				urgence.ajouterPatient(unPatient);
				List<Patient> listeAjout = new ArrayList<Patient>();
				listeAjout = urgence.rechercherTousLesPatients();
				fenRech.formaterTable( listeAjout);			

				this.dispose();
				JOptionPane.showMessageDialog(null, "Le patient est enregistré.",
						"Information", JOptionPane.INFORMATION_MESSAGE);				

			}else{

				JOptionPane.showMessageDialog(null, "informations manquantes", 
						"Avertissement", JOptionPane.WARNING_MESSAGE);					
			}			

		}else if ((e.getSource() == btnAnnuler)){			

			this.dispose();				
		}		
	}			

}
