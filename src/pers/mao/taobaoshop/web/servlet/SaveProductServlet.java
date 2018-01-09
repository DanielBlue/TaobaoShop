package pers.mao.taobaoshop.web.servlet;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import pers.mao.taobaoshop.domain.Admin;
import pers.mao.taobaoshop.ov.TaobaoBean;
import pers.mao.taobaoshop.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        json= new String(json.getBytes("iso-8859-1"), "GBK");
        TaobaoBean bean = gson.fromJson(json,TaobaoBean.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String s = sdf.format(new Date());
        System.out.println(s);


        response.getWriter().write("success");
    }
}
