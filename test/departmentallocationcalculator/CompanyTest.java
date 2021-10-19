/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

import java.util.ArrayList;
import java.util.List;
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
public class CompanyTest {
    Department ldr, dep1; 
    Person ceo, mgr1, mgr2;
    Person[] mgrsArray;
    Department dep2;
     Company instance;
    public CompanyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ceo = new Person("ceo", Person.Role.MANAGER);
        mgrsArray = new Person[]{
        mgr1 = new Person("manager 1", Person.Role.MANAGER),
        mgr2 = new Person("manager 2", Person.Role.MANAGER)};
        ldr = new Department("leadership", new Person[]{ceo});
        dep1 = new Department("department 1", mgrsArray);
        instance = new Company("company", ldr, ceo);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDepartments method, of class Company.
     */
    @Test
    public void testAddDepartment_getDepartments() {
        System.out.println("addDepartments and getDepartments");
        Department[] _departments = new Department[]{dep1};
        List<Department> result = instance.getDepartments();
        
        System.out.println("Setup added the leadership department");
        assertEquals(instance.getDepartments().size(), 1);
        assertEquals(result.get(0).getName(), "leadership");
        instance.addDepartment(_departments);
        assertEquals(instance.getDepartments().size(), 2);
        result = instance.getDepartments();
        assertEquals(result.get(1).getName(), "department 1");
        assertEquals(result.get(1).getManagers().size(), 2);
    }

    /**
     * Test of addPersons method, of class Company.
     */
    @Test
    public void testAddPersons() {
        System.out.println("addPersons");
        Person[] _persons = new Person[]{mgr1, mgr2};
        instance.addPersons(_persons);
        assertEquals(instance.getRoster().size(), 3);
        assertTrue(instance.getRoster().contains(mgr1));
        assertTrue(instance.getRoster().contains(mgr2));
        assertEquals(instance.getRoster().get(1).getName(), "manager 1");
        assertEquals(instance.getRoster().get(2).getName(), "manager 2");

    }

    /**
     * Test of Allocation method, of class Company.
     * Test 1 Manager with no reports
   */
    @Test
    public void testAllocation_1_mgr_no_reports() {
        System.out.println("Allocation");
        int _personId = mgr1.getId();
        Person[] _persons = new Person[]{mgr1, mgr2};
        instance.addPersons(_persons);
        int expResult = 600;
        int result = instance.Allocation(_personId);
        assertEquals(expResult, result);
    } 
    
