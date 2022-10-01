package pro.sky.homeworkStreamAPI;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeMath.max;

@Service
public class EmployeeService {
    List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Иванов Иван Иванович", 1, 50_000),
                new Employee("Смирнов Артем Иванович", 3, 30_000),
                new Employee("Иванов Алексей Алексеевич", 3, 150_000),
                new Employee("Сидоров Петр Петрович", 4, 45_000),
                new Employee("Васильев Андрей Степанович", 2, 56_000),
                new Employee("Смирнов Иван Иванович", 5, 60_000),
                new Employee("Потапов Иван Ирсеньевич", 2, 30_000),
                new Employee("Чижиков Олег Семенович", 3, 43_000),
                new Employee("Ежиков Мариан Владимирович", 1, 62_000),
                new Employee("Башмачкин Акакий Акакиевич", 4, 55_000)
    ));

    public static double findMaxSalaryByDepartment(List<Employee> employees, int department) {
        employees.stream()
                .filter(e -> e.getDepartment()==department);
        .max(Comparator.comparingDouble(employee -> employee.getSalary()));
        .get();
    }

    public static double findMinSalaryByDepartment(List<Employee> employees, int department) {
        employees.stream()
                .filter(e -> e.getDepartment()==department);
        .min(Comparator.comparingDouble(employee -> employee.getSalary()));
        .get();
    }
    public static List<Employee> printByDepartment(List<Employee> employees, int department) {
        final List<Employee> employeesByDepartment = employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return employeesByDepartment;
    }

    public static void printAllByDepartments(List<Employee> employees) {
        for (int i = 1; i <=5; i++) {int count = i;
            System.out.println("Department "+count);
            final List<Employee> employeesByDepartments = employees.stream()
                    .filter(e -> e.getDepartment() == count)
                    .collect(Collectors.toList());
            System.out.println(employeesByDepartments);
        }
    }
}
