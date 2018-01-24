<%@ page import="pers.mao.taobaoshop.domain.Product" %>
<%@ page import="pers.mao.taobaoshop.service.ProductService" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css"
          rel="stylesheet" type="text/css"/>
    <script language="javascript"
            src="${pageContext.request.contextPath}/js/public.js"></script>
    <script language="javascript"
            src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        <%--function addProduct() {--%>
        <%--window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";--%>
        <%--}--%>

        $(function () {
            $("tr.parent").click(function () {
                $(this).siblings(".child_" + this.id).toggle();
            }).click();
        })

    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"
      action="${pageContext.request.contextPath}/user/list.jsp"
      method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center"
           bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订单列表</strong>
            </td>
        </tr>
        <tr>
            <%--<td class="ta_01" align="right">--%>
            <%--<button type="button" id="add" name="add" value="添加"--%>
            <%--class="button_add" onclick="addProduct()">--%>
            <%--&#28155;&#21152;--%>
            <%--</button>--%>
            <%--</td>--%>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 15pt; HEIGHT: 50px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="6%">序号</td>
                        <td align="center" width="15%">取货单号</td>
                        <td align="center" width="20%">淘宝单号</td>
                        <td align="center" width="20%">快递单号</td>
                        <td align="center" width="10%">订单总价</td>
                        <td align="center" width="15%">日期</td>
                        <td width="7%" align="center">编辑</td>
                        <td width="7%" align="center">删除</td>
                    </tr>

                    <c:forEach var="orderBean" items="${orderBeanList}" varStatus="index">
                        <tr id="${orderBean.order.oid}"
                            class="parent"
                            style="HEIGHT: 50px;"
                            onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';"
                            >
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center" width="5%">${index.count}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.oid}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="20%">${orderBean.order.taobao_code}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="20%">${orderBean.order.express_code}</td>
                            <td align="center" width="10%">${orderBean.order.total_price}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.date}</td>
                            <td align="center" style="HEIGHT: 25px"><a
                                    href="${ pageContext.request.contextPath }/admin/product/edit.jsp">
                                <img
                                        src="${pageContext.request.contextPath}/images/i_edit.gif"
                                        border="0" style="CURSOR: hand">
                            </a></td>

                            <td align="center" style="HEIGHT: 25px"><a href="#"> <img
                                    src="${pageContext.request.contextPath}/images/i_del.gif"
                                    width="16" height="16" border="0" style="CURSOR: hand">
                            </a></td>

                        </tr>

                        <c:forEach var="product" items="${orderBean.productList}" varStatus="index">
                            <tr class="child_${orderBean.order.oid}"
                                style="BACKGROUND-COLOR: #FFF38F;HEIGHT: 40px">
                                <td align="center">${index.count}</td>
                                <td align="center" colspan="3">${product.name}</td>
                                <td align="center">单价:${product.price}</td>
                                <td align="center">运费:${product.freight}</td>
                                <td colspan="2"></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </td>
        </tr>

        </TBODY>
    </table>
</form>
</body>
</HTML>

