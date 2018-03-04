package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.service.OrderService;
import pers.mao.taobaoshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String currentPageStr = request.getParameter("currentPage");
        String oid = request.getParameter("oid");
        String express_code = request.getParameter("express_code");

        int currentPage = Integer.parseInt(currentPageStr);
        int count = 5;

        OrderService orderService = new OrderService();
        PageBean<OrderBean> pageBean = null;

        if (oid!=null&&!oid.isEmpty()){
            try {
                pageBean = orderService.getOrdersByOid(oid,currentPage,count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("oid",oid);
        }else if (express_code!=null&&!express_code.isEmpty()){
            try {
                pageBean = orderService.getOrdersByExpressCode(express_code,currentPage,count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("express_code",express_code);
        }else {
            try {
                pageBean = orderService.getAllOrders(currentPage,count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }
}
