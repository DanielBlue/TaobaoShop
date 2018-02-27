package pers.mao.taobaoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.pojo.Order;
import pers.mao.taobaoshop.service.OrderService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/order_list")
    public String displayOrderList(String currentPage, String oid, Model model) {

        int currentPageInt = Integer.parseInt(currentPage);
        int count = 5;


        if (null != oid && !oid.isEmpty()) {
            model.addAttribute("oid", oid);
        } else {
            oid = "";
        }
        PageBean<OrderBean> pageBean = orderService.getPageBeanByOid(oid, currentPageInt, count);
        model.addAttribute("pageBean", pageBean);
        return "/admin/product/list";
    }

    @RequestMapping(value = "/order/add_order")
    public String addOrder(Order order) {
        String oid = order.getOid();
        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12) {
            order.setDate(new Date());

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
            } else {
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
