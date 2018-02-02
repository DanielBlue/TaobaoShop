package pers.mao.taobaoshop.service;

import pers.mao.taobaoshop.dao.ProductDao;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.ov.TaobaoBean;
import pers.mao.taobaoshop.utils.DataSourceUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductService {
    private ProductDao dao = new ProductDao();

    public List<Product> getAllProduct() throws SQLException {
        return dao.getAllProduct();
    }

    public List<Product> getProductList(String oid) throws SQLException {
        return dao.getProductList(oid);
    }

    public String getTheLastOrderNum() throws SQLException {
        return dao.getTheLastOrderNum();
    }

    public void saveProduct(Product product) throws SQLException {
        dao.saveProduct(product);
    }

    public void saveOrder(Order order) throws SQLException {
        dao.saveOrder(order);
    }

    public String saveProductAndOrder(TaobaoBean bean) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date(bean.getDate()));
            Order order = new Order();
            order.setDate(date);

            for (int i = 0; i < bean.getOrder_array().size(); i++) {
                String tempOid;
                if (i < 9) {
                    tempOid = bean.getOrder_id() + "0" + (i + 1);
                } else {
                    tempOid = bean.getOrder_id() + (i + 1);
                }
                order.setTotal_price(bean.getOrder_array().get(i).getTotal_price());
                order.setOid(tempOid);
                saveOrder(order);
                TaobaoBean.OrderArrayBean orderArrayBean = bean.getOrder_array().get(i);
                for (TaobaoBean.OrderArrayBean.ProductArrayBean productArrayBean : orderArrayBean.getProduct_array()) {
                    Product product = new Product();
                    product.setOid(tempOid);
                    product.setName(productArrayBean.getProduct_desc());
                    System.out.println(productArrayBean.getProduct_desc());
                    product.setFreight(orderArrayBean.getFreight());
                    product.setPrice(productArrayBean.getProduct_price());
                    saveProduct(product);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return e.getMessage();
        }
        return null;
    }
}
