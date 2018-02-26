package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String currentPageStr = request.getParameter("currentPage");
//        String oid = request.getParameter("oid");
//
//        int currentPage = Integer.parseInt(currentPageStr);
//        int count = 5;
//
//        OrderService orderService = new OrderService();
//        PageBean<OrderBean> pageBean = null;
//
//        if (oid!=null&&!oid.isEmpty()){
//            try {
//                pageBean = orderService.getOrdersByOid(oid,currentPage,count);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            request.setAttribute("oid",oid);
//        }else {
//            try {
//                pageBean = orderService.getAllOrders(currentPage,count);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        request.setAttribute("pageBean", pageBean);
//        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }
}
