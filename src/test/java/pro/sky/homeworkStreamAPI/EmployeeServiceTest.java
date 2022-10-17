package pro.sky.homeworkStreamAPI;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;



class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeService();

    private Employee addOneWithCheck() {
        Employee testEmpl = new Employee("Kozloff", "Stepan", "Leonidovich",
                1, 16_352);
        out.add(testEmpl.getSurname(), testEmpl.getName(), testEmpl.getLastname(),
                testEmpl.getDepartment(), testEmpl.getSalary());
        Assertions.assertThat(out.findAll())
                .isNotEmpty()
                .contains(testEmpl);
        Assertions.assertThat(out.find(testEmpl.getSurname(), testEmpl.getName(),
                testEmpl.getLastname()).equals(testEmpl));
        return testEmpl;
    }
    private Employee addOneWithCheck(String surname, String name, String lastname) {
        Employee testEmpl = new Employee(surname, name, lastname,
                1, 16_352);
        out.add(testEmpl.getSurname(), testEmpl.getName(), testEmpl.getLastname(),
                testEmpl.getDepartment(), testEmpl.getSalary());
        Assertions.assertThat(out.findAll())
                .isNotEmpty()
                .contains(testEmpl);
        Assertions.assertThat(out.find(testEmpl.getSurname(), testEmpl.getName(),
                testEmpl.getLastname()).equals(testEmpl));
        return testEmpl;
    }

    @AfterEach
    public void afterEach() {
        out.findAll().forEach(employee -> out.remove(employee.getSurname(),
                employee.getName(), employee.getLastname()));
    }

    @Test
    void addPositiveTest() {
        addOneWithCheck();
    }

    @ParameterizedTest
    @MethodSource("addNegativeParams")
    void addNegativeTest(String surname, String name, String lastname, Class<Throwable> expectedExceptionType) {


        Assertions.assertThatExceptionOfType(expectedExceptionType)
                .isThrownBy(() -> out.add(surname, name, lastname, 1, 22_736));
    }

    public static Stream<Arguments> addNegativeParams() {
        return Stream.of(
                Arguments.of("Petr0v", "Petr", "Ivanovich", InvalidInputException.class),
                Arguments.of("Petrov-Ivanov1", "Petr", "Ivanovich", InvalidInputException.class),
                Arguments.of("Petrov", "Pe1r", "Ivanovich", InvalidInputException.class),
                Arguments.of("Petrov", "Pe#r", "Ivanovich", InvalidInputException.class),
                Arguments.of("Petrov", "", "Ivanovich", InvalidInputException.class),
                Arguments.of("Petrov", "Petr", "    ", InvalidInputException.class)
        );
    }

    @Test
    void addNegativeTest2() {
        Employee employee = addOneWithCheck();

        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddException.class)
                .isThrownBy(() -> out.add(employee.getSurname(), employee.getName(), employee.getLastname(),
                        employee.getDepartment(), employee.getSalary()));
    }

    @Test
    void findPositive() {
        Employee employee1 = addOneWithCheck("Folk", "Inna", "Ivanovna");
        Employee employee2 = addOneWithCheck("Hofffr", "Elena", "Palna");
        Assertions.assertThat(out.find(employee1.getSurname(), employee1.getName(), employee1.getLastname()))
                .isEqualTo(employee1);
        Assertions.assertThat(out.find(employee2.getSurname(), employee2.getName(), employee2.getLastname()))
                .isEqualTo(employee2);
    }
    @Test
    void findNegative() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.find( "Test", "Test", "Test"));


        addOneWithCheck("Folk", "Inna", "Ivanovna");
        addOneWithCheck("Hofffr", "Elena", "Palna");
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.find( "Test", "Test", "Test"));
    }


    @Test
    void removePositive() {
        Employee employee1 = addOneWithCheck("Folk", "Inna", "Ivanovna");
        Employee employee2 = addOneWithCheck("Hofffr", "Elena", "Palna");
        out.remove(employee1.getSurname(), employee1.getName(), employee1.getLastname());
        Assertions.assertThat(out.findAll())
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(employee2);
        out.remove(employee2.getSurname(), employee2.getName(), employee2.getLastname());
        Assertions.assertThat(out.findAll()).isEmpty();
    }
    @Test
    void removeNegative() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.remove( "Test", "Test", "Test"));


        addOneWithCheck("Folk", "Inna", "Ivanovna");
        addOneWithCheck("Hofffr", "Elena", "Palna");
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.remove( "Test", "Test", "Test"));
    }
}