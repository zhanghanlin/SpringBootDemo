package com.demo.test;

import com.demo.boot.business.SysService;
import com.demo.boot.web.vo.menu.MenuNode;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestMenuTree extends BaseTest {

    @Resource
    SysService sysService;

    @Test
    public void menu() {
        List<MenuNode> list = sysService.tree();
        for (MenuNode m : list) {
            System.out.println(m.getName());
        }
    }
}
