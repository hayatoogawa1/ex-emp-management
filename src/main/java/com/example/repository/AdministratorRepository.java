package com.example.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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

            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("name", administrator.getName());
            param.addValue("mail_address", administrator.getMailAddress());
            param.addValue("password", administrator.getPassword());

            template.update(insertSql, param);
        }

    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

        String findBySql = "SELECT * FROM administrators WHERE mail_address = :mailAddress AND password = :password";

        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
                .addValue("password", password);

        try {
            return template.queryForObject(findBySql, param, ADMINISTRATOR_ROW_MAPPER);
    }   catch (EmptyResultDataAccessException e) {
                    return null;
    }
}
}