     /**
     * Test of Allocation method, of class Company.
     * Test 1 Manager with 1 reporting Mgr
    */
    @Test
    public void testAllocation_1_mgr_and_1_mgr_reportee() {
        System.out.println("Allocation");
        Person mgr3 = new Person("manager 3", Person.Role.MANAGER, mgr1.getId());
        Person[] _persons = new Person[]{mgr1, mgr3};
        instance.addPersons(_persons);
        int _personId = mgr1.getId();
        int expResult = 1200;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);
        
    }  
    
    /**
     * Test of Allocation method, of class Company.
     * Test 3 levels 1 Manager with 1 manager reporting and another manager 
     * reporting to the mid-level manager.
    */
    @Test
    public void testAllocation_1_mgr_and_1_mgr_reportee_with_1_mgr_reportee() {
        System.out.println("Allocation");
        Person mgr3 = new Person("manager 3", Person.Role.MANAGER, mgr1.getId());
        Person mgr4 = new Person("manager 4", Person.Role.MANAGER, mgr3.getId());
        Person[] _persons = new Person[]{mgr1, mgr3, mgr4};
        instance.addPersons(_persons);
        int _personId = mgr1.getId();
        int expResult = 1800;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);      
    } 
    
    /**
     * Test of Allocation method, of class Company.
     * Test 3 levels 1 Manager with 1 manager reporting and another manager 
     * reporting to the mid-level manager.
    */
    @Test
    public void testAllocation_1_mgr_and_2_mgr_reportees() {
        System.out.println("Allocation");
        Person mgr3 = new Person("manager 3", Person.Role.MANAGER, mgr1.getId());
        Person mgr4 = new Person("manager 4", Person.Role.MANAGER, mgr1.getId());
        Person[] _persons = new Person[]{mgr1, mgr3, mgr4};
        instance.addPersons(_persons);
        int _personId = mgr1.getId();
        int expResult = 1800;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);      
    }  
    
     /**
     * Test of Allocation method, of class Company.
     * Test Scenario Manager A
     *                  Manager B
     *                      Developer
     *                      QA Tester
     * reporting to the mid-level manager.
    */
    @Test
    public void testAllocation_Example_Scenario_manager_A() {
        System.out.println("Example Scenario Manager A");
        Person mgrA = new Person("manager A", Person.Role.MANAGER);
        Person mgrB = new Person("manager B", Person.Role.MANAGER, mgrA.getId());
        Person dev1 = new Person("developer 1", Person.Role.DEVELOPER, mgrB.getId());
        Person qa_1 = new Person("qa tester 1", Person.Role.QATESTER, mgrB.getId());
        Person[] _persons = new Person[]{mgrA, mgrB, dev1, qa_1};
        instance.addPersons(_persons);
        int _personId = mgrA.getId();
        int expResult = 4200;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);      
    }
    
    /**
     * Test of Allocation method, of class Company.
     * Test Scenario Manager C
     *                  Manager D
     * 
     * 
    */
    @Test
    public void testAllocation_Example_Scenario_manager_C() {
        System.out.println("Example Scenario Manager C");
        Person mgrC = new Person("manager C", Person.Role.MANAGER);
        Person mgrD = new Person("manager D", Person.Role.MANAGER, mgrC.getId());
        Person[] _persons = new Person[]{mgrC, mgrD};
        instance.addPersons(_persons);
        int _personId = mgrC.getId();
        int expResult = 1200;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);      
    }

    
    /**
     * Test of Allocation method, of class Company.
     * Test Scenario Manager E
     *                  Developer
    */
    @Test
    public void testAllocation_Example_Scenario_manager_E() {
        System.out.println("Example Scenario Manager E");
        Person mgrE = new Person("manager E", Person.Role.MANAGER);
        Person dev2 = new Person("developer 2", Person.Role.DEVELOPER, mgrE.getId());
        Person[] _persons = new Person[]{mgrE, dev2};
        instance.addPersons(_persons);
        int _personId = mgrE.getId();
        int expResult = 2600;
         int result = instance.Allocation(_personId);
        assertEquals(expResult, result);      
    }
    
    
    
    /**
     * Test of ListManagersWithoutReports method, of class Company.
    */
    @Test
    public void testListManagersWithoutReports() {
        System.out.println("ListManagersWithoutReports");
        Person[] _persons = new Person[]{mgr1, mgr2};
        instance.addPersons(_persons);
        assertEquals(instance.getRoster().size(), 3);
        assertTrue(instance.getRoster().contains(mgr1));
        assertTrue(instance.getRoster().contains(mgr2));
        assertEquals(instance.getRoster().get(1).getName(), "manager 1");
        assertEquals(instance.getRoster().get(2).getName(), "manager 2");
        instance.ListManagersWithoutReports();       
    }
    
        @Test
        public void testListManagersWithoutReports_1withReportee() {
        System.out.println("ListManagersWithoutReports with Reportee");
        Person[] _persons = new Person[]{mgr1, mgr2};
        Person reportee = new Person("reportee", Person.Role.QATESTER, mgr1.getId());
        instance.addPersons(_persons);
        instance.addPersons(reportee);
        assertEquals(instance.getRoster().size(), 4);
        assertTrue(instance.getRoster().contains(mgr1));
        assertTrue(instance.getRoster().contains(mgr2));
        assertTrue(instance.getRoster().contains(reportee));
        assertEquals(instance.getRoster().get(1).getName(), "manager 1");
        assertEquals(instance.getRoster().get(2).getName(), "manager 2");
        assertEquals(instance.getRoster().get(2).getRole(), Person.Role.MANAGER);        assertEquals(instance.getRoster().get(3).getName(), "reportee");
        assertEquals(instance.getRoster().get(3).getRole(), Person.Role.QATESTER);
        instance.ListManagersWithoutReports();       
    } 

    /**
     * Test of getName method, of class Company.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String expResult = "company";
        String result = instance.getName();
        assertEquals(expResult, result);      
    }

    /**
     * Test of DepartmentAllocation method, of class Company.
     */
    @Test
    public void testDepartmentAllocation() {
        System.out.println("DepartmentAllocation");
        int expResult = 600;
        int result = instance.DepartmentAllocation(ldr);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of DepartmentAllocation method, of class Company.
     * Example Scenario
     * Given hierarchy
     *          Manager A
     *              Manager B
     *                  Developer
     *                  QA Tester
     *          Manager C
     *              Manager D
     *          Manager E
     *              Developer
     */
    @Test
    public void testDepartmentAllocation_Example_Scenario() {
        System.out.println("===========================================");
        System.out.println("DepartmentAllocation Example Scenario");
        System.out.println("BEGIN");
        System.out.println("Example Scenario Manager A");
        Person mgrA = new Person("manager A", Person.Role.MANAGER);
        Person mgrB = new Person("manager B", Person.Role.MANAGER, mgrA.getId());
        Person dev1 = new Person("developer 1", Person.Role.DEVELOPER, mgrB.getId());
        Person qa_1 = new Person("qa tester 1", Person.Role.QATESTER, mgrB.getId());
        Person[] _personsMgrA = new Person[]{mgrA, mgrB, dev1, qa_1};
        instance.addPersons(_personsMgrA);
        
        System.out.println("Example Scenario Manager C");
        Person mgrC = new Person("manager C", Person.Role.MANAGER);
        Person mgrD = new Person("manager D", Person.Role.MANAGER, mgrC.getId());
        Person[] _personsMgrC = new Person[]{mgrC, mgrD};
        instance.addPersons(_personsMgrC);
        
        System.out.println("Example Scenario Manager E");
        Person mgrE = new Person("manager E", Person.Role.MANAGER);
        Person dev2 = new Person("developer 2", Person.Role.DEVELOPER, mgrE.getId());
        Person[] _personsMgrE = new Person[]{mgrE, dev2};
        instance.addPersons(_personsMgrE);
        
        Person[] mgrsArray = new Person[]{mgrA, mgrC, mgrE};
        Department ex_Scenario_department = new Department("Example Scenario Department", mgrsArray);
        instance.addDepartment(ex_Scenario_department);
        int expResult = 8000;
        int result = instance.DepartmentAllocation(ex_Scenario_department);
        assertEquals(expResult, result);
        
        
        System.out.println("ListManagersWithoutReports with Reportee");
        instance.ListManagersWithoutReports();    
         
        System.out.println("END");
        System.out.println("===========================================");
    }

    /**
     * Test of getRoster method, of class Company.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        List<Person> expResult = new ArrayList<>();
        expResult.add(ceo);
        List<Person> result = instance.getRoster();
        assertEquals(expResult, result);
    }
    
}
