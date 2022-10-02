package pro.sky.homeworkStreamAPI;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.min;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService (EmployeeService employeeService) {

        this.employeeService = employeeService;
    }




    public Optional<Employee> findMaxSalaryByDepartment(int department) {
       return employeeService.findAll().stream()
                .filter(e -> e.getDepartment()==department)
        .max(Comparator.comparingInt(employee -> employee.getSalary()));
    }

    public Optional<Employee> findMinSalaryByDepartment(int department) {
       return employeeService.findAll().stream()
                .filter(e -> e.getDepartment()==department)
        .min(Comparator.comparingInt(employee -> employee.getSalary()));
    }
    public List<Employee> printByDepartment(int department) {
        final List<Employee> employeesByDepartment = employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return employeesByDepartment;
    }

    public void printAllByDepartments() {
        for (int i = 1; i <=5; i++) {int count = i;
            System.out.println("Department "+count);
            final List<Employee> employeesByDepartments = employeeService.findAll().stream()
                    .filter(e -> e.getDepartment() == count)
                    .collect(Collectors.toList());
            System.out.println(employeesByDepartments);
        }
    }
}
