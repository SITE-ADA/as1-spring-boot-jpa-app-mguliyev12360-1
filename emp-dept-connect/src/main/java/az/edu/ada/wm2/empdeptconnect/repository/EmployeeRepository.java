package az.edu.ada.wm2.empdeptconnect.repository;


import az.edu.ada.wm2.empdeptconnect.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //    Query Methods
    List<Employee> findByFirstName(String lastName);

    List<Employee> findByLastName(String lastName);


    //    Non-Native Query
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);


    //    Filter
    Page<Employee> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);



}
