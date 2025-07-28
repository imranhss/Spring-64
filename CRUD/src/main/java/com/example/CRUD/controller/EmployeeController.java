package com.example.CRUD.controller;

import com.example.CRUD.entity.Employee;
import com.example.CRUD.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String home(){

        return "home";
    }

    @GetMapping("/empForm")
    public String empForm(Model model){
        model.addAttribute("employee", new Employee());
        return "addemployee";
    }

    @PostMapping("/save")
    public  String save(@ModelAttribute Employee employee){

        employeeService.save(employee);
        return "redirect:/";

    }

    @GetMapping("")
    public String getAllEmployees(Model model){
        List<Employee> list=employeeService.getAll();
        model.addAttribute("list",list);
        return "home";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee employee=employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "addemployee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/";
    }



}
