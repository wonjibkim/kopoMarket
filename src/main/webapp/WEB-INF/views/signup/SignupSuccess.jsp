<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.UserInfoDTO" %>
<%@ page import="org.apache.catalina.User" %>
<%
    String msg = CmmUtil.nvl((String)request.getAttribute("msg"));

    UserInfoDTO uDTO = (UserInfoDTO) request.getAttribute("uDTO");

    if (uDTO == null){
        uDTO = new UserInfoDTO();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> 웹페이지 제목 </title>
    <script type="text/javascript">
        <%if(msg=="회원가입성공"){%>
        alert("<%=CmmUtil.nvl(uDTO.getName_user())%> 님의 회원가입을 축하합니다.");
        top.location.href="/market/index";
        <%}else if(msg=="로그인 실패"){%>
        alert("<%=msg%>");
        top.location.href="/signup/SignupUser";
        <%}%>

    </script>
</head>
<body>
<%=CmmUtil.nvl(uDTO.getName_user())%>  님의 회원가입을 축하들립니다.
</body>
</html>