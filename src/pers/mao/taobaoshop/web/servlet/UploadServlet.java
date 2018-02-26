package pers.mao.taobaoshop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String responseStr = "";
//        ProductService productService = new ProductService();
//        try {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
//            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
//            for (FileItem item : fileItems) {
//                if (item.isFormField()) {
//                    //普通表单
//
//                } else {
//                    //文件上传
//                    String fieldName = item.getName();
//
//                    if (fieldName.contains(".json")) {
//                        InputStream inputStream = item.getInputStream();
//                        String json = RequestUtils.getRequestBody(inputStream);
//                        Gson gson = new Gson();
//                        TaobaoBean bean = null;
//                        try {
//                            bean = gson.fromJson(json, TaobaoBean.class);
//                            if (bean != null) {
//                                String exception = productService.saveProductAndOrder(bean);
//                                if (exception != null) {
//                                    responseStr += exception + "\r\n";
//                                }
//                            }
//                        } catch (JsonSyntaxException e) {
//                            e.printStackTrace();
//                            responseStr += e.getMessage() + "\r\n";
//                        }
//                    } else {
//                        responseStr += "上传的文件格式错误" + "\r\n";
//                    }
//                }
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//
//            responseStr += e.getMessage() + "\r\n";
//        }
//        if (responseStr.equals("")) {
//            responseStr = "success";
//        }
//        response.getWriter().write(responseStr);
    }
}
