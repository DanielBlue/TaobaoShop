package pers.mao.taobaoshop.web.servlet;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import pers.mao.taobaoshop.domain.Order;
import pers.mao.taobaoshop.ov.ExpressInfoBean;
import pers.mao.taobaoshop.ov.InputMessage;
import pers.mao.taobaoshop.ov.OutputMessage;
import pers.mao.taobaoshop.service.OrderService;
import pers.mao.taobaoshop.utils.*;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class WxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isPost = request.getMethod().toLowerCase().equals("post");
        if (isPost) {
            // 接收消息并返回消息
            acceptMessage(request, response);
        }
    }

    private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间

        String receiveContent = inputMsg.getContent();

        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if (msgType.equals(MsgType.Text.toString()) && StrUtils.isInteger(receiveContent)) {
            // 文本消息
//            System.out.println("开发者微信号：" + inputMsg.getToUserName());
//            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
//            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
//            System.out.println("消息内容：" + inputMsg.getContent());
//            System.out.println("消息Id：" + inputMsg.getMsgId());
            String result = "";
            OrderService service = new OrderService();
            List<Order> orderList = null;
            try {
                orderList = service.getOrders(receiveContent);
                if (orderList != null && orderList.size() > 0) {
                    if (orderList.size() == 1) {
                        Order order = orderList.get(0);
                        String express_code = order.getExpress_code();
                        if (express_code != null || express_code.isEmpty()) {
                            String expressInfo = NetUtils.getExpressInfo(express_code);
                            result = formatResult(expressInfo);
                        } else {
                            result = ConstantUtils.NO_MESSAGE;
                        }
                    } else {
                        result = ConstantUtils.INPUT_INCOMPLETE;
                    }
                } else {
                    result = ConstantUtils.NO_MESSAGE;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                result = ConstantUtils.SERVER_ERROR;
            }

            String responseStr = buildOutputMessage(custermname, servername, returnTime, msgType, result);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(responseStr);
        } else if (msgType.equals(MsgType.Text.toString()) &&receiveContent.equals("老板")) {
            String responseStr = buildOutputMessage(custermname, servername, returnTime, msgType, ConstantUtils.BOSS_WX);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(responseStr);
        } else {
            String responseStr = buildOutputMessage(custermname, servername, returnTime, msgType, ConstantUtils.INPUT_ERROR);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(responseStr);
        }
        // 获取并返回多图片消息
//        if (msgType.equals(MsgType.Image.toString())) {
//            System.out.println("获取多媒体信息");
//            System.out.println("多媒体文件id：" + inputMsg.getMediaId());
//            System.out.println("图片链接：" + inputMsg.getPicUrl());
//            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());
//
//            OutputMessage outputMsg = new OutputMessage();
//            outputMsg.setFromUserName(servername);
//            outputMsg.setToUserName(custermname);
//            outputMsg.setCreateTime(returnTime);
//            outputMsg.setMsgType(msgType);
//            ImageMessage images = new ImageMessage();
//            images.setMediaId(inputMsg.getMediaId());
//            outputMsg.setImage(images);
//            System.out.println("xml转换：/n" + xs.toXML(outputMsg));
//            response.getWriter().write(xs.toXML(outputMsg));
//
//        }
    }

    private String formatResult(String expressInfo) {
        Gson gson = new Gson();
        ExpressInfoBean expressInfoBean = gson.fromJson(expressInfo, ExpressInfoBean.class);
        String message = expressInfoBean.getMessage();
        if (expressInfoBean != null && message != null && !message.isEmpty()) {
            if (!message.equals("ok")) {
                return message;
            } else {
                List<ExpressInfoBean.DataBean> data = expressInfoBean.getData();
                if (data != null && data.size() > 0) {
                    String content = "你的快递信息：\r\n\r\n";
                    for (ExpressInfoBean.DataBean dataBean : data) {
                        content += dataBean.getTime() + "\r\n" + dataBean.getContext() + "\r\n\r\n";
                    }
                    return content;
                } else {
                    return ConstantUtils.NO_MESSAGE;
                }
            }
        }
        return ConstantUtils.SERVER_ERROR;
    }

    private String buildOutputMessage(String custermname, String servername, long returnTime, String msgType, String returnContent) {

        StringBuffer str = new StringBuffer();
        str.append("<xml>");
        str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
        str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
        str.append("<CreateTime>" + returnTime + "</CreateTime>");
        str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
        str.append("<Content><![CDATA[" + returnContent + " ]]></Content>");
        str.append("</xml>");
        return str.toString();
    }
}
