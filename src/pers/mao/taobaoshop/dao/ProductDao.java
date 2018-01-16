package pers.mao.taobaoshop.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.utils.DataSourceUtils;

import java.sql.SQLException;

public class ProductDao {

    public String getTheLastOrderNum() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select oid from product_order order by id desc limit 1";
        String theLastNum = (String)runner.query(sql,new ScalarHandler());
        return theLastNum;
    }

    public void saveProduct(Product product) throws SQLException{
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into product (oid,name,price,freight) values(?,?,?,?)";
        runner.update(sql,product.getOid(),product.getName(),product.getPrice(),product.getFreight());
    }

    public void saveOrder(Order order) throws SQLException{
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into product_order (oid,total_price,date) values(?,?,?)";
        runner.update(sql,order.getOid(),order.getTotal_price(),order.getDate());
    }
}
