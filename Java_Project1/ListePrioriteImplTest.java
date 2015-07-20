package ca.uqam.inf2120.tp1.adt.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.uqam.inf2120.tp1.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl;

/**
 * UQAM - Été 2014 - INF2120 - Groupe 20 - TP1
 * 
 * ListePrioriteImplTest : Classe de test de ListePrioriteImpl.
 * 
 * @author DOGNY Gnagnely Serge ;  code permanent: DOGG03078104
 * @version 06 juin 2014
 */
public class ListePrioriteImplTest {
	
	ListePrioriteTda<ElementT> listePriorite;
	ElementT elt1;
	ElementT elt2;
	ElementT elt3;
	ElementT elt4;
	ElementT elt5;
	ElementT elt6;
	ElementT elt7;
	List<ElementT> uneListe ;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		listePriorite = new ListePrioriteImpl<ElementT>();
		uneListe = new ArrayList<ElementT>();
		elt1 = new ElementT("DOG120", 7);
		elt2 = new ElementT("KON778", 5);
		elt3 = new ElementT("KBJ962", 3);
		elt4 = new ElementT("BON205", 8);
		elt5 = new ElementT("ELT105", 3);
		elt6 = new ElementT("MOV175", 3);
		elt7 = new ElementT("KYV385", 10);
		
		uneListe.add(elt6);		
		uneListe.add(elt5);
		uneListe.add(elt3);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		listePriorite = null;
		elt1 = null;
		elt2 = null;
		elt3 = null;
		uneListe.clear();
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#ajouter(ca.uqam.inf2120.tp1.adt.Priorite)}.
	 */
	@Test
	public void testAjouterT() {
		
		listePriorite.ajouter(elt1);
		listePriorite.ajouter(elt2);
		listePriorite.ajouter(elt6);
		listePriorite.ajouter(elt3);
		assertFalse(listePriorite.estVide());		
		assertTrue(listePriorite.ajouter(elt4));
		assertEquals(1, listePriorite.ObtenirNbElments(7));
		assertEquals(1, listePriorite.ObtenirNbElments(5));
		assertEquals(1, listePriorite.ObtenirNbElments(8));
		assertEquals(2, listePriorite.ObtenirNbElments(3));
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#ajouter(java.util.List)}.
	 */
	@Test
	public void testAjouterListOfT() {
		
		listePriorite.ajouter(elt1);
		listePriorite.ajouter(uneListe);
		listePriorite.ajouter(elt6);
		listePriorite.ajouter(elt1);
		assertEquals(3, listePriorite.ObtenirNbElments(3));
		assertEquals(1, listePriorite.ObtenirNbElments(7));
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#supprimer(ca.uqam.inf2120.tp1.adt.Priorite)}.
	 */
	@Test
	public void testSupprimerT() {
		
		listePriorite.ajouter(elt7);		
		assertTrue(listePriorite.supprimer(elt7));
		assertEquals(0, listePriorite.ObtenirNbElments(10));
		listePriorite.ajouter(uneListe);
		assertFalse(listePriorite.supprimer(elt7));
		
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#supprimer(java.util.List)}.
	 */
	@Test
	public void testSupprimerListOfT() {
		
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt6);		
	    assertNull(listePriorite.supprimer(uneListe));
	    assertTrue(listePriorite.estVide());
	    listePriorite.ajouter(elt1);
	    assertEquals(uneListe, listePriorite.supprimer(uneListe));    
		
		
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#supprimer(int)}.
	 */
	@Test
	public void testSupprimerInt() {
		
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt6);		
		assertNull(listePriorite.supprimer(4));	
		listePriorite.supprimer(3);
		assertTrue(listePriorite.estVide());		
		
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#supprimer(int, boolean)}.
	 */
	@Test
	public void testSupprimerIntBoolean() {
		
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt6);
		listePriorite.ajouter(elt1);		
		assertTrue(listePriorite.supprimer(3, false).get(0).equals(elt1));
		listePriorite.supprimer(10, true);
		assertTrue(listePriorite.estVide());
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#remplacer(int, int)}.
	 */
	@Test
	public void testRemplacer() {
		
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt6);
		listePriorite.ajouter(elt1);		
		assertTrue(listePriorite.remplacer(3, 5));
		assertEquals(3, listePriorite.ObtenirNbElments(5));
			
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#ObtenirNbElments(int)}.
	 */
	@Test
	public void testObtenirNbElments() {
		
		listePriorite.ajouter(elt6);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt1);
		assertEquals(3, listePriorite.ObtenirNbElments(3));
		assertEquals(1, listePriorite.ObtenirNbElments(7));
		listePriorite.supprimer(elt5);
		assertEquals(2, listePriorite.ObtenirNbElments(3));
		
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#estVide()}.
	 */
	@Test
	public void testEstVide() {
		
		listePriorite.ajouter(elt7);
		assertFalse(listePriorite.estVide());
		listePriorite.supprimer(elt7);
		assertTrue(listePriorite.estVide());
	}

	/**
	 * Test method for {@link ca.uqam.inf2120.tp1.adt.impl.ListePrioriteImpl#iterateur()}.
	 */
	@Test
	public void testIterateur() {
		
		listePriorite.ajouter(elt3);
		listePriorite.ajouter(elt5);
		listePriorite.ajouter(elt6);	
		assertTrue(listePriorite.iterateur().next().equals(elt3));
		assertEquals(elt3,listePriorite.iterateur().next());
	
				
	}
	

}
