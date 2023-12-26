<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title> 회원가입  </title>
    <script type="text/javascript">

        <%if(msg=="회원가입되었습니다."){%>
        alert("<%=CmmUtil.nvl(uDTO.getName_user())%> 님의 회원가입을 축하합니다.");
        top.location.href="/market/index";

        <%}else if(msg=="이미 가입된 이메일 주소입니다."){%>
        alert("<%=msg%>");
        top.location.href="/signup/SignupUser";

        <%}else if(msg=="오류로 인해 회원가입이 실패하였습니다."){%>
        alert("<%=msg%>");
        top.location.href="/signup/SignupUser";
        <%}%>

    </script>
</head>
<body>
</body>
</html>