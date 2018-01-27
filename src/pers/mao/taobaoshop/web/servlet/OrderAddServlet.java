package pers.mao.taobaoshop.web.servlet;

import pers.mao.taobaoshop.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        order.setTaobao_code(express_code);
        order.setTaobao_code(total_price);

        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            order.setDate(date);
        } else {
            request.setAttribute("order",order);
            request.setAttribute("error_message","取货单号为空或者不合法，取货单号必须是12位");
            request.getRequestDispatcher("/admin/product/add.jsp").forward(request,response);
        }
    }
}
