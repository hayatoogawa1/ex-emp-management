package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {

        return "administrator/insert";

    }
}
