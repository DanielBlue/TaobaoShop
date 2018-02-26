package pers.mao.taobaoshop.mapper;

import org.apache.ibatis.annotations.Param;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.pojo.Order;
import pers.mao.taobaoshop.pojo.OrderExample;

import java.util.List;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderBean> selectOrderBeanByOid(String oid);
}