package pers.mao.taobaoshop.dao;

import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.SelectLimitVo;

import java.util.List;

public interface OrderDao {

    List<OrderBean> selectOrderBeanByOid(SelectLimitVo limitVo);

    int getTotalCount(String oid);
}
