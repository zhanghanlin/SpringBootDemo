package com.demo.boot.web.controller;

import com.demo.boot.business.SysService;
import com.demo.boot.entity.User;
import com.demo.boot.utils.CacheUtils;
import com.demo.boot.utils.StringUtils;
import com.demo.boot.utils.UserUtils;
import com.demo.boot.web.vo.Login;
import com.demo.boot.web.vo.Register;
import com.demo.boot.web.vo.menu.MenuNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

import static com.demo.boot.dict.Boot.LOGIN_FAIL_COUNT_KEY;


@RestController
public class SysController {

    static final Logger LOG = LoggerFactory.getLogger(SysController.class);

    @Resource
    SysService sysService;

    @RequestMapping("/")
    public ModelAndView index() {
        List<MenuNode> list = sysService.tree();
        return new ModelAndView("index", "perms", list);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        ModelAndView model = new ModelAndView("login");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(Login login, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }
        String username = login.getUserName();
        UsernamePasswordToken token = new UsernamePasswordToken(username, login.getPassword());
        token.setRememberMe(login.isRememberMe());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            if (StringUtils.isBlank(username) || StringUtils.isBlank(login.getPassword())) {
                LOG.info("请输入用户名或密码");
                redirectAttributes.addFlashAttribute("message", "请输入用户名或密码");
            } else {
                LOG.info("对用户[" + username + "]进行登录验证..验证开始");
                currentUser.login(token);
                LOG.info("对用户[" + username + "]进行登录验证..验证通过");
            }
        } catch (UnknownAccountException uae) {
            LOG.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            LOG.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            LOG.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            LOG.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            LOG.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            LOG.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            CacheUtils.remove(LOGIN_FAIL_COUNT_KEY + username);
            return new ModelAndView(new RedirectView("/"));
        } else {
            token.clear();
            return new ModelAndView(new RedirectView("login"));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm(Register register) {
        return new ModelAndView("register", "register", register);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(Register register, RedirectAttributes redirectAttributes) {
        try {
            if (register.isEmpty()) {
                redirectAttributes.addFlashAttribute("register", register);
                redirectAttributes.addFlashAttribute("message", "信息填写不完整");
            } else {
                sysService.register(register);
                redirectAttributes.addFlashAttribute("message", "注册成功");
                return new ModelAndView(new RedirectView("login"));
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "注册失败");
        }
        return new ModelAndView(new RedirectView("register"));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        UserUtils.clearCache();
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return new ModelAndView(new RedirectView("login"));
    }

    @RequestMapping("/403")
    public ModelAndView unauthorized() {
        return new ModelAndView("error/403");
    }

    @RequestMapping("/404")
    public ModelAndView notFound() {
        return new ModelAndView("error/404");
    }

    @RequestMapping("/500")
    public ModelAndView internalServerError() {
        return new ModelAndView("error/500");
    }
}
