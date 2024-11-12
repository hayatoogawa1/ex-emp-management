package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeSevice {

     @Autowired
    private EmployeeRepository repository;


    public List<Employee> findAll(){

        return repository.findAll();

    } 

    public Employee load(Integer id){

        return repository.load(id);
    }

    public void update(Employee employee){

        repository.update(employee);

    }


}
