package pers.mao.taobaoshop.domain;

public class Order {
    private String oid;
    private String order_code;
    private String taobao_code;
    private String express_code;
    private String date;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getTaobao_code() {
        return taobao_code;
    }

    public void setTaobao_code(String taobao_code) {
        this.taobao_code = taobao_code;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
