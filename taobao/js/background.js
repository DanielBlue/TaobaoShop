// 获取当前选项卡ID
function getCurrentTabId(callback) {
    chrome.tabs.query({active: true, currentWindow: true}, function (tabs) {
        if (callback) callback(tabs.length ? tabs[0].id : null);
    });
}

function sendMessageToContentScript(message, callback) {
    getCurrentTabId(function (tabId) {
        chrome.tabs.sendMessage(tabId, message, function (response) {
            if (callback) callback(response);
        });
    });
}

var json
var print_str
var currentId

chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {
    // sendMessageToContentScript({message:"success"});//做出回应
    if (request.greeting == "save") {
        //存入
        if (request.json != null) {
            json = request.json
            print_str = request.print_str
            currentId = request.currentId
            sendResponse({message: "success", info: "存入成功"})
        } else {
            sendResponse({message: "failed", info: "存入数据为空，请刷新再试"})
        }
    } else if (request.greeting == "read") {
        if (json != null) {
            sendResponse({message: "success", info: "成功", json: json, print_str: print_str, currentId: currentId})
        } else {
            sendResponse({message: "failed", info: "数据为空"})
        }
    }
});