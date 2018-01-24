package pers.mao.taobaoshop.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    public List<Order> getAllOrders() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product_order order by id desc";
        return runner.query(sql, new BeanListHandler<Order>(Order.class));
    }
}
