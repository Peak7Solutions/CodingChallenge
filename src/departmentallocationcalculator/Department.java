/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

import departmentallocationcalculator.Person;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author roger
 */
public class Department {
    private String name;
    private List<Person> managers;
    
    public Department(String name, Person... _managers) {
        this.name = name;
        managers = Arrays.asList(_managers);
    }
    
    public String getName() {
        return name;
    }
    
    public List<Person> getManagers() {
        return managers;
    }
}
