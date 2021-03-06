package com.demo.boot.web.controller.api;

import com.demo.boot.business.RoleService;
import com.demo.boot.entity.Role;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.web.vo.TablePage;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("role/api")
public class RoleApiController {

    @Resource
    RoleService roleService;

    @RequestMapping("list")
    @ResponseBody
    public TablePage<Role> list(@RequestParam(defaultValue = "1", required = false) int start,
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
        Page<Role> page = roleService.getAllByPage((start / length) + 1, length, sort + " " + order);
        TablePage<Role> tablePage = new TablePage<>(draw, page.getTotal(), page.size(), page.getResult());
        return tablePage;
    }
}
