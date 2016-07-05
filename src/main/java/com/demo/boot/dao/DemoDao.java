package com.demo.boot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DemoDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    public List find() {
        String sql = "select * from c_product";
        return jdbcTemplate.queryForList(sql);
    }
}
