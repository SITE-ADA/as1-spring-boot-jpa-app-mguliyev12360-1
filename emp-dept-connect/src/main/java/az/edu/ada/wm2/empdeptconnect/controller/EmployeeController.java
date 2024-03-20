package az.edu.ada.wm2.empdeptconnect.controller;


import az.edu.ada.wm2.empdeptconnect.model.Department;
import az.edu.ada.wm2.empdeptconnect.model.Employee;
import az.edu.ada.wm2.empdeptconnect.service.DepartmentService;
import az.edu.ada.wm2.empdeptconnect.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }




    @GetMapping({"", "/", "/all-employees"})
    public String listEmployees(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "id,asc") String sort,
                                @RequestParam(required = false) String lastName) {
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(Sort.Direction.fromString(sortParams[1]), sortParams[0])));

        Page<Employee> employeePage;
        if (lastName != null && !lastName.isEmpty()) {

            employeePage = employeeService.findByLastNameContainingIgnoreCase(lastName, pageable);
        } else {
            employeePage = employeeService.findAllEmployees(pageable);
        }

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("sort", sort);
        model.addAttribute("lastNameFilter", lastName);
        return "employee/index";
    }









    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/new";
    }


    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, @RequestParam("departmentId") Long departmentId, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "employee/new";
        }

        Department department = departmentService.getById(departmentId);
        if (department != null) {
            employee.setDepartment(department);
        } else {
            // If no department is found for the ID, handle the error.
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid department ID.");
            return "redirect:/employees/new";
        }

        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee created successfully.");
        return "redirect:/employees";
    }










    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employee/update";
        } else {
            return "redirect:/employees";
        }
    }



    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        employeeService.save(employee);
        return "redirect:/employees";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }







    @GetMapping("/search/by-first-name")
    public String findByFirstName(@RequestParam("firstName") String firstName, Model model) {
        List<Employee> employees = employeeService.findByFirstName(firstName);
        model.addAttribute("employees", employees);
        return "employee/info";
    }


    @GetMapping("/search/by-last-name")
    public String findByLastName(@RequestParam("lastName") String lastName, Model model) {
        List<Employee> employees = employeeService.findByLastName(lastName);
        model.addAttribute("employees", employees);
        return "employee/info";
    }




    @GetMapping("/search/by-salary-greater-than")
    public String findEmployeesWithSalaryGreaterThan(@RequestParam("salary") Double salary, Model model) {
        List<Employee> employees = employeeService.findEmployeesWithSalaryGreaterThan(salary);
        model.addAttribute("employees", employees);
        return "employee/info";
    }






}
