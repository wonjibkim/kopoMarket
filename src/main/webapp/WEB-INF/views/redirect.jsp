<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String msg = (String)request.getAttribute("msg");
    String url = (String)request.getAttribute("url");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> 웹페이지 제목 </title>
    <script type="text/javascript">
        alert("<%=msg %>")
        location.href = "<%=url %>"
    </script>
</head>
<body>

</body>
</html>