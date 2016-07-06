package com.demo.boot.service;

import com.demo.boot.dao.jdbc.DemoDao;
import com.demo.boot.dao.mapper.DemoMapper;
import com.demo.boot.model.Demo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    DemoDao demoDao;

    @Resource
    DemoMapper demoMapper;

    public List<Demo> findJdbc() {
        return demoDao.find();
    }

    public List<Demo> findMapper() {
        return demoMapper.find();
    }
}
