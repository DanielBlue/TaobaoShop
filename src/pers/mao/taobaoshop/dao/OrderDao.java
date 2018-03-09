package pers.mao.taobaoshop.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    public List<Order> getAllOrders() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order order by oid desc";
        return runner.query(sql, new BeanListHandler<Order>(Order.class));
    }

    public List<Order> getAllOrders(int currentPage, int count) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), currentPage, count);
    }

    public List<Order> getOrdersByOid(String oid, int currentPage, int count) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid like ? order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%" + oid + "%", currentPage, count);
    }

    public List<Order> getOrdersByAcode(String alipay_code) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where alipay_code = ? ";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), alipay_code);
    }

    public Order getOrder(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid = ?";
        return runner.query(sql, new BeanHandler<Order>(Order.class), oid);
    }

    public void updateOrderByOid(String oid, String taobao_code, String express_code, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product_order set taobao_code = ? , express_code = ? , order_state = ? where oid = ?";
        runner.update(sql, taobao_code, express_code, order_state, oid);
    }

    public int getTotalCount() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order";
        Long query = (Long) runner.query(sql, new ScalarHandler());
        return query.intValue();
    }

    public int getTotalCountByOid(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where oid like ?";
        Object result = runner.query(sql, new ScalarHandler(), "%" + oid + "%");
        if (result != null) {
            Long query = (Long) result;
            return query.intValue();
        }
        return 0;
    }

    public List<Order> getOrders(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid like ?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), oid + "%");
    }

    public void addOrder(Order order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into product_order (oid,taobao_code,express_code,total_price,date,alipay_code) values (?,?,?,?,?,?)";
        runner.update(sql, order.getOid(), order.getTaobao_code(), order.getExpress_code(), order.getTotal_price(), order.getDate(), order.getAlipay_code());
    }

    public void updateOrderByAcode(String alipay_code, String taobao_code, String express_code) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product_order set taobao_code = ? , express_code = ? where alipay_code = ?";
        runner.update(sql, taobao_code, express_code, alipay_code);
    }

    public void deleteOrder(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from product_order where oid = ?";
        runner.update(sql, oid);
    }

    public int getTotalCountByExpressCode(String express_code) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where express_code like ?";
        Object result = runner.query(sql, new ScalarHandler(), "%" + express_code + "%");
        if (result != null) {
            Long query = (Long) result;
            return query.intValue();
        }
        return 0;
    }

    public List<Order> getOrdersByExpressCode(String express_code, int index, int count) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where express_code like ? order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%" + express_code + "%", index, count);
    }

    public int getTotalCountByOidAndOrderState(String oid, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where oid like ? and order_state = ?";
        Object result = runner.query(sql, new ScalarHandler(), "%" + oid + "%", order_state);
        if (result != null) {
            Long query = (Long) result;
            return query.intValue();
        }
        return 0;
    }

    public List<Order> getOrdersByOidAndOrderState(String oid, int currentPage, int count, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where oid like ? and order_state = ? order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%" + oid + "%", order_state, currentPage, count);
    }

    public int getTotalCountByExpressCodeAndOrderState(String express_code, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where express_code like ? and order_state = ?";
        Object result = runner.query(sql, new ScalarHandler(), "%" + express_code + "%", order_state);
        if (result != null) {
            Long query = (Long) result;
            return query.intValue();
        }
        return 0;
    }

    public List<Order> getOrdersByExpressCodeAndOrderState(String express_code, int index, int count, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where express_code like ? and order_state = ? order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), "%" + express_code + "%", order_state, index, count);
    }

    public int getTotalCountAndOrderState(String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product_order where order_state = ?";
        Long query = (Long) runner.query(sql, new ScalarHandler(), order_state);
        return query.intValue();
    }

    public List<Order> getAllOrdersAndOrderState(int currentPage, int count, String order_state) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order where order_state = ? order by oid desc limit ?,?";
        return runner.query(sql, new BeanListHandler<Order>(Order.class), order_state, currentPage, count);
    }
}
