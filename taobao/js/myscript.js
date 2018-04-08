$("#content").prepend("<br/><div><a id='sync_to_server' role='button' style='padding: 10px;font-size: 18px;background: dodgerblue';color: whitesmoke>同步淘宝订单号和运货号到服务器</a></div><br/>")


$("#sync_to_server").click(function () {

    var host = window.location.host
    var json_obj = new Object()
    if (host == "trade.tmall.com") {
        json_obj.alipayCode = $(".trade-dropdown-table span").eq(2).html()
        json_obj.taobaoCode = $("span.ui-trade-label.middleText").html()
        json_obj.expressCode = $("div.content-package.package-1 span").eq(3).html()
    } else if (host == "trade.taobao.com") {
        json_obj.alipayCode = $("span.misc-info-mod__content___1i_60 span").eq(1).html()
        json_obj.taobaoCode = $("span.misc-info-mod__content___1i_60 span").eq(0).html()
        json_obj.expressCode = $("#detail-panel td").eq(2).html()
    } else {
        alert("网页url错误")
    }

    if (isNaN(json_obj.alipayCode)) {
        json_obj.alipayCode = undefined;
    }

    if (isNaN(json_obj.taobaoCode)) {
        json_obj.taobaoCode = undefined;
    }

    if (isNaN(json_obj.expressCode)) {
        json_obj.expressCode = undefined;
    }

    var alert_str = "支付宝单号：" + json_obj.alipayCode + "\n\r" +
        "订单号：" + json_obj.taobaoCode + "\n\r" +
        "运货单号：" + json_obj.expressCode

    var boolean = confirm(alert_str)
    if (boolean) {
        // 114.67.241.157

        $.ajax({
            type: "POST",
            async: false,
            timeout: 5000,
            url: "http://localhost:8080/order/code_update_order",
			contentType:'application/json; charset=utf-8',
            data: json_obj,
            dataType: "json",
            success: function (data) {
                alert(data.info)
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown)
            }
        })
    }

})






