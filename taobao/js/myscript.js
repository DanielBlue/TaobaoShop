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

// chrome.storage.local.clear(function () {
//     alert('清除成功')
// })
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


function getFormatDate() {
    var date = new Date().Format("yyyyMMdd")
    return date
}

function retuanLastOrderId(lastOrderId) {
    if (lastOrderId === undefined) {
        chrome.storage.local.clear()
    } else {
        chrome.storage.local.set({'order_id': lastOrderId})
    }
}

$("div #submitOrder_1").append("<div><br/><br/><a id='post_to_server' role='button' class='go-btn' style='font-size: 20px;background: dodgerblue'>保存到服务器</a>" +
    "<br/><br/><br/><a id='post_to_local' role='button' class='go-btn' style='font-size: 20px;background: green'>保存到本地</a>" +
    "<br/><br/><br/><a id='clear' role='button' class='go-btn' style='font-size: 20px;background: purple'>清除order_id</a></div>");

$("#clear").click(function () {
    chrome.storage.local.clear(function () {
        alert("清除成功")
    })
})

$("#post_to_server").click(function () {
    chrome.storage.local.get('order_id', function (result) {
        var currentId
        var lastOrderId = result.order_id
        if (lastOrderId === undefined) {
            //订单为空，初始化记录
            currentId = getFormatDate() + "01"
        } else {
            //有记录，对比是否今天的
            if (lastOrderId.substring(0, 8) == getFormatDate()) {
                //今天的继续加
                currentId = parseInt(lastOrderId) + 1 + ""
            } else {
                //不是今天的，重新开始
                currentId = getFormatDate() + "01"
            }
        }
        var show_str_temp = currentId + "\r\n" + show_str
        var boolean = confirm(show_str_temp);

        if (boolean) {
            body.order_id = currentId;
            body.date = new Date().getTime();
            var json = JSON.stringify(body);
            $.ajax({
                type: "post",
                async: false,
                url: "http://localhost/product/save",
                data: json,
                contentType: "application/json",
                dataType: "text",
                success: function (data) {
                    if (data == "success") {
                        chrome.storage.local.set({'order_id': currentId})
                        //success
                        show_str = show_str_temp
                        print_str = "<div style='font-size: 40px;margin-top: 100px' align='center'><p><b>" + currentId + "</b></p></div>" + print_str;
                        window.print();
                    } else {
                        retuanLastOrderId(lastOrderId)
                        alert("保存到服务器失败\r\n"+data)
                    }
                }
            });
        } else {
            retuanLastOrderId(lastOrderId)
        }
    })

})

$("#post_to_local").click(function () {
    chrome.storage.local.get('order_id', function (result) {
        var currentId
        var lastOrderId = result.order_id
        if (lastOrderId === undefined) {
            //订单为空，初始化记录
            currentId = getFormatDate() + "01"
        } else {
            //有记录，对比是否今天的
            if (lastOrderId.substring(0, 8) == getFormatDate()) {
                //今天的继续加
                currentId = parseInt(lastOrderId) + 1 + "";
            } else {
                //不是今天的，重新开始
                currentId = getFormatDate() + "01"
            }
        }

        var show_str_temp = currentId + "\r\n" + show_str
        var boolean = confirm(show_str_temp);

        if (boolean) {
            chrome.storage.local.set({'order_id': currentId}, function () {
                body.order_id = currentId;
                body.date = new Date().getTime();
                var json = JSON.stringify(body);
                export_raw(currentId + '.json', json);
                print_str = "<div style='font-size: 40px;margin-top: 100px' align='center'><p><b>" + currentId + "</b></p></div>" + print_str;
                window.print();
            })
        } else {
            retuanLastOrderId(lastOrderId)
        }
    })

})

function fake_click(obj) {
    var ev = document.createEvent("MouseEvents");
    ev.initMouseEvent(
        "click", true, false, window, 0, 0, 0, 0, 0
        , false, false, false, false, 0, null
    );
    obj.dispatchEvent(ev);
}

function export_raw(name, data) {
    var urlObject = window.URL || window.webkitURL || window;

    var export_blob = new Blob([data]);

    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
    save_link.href = urlObject.createObjectURL(export_blob);
    save_link.download = name;
    fake_click(save_link);
}


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
