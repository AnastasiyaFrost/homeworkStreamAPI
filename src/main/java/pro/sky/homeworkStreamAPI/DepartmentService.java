package pro.sky.homeworkStreamAPI;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.min;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    public Optional<Employee> findEmployeeWithMaxSalaryFromDepartment(int department) {
       return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public Optional<Employee> findEmployeeWithMinSalaryFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public List<Employee> printByDepartment(int department) {
        final List<Employee> employeesByDepartment = employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return employeesByDepartment;
    }

    public Map<Integer, List<Employee>> printAllByDepartments() {
        Map<Integer, List<Employee>> tempEmplList = employeeService.findAll().stream()
                .collect(Collectors.groupingBy((Employee::getDepartment)));
        return tempEmplList;
    }
}

