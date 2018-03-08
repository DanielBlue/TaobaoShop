package pers.mao.taobaoshop.web.servlet;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.sf.json.JSONObject;
import pers.mao.taobaoshop.utils.NetUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ExpressQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String express_code = request.getParameter("express_code");
        String result = NetUtils.getExpressInfo(express_code);
        if (result != null&&!result.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
        }else {
            result="暂无快递信息";
            response.setContentType("application/json;charset=UTF-8");
        }
        response.getWriter().write(result);
    }
}
