package pro.sky.homeworkStreamAPI;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController (EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

public void FindMaxSalaryByDepart(@RequestParam int departmentId) {
        EmployeeService.findMaxSalaryByDepartment(employees, departmentId);
}
    public void FindMinSalaryByDepart(@RequestParam int departmentId) {
        EmployeeService.findMinSalaryByDepartment(employees, departmentId);
    }
    public void printByDepartment(@RequestParam int departmentId) {
        EmployeeService.printByDepartment(employees, departmentId);
    }
    public void printAllByDepartments() {
        EmployeeService.printByDepartment(employees);
    }
}
