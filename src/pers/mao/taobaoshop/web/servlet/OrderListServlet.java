package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String currentPageStr = request.getParameter("currentPage");
        String oid = request.getParameter("oid");
        String express_code = request.getParameter("express_code");
        String order_state = request.getParameter("order_state");

        if (order_state == null) {
            order_state = "2";
        }

        int currentPage = Integer.parseInt(currentPageStr);
        int count = 5;

        OrderService orderService = new OrderService();
        PageBean<OrderBean> pageBean = null;

        if (oid != null && !oid.isEmpty()) {
            try {
                pageBean = orderService.getOrdersByOid(oid, currentPage, count, order_state);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("oid", oid);
        } else if (express_code != null && !express_code.isEmpty()) {
            try {
                pageBean = orderService.getOrdersByExpressCode(express_code, currentPage, count, order_state);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("express_code", express_code);
        } else {
            try {
                pageBean = orderService.getAllOrders(currentPage, count, order_state);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("order_state", Integer.parseInt(order_state));
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }
}
