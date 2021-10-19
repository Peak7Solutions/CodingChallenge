/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

/**
 *
 * @author roger
 */
public class Person {
    
    public enum Role {
        DEVELOPER(2000), MANAGER(600), QATESTER(1000);
        
        private final int allocation; // in dollars
        
        Role(int _allocation) {
            this.allocation = _allocation;
        }
        
        private int allocation() { return allocation; }
    }
    
    private int id;
    private String name;
    private Role role;
    private int reportsTo = 0;
    private static int numberOfPersons = 0;
    
    public Person(String _name, Role _role) {
        name = _name;
        role = _role;
        id = ++numberOfPersons;
    }
    public Person(String _name, Role _role, int _reportsTo) {
        name = _name; 
        role = _role;
        reportsTo = _reportsTo;
        
        // increment number of persons
        // and assign ID number
        id = ++numberOfPersons;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAllocation() {
        return role.allocation();
    }
    
    public Role getRole() {
        return role;
    }
    
    public int getReportsTo() {
        return reportsTo;
    }
    
    public int compareTo(Person p) {
        if(getName()== "" || p.getName() == "") {
            return 0;
        }
        
        return getName().compareTo(p.getName());
    }
}
