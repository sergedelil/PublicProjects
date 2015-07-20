package ca.uqam.inf2120.tp2.adt.test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ca.uqam.inf2120.tp2.adt.impl.ListePrioriteChaineeImpl;
import ca.uqam.inf2120.tp2.adt.ListePrioriteTda;
import ca.uqam.inf2120.tp2.adt.test.ElementT;

public class ListePrioriteChaineeImplTest {
	
	ListePrioriteTda<ElementT> listePrioriteChainee;
	ElementT elt1;
	ElementT elt2;
	ElementT elt3;
	ElementT elt4;
	ElementT elt5;
	ElementT elt6;
	ElementT elt7;
	ElementT elt8;
	ElementT elt9;
	List<ElementT> uneListe ;

	@Before
	public void setUp() throws Exception {
		
		listePrioriteChainee = new ListePrioriteChaineeImpl<ElementT>();
		uneListe = new ArrayList<ElementT>();
		elt1 = new ElementT("DOG120", 7);
		elt2 = new ElementT("KON778", 5);
		elt3 = new ElementT("KBJ962", 3);
		elt4 = new ElementT("BON205", 8);
		elt5 = new ElementT("ELT105", 3);
		elt6 = new ElementT("MOV175", 3);
		elt7 = new ElementT("KYV385", 10);
		elt8 = null;
		elt9 = new ElementT("KYV385", -10);
		
		uneListe.add(elt3);		
		uneListe.add(elt1);
		uneListe.add(elt4);
	}

	@After
	public void tearDown() throws Exception {		
		
		listePrioriteChainee = null;
		elt1 = null;
		elt2 = null;
		elt3 = null;
		elt4 = null;
		elt5 = null;
		elt6 = null;
		elt7 = null;
		elt8 = null;
		elt9 = null;
		uneListe.clear();
	}

	@Test
	public void testIterator() {		 		
	
		listePrioriteChainee.ajouter(elt1);
		listePrioriteChainee.ajouter(elt2);
		Iterator<ElementT> it = listePrioriteChainee.iterator();
		assertTrue(it.hasNext());		
		assertEquals(elt1, it.next());
		assertTrue(it.hasNext());	
	
	}

	@Test
	public void testHasNext() {
		
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt2);
		Iterator<ElementT> it = listePrioriteChainee.iterator();
		assertTrue(it.hasNext());		
		assertEquals(elt2, it.next());
		assertTrue(it.hasNext());	
				
	}

	@Test
	public void testNext() {	
		
		listePrioriteChainee.ajouter(elt4);
		listePrioriteChainee.ajouter(elt7);
		Iterator<ElementT> it = listePrioriteChainee.iterator();
		assertTrue(it.hasNext());		
		assertEquals(elt7, it.next());
		assertTrue(it.hasNext());		
			
	}
	
	/*
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	public void testAjouterT() {
		
		listePrioriteChainee.ajouter(elt1);		
		listePrioriteChainee.ajouter(elt3);		
		listePrioriteChainee.ajouter(elt2);
		listePrioriteChainee.ajouter(elt8);
		listePrioriteChainee.ajouter(elt9);
		assertTrue(listePrioriteChainee.iterator().hasNext());
		assertFalse(listePrioriteChainee.estVide());		
		assertEquals(3, listePrioriteChainee.obtenirNbElments());
		assertFalse(listePrioriteChainee.ajouter(elt8));
		
	}

	@Test
	public void testAjouterListOfT() {
		
		assertTrue(listePrioriteChainee.estVide());
		listePrioriteChainee.ajouter(uneListe);		
		assertEquals(3,listePrioriteChainee.obtenirNbElments());		
		
	}

	@Test
	public void testSupprimerT() {
		
		listePrioriteChainee.ajouter(elt1);
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt4);
		listePrioriteChainee.ajouter(elt7);
		assertTrue(listePrioriteChainee.supprimer(elt1));
		assertEquals(3,listePrioriteChainee.obtenirNbElments());
		
	}

	@Test
	public void testSupprimerListOfT() {		
		
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt1);
		listePrioriteChainee.ajouter(elt4);		
	    assertNull(listePrioriteChainee.supprimer(uneListe));	    
	    assertTrue(listePrioriteChainee.estVide());
	    listePrioriteChainee.ajouter(elt2);	    
	    assertEquals(uneListe, listePrioriteChainee.supprimer(uneListe)); 

	}

	@Test
	public void testSupprimerInt() {
		
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt5);
		listePrioriteChainee.ajouter(elt6);		
		assertNull(listePrioriteChainee.supprimer(4));	
		listePrioriteChainee.supprimer(3);
		assertTrue(listePrioriteChainee.estVide());
		listePrioriteChainee.ajouter(elt1);	
		listePrioriteChainee.ajouter(elt2);	
		listePrioriteChainee.ajouter(elt4);			
		assertEquals(elt4,(listePrioriteChainee.supprimer(8)).get(0));
		
	}

	@Test
	public void testSupprimerIntBoolean() {
		
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt5);
		listePrioriteChainee.ajouter(elt6);
		listePrioriteChainee.ajouter(elt1);		
		assertTrue(listePrioriteChainee.supprimer(3, false).get(0).equals(elt1));
		listePrioriteChainee.supprimer(10, true);		
		assertTrue(listePrioriteChainee.estVide());
		
	}

	@Test
	public void testRemplacer() {
		
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt5);
		listePrioriteChainee.ajouter(elt6);
		listePrioriteChainee.ajouter(elt1);		
		assertTrue(listePrioriteChainee.remplacer(3, 5));
		assertFalse(listePrioriteChainee.remplacer(15, 5));
		assertEquals(3, listePrioriteChainee.obtenirNbElments(5));
		assertEquals(4, listePrioriteChainee.obtenirNbElments());
		
	}

	@Test
	public void testContient() {
		
		listePrioriteChainee.ajouter(elt1);
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt4);
		listePrioriteChainee.ajouter(elt7);
		assertTrue(listePrioriteChainee.contient(elt7));
		assertFalse(listePrioriteChainee.contient(elt5));
		listePrioriteChainee.supprimer(elt7);
		assertFalse(listePrioriteChainee.contient(elt7));

	}

	@Test
	public void testObtenirNbElmentsInt() {
		
		listePrioriteChainee.ajouter(elt6);
		listePrioriteChainee.ajouter(elt5);
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt1);		
		assertEquals(1, listePrioriteChainee.obtenirNbElments(7));
		listePrioriteChainee.supprimer(elt5);
		assertEquals(2, listePrioriteChainee.obtenirNbElments(3));
		
	}

	@Test
	public void testObtenirNbElments() {
		
		listePrioriteChainee.ajouter(elt6);
		listePrioriteChainee.ajouter(elt1);
		listePrioriteChainee.ajouter(elt3);
		listePrioriteChainee.ajouter(elt2);
		assertEquals(4,listePrioriteChainee.obtenirNbElments() );
		listePrioriteChainee.supprimer(elt6);
		assertEquals(3,listePrioriteChainee.obtenirNbElments() );

	}

	@Test
	public void testEstVide() {
		
		assertTrue(listePrioriteChainee.estVide());
		listePrioriteChainee.ajouter(elt6);
		assertFalse(listePrioriteChainee.estVide());
		listePrioriteChainee.supprimer(elt6);
		assertTrue(listePrioriteChainee.estVide());
	}

}
