package pers.mao.taobaoshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mao.taobaoshop.dao.OrderDao;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.ov.OrderBean;
import pers.mao.taobaoshop.ov.PageBean;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDao orderDao;

    public List<Order> getAllOrders() throws SQLException {
//        return dao.getAllOrders();
        return null;

    }

    public PageBean<OrderBean> getAllOrders(int currentPage, int count) throws SQLException {
//        PageBean<OrderBean> pageBean = new PageBean();
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setCurrentCount(count);
//
//        int totalCount = dao.getTotalCount();
//        pageBean.setTotalCount(totalCount);
//
//        int totalPage = (int) Math.ceil(1.0*totalCount/count);
//        pageBean.setTotalPage(totalPage);
//
//        List<OrderBean> orderBeanList = new ArrayList<>();
//        int index = (currentPage-1)*count;
//        List<Order> orderList = dao.getAllOrders(index,count);
//
//        initItemList(pageBean, orderBeanList, orderList);
//
//        return pageBean;
        return null;

    }

    private void initItemList(PageBean<OrderBean> pageBean, List<OrderBean> orderBeanList, List<Order> orderList) {
//        if (orderList != null && orderList.size() >= 0) {
//            OrderBean orderBean;
//            for (Order order : orderList) {
//                try {
//                    orderBean = new OrderBean();
//                    orderBean.setOrder(order);
//                    orderBean.setProductList(productDao.getProductList(order.getOid()));
//                    orderBeanList.add(orderBean);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            pageBean.setItemList(orderBeanList);
//        }

    }

    public Order getOrder(String oid) throws SQLException {
//        return dao.getOrder(oid);
        return null;
    }

    public List<Order> getOrders(String oid) throws SQLException {
//        return dao.getOrders(oid);
        return null;
    }

    public void updateOrderByOid(String oid, String taobao_code, String express_code) throws SQLException {
//        dao.updateOrderByOid(oid,taobao_code,express_code);
    }


    public void updateOrderByAcode(String alipay_code, String taobao_code, String express_code) throws SQLException {
//        dao.updateOrderByAcode(alipay_code,taobao_code,express_code);
    }


    public PageBean<OrderBean> getOrdersByOid(String oid, int currentPage, int count) throws SQLException {
//        PageBean<OrderBean> pageBean = new PageBean();
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setCurrentCount(count);
//
//        int totalCount = dao.getTotalCount(oid);
//        pageBean.setTotalCount(totalCount);
//
//        int totalPage = (int) Math.ceil(1.0*totalCount/count);
//        pageBean.setTotalPage(totalPage);
//
//        List<OrderBean> orderBeanList = new ArrayList<>();
//        int index = (currentPage-1)*count;
//        List<Order> orderList = dao.getOrdersByOid(oid,index,count);
//
//        initItemList(pageBean, orderBeanList, orderList);
//
//        return pageBean;
        return null;
    }

    public List<Order> getOrdersByAcode(String alipay_code) throws SQLException {
//        return dao.getOrdersByAcode(alipay_code);
        return null;

    }

    public void addOrder(Order order) throws SQLException {

//        dao.addOrder(order);
    }

    public void deleteOrder(String oid) {
//        dao.deleteOrder(oid);
    }

    @Override
    public PageBean<OrderBean> getPageBeanByOid(String oid, int currentPage, int count) {
        PageBean<OrderBean> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);
        int totalCount = orderDao.getTotalCount(oid);
        pageBean.setTotalCount(totalCount);
        int totalPage = (int) Math.ceil(1.0*totalCount/count);
        pageBean.setTotalPage(totalPage);

        List<OrderBean> orderBeanList = orderDao.selectOrderBeanByOid(oid);

        pageBean.setItemList(orderBeanList);

        return pageBean;
    }
}
