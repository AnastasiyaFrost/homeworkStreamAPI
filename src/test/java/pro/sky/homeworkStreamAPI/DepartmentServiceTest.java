package pro.sky.homeworkStreamAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;
    @BeforeEach
    public void beforeEach(){
        when(employeeService.findAll()).thenReturn(
                Arrays.asList(
                        new Employee("Pepe", "Pig", "Pig",
                                2, 37_400),
                        new Employee("Wuff", "Ann", "Marie",
                                3, 28_877),
                        new Employee("Иванов", "Иван", "Иванович",
                                2, 50_000),
                        new Employee("Смирнов", "Артем", "Иванович",
                                3, 30_000),
                        new Employee("Иванов", "Алексей", "Алексеевич",
                                3, 150_000),
                        new Employee("Сидоров", "Петр", "Петрович",
                                2, 45_000)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryTestParams")
    public void employeeWithMaxSalaryTest(int department,
                                          Employee expected){
        Assertions.assertEquals(expected, departmentService.
                findEmployeeWithMaxSalaryFromDepartment(department));
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest(){
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.findEmployeeWithMaxSalaryFromDepartment(11));
    }

    public static Stream<Arguments> employeeWithMaxSalaryTestParams(){
        return Stream.of(
                Arguments.of(2, new Employee("Иванов", "Иван",
                        "Иванович", 2, 50_000)),
                Arguments.of(3, new Employee("Иванов", "Алексей", "Алексеевич",
                        3, 150_000))
        );
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryTestParams")
    public void employeeWithMinSalaryTest(int department,
                                          Employee expected){
        Assertions.assertEquals(expected,
                departmentService.findEmployeeWithMinSalaryFromDepartment(department));
    }

    public static Stream<Arguments> employeeWithMinSalaryTestParams(){
        return Stream.of(
                Arguments.of(2, new Employee("Pepe", "Pig", "Pig",
                        2, 37_400)),
                Arguments.of(3, new Employee("Wuff", "Ann", "Marie",
                        3, 28_877))
        );
    }
    @Test
    public void employeeWithMinSalaryNegativeTest(){
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.findEmployeeWithMinSalaryFromDepartment(11));
    }


    @ParameterizedTest
    @MethodSource("printByDepartmentTestParams")
    public void printByDepartmentTest(int department,
                                          List<Employee> expected){
        Assertions.assertEquals(expected, departmentService.
                printByDepartment(department));
    }

    public static Stream<Arguments> printByDepartmentTestParams(){
        return Stream.of(
                Arguments.of(1, Collections.emptyList()),
                Arguments.of(2, Arrays.asList(
                        new Employee("Pepe", "Pig", "Pig",
                                2, 37_400),
                                new Employee("Иванов", "Иван", "Иванович",
                                        2, 50_000),
                                new Employee("Сидоров", "Петр", "Петрович",
                                        2, 45_000)
                                )
                ),
                Arguments.of(3, Arrays.asList(
                        new Employee("Wuff", "Ann", "Marie",
                                3, 28_877),
                        new Employee("Смирнов", "Артем", "Иванович",
                                3, 30_000),
                        new Employee("Иванов", "Алексей", "Алексеевич",
                                3, 150_000)
                        )
                )
        );
    }


    private Map <Integer, List<Employee>> expectResult = new HashMap<>(Map.of(
            2, List.of(new Employee("Pepe", "Pig", "Pig",
                            2, 37_400),
                    new Employee("Иванов", "Иван", "Иванович",
                            2, 50_000),
                    new Employee("Сидоров", "Петр", "Петрович",
                            2, 45_000)),
            3, List.of(new Employee("Wuff", "Ann", "Marie",
                            3, 28_877),
                    new Employee("Смирнов", "Артем", "Иванович",
                            3, 30_000),
                    new Employee("Иванов", "Алексей", "Алексеевич",
                            3, 150_000))
    )
    );
@Test
public void printAllByDepartmentsTest(Map <Integer, List<Employee>> expectResult){
        Assertions.assertEquals(expectResult,
                        departmentService.printAllByDepartments());
    }
}
