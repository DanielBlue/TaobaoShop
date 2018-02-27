package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String date = sdf.format(new Date());
//            order.setDate(date);
//            OrderService orderService = new OrderService();
//            boolean isExisted = false;
//            try {
//                if (orderService.getOrder(oid) != null) {
//                    isExisted = true;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            if (!isExisted) {
//                try {
//                    orderService.addOrder(order);
//                    response.sendRedirect("/order/order_list?currentPage=1");
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    request.setAttribute("order", order);
//                    request.setAttribute("error_message", "添加异常,请重新提交");
//                    request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
//                }
//            }else {
//                request.setAttribute("order", order);
//                request.setAttribute("error_message", "该取货单号已存在");
//                request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
//            }
//        } else {
//            request.setAttribute("order", order);
//            request.setAttribute("error_message", "取货单号为空或者不合法，取货单号必须是12位");
//            request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
//        }
    }
}
