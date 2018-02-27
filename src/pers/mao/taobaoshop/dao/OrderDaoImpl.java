package pers.mao.taobaoshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.mao.taobaoshop.mapper.OrderMapper;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.SelectLimitVo;
import pers.mao.taobaoshop.pojo.OrderExample;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderBean> selectOrderBeanByOid(SelectLimitVo limitVo) {
        List<OrderBean> orderBeanList = orderMapper.selectOrderBeanByOid(limitVo);

        return orderBeanList;
    }

    @Override
    public int getTotalCount(String oid) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOidLike("%"+oid+"%");
        int count = orderMapper.countByExample(orderExample);
        return count;
    }
    //    public List<Order> getAllOrders() throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order order by oid desc";
//        return runner.query(sql, new BeanListHandler<Order>(Order.class));
//    }
//
//    public List<Order> getAllOrders(int currentPage, int count) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order order by oid desc limit ?,?";
//        return runner.query(sql, new BeanListHandler<Order>(Order.class), currentPage, count);
//    }
//
//    public List<Order> getOrdersByOid(String oid, int currentPage, int count) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order where oid like ? order by oid desc limit ?,?";
//        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%"+oid+"%", currentPage, count);
//    }
//
//    public List<Order> getOrdersByAcode(String alipay_code) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order where alipay_code = ? ";
//        return runner.query(sql, new BeanListHandler<Order>(Order.class), alipay_code);
//    }
//
//    public Order getOrder(String oid) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order where oid = ?";
//        return runner.query(sql, new BeanHandler<Order>(Order.class), oid);
//    }
//
//    public void updateOrderByOid(String oid, String taobao_code, String express_code) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "update product_order set taobao_code = ? , express_code = ? where oid = ?";
//        runner.update(sql, taobao_code, express_code, oid);
//    }
//
//    public int getTotalCount() throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select count(*) from product_order";
//        Long query = (Long) runner.query(sql, new ScalarHandler());
//        return query.intValue();
//    }
//
//    public int getTotalCount(String oid) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select count(*) from product_order where oid like ?";
//        Object result = runner.query(sql, new ScalarHandler(), "%"+oid+"%");
//        if (result != null) {
//            Long query = (Long) result;
//            return query.intValue();
//        }
//        return 0;
//    }
//
//    public List<Order> getOrders(String oid) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "select * from product_order where oid like ?";
//        return runner.query(sql, new BeanListHandler<Order>(Order.class), oid+"%");
//    }
//
//    public void addOrder(Order order) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "insert into product_order (oid,taobao_code,express_code,total_price,date,alipay_code) values (?,?,?,?,?,?)";
//        runner.update(sql, order.getOid(), order.getTaobao_code(), order.getExpress_code(), order.getTotal_price(), order.getDate(),order.getAlipay_code());
//    }
//
//    public void updateOrderByAcode(String alipay_code, String taobao_code, String express_code) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "update product_order set taobao_code = ? , express_code = ? where alipay_code = ?";
//        runner.update(sql, taobao_code, express_code, alipay_code);
//    }
//
//    public void deleteOrder(String oid) throws SQLException {
//        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql = "delete from product_order where oid = ?";
//        runner.update(sql,oid);
//    }
}
