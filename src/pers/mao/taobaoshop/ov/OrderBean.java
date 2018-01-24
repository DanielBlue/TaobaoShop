package pers.mao.taobaoshop.ov;

import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;

import java.util.List;

public class OrderBean {
    private Order order;
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Order getOrder() {

        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
