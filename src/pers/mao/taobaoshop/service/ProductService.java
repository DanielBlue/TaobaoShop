package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.dao.ProductDao;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;

import java.sql.SQLException;

public class ProductService {
    private ProductDao dao = new ProductDao();

    public String getTheLastOrderNum() throws SQLException {
        return dao.getTheLastOrderNum();
    }

    public void saveProduct(Product product) throws SQLException {
        dao.saveProduct(product);
    }

    public void saveOrder(Order order) throws SQLException{
        dao.saveOrder(order);
    }

}
