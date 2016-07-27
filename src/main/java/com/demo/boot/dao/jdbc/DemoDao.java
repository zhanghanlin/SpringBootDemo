package com.demo.boot.dao.jdbc;

import com.demo.boot.model.Demo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DemoDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    public List<Demo> find() {
        String sql = "select * from demo";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Demo.class));
    }
}
