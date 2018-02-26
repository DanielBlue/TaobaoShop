package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String oid = request.getParameter("oid");
//        String taobao_code = request.getParameter("taobao_code");
//        String express_code = request.getParameter("express_code");
//
//        OrderService orderService = new OrderService();
//        try {
//            orderService.updateOrderByOid(oid,taobao_code,express_code);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        response.sendRedirect("/order/order_list?currentPage=1");
    }
}
