package pro.sky.homeworkStreamAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController (DepartmentService departmentService) {

        this.departmentService = departmentService;
    }
@GetMapping(path = "/departments/max-salary")
    public Optional<Employee> findMaxSalaryByDepart(@RequestParam ("departmentId") int department) {
         return departmentService.findEmployeeWithMaxSalaryFromDepartment(department);
    }
    @GetMapping(path = "/departments/min-salary")
    public Optional<Employee> findMinSalaryByDepart(@RequestParam ("departmentId") int department) {
        return departmentService.findEmployeeWithMinSalaryFromDepartment(department);
    }
    @GetMapping(path = "/departments/all", params = "departmentId")
    public List<Employee> printByDepartment(@RequestParam ("departmentId") int department) {
        return departmentService.printByDepartment(department);

    }
    @GetMapping(path = "/departments/all")
    public Map<Integer, List<Employee>> printAllByDepartments() {
        return departmentService.printAllByDepartments();
    }
}
