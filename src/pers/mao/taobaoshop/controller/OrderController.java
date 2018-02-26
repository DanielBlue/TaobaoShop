package pers.mao.taobaoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.service.OrderService;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/order_list")
    public String displayOrderList(String currentPageStr, String oid, Model model) {

        int currentPage = Integer.parseInt(currentPageStr);
        int count = 5;


        PageBean<OrderBean> pageBean = orderService.getPageBeanByOid(oid, currentPage, count);
        if (null != oid && !oid.isEmpty()) {
            model.addAttribute("oid",oid);
        }
        model.addAttribute("pageBean", pageBean);
        return "/admin/product/list";
    }
}
