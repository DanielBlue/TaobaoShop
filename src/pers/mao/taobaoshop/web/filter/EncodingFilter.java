package pers.mao.taobaoshop.web.filter;

import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/admin/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletRequest encodingRequest = (HttpServletRequest) Proxy.newProxyInstance(
                request.getClass().getClassLoader(),
                request.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String name = method.getName();
                        if ("getParameter".equals(name)) {
                            String invoke = (String) method.invoke(request, args);
                            if (invoke!=null){
                                invoke = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
                                return invoke;
                            }
                        }
                        return method.invoke(request, args);
                    }
                });

        chain.doFilter(encodingRequest, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
