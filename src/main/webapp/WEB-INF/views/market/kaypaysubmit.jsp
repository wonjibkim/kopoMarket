<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static java.awt.SystemColor.info" %>
<%@ page import="kopo.poly.dto.KakaoPayApprovalVO" %>
<%
    KakaoPayApprovalVO info = (KakaoPayApprovalVO) request.getAttribute("info");

    if (info == null) {
        info = new KakaoPayApprovalVO();
    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글보기</title>




    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="ogani-master/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/style.css" type="text/css">

    <!--    registration css-->
    <link rel="stylesheet" href="/comport-master/assets/css/animate-3.7.0.css">
    <link rel="stylesheet" href="/comport-master/assets/css/font-awesome-4.7.0.min.css">
    <link rel="stylesheet" href="/comport-master/assets/fonts/flat-icon/flaticon.css">
    <link rel="stylesheet" href="/comport-master/assets/css/bootstrap-4.1.3.min.css">
    <link rel="stylesheet" href="/comport-master/assets/css/owl-carousel.min.css">
    <link rel="stylesheet" href="/comport-master/assets/css/nice-select.css">
    <link rel="stylesheet" href="/comport-master/assets/css/style.css">


<%--    js--%>
    <script src="ogani-master/js/jquery-3.3.1.min.js"></script>
    <script src="ogani-master/js/bootstrap.min.js"></script>
    <script src="ogani-master/js/jquery.nice-select.min.js"></script>
    <script src="ogani-master/js/jquery-ui.min.js"></script>
    <script src="ogani-master/js/jquery.slicknav.js"></script>
    <script src="ogani-master/js/mixitup.min.js"></script>
    <script src="ogani-master/js/owl.carousel.min.js"></script>
    <script src="ogani-master/js/main.js"></script>


    <script type="text/javascript">

    </script>




</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>

<section class="breadcrumb-section set-bg" data-setbg="/ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>결제완료</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html"></a>
                        <span></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<br>
<br>

<div class="checkout__order" style="margin: auto; width: 500px; ">
    <h4>주문 정보</h4>
    <div class="checkout__order__products">주문번호 <span><%=info.getPartner_order_id() %></span></div>
    <ul>
        <li>상품명 <span><%=info.getItem_name() %> 등등</span></li>
        <li> 총 상품수량 <span> <%= info.getQuantity()%></span></li>

    </ul>
    <div class="checkout__order__subtotal">결제금액 <span> <%=info.getAmount().getTotal() %> </span></div>
    <div class="checkout__order__total">결제방법 <span><%= info.getPayment_method_type()%></span></div>

    <p>결제완료 되었습니다. 이용해주셔서 감사합니다</p>


    <button type="button" class="site-btn" onclick="location.href='/market/index'">Home으로 가기</button>
</div>

<br>
<br>






<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>