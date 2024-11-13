package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/showList") //従業員一覧を出力します。
    public String showList(Model model) {

        List<Employee> employeeList = service.showList(); //ListでfindAllで全件抽出します。

        model.addAttribute("employeeList", employeeList);

        return "employee/list"; //従業員一覧を格納しフォワードします。


       }
}
