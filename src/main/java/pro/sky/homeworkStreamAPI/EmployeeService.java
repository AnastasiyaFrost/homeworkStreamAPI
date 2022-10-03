package pro.sky.homeworkStreamAPI;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>(Arrays.asList(
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

    public Collection<Employee> findAll(){
        return new ArrayList<>(employees);
    }
}
