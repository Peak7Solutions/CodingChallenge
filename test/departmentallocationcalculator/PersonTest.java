/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class PersonTest {
    Person developer;
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        developer = new Person("john", Person.Role.DEVELOPER);
    }
    
    @After
    public void tearDown() {
        developer = null;
    }

    /**
     * Test of getId method, of class Person.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = developer.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "john";
        String result = developer.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAllocation method, of class Person.
     */
    @Test
    public void testGetAllocation() {
        System.out.println("getAllocation");
        int expResult = 2000;
        int result = developer.getAllocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getReportsTo method, of class Person.
     */
    @Test
    public void testGetReportsTo() {
        System.out.println("getReportsTo");
        int expResult = 0;
        int result = developer.getReportsTo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
