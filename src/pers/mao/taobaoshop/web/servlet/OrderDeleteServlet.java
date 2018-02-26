package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OrderDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        OrderService orderService = new OrderServiceImpl();
//        ProductService productService = new ProductServiceImpl();
//
//        String oid = request.getParameter("oid");
//
//        orderService.deleteOrder(oid);
//        productService.deleteProduct(oid);
//
//        response.sendRedirect("/order/order_list?currentPage=1");
    }
}
