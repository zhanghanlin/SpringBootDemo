package com.demo.boot.service;

import com.demo.boot.dao.DemoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    DemoDao demoDao;

    public List find() {
        return demoDao.find();
    }
}
