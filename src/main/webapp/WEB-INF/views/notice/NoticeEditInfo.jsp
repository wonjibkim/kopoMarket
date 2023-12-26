<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
	NoticeDTO rDTO = (NoticeDTO) request.getAttribute("rDTO");

//공지글 정보를 못불러왔다면, 객체 생성
	if (rDTO == null) {
		rDTO = new NoticeDTO();

	}

	String ss_email = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));

	System.out.println("email : " + CmmUtil.nvl(rDTO.getEmail()));
	System.out.println("ss_email : " + ss_email);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>게시판 글 수정하기</title>
	<link rel="stylesheet" href="/comport-master/assets/css/style.css">
</head>
<body>

<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>
<!-- Header Section End -->

<section class="contact-form section-padding3">
	<div class="container">
		<div class="row">
			<div class="container">


				<form id="frm" name="frm" method="post" action="/notice/updateBoard?nSeq=<%=request.getParameter("nSeq")%>">
					제목<input type="text"  name="title" cols="100" rows="1"  value="<%=CmmUtil.nvl(rDTO.getTitle())%>">
					작성자<input type="text" name="email" readonly value="<%=CmmUtil.nvl(rDTO.getEmail())%>">
					<br> <br>
					문의글내용<textarea name="contents" cols="100" rows="10"><%=CmmUtil.nvl(rDTO.getContents()).replaceAll("\r\n", "<br/>") %></textarea>




					<br><br>

					<div class="text-center">
						<button type="submit" class="template-btn">수정하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->