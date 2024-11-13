package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("")
public class AdministratorController {

    @Autowired
    private HttpSession session;

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

    @GetMapping("/")
    public String toLogin(LoginForm form, Model model){

        model.addAttribute("form",form);

        return "administrator/login";
    
    }

    @PostMapping("/login")
    public String login(LoginForm form, Model model){

        String pass1 = form.getMailAddress();
        String pass2 = form.getPassword();

        Administrator administrator = service.login(pass1, pass2);

        if( administrator == null || form.getMailAddress().isEmpty()) {
            model.addAttribute("error","メールアドレスまたはパスワードが不正です");
            return "administrator/login";
        } else{
            session.setAttribute("administratorName",administrator.getName());
        }

        return "redirect:/employee/showList";


    }
}
