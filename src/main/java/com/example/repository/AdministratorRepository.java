package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Administrator;
@Repository

public class AdministratorRepository {


    @Autowired
    public NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {

        Administrator administrator = new Administrator();

        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));

        return administrator;

    };
    
    public void insert(Administrator administrator) {
        
        if (administrator.getId() == null) {
            String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name, :mail_address, :password)";
            
            //SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
            MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", administrator.getName());
        param.addValue("mail_address", administrator.getMailAddress());
        param.addValue("password", administrator.getPassword());

            template.update(insertSql, param);
        }
        
    }
    
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        
        String findBySql = "SELECT * FROM administrators WHERE mail_address = :mailAddress AND password = :password";
        
        SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address",mailAddress).addValue("password",password);

        
        return template.queryForObject(findBySql, param, ADMINISTRATOR_ROW_MAPPER);
    }
    
}


    // public Administrator load(Integer id) {

    //     String sql = "SELECT id,name,emailAddress,password FROM administrators WHERE id=:id";
    //     SqlParameterSource param = new MapSqlParameterSource.addvalue("id", id);

    //     Administrator administrator = template.queryForObject(sql, ADMINISTRATOR_ROW_MAPPER);

    //     return administrator;
    // }

    // public List<Administrator> findAll() {
    //     String sql = "SELECT * FROM administrators";
    //     return template.query(sql,administratorRowMapper);

    // }
    // public Administrator load(Integer id) {

    //     String sql = "SELECT id,name,emailAddress,password FROM administrators WHERE id=:id";
    //     SqlParameterSource param = new MapSqlParameterSource.addvalue("id", id);

    //     Administrator administrator = template.queryForObject(sql, ADMINISTRATOR_ROW_MAPPER);

    //     return administrator;
    // }

    // public List<Administrator> findAll() {
    //     String sql = "SELECT * FROM administrators";
    //     return template.query(sql,administratorRowMapper);

    // }
    // public Administrator load(Integer id) {

    //     String sql = "SELECT id,name,emailAddress,password FROM administrators WHERE id=:id";
    //     SqlParameterSource param = new MapSqlParameterSource.addvalue("id", id);

    //     Administrator administrator = template.queryForObject(sql, ADMINISTRATOR_ROW_MAPPER);

    //     return administrator;
    // }

    // public List<Administrator> findAll() {
    //     String sql = "SELECT * FROM administrators";
    //     return template.query(sql,administratorRowMapper);

    // }