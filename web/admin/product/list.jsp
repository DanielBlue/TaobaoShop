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

        $(function () {
            $("tr.parent").click(function () {
                $(this).siblings(".child_" + this.id).toggle();
            }).click();

            $("td.td_edit").click(function (e) {
                e.stopPropagation();
            })

            $("td.td_delete").click(function (e) {
                e.stopPropagation();
            })
        })

        function addProduct() {
            window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
        }

    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"
      action="${pageContext.request.contextPath}/order/order_list?currentPage=1"
      method="post">

    &nbsp;&nbsp;订单号：<input type="text" name="oid" value="${oid}">

    &nbsp;&nbsp;&nbsp;&nbsp;快递单号：<input type="text" name="express_code" value="${express_code}">

    &nbsp;&nbsp;&nbsp;&nbsp;订单状态：<select name="order_state">
                                        <c:if test="${order_state==0}">
                                            <option value="0">未完成</option>
                                            <option value="1">已完成</option>
                                            <option value="2">不限</option>
                                        </c:if>

                                        <c:if test="${order_state==1}">
                                            <option value="1">已完成</option>
                                            <option value="2">不限</option>
                                            <option value="0">未完成</option>
                                        </c:if>

                                        <c:if test="${order_state==2}">
                                            <option value="2">不限</option>
                                            <option value="0">未完成</option>
                                            <option value="1">已完成</option>
                                        </c:if>
                                        </select>

    &nbsp;&nbsp;<input type="submit" value="搜索">
    <br/>
    <br/>
    <table cellSpacing="1" cellPadding="0" width="100%" align="center"
           bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订单列表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="添加"
                        style="height: 30px;text-align: center;border: 1px solid #8AA2CC;color: #2F3F5B;margin-right: 20px"
                        onclick="addProduct()">
                    &#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 15pt; HEIGHT: 50px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="5%">序号</td>
                        <td align="center" width="10%">取货单号</td>
                        <td align="center" width="13%">淘宝单号</td>
                        <td align="center" width="13%">快递单号</td>
                        <td align="center" width="13%">交易号</td>
                        <td align="center" width="10%">订单总价</td>
                        <td align="center" width="15%">日期</td>
                        <td align="center" width="7%">日期</td>
                        <td width="7%" align="center">编辑</td>
                        <td width="7%" align="center">删除</td>
                    </tr>

                    <c:forEach var="orderBean" items="${pageBean.itemList}" varStatus="index">
                        <tr id="${orderBean.order.oid}"
                            class="parent"
                            style="HEIGHT: 50px;"
                            onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="5%">${pageBean.currentCount*(pageBean.currentPage-1)+index.count}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="10%">${orderBean.order.oid}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.taobao_code}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.express_code}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.alipay_code}</td>
                            <td align="center" width="10%">${orderBean.order.total_price}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.date}</td>
                            <td align="center" style="HEIGHT: 25px" class="td_edit">
                                <a href="${ pageContext.request.contextPath }/order/edit_order?oid=${orderBean.order.oid}">
                                    <img src="${pageContext.request.contextPath}/images/i_edit.gif"
                                         border="0" style="CURSOR: hand">
                                </a></td>

                            <td align="center" style="HEIGHT: 25px" class="td_delete">
                                <a href="#"
                                   onclick="confirm('确定删除吗？')?location.href='${ pageContext.request.contextPath }/order/delete_order?oid=${orderBean.order.oid}':''">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif"
                                         width="16" height="16" border="0" style="CURSOR: hand">
                                </a></td>
                        </tr>

                        <c:forEach var="product" items="${orderBean.productList}" varStatus="index">
                            <tr class="child_${orderBean.order.oid}"
                                style="BACKGROUND-COLOR: #FFF38F;HEIGHT: 40px">
                                <td align="center">${index.count}</td>
                                <td align="center" colspan="4">${product.name}</td>
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

<!--分页 -->
<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
    <ul class="pagination" style="text-align: center; margin-top: 10px;white-space: nowrap; list-style:none">

        <c:if test="${pageBean.totalCount>0 }">

            <!-- 上一页 -->
            <!-- 判断当前页是否是第一页 -->
            <c:if test="${pageBean.currentPage==1 }">
                <li class="disabled" style="float:left;margin-left: 15px">
                    <a href="javascript:void(0);" aria-label="Previous">
                        <span aria-hidden="true" style="font-size:20px">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage!=1 }">
                <li style="float:left;margin-left: 15px">
                    <a href="${pageContext.request.contextPath }/order/order_list?currentPage=${pageBean.currentPage-1}&oid=${oid}&express_code=${express_code}&order_state=${order_state}"
                       aria-label="Previous">
                        <span aria-hidden="true" style="font-size:20px">&laquo;</span>
                    </a>
                </li>
            </c:if>
        </c:if>


        <c:forEach begin="1" end="${pageBean.totalPage }" var="page">
            <!-- 判断当前页 -->
            <c:if test="${pageBean.currentPage==page }">
                <li class="active" style="float:left;margin-left: 15px;"><a style="font-size:large;color: red;"
                                                                            href="javascript:void(0);">${page}</a></li>
            </c:if>
            <c:if test="${pageBean.currentPage!=page }">
                <li style="float:left;margin-left: 15px;"><a style="font-size:large"
                                                             href="${pageContext.request.contextPath }/order/order_list?currentPage=${page}&oid=${oid}&express_code=${express_code}&order_state=${order_state}">${page}</a>
                </li>
            </c:if>
        </c:forEach>

        <c:if test="${pageBean.totalCount>0 }">

            <!-- 判断当前页是否是最后一页 -->
            <c:if test="${pageBean.currentPage==pageBean.totalPage }">
                <li class="disabled" style="float:left;margin-left: 15px">
                    <a href="javascript:void(0);" aria-label="Next">
                        <span aria-hidden="true" style="font-size:20px">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage!=pageBean.totalPage }">
                <li style="float:left;margin-left: 15px">
                    <a href="${pageContext.request.contextPath }/order/order_list?currentPage=${pageBean.currentPage+1}&oid=${oid}&express_code=${express_code}&order_state=${order_state}"
                       aria-label="Next">
                        <span aria-hidden="true" style="font-size:20px">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </c:if>


    </ul>
</div>
<!-- 分页结束 -->
</body>
</HTML>

