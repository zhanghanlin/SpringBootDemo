package com.demo.boot.controller.api;

import com.demo.boot.business.UserService;
import com.demo.boot.entity.User;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.vo.TablePage;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user/api")
public class UserApiController {

    @Resource
    UserService userService;

    @RequestMapping("list")
    @ResponseBody
    public TablePage<User> list(@RequestParam(defaultValue = "1", required = false) int start,
                                @RequestParam(defaultValue = "10", required = false) int length,
                                @RequestParam(value = "order[0][column]", defaultValue = "0", required = false) String column,
                                @RequestParam(value = "order[0][dir]", required = false) String dir,
                                @RequestParam(defaultValue = "1", required = false) int draw,
                                HttpServletRequest request) {
        String sort = "id";
        String order = "DESC";
        String columnName = request.getParameter("columns[" + column + "][data]");
        if (StringUtils.isNotBlank(columnName)) {
            sort = StringUtils.camelToUnderline(columnName);
        }
        if (StringUtils.isNotBlank(dir)) {
            order = dir;
        }
        Page<User> page = userService.getAllByPage((start / length) + 1, length, sort + " " + order);
        TablePage<User> tablePage = new TablePage<>(draw, page.getTotal(), page.size(), page.getResult());
        return tablePage;
    }
}
