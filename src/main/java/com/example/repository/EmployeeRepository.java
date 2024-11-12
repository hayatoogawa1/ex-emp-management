package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    public NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {

        Employee employee = new Employee();

        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount"));

        return employee;

    };

    public List<Employee> findAll() {

        String findAllSql = "SELECT id, name, image, gender, hire_date, mailAddress, zipCode, address, telephone, salary, characteristics, dependentsCount FROM employees ORDER BY hire_date DESC";

        List<Employee> employeeList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);


     if (employeeList.isEmpty()) {
        return new ArrayList<>(); 
    }

        return employeeList;
    }

    public Employee load(Integer id) {

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        String loadSql = "SELECT id, name, image, gender, hire_date, mailAddress, zipCode, address, telephone, salary, characteristics, dependentsCount FROM employees WHERE id=:id";
        
        try{
        Employee employee = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);

        return employee; } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void update(Employee employee) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String updateSql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hire_date, mail_address=:mail_address, zip_code=:zip_code, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependentsCount=:dependentsCount WHERE id=:id";

        template.update(updateSql, param);
    }

}
