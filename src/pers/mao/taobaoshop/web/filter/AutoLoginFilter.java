package pers.mao.taobaoshop.web.filter;

import pers.mao.taobaoshop.domain.Admin;
import pers.mao.taobaoshop.service.AdminService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "AutoLoginFilter",urlPatterns = "/admin/*")
public class AutoLoginFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        AdminService service = new AdminService();


        String cookie_name = null;
        String cookie_password = null;

        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length>0){
            for (Cookie c : cookies) {
                if (c.getName().equals("cookie_name")){
                    cookie_name = c.getValue();
                }

                if (c.getName().equals("cookie_password")){
                    cookie_password = c.getValue();
                }
            }

            if (cookie_name!=null&&cookie_password!=null){
                try {
                    Admin admin = service.login(cookie_name,cookie_password);
                    session.setAttribute("admin",admin);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
