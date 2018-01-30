package pers.mao.taobaoshop.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.ov.PageBean;
import pers.mao.taobaoshop.utils.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    public List<Order> getAllOrders() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order order by id desc";
        return runner.query(sql, new BeanListHandler<Order>(Order.class));
    }

    public List<Order> getAllOrders(int currentPage, int count) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order order by id desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), currentPage, count);
    }

    public List<Order> getOrdersByOid(String oid, int currentPage, int count) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid like ? order by id desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%"+oid+"%", currentPage, count);
    }

    public Order getOrder(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid = ?";
        return runner.query(sql, new BeanHandler<Order>(Order.class), oid);
    }

    public void updateOrder(String oid, String taobao_code, String express_code) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product_order set taobao_code = ? , express_code = ? where oid = ?";
        runner.update(sql, taobao_code, express_code, oid);
    }

    public int getTotalCount() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order";
        Long query = (Long) runner.query(sql, new ScalarHandler());
        return query.intValue();
    }

    public int getTotalCount(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where oid like ?";
        Object result = runner.query(sql, new ScalarHandler(), "%"+oid+"%");
        if (result != null) {
            Long query = (Long) result;
            return query.intValue();
        }
        return 0;
    }

    public List<Order> getOrders(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid like ?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), oid+"%");
    }

    public void addOrder(Order order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into product_order (oid,taobao_code,express_code,total_price,date) values (?,?,?,?,?)";
        runner.update(sql, order.getOid(), order.getTaobao_code(), order.getExpress_code(), order.getTotal_price(), order.getDate());
    }
}
