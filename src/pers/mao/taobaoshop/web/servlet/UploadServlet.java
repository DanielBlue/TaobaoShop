package pers.mao.taobaoshop.web.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pers.mao.taobaoshop.ov.TaobaoBean;
import pers.mao.taobaoshop.service.OrderService;
import pers.mao.taobaoshop.service.ProductService;
import pers.mao.taobaoshop.utils.DataSourceUtils;
import pers.mao.taobaoshop.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String responseStr = "";
        ProductService productService = new ProductService();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    //普通表单

                } else {
                    //文件上传
                    String fieldName = item.getName();

                    if (fieldName.contains(".json")) {
                        InputStream inputStream = item.getInputStream();
                        String json = RequestUtils.getRequestBody(inputStream);
                        Gson gson = new Gson();
                        TaobaoBean bean = null;
                        try {
                            bean = gson.fromJson(json, TaobaoBean.class);
                            if (bean != null) {
                                String exception = productService.saveProductAndOrder(bean);
                                if (exception != null) {
                                    responseStr += exception + "\r\n";
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            responseStr += e.getMessage() + "\r\n";
                        }
                    } else {
                        responseStr += "上传的文件格式错误" + "\r\n";
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();

            responseStr += e.getMessage() + "\r\n";
        }
        if (responseStr.equals("")) {
            responseStr = "success";
        }
        response.getWriter().write(responseStr);
    }
}
