$("#J_Order").after("<div><br/><a id='post_to_server' role='button' class='ui-button ui-button-lblue' style='font-size: 20px;background: orange'>保存到服务器</a>" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id='post_to_local' role='button' class='ui-button ui-button-lblue' style='font-size: 20px;background: green'>保存到本地</a></div>")

$("#J_OrderExtTrigger")[0].click()

var alipay_code_array = new Array();

function init_aplipay_code_array() {

    if ($("tbody .trade-num").length > 0) {
        for (var i = 0; i < $("tbody .trade-num").length; i++) {
            alipay_code_array[i] = $("tbody .trade-num").eq(i).html()
        }
    } else if ($("#J_OrderExt td:last").html() != undefined) {
        alipay_code_array[0] = $("#J_OrderExt td:last").html()
    }
}

function init_json_str(response) {
    print_str = response.print_str
    currentId = response.currentId
    print_str = "<div style='font-size: 40px;margin-top: 100px' align='center'><p><b>" + currentId + "</b></p></div>" + print_str;
    var json_obj = JSON.parse(response.json)

    for (var x = 0; x < alipay_code_array.length; x++) {
        json_obj.order_array[x].alipay_code = alipay_code_array[x]
    }

    var json_str_temp = JSON.stringify(json_obj);
    return json_str_temp;
}


$("#post_to_server").click(post_to_server)

function post_to_server() {
    init_aplipay_code_array()
    chrome.runtime.sendMessage({greeting: "read"}, function (response) {
        if (response.message == "success") {
            var json_str = init_json_str(response);
            $.ajax({
                type: "post",
                async: false,
                //114.67.241.157
                url: "https://localhost/product/save",
                data: json_str,
                contentType: "application/json",
                dataType: "text",
                success: function (data) {
                    if (data == "success") {
                        window.print();
                    } else {
                        alert("保存到服务器失败\r\n失败原因：" + data)
                    }
                }
            });
        } else {
            alert(response.info)
        }
    })
}

var print_str

$("#post_to_local").click(post_to_local)

function post_to_local() {
    init_aplipay_code_array()
    chrome.runtime.sendMessage({greeting: "read"}, function (response) {
        if (response.message == "success") {
            var json_str = init_json_str(response);
            export_raw(currentId + '.json', json_str);
            window.print();
        } else {
            alert(response.info)
        }
    })
}


//打印监听
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
    // $("body").html(tempHtml);
    window.location.reload()
}


//打印
function export_raw(name, data) {
    var urlObject = window.URL || window.webkitURL || window;
    var export_blob = new Blob([data]);
    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
    save_link.href = urlObject.createObjectURL(export_blob);
    save_link.download = name;
    fake_click(save_link);
}

//打印
function fake_click(obj) {
    var ev = document.createEvent("MouseEvents");
    ev.initMouseEvent(
        "click", true, false, window, 0, 0, 0, 0, 0
        , false, false, false, false, 0, null
    );
    obj.dispatchEvent(ev);
}

