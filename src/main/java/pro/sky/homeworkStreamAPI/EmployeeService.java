package pro.sky.homeworkStreamAPI;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeService {
    private static final List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Иванов", "Иван", "Иванович", 1, 50_000),
            new Employee("Смирнов", "Артем", "Иванович", 3, 30_000),
            new Employee("Иванов", "Алексей", "Алексеевич", 3, 150_000),
            new Employee("Сидоров", "Петр", "Петрович", 4, 45_000),
            new Employee("Васильев", "Андрей", "Степанович", 2, 56_000),
            new Employee("Смирнов", "Иван", "Иванович", 5, 60_000),
            new Employee("Потапов", "Иван", "Ирсеньевич", 2, 30_000),
            new Employee("Чижиков", "Олег", " Семенович", 3, 43_000),
            new Employee("Ежиков ", "Мариан", " Владимирович", 1, 62_000),
            new Employee("Башмачкин", "Акакий", " Акакиевич", 4, 55_000)
    ));
public static Employee add(String surname, String name, String lastname, int department, int salary) {
    if(!checkName(name, surname, lastname)) {
        throw new InvalidInputException();
    }
    Employee employee = new Employee(surname, name, lastname, department, salary);
    if(employees.contains(employee.getFullname())) {
        throw new EmployeeAlreadyAddException();
    }
    employees.add(employee);
    return employee;
}

    public Employee find(String surname, String name, String lastname) {
        if(!checkName(name, surname, lastname)) {
            throw new InvalidInputException();
        }
        String fullname = surname + " " + name + " " + lastname;
        Employee empl = (Employee) employees.stream().filter(e -> e.getFullname().equals(fullname));
        if (empl != null) {
            return  empl;
        } else {throw new EmployeeNotFoundException();}
}


public static Employee remove(String surname, String name, String lastname) {
        if(!checkName(name, surname, lastname)) {
            throw new InvalidInputException();
        }
        String fullname = surname + " " + name + " " + lastname;
    Employee empl = (Employee) employees.stream().filter(e -> e.getFullname().equals(fullname));
    if (empl != null) {
        employees.remove(empl);
        return  empl;
    } else {throw new EmployeeNotFoundException();}

}
    private static boolean checkName(String name, String surname, String lastname) {
        return isAlpha(name) && isAlpha(surname) && isAlpha(lastname);
    }


    public Collection<Employee> findAll() {
        return new ArrayList<>(employees);
    }
    public static int countSalarySum() {
        int allSalarySum = 0;
        employees.stream()
                .map(e -> e.getSalary() + allSalarySum);
        return allSalarySum;
    }
    public double countAverageSalary() {
    double average = (double) countSalarySum()/employees.size();
    return average;
    }
}
