package pers.mao.taobaoshop.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CheckLoginFilter", urlPatterns = "/admin/*")
public class CheckLoginFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        if (!(uri.equals("/admin/")||uri.equals("/admin/index.jsp")||uri.equals("/admin/login"))) {
            if (session.getAttribute("admin") == null) {
                response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
