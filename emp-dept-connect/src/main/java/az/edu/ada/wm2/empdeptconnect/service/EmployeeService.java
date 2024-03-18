package az.edu.ada.wm2.empdeptconnect.service;


import az.edu.ada.wm2.empdeptconnect.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee save(Employee employee);
    void delete(Long id);

    List<Employee> findByFirstName(String lastName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findEmployeesWithSalaryGreaterThan(Double salary);



    Page<Employee> findAllEmployees(Pageable pageable);


    Page<Employee> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);



}
