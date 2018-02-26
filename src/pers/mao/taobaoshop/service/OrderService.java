package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;

public interface OrderService {


    PageBean<OrderBean> getPageBeanByOid(String oid, int currentPage, int count);
}
