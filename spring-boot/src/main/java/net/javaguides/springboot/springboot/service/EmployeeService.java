package net.javaguides.springboot.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.springboot.model.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(long id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long id);

}
