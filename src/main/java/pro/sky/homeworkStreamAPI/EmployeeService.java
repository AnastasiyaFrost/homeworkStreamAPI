package pro.sky.homeworkStreamAPI;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>(Arrays.asList(
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
public Employee add(String surname, String name, String lastname, int department, int salary) {
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
        Employee empl = (Employee) employees.stream().filter(e -> e.getFullname() == fullname);
        if (empl != null) {
            return  empl;
        } else throw new EmployeeNotFoundException();
}


public List<Employee> remove(String surname, String name, String lastname) {
        if(!checkName(name, surname, lastname)) {
            throw new InvalidInputException();
        }
        String fullname = surname + " " + name + " " + lastname;
    if (employees.contains(fullname)) {
        employees.remove(fullname);
        return employees;
    } else throw new EmployeeNotFoundException();
}
    private boolean checkName(String name, String surname, String lastname) {
        return isAlpha(name) && isAlpha(surname) && isAlpha(lastname);
    }


    public Collection<Employee> findAll() {
        return new ArrayList<>(employees);
    }
    public int countSalarySum() {
        int allSalarySum = 0;
        employees.stream()
                .map(e -> e.getSalary() + allSalarySum);
        return allSalarySum;
    }
    public double countAverageSalary() {
    double average = (double) countSalarySum()/employees.size();
    return average;
    }

    public Optional<Employee> findEmployeeWithMinSalary() {
        return employees.stream()
                .min(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public Optional<Employee> findEmployeeWithMaxSalary() {
        return employees.stream()
                .max(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public List<String> printAllFullNames() {
        final List<String> fullnames = employees.stream()
                .map(Employee::getFullname)
                .collect(Collectors.toList());
        return fullnames;
    }
}
