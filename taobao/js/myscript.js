var print_str = "<div align='center'>";
var show_str = "";
var body = new Object();
var order_array = new Array();
var total_agent_cost = 0;

// 确认订单页面结构
// body
//     .realPay-price	所有店铺商品的总价
//     .order-order	所有店铺的订单list
//          .select-price	一个店铺的运费
//          .style-middle-bold-red.J_ShopTotal	一个店铺的总价
//          .order-item order-item-column-4	一个店铺所有订单的list
//              .info-title	商品描述
//              .simple-price 商品价格


body.total_price = $(".realPay-price").html();

for (var i = 0; i < $(".order-order").length; i++) {
    var shop_order = new Object();
    var product_array = new Array();
    shop_order.freight = $(".select-price").eq(i).html();
    shop_order.total_price = $('.style-middle-bold-red.J_ShopTotal').eq(i).html();

    print_str += "<div style='font-size: medium'><p>" + (i + 1) + ".";
    show_str += (i + 1) + ".";

    for (var x = 0; x < $(".order-order:eq(" + i + ") .order-item.order-item-column-4").length; x++) {
        var product_order = new Object();
        product_order.product_desc = (i + 1) + ".(" + (x + 1) + ")" + $(".order-order:eq(" + i + ") .info-title:eq(" + x + ")").html();
        product_order.product_price = $(".order-order:eq(" + i + ") .simple-price:eq(" + x + ")").html();
        product_array[x] = product_order;

        print_str += "(" + (x + 1) + ")" + "  商品：" + $(".order-order:eq(" + i + ") .info-title:eq(" + x + ")").html() + "</p>"
            + "<p>价格：" + product_order.product_price + "元</p>";

        show_str += "(" + (x + 1) + ")" + "  商品：" + $(".order-order:eq(" + i + ") .info-title:eq(" + x + ")").html() + "\n"
            + "价格：" + product_order.product_price + "元\n";

    }

    print_str += "<p>店铺运费：" + shop_order.freight + "元</p>";
    show_str += "店铺运费：" + shop_order.freight + "元\n";

    if (parseInt(shop_order.total_price) >= 30) {
        print_str += "<p>代购费：5元</p>"
        show_str += "代购费：5元\n"
        total_agent_cost += 5;
    } else {
        print_str += "<p>代购费：3元</p>"
        show_str += "代购费：3元\n"
        total_agent_cost += 3
    }

    print_str += "<p>店铺总价：" + shop_order.total_price + "元</p></div><br/>";
    show_str += "店铺总价：" + shop_order.total_price + "元\r\n";


    shop_order.product_array = product_array;
    order_array[i] = shop_order;
}

print_str += "<div style='font-size: 30px'><p>商品总价格：" + body.total_price + "元</p>"
    + "<p>总含代购费：" + total_agent_cost + "元</p>"
    + "<p>总价格(含代购费)：" + (parseFloat(body.total_price) + total_agent_cost) + "元</p></div></div>";

show_str += "总价格：" + body.total_price + "元\n"
    + "总代购费：" + total_agent_cost + "元\n"
    + "总价格(含代购费)：" + (parseFloat(body.total_price) + total_agent_cost) + "元\n";

body.order_array = order_array;
var json = JSON.stringify(body);

var tempHtml = $("body").html();

if (window.matchMedia) {
    var mediaQueryList = window.matchMedia('print');
    mediaQueryList.addListener(function (mql) {
        if (mql.matches) {
            $("body").html(print_str);
        } else {
            $("body").html(tempHtml);
        }
    });
}

window.onbeforeprint = function () {
    $("body").html(print_str);
}

window.onafterprint = function () {
    $("body").html(tempHtml);
}

var boolean = confirm(show_str);
if (boolean) {
    $.ajax({
        type: "post",
        async: false,
        url: "http://114.67.241.157/product/save",
        data: json,
        contentType: "application/json",
        dataType: "text",
        success: function (data) {
            //success
            print_str = "<div style='font-size: 40px;margin-top: 100px' align='center'><p><b>" + data + "</b></p></div>" + print_str;
            window.print();
        }
    });
}
// 114.67.241.157

// var xhr = new XMLHttpRequest();
// xhr.open("GET", "http://114.67.241.157/product/save?data="+json, false);
// xhr.onreadystatechange = function () {
//     if (xhr.readyState === 4) {
//         var responseText = xhr.responseText;
//         print_str = "<div style='font-size: 40px;margin-top: 100px' align='center'><p><b>" + responseText + "</b></p></div>" + print_str;
//         window.print();
//     }
// };
// xhr.setRequestHeader("Content-Type", "application/json;");
// xhr.send();
