<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="java.util.ArrayList" %>

<%
//    session.setAttribute("SESSION_USER_ID", "USER01");

    List<FoodDTO> rList = (List<FoodDTO>) request.getAttribute("rList");


    if (rList == null) {
        rList = new ArrayList<FoodDTO>();

    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글보기</title>
    <script type="text/javascript">

        //상세보기 이동
        function doDetail(p_num) {
            location.href = "/market/mainsell?p_num="+ p_num;
        }

        //해당 등록음식 삭제하기
        // function FoodDelete(p_num) {
        //     console.log(p_num);
        //     $.ajax({
        //         type: 'POST',
        //         url :  "/market/FoodDelete",
        //         data : {"p_num" : p_num},//보낼 데이터의 키값과 value값
        //         dataType: "json",
        //         success(result) {
        //             console.log(result);
        //             if(result==null) {
        //                 alert("실패했습니다.")
        //             } else{
        //                 $('#test').empty();
        //                 var str = '';
        //                 for(var i=0; i<result.length;i++) {
        //                     str += '<tr><td class="shoping__cart__item"> <h5><a href="javascript:doDetail(' + result[i].p_num +');">' + result[i].p_name + '</a> </h5></td>' +
        //                         ' <td class="shoping__cart__price">' +result[i].p_price+' <td class="shoping__cart__total">'+ result[i].p_sell + '<td>' +
        //                         '<td class="shoping__cart__total">'+result[i].p_period + '<td>' +
        //                         '<td class="shoping__cart__total">' + result[i].p_ancestry +'<td>'+
        //                         '<a href="javascript:FoodDelete('+result[i].p_num +');"><span class="icon_close"></span></a></td><tr>'
        //
        //                 }
        //                 $('#test').append(str);
        //             }
        //         }
        //     })
        // }


    </script>



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






</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>



카카오페이 결제가 정상적으로 완료되었습니다.

결제일시:     [[${info.approved_at}]]<br/>
주문번호:    [[${info.partner_order_id}]]<br/>
상품명:    [[${info.item_name}]]<br/>
상품수량:    [[${info.quantity}]]<br/>
결제금액:    [[${info.amount.total}]]<br/>
결제방법:    [[${info.payment_method_type}]]<br/>



<h2>[[${info}]]</h2>








<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>