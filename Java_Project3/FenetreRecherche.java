package ca.uqam.inf2120.tp3.interfacegraphiques;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import ca.uqam.inf2120.tp3.modele.*;




/**
 * FenetreRechercheEtudiants : Fenêtre (JFrame) de recherche d’étudiant avec des
 * étudiants dans la liste .
 * 
 * @author Serge Dogny
 * @version Juillet 2014
 * 
 */
@SuppressWarnings("serial")
public class FenetreRecherche extends JFrame implements ActionListener{

	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnAfficher;
	private JButton btnFermer;
	private JButton btnRechercher;
	private JTextField champRecherche;
	private JPanel panelHaut;
	private JPanel panelCenter;
	private JPanel panelCenter2;
	private JPanel panneauCentral;
	private JPanel panelDuBas;
	private JRadioButton rdbtnTousLesPatients;
	private JRadioButton rdbtnInf;
	private JRadioButton rdbtnEgal;
	private JRadioButton rdbtnSup;
	private JRadioButton rdbtnId;	
	private JTable tablePatients;
	private String chaineLue;
	private GestionUrgenceCliniqueVeterinaire urgence = new GestionUrgenceCliniqueVeterinaire();
	List<Patient> maListe;
	int valeur;

	/**
	 * Constructeur.
	 */
	public FenetreRecherche() {


		setTitle("                                        Clinique véterinaire - Soins Pour Tous (SPT)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 350);

		panelHaut = new JPanel(new GridLayout(3, 2));
		panelHaut.setBorder(new TitledBorder(null, "Type de recherche ",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

		rdbtnId = new JRadioButton("Identifiant");
		rdbtnId.setSelected(true);
		panelHaut.add(rdbtnId);

		rdbtnSup = new JRadioButton(" > à la prriorité donnée");
		panelHaut.add(rdbtnSup);

		rdbtnEgal = new JRadioButton(" = à la prriorité donnée");
		panelHaut.add(rdbtnEgal);

		rdbtnInf = new JRadioButton(" < à la prriorité donnée");
		panelHaut.add(rdbtnInf);

		rdbtnTousLesPatients = new JRadioButton("Tous les patients");
		panelHaut.add(rdbtnTousLesPatients);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnId);
		buttonGroup.add(rdbtnSup);
		buttonGroup.add(rdbtnEgal);
		buttonGroup.add(rdbtnInf);
		buttonGroup.add(rdbtnTousLesPatients);

		add(panelHaut, BorderLayout.NORTH);

		panelCenter = new JPanel(new FlowLayout());
		panelCenter2 = new JPanel(new FlowLayout());
		panneauCentral = new JPanel(new BorderLayout());

		champRecherche = new JTextField();
		panelCenter.add(champRecherche);
		champRecherche.setColumns(20);

		btnRechercher = new JButton("Rechercher");
		panelCenter.add(btnRechercher);

		panneauCentral.add(panelCenter, BorderLayout.NORTH);
		add(panneauCentral, BorderLayout.CENTER );

		panelDuBas = new JPanel(new FlowLayout());
		FlowLayout fl_panelDuBas = (FlowLayout) panelDuBas.getLayout();
		fl_panelDuBas.setVgap(20);

		btnAjouter = new JButton("Ajouter");
		panelDuBas.add(btnAjouter);		

		if (urgence.estVide()){			

			btnModifier = new JButton("Modifier");
			panelDuBas.add(btnModifier);
			btnModifier.setEnabled(false);

			btnSupprimer = new JButton("Supprimer");
			panelDuBas.add(btnSupprimer);
			btnSupprimer.setEnabled(false);

			btnAfficher = new JButton("Afficher");
			panelDuBas.add(btnAfficher);
			btnAfficher.setEnabled(false);


		}else{			

			btnModifier = new JButton("Modifier");
			panelDuBas.add(btnModifier);			

			btnSupprimer = new JButton("Supprimer");
			panelDuBas.add(btnSupprimer);			

			btnAfficher = new JButton("Afficher");
			panelDuBas.add(btnAfficher);		

		}

		btnFermer = new JButton("Fermer");
		panelDuBas.add(btnFermer);

		fl_panelDuBas.setAlignment(FlowLayout.RIGHT);
		add(panelDuBas, BorderLayout.SOUTH);


		btnRechercher.addActionListener(this);
		btnFermer.addActionListener(this);

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenetreAjout fenetreAjout = new FenetreAjout(urgence);
				fenetreAjout.setVisible(true);

			}
		});

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				 
				int ligne = tablePatients.getSelectedRow();					
				String numeros = (String) tablePatients.getModel().getValueAt(ligne,0);	

