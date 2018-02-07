$("#content").prepend("<br/><div><a id='sync_to_server' role='button' style='padding: 10px;font-size: 18px;background: dodgerblue';color: whitesmoke>同步淘宝订单号和运货号到服务器</a></div><br/>")


$("#sync_to_server").click(function () {

    var host = window.location.host
    var json_obj = new Object()
    if (host == "trade.tmall.com") {


        json_obj.alipay_code = $(".trade-dropdown-table span").eq(2).html()
        json_obj.taobao_code = $("span.ui-trade-label.middleText").html()
        json_obj.express_code = $("div.content-package.package-1 span").eq(3).html()


    } else if (host == "trade.taobao.com") {
        json_obj.alipay_code = $("span.misc-info-mod__content___1i_60 span").eq(1).html()
        json_obj.taobao_code = $("span.misc-info-mod__content___1i_60 span").eq(0).html()
        json_obj.express_code = $("#detail-panel td").eq(2).html()
    } else {
        alert("网页url错误")
    }

    if(isNaN(json_obj.alipay_code)){
        json_obj.alipay_code = undefined;
    }

    if(isNaN(json_obj.taobao_code)){
        json_obj.taobao_code = undefined;
    }

    if(isNaN(json_obj.express_code)){
        json_obj.express_code = undefined;
    }

    var alert_str = "支付宝单号：" + json_obj.alipay_code + "\n\r" +
        "订单号：" + json_obj.taobao_code + "\n\r" +
        "运货单号：" + json_obj.express_code

    var boolean = confirm(alert_str)
    if (boolean){
        $.post("https://localhost:8443/order/update_taobao_code",
            json_obj,
            function (data) {
                alert(data)
        },
            "text")
    }

})






