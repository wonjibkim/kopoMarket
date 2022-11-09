<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%

//전달받은 메시지
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>처리페이지</title>
<script type="text/javascript">

    <%if(msg=="로그인 성공"){%>
	alert("<%=msg%>");
	top.location.href="/market/index";
    <%}else if(msg=="로그인 실패"){%>
    alert("<%=msg%>");
    top.location.href="/login/login";
    <%}else if(msg=="회원가입 되었습니다."){%>
    alert("<%=msg%>");
    top.location.href="/signup/login";
    <%}else if(msg=="이미 가입된 이메일 주소입니다."){%>
    alert("<%=msg%>");
    top.location.href="/signup/SignupUser";
    <%}%>
		
</script>
</head>
<body>

</body>
</html>