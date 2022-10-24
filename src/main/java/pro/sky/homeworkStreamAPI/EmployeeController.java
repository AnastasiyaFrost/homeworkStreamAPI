package pro.sky.homeworkStreamAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("surname") String surname, @RequestParam("name") String name,
                      @RequestParam("lastname") String lastname,
                      @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return EmployeeService.add(surname, name, lastname, department, salary);
    }

    @GetMapping(path = "remove")
    public Employee remove(@RequestParam("surname") String surname, @RequestParam("name") String name,
                                 @RequestParam("lastname") String lastname) {
        return EmployeeService.remove(surname, name, lastname);
    }
    @GetMapping(path = "/countSalarySum")
    public int countSalarySum() {
        return EmployeeService.countSalarySum();
    }
}
