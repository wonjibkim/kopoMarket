<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    //Controller에서 model.addAttribute("res", String.valueOf(res)); 로 부터 저장된 값 불러오기
    //CmmUtil.nvl 함수를 통해 model.addAttribute("res") 저장한 값이 NULL인 경우,
    //0으로 값이 저장되도록 로직 처리함
    //0이면 메일 발송 실패하는 것으로 스프링의 Service에서 정의했기 떄문에 0으로 처리함
    String jspRes = CmmUtil.nvl((String)request.getAttribute("res"), "0");

    //웹 URL로부터 전달받는 값들 (ㅅ프링은 기본적으로 포워드 방식으로 페이지를 이동하기 떄문에
    //url에서 입력받은 request 값을 가져올 수 있음, 일반적인 jsp에선 불가능함
    String email_user = CmmUtil.nvl(request.getParameter("email_user")); //받는사람
    String title = CmmUtil.nvl(request.getParameter("title"));// 제목
    String contents = CmmUtil.nvl(request.getParameter("contents")); //내용
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> 메일 발송 결과 보기 ( 인증번호 ) </title>

</head>
<body>
<%
    //메일 발송이 성공했다면...
    if (jspRes.equals("1")){
        out.println(email_user + "로 메일이 전송하였습니다.");
        //out.println("메일제목 : " + title);
        //out.println("메일내용 : " + contents);

        //메일발송이 실패했다면...
    }else {
        out.println(email_user + "로 메일 전송이 실패했습니다.");
    }
%>

</body>
</html>