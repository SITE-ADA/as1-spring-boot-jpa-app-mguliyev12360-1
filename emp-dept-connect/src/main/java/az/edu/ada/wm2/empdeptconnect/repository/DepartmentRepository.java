package az.edu.ada.wm2.empdeptconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import az.edu.ada.wm2.empdeptconnect.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //    Query Methods
    List<Department> findByNameContainingIgnoreCase(String name);


    List<Department> findByName(String name);


    //    Non-Native Query
    @Query("SELECT d FROM Department d ORDER BY d.name")
    List<Department> findAllDepartmentsOrderedByName();


    //    Filter
    Page<Department> findByNameContainingIgnoreCase(String name, Pageable pageable);



}

