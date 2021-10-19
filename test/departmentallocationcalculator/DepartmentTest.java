/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

import java.util.List;
import java.util.Arrays;
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
public class DepartmentTest {
    
    public DepartmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Department.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Department instance = new Department("Engineering", new Person("jeff", Person.Role.MANAGER));
        String expResult = "Engineering";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getManagers method, of class Department.
     */
    @Test
    public void testGetManagers() {
        System.out.println("getManagers");
        Person jeff = new Person("jeff", Person.Role.MANAGER);
        Person joe = new Person("joe", Person.Role.MANAGER);
        Department instance = new Department(
                                        "Engineering", 
                                        jeff, 
                                        joe);
        List<Person> expResult;
        Person[] expResultArray = new Person[] {jeff, joe};
        expResult = Arrays.asList(expResultArray);
        List<Person> result = instance.getManagers();
        assertEquals(result.size(), 2);
        assertEquals(expResult, result);
    }
    
}
