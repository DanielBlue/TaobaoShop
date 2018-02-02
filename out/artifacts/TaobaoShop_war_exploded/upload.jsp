<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/2
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
</head>
<body>
    <div align="center" style="margin-top: 100px">
        <form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/upload" method="post"
              enctype="multipart/form-data">
            <input type="file" name="upload" multiple />
            <button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
                &#30830;&#23450;
            </button>
        </form>
    </div>
</body>
</html>
