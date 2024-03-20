package az.edu.ada.wm2.empdeptconnect.controller;

import az.edu.ada.wm2.empdeptconnect.model.Department;
import az.edu.ada.wm2.empdeptconnect.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }





    @GetMapping({"", "/", "/all-departments"})
    public String listDepartments(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "id,asc") String sort,
                                  @RequestParam(required = false) String name) {
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(Sort.Direction.fromString(sortParams[1]), sortParams[0])));
        Page<Department> departmentPage;

        if (name != null && !name.trim().isEmpty()) {
            departmentPage = departmentService.findByNameContainingIgnoreCasePageable(name, pageable);
        } else {
            departmentPage = departmentService.findAllDepartments(pageable);
        }

        model.addAttribute("departmentPage", departmentPage);
        model.addAttribute("sort", sort);
        model.addAttribute("nameFilter", name); // Pass the filter parameter
        return "department/index";
    }



    @GetMapping("/new")
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/new";
    }

    @PostMapping
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/departments"; // Redirects to the list view after saving
    }






    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department/update"; // Path to the form to update an existing department
        } else {
            return "redirect:/departments"; // Redirects to list view if department not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
        department.setId(id);
        departmentService.save(department);
        return "redirect:/departments"; // Redirects to the list view after update
    }



    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return "redirect:/departments"; // Redirects to the list view after deletion
    }







    @GetMapping("/search")
    public String findByNameContainingIgnoreCase(@RequestParam("name") String name, Model model) {
        List<Department> departments = departmentService.findByNameContainingIgnoreCase(name);
        model.addAttribute("departments", departments);
        return "department/list";
    }

    // Handler for listing all departments ordered by name
    @GetMapping("/ordered")
    public String findAllDepartmentsOrderedByName(Model model) {
        List<Department> departments = departmentService.findAllDepartmentsOrderedByName();
        model.addAttribute("departments", departments);
        return "department/list";
    }



    @GetMapping("/searchByName")
    public String findByName(@RequestParam("name") String name, Model model) {
        List<Department> departments = departmentService.findByName(name);
        model.addAttribute("departments", departments);
        return "department/list";
    }





}
