package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String responseStr = "success";
//        Gson gson = new Gson();
//        ProductService service = new ProductService();
//        String json = request.getParameter("data");
//        if (json != null && !json.isEmpty()) {
//            json = new String(json.getBytes("iso-8859-1"), "GBK");
//        } else {
//            json = RequestUtils.getRequestBody(request);
//        }
//
////        String theLastNum = "";
////        try {
////            theLastNum = service.getTheLastOrderNum();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
////        String s = sdf2.format(new Date());
////
////
////        if (theLastNum != null && !theLastNum.isEmpty()) {
////            String tempStr = theLastNum.substring(0, 8);
////            if (tempStr.equals(s)) {
////                //当天已有记录
////                long i = Long.parseLong(theLastNum.substring(0, 10));
////                oid = String.valueOf(i + 1);
////            } else {
////                //当天第一条
////                oid = s + "01";
////            }
////        } else {
////            //之前无记录
////            oid = s + "01";
////        }
//
//
//        TaobaoBean bean = null;
//        try {
//            bean = gson.fromJson(json, TaobaoBean.class);
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//            responseStr = e.getMessage();
//        }
//        if (bean != null) {
//            String exception = service.saveProductAndOrder(bean);
//            if (exception!=null){
//                responseStr = exception;
//            }
//        }
//        response.getWriter().write(responseStr);
    }
}
