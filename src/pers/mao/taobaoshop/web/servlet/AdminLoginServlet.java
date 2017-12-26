package pers.mao.taobaoshop.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.sun.corba.se.spi.ior.IdentifiableFactory;
import net.sf.json.JSONObject;
import pers.mao.taobaoshop.domain.Admin;
import pers.mao.taobaoshop.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = null;
        AdminService service = new AdminService();
        HttpSession session = request.getSession();

        String adminName = request.getParameter("username");
        String adminPassword = request.getParameter("password");


        try {
            admin = service.login(adminName, adminPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (admin != null) {
            String autologin = request.getParameter("autologin");
            if (autologin != null) {
                Cookie cookie_name = new Cookie("cookie_name",adminName);
                Cookie cookie_password = new Cookie("cookie_password",adminPassword);

                cookie_name.setMaxAge(60*60);
                cookie_password.setMaxAge(60*60);

                cookie_name.setPath(request.getContextPath());
                cookie_password.setPath(request.getContextPath());

                response.addCookie(cookie_name);
                response.addCookie(cookie_password);
            }

            session.setAttribute("admin",admin);

            response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
        }else {
            request.setAttribute("loginInfo","账号或密码错误");
            request.getRequestDispatcher(request.getContextPath()+"/admin/index.jsp").forward(request,response);
            return;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
