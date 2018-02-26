package pers.mao.taobaoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.mao.taobaoshop.ov.AdminVo;
import pers.mao.taobaoshop.pojo.Admin;
import pers.mao.taobaoshop.service.AdminService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login",method = {RequestMethod.POST})
    public String adminLogin(AdminVo adminVo, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        Admin admin = adminVo.getAdmin();
        int count = adminService.adminLogin(admin);
        if (count!=0){
            //登录成功
            String autologin = adminVo.getAutologin();
            if (autologin != null) {
                Cookie cookie_name = new Cookie("cookie_name", admin.getAdminName());
                Cookie cookie_password = new Cookie("cookie_password", admin.getAdminPassword());

                cookie_name.setMaxAge(60 * 60);
                cookie_password.setMaxAge(60 * 60);

                cookie_name.setPath(request.getContextPath());
                cookie_password.setPath(request.getContextPath());

                response.addCookie(cookie_name);
                response.addCookie(cookie_password);
            }
            session.setAttribute("admin",admin);

            return "redirect:/admin/home.jsp";
        }else {
            //登录失败
            model.addAttribute("loginInfo","账号或密码错误");
            return "/admin/index";
        }
    }
}

