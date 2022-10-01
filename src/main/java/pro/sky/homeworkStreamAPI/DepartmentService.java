package pro.sky.homeworkStreamAPI;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeMath.max;
@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService (EmployeeService employeeService) {

        this.employeeService = employeeService;
    }



    public Employee findMaxSalaryByDepartment(int department) {
        employees.stream()
                .filter(e -> e.getDepartment()==department);
        .max(Comparator.comparingDouble(employee -> employee.getSalary()));
        .get();
    }

    public double findMinSalaryByDepartment(int department) {
        employees.stream()
                .filter(e -> e.getDepartment()==department);
        .min(Comparator.comparingDouble(employee -> employee.getSalary()));
        .get();
    }
    public List<Employee> printByDepartment(int department) {
        final List<Employee> employeesByDepartment = employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return employeesByDepartment;
    }

    public void printAllByDepartments(List<Employee> employees) {
        for (int i = 1; i <=5; i++) {int count = i;
            System.out.println("Department "+count);
            final List<Employee> employeesByDepartments = employees.stream()
                    .filter(e -> e.getDepartment() == count)
                    .collect(Collectors.toList());
            System.out.println(employeesByDepartments);
        }
    }
}
