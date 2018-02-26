package pers.mao.taobaoshop.dao;

import pers.mao.taobaoshop.ov.OrderBean;

import java.util.List;

public interface OrderDao {

    List<OrderBean> selectOrderBeanByOid(String oid);

    int getTotalCount(String oid);
}
