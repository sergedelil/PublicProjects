package ca.uqam.inf2120.tp3.interfacegraphiques;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import ca.uqam.inf2120.tp3.modele.*;


@SuppressWarnings("serial")
public class FenetreModifie extends JDialog implements ActionListener{

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
	JButton btnModifier;
	JButton btnAnnuler;
	FlowLayout gestionnairePanneauBas;		 
	JTable tablePatients;

	public FenetreModifie(GestionUrgenceCliniqueVeterinaire urgence, String element) {
		this.urgence = urgence;
		this.element = element;		
		init();
	}	

	private void init() {

		unPatient = urgence.rechercherParIdentifiant(element);

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

		raison = new JLabel("Raison de l'urgence");
		saisieRaison = new JTextArea(2, 12);		
		panneauMilieu.add(raison);
		panneauMilieu.add(saisieRaison);
		saisieRaison.setText(unPatient.getRaisonUrgence());

		priorite = new JLabel("Priorité");
		champPriorite = new JComboBox<String>();
		champPriorite.addItem("5");
		champPriorite.addItem("4");
		champPriorite.addItem("3");
		champPriorite.addItem("2");
		champPriorite.addItem("1");		
		panneauMilieu.add(priorite);
		panneauMilieu.add(champPriorite);

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

		prenom = new JLabel("Prénom");
		saisiePrenom = new JTextField();
		panneauMilieu2.add(prenom);
		panneauMilieu2.add(saisiePrenom);
		saisiePrenom.setText(unPatient.getProprietaire().getPrenom());

		telephone = new JLabel("Téléphone");
		saisieTelephone = new JTextField();
		panneauMilieu2.add(telephone);
		panneauMilieu2.add(saisieTelephone);
		saisieTelephone.setText(unPatient.getProprietaire().getTelephone());

		adresse = new JLabel("Adresse");
		saisieAdresse = new JTextArea(2, 12);
		panneauMilieu2.add(adresse);
		panneauMilieu2.add(saisieAdresse);		
		saisieAdresse.setText(unPatient.getProprietaire().getAdresse());

		// Création du panneau du bas avec ses composants.
		panneauBas = new JPanel();
		gestionnairePanneauBas = new FlowLayout(FlowLayout.RIGHT);
		panneauBas.setLayout(gestionnairePanneauBas);

		btnModifier = new JButton("Modifier");
		btnAnnuler = new JButton("Annuler");

		panneauBas.add(btnModifier);
		panneauBas.add(btnAnnuler);

		setTitle("            STP - Modification d'un patient");		
		setBounds(400, 200, 390, 450);

		JPanel panneauInter = new JPanel(new GridLayout(2, 1));

		panneauInter.add(panneauMilieu);
		panneauInter.add(panneauMilieu2);

		add(panneauHaut, BorderLayout.NORTH);
		add(panneauInter, BorderLayout.CENTER);
		add(panneauBas , BorderLayout.SOUTH);

		// Définir le type de fermetire
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btnModifier.addActionListener(this);
		btnAnnuler.addActionListener(this);		

	}

	@Override
	public void actionPerformed(ActionEvent e) {		

		String espece = null;

		if(e.getSource() == btnModifier){

			if(saisieNomP.getText().trim().length() != 0 && saisiePrenom.getText().trim().length() != 0 
					&& saisieAdresse.getText().trim().length() != 0 && saisieTelephone.getText().trim().length() != 0
					&& saisieAge.getText().trim().length() != 0	&& saisieRaison.getText().trim().length() != 0){

				if(btnFelin.isSelected()){					
					espece = "Félin";
				}else if(btnCanin.isSelected()){				
					espece = "Canin";				
				}else if(btnAutre.isSelected()){				
					espece = "Autre";				
				}
				unPatient.setEspece(espece);
				unPatient.setAge(saisieAge.getText().trim());
				unPatient.setRaisonUrgence(saisieRaison.getText().trim());
				unPatient.getProprietaire().setNom(saisieNomP.getText().trim());						
				unPatient.getProprietaire().setPrenom(saisiePrenom.getText().trim());
				unPatient.getProprietaire().setAdresse(saisieAdresse.getText().trim());
				unPatient.getProprietaire().setTelephone(saisieTelephone.getText().trim()); 				
				unPatient.setPriorite(Integer.parseInt((String)champPriorite.getSelectedItem().toString()));				

				List<Patient> maListe = new ArrayList<Patient>();
				maListe = urgence.rechercherParPriorite(unPatient.getPriorite(), 0);

				for(int i = 0; i < maListe.size(); i++){

					Patient patient = maListe.get(i);
					urgence.supprimerPatient(patient);					
				}

				List<Patient> laListe = new ArrayList<Patient>();
				laListe = trierListe(maListe);

				for(int i = 0; i < laListe.size(); i++){

					Patient patient = laListe.get(i);
					urgence.ajouterPatient(patient);	
				}

				this.dispose();
				JOptionPane.showMessageDialog(null, "Le patient a été modifié.",
						"Information", JOptionPane.INFORMATION_MESSAGE);				

			}else{

				JOptionPane.showMessageDialog(null, "informations manquantes", 
						"Avertissement", JOptionPane.WARNING_MESSAGE);					
			}			

		}else if ((e.getSource() == btnAnnuler)){			

			this.dispose();				
		}		
	}

	public List<Patient> trierListe(List<Patient> liste) {

		int minIndice;		
		Patient temp;

		for (int i = 0; i < liste.size() - 1; i++) {

			minIndice = i;

			for (int j = i + 1; j < liste.size(); j++) {
				if (liste.get(j).getDateHeureArrivee().compareTo(liste.get(minIndice).getDateHeureArrivee()) < 0) {
					minIndice = j;
				}
			}

			temp = liste.get(minIndice);
			liste.set(minIndice, liste.get(i));
			liste.set(i, temp);			 
		}

		return liste;		
	}
}
