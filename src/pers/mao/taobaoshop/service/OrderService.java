package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.dao.OrderDao;
import pers.mao.taobaoshop.domain.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao dao = new OrderDao();

    public List<Order> getAllOrders() throws SQLException {
        return dao.getAllOrders();
    }
}
