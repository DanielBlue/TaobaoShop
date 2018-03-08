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

    public PageBean<OrderBean> getAllOrders(int currentPage, int count,String order_state) throws SQLException {
        PageBean<OrderBean> pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);

        if ("2".equals(order_state)){
            int totalCount = dao.getTotalCount();
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0*totalCount/count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage-1)*count;
            List<Order> orderList = dao.getAllOrders(index,count);

            initItemList(pageBean, orderBeanList, orderList);
        }else {
            int totalCount = dao.getTotalCountAndOrderState(order_state);
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0*totalCount/count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage-1)*count;
            List<Order> orderList = dao.getAllOrdersAndOrderState(index,count,order_state);

            initItemList(pageBean, orderBeanList, orderList);
        }
        return pageBean;
    }

    private void initItemList(PageBean<OrderBean> pageBean, List<OrderBean> orderBeanList, List<Order> orderList) {
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
    }

    public Order getOrder(String oid) throws SQLException {
        return dao.getOrder(oid);
    }

    public List<Order> getOrders(String oid) throws SQLException {
        return dao.getOrders(oid);
    }

    public void updateOrderByOid(String oid, String taobao_code, String express_code, String order_state) throws SQLException {
        dao.updateOrderByOid(oid,taobao_code,express_code,order_state);
    }


    public void updateOrderByAcode(String alipay_code, String taobao_code, String express_code) throws SQLException {
        dao.updateOrderByAcode(alipay_code,taobao_code,express_code);
    }


    public PageBean<OrderBean> getOrdersByOid(String oid, int currentPage, int count, String order_state) throws SQLException {
        PageBean<OrderBean> pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);

        if ("2".equals(order_state)){
            //不限
            int totalCount = dao.getTotalCountByOid(oid);
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0 * totalCount / count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage - 1) * count;
            List<Order> orderList = dao.getOrdersByOid(oid, index, count);

            initItemList(pageBean, orderBeanList, orderList);
        }else {
            //有筛选
            int totalCount = dao.getTotalCountByOidAndOrderState(oid,order_state);
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0 * totalCount / count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage - 1) * count;
            List<Order> orderList = dao.getOrdersByOidAndOrderState(oid, index, count,order_state);

            initItemList(pageBean, orderBeanList, orderList);
        }

        return pageBean;
    }

    public List<Order> getOrdersByAcode(String alipay_code) throws SQLException{
        return dao.getOrdersByAcode(alipay_code);
    }

    public void addOrder(Order order) throws SQLException {
        dao.addOrder(order);
    }

    public void deleteOrder(String oid) throws SQLException {
        dao.deleteOrder(oid);
    }

    public PageBean<OrderBean> getOrdersByExpressCode(String express_code, int currentPage, int count, String order_state) throws SQLException {
        PageBean<OrderBean> pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(count);

        if ("2".equals(order_state)) {
            int totalCount = dao.getTotalCountByExpressCode(express_code);
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0*totalCount/count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage-1)*count;
            List<Order> orderList = dao.getOrdersByExpressCode(express_code,index,count);

            initItemList(pageBean, orderBeanList, orderList);
        }else {
            int totalCount = dao.getTotalCountByExpressCodeAndOrderState(express_code,order_state);
            pageBean.setTotalCount(totalCount);

            int totalPage = (int) Math.ceil(1.0*totalCount/count);
            pageBean.setTotalPage(totalPage);

            List<OrderBean> orderBeanList = new ArrayList<>();
            int index = (currentPage-1)*count;
            List<Order> orderList = dao.getOrdersByExpressCodeAndOrderState(express_code,index,count,order_state);

            initItemList(pageBean, orderBeanList, orderList);
        }
        return pageBean;
    }
}
