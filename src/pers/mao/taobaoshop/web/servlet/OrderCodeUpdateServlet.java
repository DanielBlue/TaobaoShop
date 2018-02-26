package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OrderCodeUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        OrderService service = new OrderService();
//
//        String alipay_code = request.getParameter("alipay_code");
//        String taobao_code = request.getParameter("taobao_code");
//        String express_code = request.getParameter("express_code");
//
//        String responseStr = "更新成功";
//
//
//        try {
//            List<Order> orderList = service.getOrdersByAcode(alipay_code);
//            if (orderList != null && orderList.size() != 0) {
//                service.updateOrderByAcode(alipay_code, taobao_code, express_code);
//            }else {
//                responseStr = "载入失败\r\n失败原因：该支付宝单号在系统中不存在";
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            responseStr = "载入失败\r\n失败原因：" + e.getMessage();
//        }
//
//        response.setContentType("text/xml;charset=UTF-8");
//        response.getWriter().write(responseStr);
    }
}
