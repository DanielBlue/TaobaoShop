package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.ov.OrderBean;
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
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();
        HttpSession session = request.getSession();
        List<Order> orderList = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        try {
            orderList = orderService.getAllOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (orderList != null && orderList.size() >= 0) {
            OrderBean orderBean;
            for (Order order : orderList) {
                try {
                    orderBean = new OrderBean();
                    orderBean.setOrder(order);
                    orderBean.setProductList(productService.getProductList(order.getOid()));
                    orderBeanList.add(orderBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            session.setAttribute("orderBeanList", orderBeanList);
        }

        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }
}
