package model;

import java.math.BigDecimal;

public class Employee {

    private int id;
    private String name;
    private String position;
    private String department;
    private int age;
    private BigDecimal salary;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String position, String department, int age, BigDecimal salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
