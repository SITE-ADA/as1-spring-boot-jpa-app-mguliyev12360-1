package az.edu.ada.wm2.empdeptconnect.service.impl;


import az.edu.ada.wm2.empdeptconnect.model.Employee;
import az.edu.ada.wm2.empdeptconnect.repository.EmployeeRepository;
import az.edu.ada.wm2.empdeptconnect.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    @Override
    public List<Employee> findEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }



    @Override
    public Page<Employee> findAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }



    @Override
    public Page<Employee> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable) {
        return employeeRepository.findByLastNameContainingIgnoreCase(lastName, pageable);
    }

}
