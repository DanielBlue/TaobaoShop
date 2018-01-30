package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String oid = request.getParameter("oid");
        String taobao_code = request.getParameter("taobao_code");
        String express_code = request.getParameter("express_code");
        String total_price = request.getParameter("total_price");

        Order order = new Order();
        order.setOid(oid);
        order.setTaobao_code(taobao_code);
        order.setExpress_code(express_code);
        order.setTotal_price(total_price);

        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            order.setDate(date);
            OrderService orderService = new OrderService();
            boolean isExisted = false;
            try {
                if (orderService.getOrder(oid) != null) {
                    isExisted = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!isExisted) {
                try {
                    orderService.addOrder(order);
                    response.sendRedirect("/order/order_list?currentPage=1");
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("order", order);
                    request.setAttribute("error_message", "添加异常,请重新提交");
                    request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("order", order);
                request.setAttribute("error_message", "该取货单号已存在");
                request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("order", order);
            request.setAttribute("error_message", "取货单号为空或者不合法，取货单号必须是12位");
            request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
        }
    }
}
