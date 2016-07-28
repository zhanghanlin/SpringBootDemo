package com.demo.boot.controller;

import com.demo.boot.util.image.zxing.ZXingUtil;
import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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


    static Map<String, String> map = new HashMap<>();

    @RequestMapping("saveBase64")
    @ResponseBody
    public String saveBase64(String base) {
        String uuid = UUID.randomUUID().toString();
        base = base.split("base64,")[1];
        map.put(uuid, base);
        return uuid;
    }

    @RequestMapping("base642jpeg")
    public void base642jpeg(String uuid, HttpServletResponse response) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String base = map.get(uuid);
            byte[] bytes = decoder.decodeBuffer(base);
            ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(stream);
            ImageIO.write(bufferedImage, "JPG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}