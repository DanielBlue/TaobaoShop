package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.service.OrderService;
import pers.mao.taobaoshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class OrderDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();

        String oid = request.getParameter("oid");

        try {
            orderService.deleteOrder(oid);
            productService.deleteProduct(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/order/order_list?currentPage=1");
    }
}
