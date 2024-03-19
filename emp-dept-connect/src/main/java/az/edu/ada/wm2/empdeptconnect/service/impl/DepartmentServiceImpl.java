package az.edu.ada.wm2.empdeptconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import az.edu.ada.wm2.empdeptconnect.model.Department;
import az.edu.ada.wm2.empdeptconnect.model.Employee;
import az.edu.ada.wm2.empdeptconnect.repository.DepartmentRepository;
import az.edu.ada.wm2.empdeptconnect.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }


    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }





    @Override
    public List<Department> findByNameContainingIgnoreCase(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }



    @Override
    public List<Department> findAllDepartmentsOrderedByName() {
        return departmentRepository.findAllDepartmentsOrderedByName();
    }






    @Override
    public Page<Department> findAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }



    @Override
    public Page<Department> findByNameContainingIgnoreCasePageable(String name, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCase(name, pageable);
    }



    @Override
    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }






}
