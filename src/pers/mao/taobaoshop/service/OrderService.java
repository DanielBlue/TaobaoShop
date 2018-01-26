package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.dao.OrderDao;
import pers.mao.taobaoshop.dao.ProductDao;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private OrderDao dao = new OrderDao();
    private ProductDao productDao = new ProductDao();

    public List<Order> getAllOrders() throws SQLException {
        return dao.getAllOrders();
    }

    public PageBean<OrderBean> getAllOrders(int currentPage, int count) throws SQLException {
        PageBean<OrderBean> pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);

        int totalCount = dao.getTotalCount();
        pageBean.setTotalCount(totalCount);

        int totalPage = (int) Math.ceil(1.0*totalCount/count);
        pageBean.setTotalPage(totalPage);

        List<OrderBean> orderBeanList = new ArrayList<>();
        int index = (currentPage-1)*count;
        List<Order> orderList = dao.getAllOrders(index,count);

        if (orderList != null && orderList.size() >= 0) {
            OrderBean orderBean;
            for (Order order : orderList) {
                try {
                    orderBean = new OrderBean();
                    orderBean.setOrder(order);
                    orderBean.setProductList(productDao.getProductList(order.getOid()));
                    orderBeanList.add(orderBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            pageBean.setItemList(orderBeanList);
        }

        return pageBean;
    }

    public Order getOrder(String oid) throws SQLException {
        return dao.getOrder(oid);
    }

    public List<Order> getOrders(String oid) throws SQLException {
        return dao.getOrders(oid);
    }

    public void updateOrder(String oid, String taobao_code, String express_code) throws SQLException {
        dao.updateOrder(oid,taobao_code,express_code);
    }

    public PageBean<OrderBean> getOrdersByOid(String oid, int currentPage, int count) throws SQLException {
        PageBean<OrderBean> pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);

        int totalCount = dao.getTotalCount(oid);
        pageBean.setTotalCount(totalCount);

        int totalPage = (int) Math.ceil(1.0*totalCount/count);
        pageBean.setTotalPage(totalPage);

        List<OrderBean> orderBeanList = new ArrayList<>();
        int index = (currentPage-1)*count;
        List<Order> orderList = dao.getOrdersByOid(oid,index,count);

        if (orderList != null && orderList.size() >= 0) {
            OrderBean orderBean;
            for (Order order : orderList) {
                try {
                    orderBean = new OrderBean();
                    orderBean.setOrder(order);
                    orderBean.setProductList(productDao.getProductList(order.getOid()));
                    orderBeanList.add(orderBean);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            pageBean.setItemList(orderBeanList);
        }

        return pageBean;
    }
}
