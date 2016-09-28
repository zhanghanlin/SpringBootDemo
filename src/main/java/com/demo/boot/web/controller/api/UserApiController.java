package com.demo.boot.web.controller.api;

import com.demo.boot.business.UserService;
import com.demo.boot.entity.User;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.web.vo.TablePage;
import com.demo.boot.web.vo.TreeVo;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user/api")
public class UserApiController {

    @Resource
    UserService userService;

    /**
     * 用户列表
     *
     * @param start
     * @param length
     * @param column
     * @param dir
     * @param draw
     * @param request
     * @return
     */
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

    /**
     * 根据角色Id得到用户列表
     *
     * @param roleId 角色Id
     * @param inRole 是否获取属于这个角色的用户
     * @return
     */
    @RequestMapping("userRoleTree")
    @ResponseBody
    public List<TreeVo> getByRole(String roleId, boolean inRole) {
        List<TreeVo> list = Lists.newArrayList();
        List<User> userList;
        if (inRole) {
            userList = userService.getByRole(roleId);
        } else {
            userList = userService.getByNotRole(roleId);
        }
        for (User u : userList) {
            TreeVo treeVo = new TreeVo();
            treeVo.setId(u.getId());
            treeVo.setpId("0");
            treeVo.setName(u.getDisplayName());
            list.add(treeVo);
        }
        return list;
    }

    /**
     * 根据角色Id得到用户列表
     *
     * @param start
     * @param length
     * @param column
     * @param dir
     * @param draw
     * @param request
     * @return
     */
    @RequestMapping("userRole")
    @ResponseBody
    public TablePage<User> getPageByRole(String roleId,
                                         @RequestParam(defaultValue = "1", required = false) int start,
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
        Page<User> page = userService.getPageByRole(roleId, (start / length) + 1, length, sort + " " + order);
        TablePage<User> tablePage = new TablePage<>(draw, page.getTotal(), page.size(), page.getResult());
        return tablePage;
    }
}