				FenetreModifie fene = new FenetreModifie(urgence, numeros);
				fene.setVisible(true);

			}
		});

		btnSupprimer.addActionListener(this);

		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = tablePatients.getSelectedRow();					
				String numeros = (String) tablePatients.getModel().getValueAt(ligne,0);
				FenetreAffiche feneAff = new FenetreAffiche(urgence, numeros);
				feneAff.setVisible(true);

			}
		});		

		rdbtnTousLesPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				champRecherche.setEnabled(false);

			}
		});		

		rdbtnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				champRecherche.setEnabled(true);

			}
		});		

		rdbtnSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				champRecherche.setEnabled(true);

			}
		});		

		rdbtnInf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				champRecherche.setEnabled(true);

			}
		});	

		rdbtnEgal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				champRecherche.setEnabled(true);

			}
		});		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		FenetreRecherche fenetre = new FenetreRecherche();
		fenetre.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {	


		if(evt.getSource() == btnSupprimer){

			String code;
			Patient unPatient;
			int ligne;
			List<Patient> uneListe = new ArrayList<Patient>();
			uneListe = urgence.rechercherTousLesPatients();

			if (uneListe.size() == 1){

				ligne = tablePatients.getSelectedRow();					
				code = (String) tablePatients.getModel().getValueAt(ligne,0);	
				unPatient = urgence.rechercherParIdentifiant(code);
				urgence.supprimerPatient(unPatient);
				this.dispose();
				FenetreRecherche fenetre = new FenetreRecherche();
				fenetre.setVisible(true);

			}else{

				ligne = tablePatients.getSelectedRow();					
				code = (String) tablePatients.getModel().getValueAt(ligne,0);	
				unPatient = urgence.rechercherParIdentifiant(code);
				urgence.supprimerPatient(unPatient);

				maListe = new ArrayList<Patient>();
				maListe = urgence.rechercherTousLesPatients();
				formaterTable( maListe);
			}

		}else if(evt.getSource() == btnFermer){	

			this.dispose();				

		}else if (evt.getSource() == btnRechercher) {

			if (rdbtnTousLesPatients.isSelected()) {

				if(urgence.estVide()){

					JOptionPane.showMessageDialog(null, "Aucun patient n'est enregistré.",
							"Information", JOptionPane.INFORMATION_MESSAGE);					

				}else{						

					maListe = new ArrayList<Patient>();
					maListe = urgence.rechercherTousLesPatients();
					formaterTable( maListe);

					btnModifier.setEnabled(true);						
					btnSupprimer.setEnabled(true);						
					btnAfficher.setEnabled(true);
				}					

			}else if(rdbtnId.isSelected()){

				chaineLue = champRecherche.getText();
				boolean trouve;

				if(chaineLue.trim().length() == 0){

					JOptionPane.showMessageDialog(null, "Aucun identifiant n'est entré.",
							"Message", JOptionPane.WARNING_MESSAGE);							
				}else{

					trouve = urgence.contient(chaineLue);						

					if(trouve){

						Patient unPatient = urgence.rechercherParIdentifiant(chaineLue);
						maListe = new ArrayList<Patient>();
						maListe.add(unPatient);
						formaterTable( maListe);	
						btnModifier.setEnabled(true);						
						btnSupprimer.setEnabled(true);						
						btnAfficher.setEnabled(true);

					}else{

						JOptionPane.showMessageDialog(null, "Aucun patient avec l'identifiant  "+ chaineLue, 
								"Message", JOptionPane.INFORMATION_MESSAGE);							
					}						
				}

			}else if(rdbtnSup.isSelected()){

				chaineLue = champRecherche.getText();

				if(chaineLue.trim().length() == 0){

					JOptionPane.showMessageDialog(null, "Aucune priorité n'est entré.",
							"Message", JOptionPane.WARNING_MESSAGE);

				}else if (chaineLue.trim().length() != 1){

					JOptionPane.showMessageDialog(null, "Entrée non valide", 
							"Message", JOptionPane.WARNING_MESSAGE);

				}else {

					if(chaineLue.trim().charAt(0) >= '0' && chaineLue.trim().charAt(0) <= '9'){

						valeur = Integer.parseInt(chaineLue.trim());

						if(valeur == 1 || valeur == 2 || valeur == 3 || valeur == 4 || valeur == 5 ){

							maListe = new ArrayList<Patient>();
							maListe = urgence.rechercherParPriorite(valeur, 1);

							if(maListe != null){

								formaterTable( maListe);

								btnModifier.setEnabled(true);						
								btnSupprimer.setEnabled(true);						
								btnAfficher.setEnabled(true);

							}else {

								JOptionPane.showMessageDialog(null, "Patients inexistents", 
										"Message", JOptionPane.WARNING_MESSAGE);								
							}
						}else{

							JOptionPane.showMessageDialog(null, "Priorité non valide", 
									"Message", JOptionPane.WARNING_MESSAGE);		

						}

					}else{

						JOptionPane.showMessageDialog(null, "Entrer une valeur numérique SVP.", 
								"Message", JOptionPane.WARNING_MESSAGE);							
					}						
				}					

			}else if(rdbtnInf.isSelected()){					

				chaineLue = champRecherche.getText();

				if(chaineLue.trim().length() == 0){

					JOptionPane.showMessageDialog(null, "Aucune priorité n'est entré.",
							"Message", JOptionPane.WARNING_MESSAGE);

				}else if (chaineLue.trim().length() != 1){

					JOptionPane.showMessageDialog(null, "Entrée non valide", 
							"Message", JOptionPane.WARNING_MESSAGE);

				}else {

					if(chaineLue.trim().charAt(0) >= '0' && chaineLue.trim().charAt(0) <= '9'){

						valeur = Integer.parseInt(chaineLue.trim());

						if(valeur == 1 || valeur == 2 || valeur == 3 || valeur == 4 || valeur == 5 ){

							maListe = new ArrayList<Patient>();
							maListe = urgence.rechercherParPriorite(valeur, -1);

							if(maListe != null){

								formaterTable( maListe);

								btnModifier.setEnabled(true);						
								btnSupprimer.setEnabled(true);						
								btnAfficher.setEnabled(true);

							}else {

								JOptionPane.showMessageDialog(null, "Patients inexistents", 
										"Message", JOptionPane.WARNING_MESSAGE);								
							}
						}else{

							JOptionPane.showMessageDialog(null, "Priorité non valide", 
									"Message", JOptionPane.WARNING_MESSAGE);		

						}

					}else{

						JOptionPane.showMessageDialog(null, "Entrer une valeur numérique SVP.", 
								"Message", JOptionPane.WARNING_MESSAGE);							
					}						
				}				


			}else if(rdbtnEgal.isSelected()){					

				chaineLue = champRecherche.getText();

				if(chaineLue.trim().length() == 0){

					JOptionPane.showMessageDialog(null, "Aucune priorité n'est entré.",
							"Message", JOptionPane.WARNING_MESSAGE);

				}else if (chaineLue.trim().length() != 1){

					JOptionPane.showMessageDialog(null, "Entrée non valide", 
							"Message", JOptionPane.WARNING_MESSAGE);

				}else {

					if(chaineLue.trim().charAt(0) >= '0' && chaineLue.trim().charAt(0) <= '9'){

						valeur = Integer.parseInt(chaineLue.trim());

						if(valeur == 1 || valeur == 2 || valeur == 3 || valeur == 4 || valeur == 5 ){

							maListe = new ArrayList<Patient>();
							maListe = urgence.rechercherParPriorite(valeur, 0);

							if(maListe != null){

								formaterTable( maListe);

								btnModifier.setEnabled(true);						
								btnSupprimer.setEnabled(true);						
								btnAfficher.setEnabled(true);

							}else {

								JOptionPane.showMessageDialog(null, "Patients inexistents", 
										"Message", JOptionPane.WARNING_MESSAGE);								
							}
						}else{

							JOptionPane.showMessageDialog(null, "Priorité non valide", 
									"Message", JOptionPane.WARNING_MESSAGE);		

						}

					}else{

						JOptionPane.showMessageDialog(null, "Entrer une valeur numérique SVP.", 
								"Message", JOptionPane.WARNING_MESSAGE);							
					}						
				}				
			}
		}		
	}

	public void formaterTable(List<Patient> maListe) {
		panneauCentral.remove(panelCenter2);
		panelCenter2 = new JPanel(new FlowLayout());
		panelCenter2.setPreferredSize(new Dimension(10, 15));
		panelCenter2.setOpaque(true);


		tablePatients = new JTable();
		tablePatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePatients.setFillsViewportHeight(true);
		tablePatients.setPreferredScrollableViewportSize(new Dimension(600, 70));					
		tablePatients.setModel(urgence.creerModeleAvecColonnesNonEditables(maListe));

		tablePatients.setRowSelectionInterval(0, 0);
		tablePatients.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablePatients.getColumnModel().getColumn(1).setResizable(false);
		tablePatients.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablePatients.getColumnModel().getColumn(2).setResizable(false);
		tablePatients.getColumnModel().getColumn(2).setPreferredWidth(80);
		tablePatients.getColumnModel().getColumn(3).setResizable(false);
		tablePatients.getColumnModel().getColumn(3).setPreferredWidth(150);					
		tablePatients.getColumnModel().getColumn(4).setResizable(false);
		tablePatients.getColumnModel().getColumn(4).setPreferredWidth(200);	

		JScrollPane scrollPane = new JScrollPane(tablePatients);
		scrollPane.setPreferredSize(new Dimension(600, 70));
		panelCenter2.add(scrollPane);
		panneauCentral.add(panelCenter, BorderLayout.NORTH);
		panneauCentral.add(panelCenter2, BorderLayout.CENTER );
		panneauCentral.repaint();
		panneauCentral.validate();		
		this.add(panneauCentral,BorderLayout.CENTER);
		this.repaint();
		this.validate();

	}	

}
