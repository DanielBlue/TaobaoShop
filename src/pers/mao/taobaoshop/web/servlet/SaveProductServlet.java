package pers.mao.taobaoshop.web.servlet;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.model.core.ID;
import net.sf.json.JSONObject;
import pers.mao.taobaoshop.domain.Admin;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.domain.Product;
import pers.mao.taobaoshop.ov.TaobaoBean;
import pers.mao.taobaoshop.service.ProductService;
import pers.mao.taobaoshop.utils.RequestUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        ProductService service = new ProductService();
        String json = request.getParameter("data");
        if (json != null && !json.isEmpty()) {
            json = new String(json.getBytes("iso-8859-1"), "GBK");
        } else {
            json = RequestUtils.getRequestBody(request);
        }

        String theLastNum = "";
        try {
            theLastNum = service.getTheLastOrderNum();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String s = sdf2.format(new Date());

        String oid = "";

        if (theLastNum != null && !theLastNum.isEmpty()) {
            String tempStr = theLastNum.substring(0, 8);
            if (tempStr.equals(s)) {
                //当天已有记录
                long i = Long.parseLong(theLastNum.substring(0, 10));
                oid = String.valueOf(i + 1);
            } else {
                //当天第一条
                oid = s + "01";
            }
        } else {
            //之前无记录
            oid = s + "01";
        }

        TaobaoBean bean = gson.fromJson(json, TaobaoBean.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        Order order = new Order();
        order.setDate(date);

        for (int i = 0; i < bean.getOrder_array().size(); i++) {
            String tempOid = oid;
            if (i < 9) {
                tempOid = oid + "0" + (i + 1);
            } else {
                tempOid = oid + (i + 1);
            }
            order.setTotal_price(bean.getOrder_array().get(i).getTotal_price());
            order.setOid(tempOid);
            try {
                service.saveOrder(order);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            TaobaoBean.OrderArrayBean orderArrayBean = bean.getOrder_array().get(i);
            for (TaobaoBean.OrderArrayBean.ProductArrayBean productArrayBean : orderArrayBean.getProduct_array()) {
                Product product = new Product();
                product.setOid(tempOid);
                product.setName(productArrayBean.getProduct_desc());
                System.out.println(productArrayBean.getProduct_desc());
                product.setFreight(orderArrayBean.getFreight());
                product.setPrice(productArrayBean.getProduct_price());
                try {
                    service.saveProduct(product);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        response.getWriter().write(oid);
    }
}
