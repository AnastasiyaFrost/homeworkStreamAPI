package pro.sky.homeworkStreamAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/add")
    public String add(@RequestParam("surname") String surname, @RequestParam("name") String name,
                      @RequestParam("lastname") String lastname,
                      @RequestParam("department") int department, @RequestParam("salary") int salary) {
        add(surname, name, lastname, department, salary);
        return "Сотрудник добавлен";
    }

    @GetMapping(path = "/employee/remove")
    public List<Employee> remove(@RequestParam("surname") String surname, @RequestParam("name") String name,
                                 @RequestParam("lastname") String lastname) {
        return remove(surname, name, lastname);
    }
    @GetMapping(path = "/employee/countSalarySum")
    public int countSalarySum() {
        return countSalarySum();
    }
    @GetMapping(path = "/employee/findEmployeeWithMinSalary")
    public Optional<Employee> findEmployeeWithMinSalary() {
        return findEmployeeWithMinSalary();
    }
}
