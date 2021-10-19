/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentallocationcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author roger
 */
public class Company {
    private String name;
    private List<Department> departments;
    private List<Person> roster;
    
    public Company(String _name, Department _department, Person _person) {
        name = _name;
        departments = new ArrayList<>();
        departments.add(_department);
        roster = new ArrayList<>();
        roster.add(_person);
    }
    
    public String getName() {
        return name;
    }
    
    public void addDepartment(Department... _departments) {
        for (Department dep : _departments) {
            departments.add(dep);
        }
    }
    
    public void addPersons(Person... _persons) {
        roster.addAll(Arrays.asList(_persons));
    }
    
    public int Allocation(int _personId) {
         Optional<Person> allocation = roster
                 .stream()
                 .filter(p -> p.getId() ==_personId)
                 .findFirst();
                 
         
         if (!allocation.isPresent()) return 0; 
         int n = allocation.get().getAllocation(); 
       if (!roster
               .stream()
               .anyMatch(p -> p.getReportsTo() == _personId)) {
                                    //.filter(p -> p.getReportsTo() == _personId);
        
        return allocation.get().getReportsTo()== 0 ? n : 0;
       
       } else {
                int allocationSum = 
                  roster
                    .stream()
                    .filter(p -> p.getReportsTo() == _personId)
                    .mapToInt(Person::getAllocation)
                    .sum();
               
                List<Person> reportees = 
                        roster
                            .stream()
                            .filter(p -> p.getReportsTo() == _personId)
                            .collect(Collectors.toList());
                int reporteesAllocation = 0;
                
                for (Person item : reportees) {
                    allocationSum += Allocation(item.getId());
                }
                        
           return allocation.get().getReportsTo() == 0 ? (n +allocationSum): (allocationSum);
       }
    }
    
    public int DepartmentAllocation(Department _department) {
        int sum = 0;
        for (Person mgr : _department.getManagers()) {
            sum += Allocation(mgr.getId());
        }
        return sum;
    }
        
    public List<Department> getDepartments() {
        return departments;
    }
    
    public List<Person> getRoster() {
        return roster;
    }
    
    public void ListManagersWithoutReports() {
        List<Person> managersNoReports = new ArrayList<>();
        List<Person> managersWithReports = new ArrayList<>();
        managersNoReports = roster.stream()
            .filter(mgr -> mgr.getRole() == Person.Role.MANAGER)
            .collect(Collectors.toList());
        
        managersNoReports
                .stream()
                .forEach( item -> {
                if(
                    roster
                        .stream()
                        .anyMatch(
                                p -> p.getReportsTo() == item.getId()
                        )
                ) {
                  managersWithReports.add(item);
                }
            });
        managersNoReports.removeAll(managersWithReports);
        
        Collections.sort(managersNoReports, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        
        managersNoReports
            .forEach(mgr -> 
                System.out.println("Id: " + mgr.getId() + ", Name: " + mgr.getName())
            );
    }
}
