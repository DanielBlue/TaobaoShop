package pers.mao.taobaoshop.domain;

public class Product {
    private int id;
    //订单id
    private String oid;
    //商品描述
    private String name;
    //商品价格
    private String price;
    //运费
    private String freight;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }
}
