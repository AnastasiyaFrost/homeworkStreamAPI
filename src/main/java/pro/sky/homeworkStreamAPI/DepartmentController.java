package pro.sky.homeworkStreamAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController (DepartmentService departmentService) {

        this.departmentService = departmentService;
    }
@GetMapping(path = "/departments/max-salary")
    public void findMaxSalaryByDepart(@RequestParam int departmentId) {
        EmployeeService.findMaxSalaryByDepartment(departmentId);
    }
    @GetMapping(path = "/departments/min-salary")
    public void findMinSalaryByDepart(@RequestParam int departmentId) {
        EmployeeService.findMinSalaryByDepartment(departmentId);
    }
    @GetMapping(path = "/departments/all")
    public List<Employee> printByDepartment(@RequestParam int departmentId) {
        EmployeeService.printByDepartment(departmentId);
        return List < Employee >;
    }
    @GetMapping(path = "/departments/all")
    public Map<int, List<Employee>> printAllByDepartments() {
        EmployeeService.printByDepartment();
        return Map<int, List<Employee>>;
    }
}
