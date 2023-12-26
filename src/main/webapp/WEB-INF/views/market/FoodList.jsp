<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            location.href = "/market/FoodEditInfo?p_num=" + p_num;
        }

        //해당 등록음식 삭제하기
        function FoodDelete(p_num) {
            console.log(p_num);
            $.ajax({
                type: 'POST',
                url: "/market/FoodDelete",
                data: {"p_num": p_num},//보낼 데이터의 키값과 value값
                dataType: "json",
                success(result) {
                    console.log(result);
                    if (result == null) {
                        console.log(result)
                        alert("실패했습니다.")
                    } else {
                        console.log(result)
                        $('#test').empty();
                        var str = '';
                        for (var i = 0; i < result.length; i++) {
                            str +=
                                '<tr><td class="shoping__cart__item">' +


                                '<img style="width: 150px; height: 150px; object-fit: cover;" src="'+
                                result[i].p_filePath + '/' + result[i].p_fileName +
                                '" alt="">'




                                +
                                '<h5><a href="javascript:doDetail('+result[i].food_num
                                +');">'+ result[i].p_name + '</a></h5></td>'


                                + '<td class="shoping__cart__price">' + result[i].p_price + '</td>'
                                + '<td class="shoping__cart__price">' +       result[i].p_sell   +'</td>'
                                + '<td class="shoping__cart__price">' +       result[i].p_period   +'</td>'
                                + '<td class="shoping__cart__price">' +       result[i].p_ancestry   +'</td>'

                                +'<td class="shoping__cart__item__close"><a href="javascript:FoodDelete('
                                +result[i].p_num
                                +');"><span class="icon_close"></span></a></td></tr>'

                        }
                        $('#test').append(str);
                    }
                }
            })
        }


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

    <style type="text/css">
        shoping__cart__item > img {
            width: 300px;
            height: 150px;
            object-fit: cover;
        }
    </style>


</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp" %>

<%--<table border="1" width="100%">--%>


<%--<section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-lg-12 text-center">--%>
<%--                <div class="breadcrumb__text">--%>
<%--                    <h2>판매자 상품 조회</h2>--%>
<%--                    <!----%>
<%--                                            <div class="breadcrumb__option">--%>
<%--                                                <a href="./index.html">신선한</a>--%>
<%--                                                <span>상품을</span>--%>
<%--                                            </div>--%>
<%--                    -->--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
<!-- Breadcrumb Section End -->

<!-- Shoping Cart Section Begin -->


<section class="breadcrumb-section set-bg" data-setbg="/ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>판매 음식</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html"></a>
                        <span></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



<section class="shoping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <table>
                        <thead>

                        <tr>
                            <th class="shoping__product">상품</th>
                            <th>가격</th>
                            <th>수량</th>
                            <th>유통기한</th>
                            <th>원산지</th>
                        </tr>
                        </thead>


                        <tbody id="test">

                        <%
                            for (int i = 0; i < rList.size(); i++) {
                                FoodDTO rDTO = rList.get(i);

                                if (rDTO == null) {
                                    rDTO = new FoodDTO();
                                }

                        %>

                        <%--                            <img src="<%=rDTO.getP_filePath()%>" alt=""> 이미지 넣기--%>
                        <tr>
                            <td class="shoping__cart__item">
                                <img style="width: 150px; height: 150px; object-fit: cover;"
                                     src="<%=rDTO.getP_filePath()%>/<%=rDTO.getP_fileName()%>" alt="">
                                <h5>  <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getP_num())%>');"> <%=CmmUtil.nvl(rDTO.getP_name())%> </a> </h5>
                            </td>

                            <td class="shoping__cart__price"><%=CmmUtil.nvl(rDTO.getP_price())%>
                            </td>

                            <%--                            수량 text 상자로--%>

                            <%--                            <td class="shoping__cart__quantity">--%>
                            <%--                                <div class="quantity">--%>
                            <%--                                    <div class="pro-qty">--%>
                            <%--                                        <input type="text" value="<%=CmmUtil.nvl(rDTO.getP_sell())%>">--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </td>--%>

                            <td class="shoping__cart__total"><%=CmmUtil.nvl(rDTO.getP_sell())%>
                            </td>

                            <td class="shoping__cart__total"><%= rList.get(i).getP_period()%>
                            </td>

                            <td class="shoping__cart__total"><%= rList.get(i).getP_ancestry()%>
                            </td>

                            <td class="shoping__cart__item__close">

                                <%--                                <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getP_num())%>');">--%>
                                <%--                                    <%=CmmUtil.nvl(rDTO.getP_name())%> </a>--%>
                                <a href="javascript:FoodDelete('<%=CmmUtil.nvl(rDTO.getP_num())%>');">
                                    <span class="icon_close"></span>
                                </a>


                            </td>


                            <%--                            <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getP_num())%>');">--%>
                            <%--                                <%=CmmUtil.nvl(rDTO.getP_name())%> </a>--%>


                        </tr>
                        <% } %>
                        </tbody>


                    </table>


                </div>
            </div>
        </div>


        <div class="row">


            <div class="col-lg-12">
                <div class="shoping__cart__btns">
                    <%--                    <a href="/market/insert" class="primary-btn cart-btn">물품등록</a>--%>
                    <a href="/market/FoodReg" class="primary-btn cart-btn cart-btn-right"><span
                            class="icon_loading"></span>
                        물품등록</a>
                </div>
                <div class="shoping__cart__btns">
                    <%--                    <a href="/market/insert" class="primary-btn cart-btn">물품등록</a>--%>
                    <a href="/market/FoodListShelf" class="primary-btn cart-btn cart-btn-right"><span
                            class="icon_loading"></span>
                        유통기한 지난 품목 관리</a>
                </div>
                <div class="shoping__cart__btns">
                    <%--                    <a href="/market/insert" class="primary-btn cart-btn">물품등록</a>--%>
                    <a href="/market/FoodListZero" class="primary-btn cart-btn cart-btn-right"><span
                            class="icon_loading"></span>
                        재고 0인 것 게시판</a>
                </div>



            </div>
            <!--
                            <div class="col-lg-6">
                                <div class="shoping__continue">
                                    <div class="shoping__discount">
                                        <h5>Discount Codes</h5>
                                        <form action="#">
                                            <input type="text" placeholder="Enter your coupon code">
                                            <button type="submit" class="site-btn">APPLY COUPON</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="shoping__checkout">
                                    <h5>Cart Total</h5>
                                    <ul>
                                        <li>Subtotal <span>$454.98</span></li>
                                        <li>Total <span>$454.98</span></li>
                                    </ul>
                                    <a href="#" class="primary-btn">PROCEED TO CHECKOUT</a>
                                </div>
                            </div>
            -->
        </div>
    </div>
</section>
<!-- Shoping Cart Section End -->


<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp" %>
<!-- Footer Section End -->
</body>
</html>