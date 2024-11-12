package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {

        return "administrator/insert";

    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        
        BeanUtils.copyProperties(form, administrator);

        service.insert(administrator);

        
        return"redirect:/";

    }
}
