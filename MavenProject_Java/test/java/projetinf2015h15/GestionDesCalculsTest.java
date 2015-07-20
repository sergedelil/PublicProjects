/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestionDesCalculsTest {
   
    String numeroSoin1;
    String numeroSoin2;
    String numeroSoin3;
    Double montant1;
    Double montant2;
    int soin1;
    int soin2;
    
    @Before
    public void setUp() {
        numeroSoin1 = "0";
        numeroSoin2 = "100";
        numeroSoin3 = "600";
	montant1 = 234.00;
        montant2 = 200.00;
        soin1 = 175;
        soin2 = 150;
    }
    
    @After
    public void tearDown() {
        numeroSoin1 = null;
        numeroSoin2 = null;
        numeroSoin3 = null;
	montant1 = 0.00;
	montant2 = 0.00;
        soin1 = 0;
        soin2 = 0;
    }

    public void test2RembourserContratE()  {
        double resultat = GestionDesCalculs.rembourserContratE(soin1, montant1);
        double esperer = 58.50;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void testRembourserContratE()  {
        double resultat = GestionDesCalculs.rembourserContratE(soin2, montant2);
        double esperer = 30.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void test2RembourserContratD()  {
        double resultat = GestionDesCalculs.rembourserContratD(soin2, montant2);
        double esperer = 150.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void testRembourserContratD()  {
        double resultat = GestionDesCalculs.rembourserContratD(soin1, montant2);
        double esperer = 190.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void test2RembourserContratC()  {
        double resultat = GestionDesCalculs.rembourserContratC(soin1, montant1);
        double esperer = 210.60;
        assertEquals(esperer, resultat, 0);
        
    }
    @Test
    public void testRembourserContratC()  {
        double resultat = GestionDesCalculs.rembourserContratC(soin2, montant2);
        double esperer = 170.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void test2RembourserContratB()  {
        double resultat = GestionDesCalculs.rembourserContratB(soin2, montant2);
        double esperer = 0.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void testRembourserContratB()  {
        double resultat = GestionDesCalculs.rembourserContratB(soin1, montant1);
        double esperer = 175.50;
        assertEquals(esperer, resultat, 0);
        
    }
    @Test
    public void testRembourserContratA()  {
        double resultat = GestionDesCalculs.rembourserContratA(soin1, montant1);
        double esperer = 117.00;
        assertEquals(esperer, resultat, 0);
    }
    @Test
    public void test2RembourserContratA()  {
        double resultat = GestionDesCalculs.rembourserContratA(soin2, montant2);
        double esperer = 0.00;
        assertEquals(esperer, resultat, 0);
    }

    @Test
    public void testAdmetUnMontantMax() {
        assertFalse(GestionDesCalculs.admetUnMontantMax(numeroSoin1));
    }
    @Test
    public void test2AdmetUnMontantMax() {
	assertTrue(GestionDesCalculs.admetUnMontantMax(numeroSoin2));
    }
    @Test
    public void test3AdmetUnMontantMax() {
	assertTrue(GestionDesCalculs.admetUnMontantMax(numeroSoin3));
    }
    
}
