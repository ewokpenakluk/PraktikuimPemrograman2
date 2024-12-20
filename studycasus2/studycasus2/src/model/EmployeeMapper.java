package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {

    @Select("SELECT * FROM employees")
    List<Employee> getAllEmployees();

    @Insert("INSERT INTO employees (name, position, department, age, salary) VALUES (#{name}, #{position}, #{department}, #{age}, #{salary})")
    void addEmployee(Employee employee);

    @Update("UPDATE employees SET name=#{name}, position=#{position}, department=#{department}, age=#{age}, salary=#{salary} WHERE id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employees WHERE id=#{id}")
    void deleteEmployee(int id);
}
