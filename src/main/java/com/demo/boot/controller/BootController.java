package com.demo.boot.controller;

import com.demo.boot.zxing.ZXingUtil;
import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("boot")
public class BootController {

    @RequestMapping({"", "/"})
    public ModelAndView boot() {
        return new ModelAndView("boot");
    }

    @RequestMapping("code")
    public void code(String cont, HttpServletResponse response) {
        try {
            ZXingUtil.encode(cont, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
