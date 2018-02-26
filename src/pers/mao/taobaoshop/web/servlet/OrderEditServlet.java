package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String oid = request.getParameter("oid");
//        OrderService orderService = new OrderService();
//        Order order = null;
//        try {
//            order = orderService.getOrder(oid);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (order!=null){
//            request.setAttribute("order",order);
//        }
//
//        request.getRequestDispatcher("/admin/product/edit.jsp").forward(request,response);
    }
}
