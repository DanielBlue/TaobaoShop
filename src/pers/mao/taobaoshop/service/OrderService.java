package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.pojo.Order;

public interface OrderService {


    PageBean<OrderBean> getPageBeanByOid(String oid, int currentPage, int count);

    Boolean getOrderIsExistedByOid(String oid);

    void addOrder(Order order);
}
