package pro.sky.homeworkStreamAPI;

import java.util.Objects;

public class Employee {
    private String fullname;
    private double salary;
    private int department;

    public Employee(String fullname, int department, double salary) {
        this.fullname = fullname;
        this.department = department;
        this.salary = salary;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullname='" + fullname + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(fullname, employee.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname);
    }
}
