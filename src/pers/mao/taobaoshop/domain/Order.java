package pers.mao.taobaoshop.domain;

public class Order {
    private int id;
    //订单id
    private String oid;
    //淘宝订单号
    private String taobao_code;
    //快递号
    private String express_code;

    private String alipay_code;
    //总价
    private String total_price;
    //日期
    private String date;

    /**
     * 订单状态码
     * 0    未完成
     * 1    已完成
     * 2    不限
     * 3    凭证遗失
     * 4    遗失已完成
     */
    private String order_state;

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getAlipay_code() {
        return alipay_code;
    }

    public void setAlipay_code(String alipay_code) {
        this.alipay_code = alipay_code;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    /**
     * 订单状态码
     * 0    未完成
     * 1    已完成
     * 2    不限
     * 3    凭证遗失
     * 4    遗失已完成
     */
    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }
}
