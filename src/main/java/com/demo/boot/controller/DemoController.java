package com.demo.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.boot.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    DemoService demoService;

    @RequestMapping("{name}")
    public ModelAndView hello(@PathVariable("name") String name) {
        return new ModelAndView("hello", "name", name);
    }

    @RequestMapping("find")
    public String findJdbc() {
        return JSONObject.toJSONString(demoService.findJdbc());
    }

    @RequestMapping("find_m")
    public String findMapper() {
        return JSONObject.toJSONString(demoService.findMapper());
    }
}
