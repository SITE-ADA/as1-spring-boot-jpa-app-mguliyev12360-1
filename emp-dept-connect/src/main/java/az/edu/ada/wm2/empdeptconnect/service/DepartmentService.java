package az.edu.ada.wm2.empdeptconnect.service;

import az.edu.ada.wm2.empdeptconnect.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    Department getById(Long id);
    Department save(Department department);
    void delete(Long id);



    List<Department> findByNameContainingIgnoreCase(String name);
    List<Department> findAllDepartmentsOrderedByName();

    List<Department> findByName(String name);



    Page<Department> findAllDepartments(Pageable pageable);



    Page<Department> findByNameContainingIgnoreCasePageable(String name, Pageable pageable);





}