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


public class DollarTest {
    
    Dollar m1 ;
    Dollar m2 ;
    Dollar m3;
    Dollar instance;
    
    @Before
    public void setUp() {
        m1 = new Dollar(25, 43);
        m2 = new Dollar(17, 35);
        instance = new Dollar(234,56);
        
    }
    
    @After
    public void tearDown() {
        m1 = null;
        m2 = null;
        instance = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDollar() {
        m3 = new Dollar (0,234);
    }
    
    @Test
    public void testToString() {
        assertNotNull(m1.toString());
    }
    @Test
    public void testToString2() {
        assertEquals("25.43$", m1.toString());
    }

    @Test
    public void testGetDollars() {
        assertEquals(234, instance.getDollars());
    }
    @Test
    public void testGetDollars2() {
        assertEquals(25, m1.getDollars());
    }
    @Test
    public void testGetDollars3() {
        assertEquals(17, m2.getDollars());
    }

    @Test
    public void testGetCents() {
        assertEquals(56, instance.getCents());
    }

    @Test
    public void testGetTotalCents() {
        assertEquals(23456, instance.getTotalCents());
    }
    @Test
    public void testGetTotalCents2() {
        assertEquals(2543, m1.getTotalCents());
    }
    @Test
    public void testGetTotalCents3() {
        assertEquals(1735, m2.getTotalCents());
    }

    @Test
    public void testAdditionner() {
        m1.additionner(m2);
        assertEquals(4278, m1.getTotalCents());
    }

    @Test
    public void testSoustraire() throws OperationInvalideException {
        m1.soustraire(m2);
        assertEquals(808, m1.getTotalCents());
    }
    @Test(expected = OperationInvalideException.class)
    public void testSoustraire2() throws OperationInvalideException {
        m2.soustraire(m1);
        assertEquals(808, m2.getCents());
    }

    @Test
    public void testPourcentage() {
        instance.pourcentage(50);
        assertEquals(11728, instance.getTotalCents());
    }

    @Test
    public void testMultiplier() {
        m2.multiplier(m1);
        assertEquals(4412105, m2.getTotalCents() );
    }
}