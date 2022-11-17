<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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

        function doRegUserCheck(f) {
            if (f.p_name.value === "") {
                alert("음식명을 입력하세요.");
                f.p_name.focus();
                return false;
            }

            if (f.p_price.value === "") {
                alert("상품가격을 입력하세요.");
                f.p_price.focus();
                return false;
            }

            if (f.p_sell.value === "") {
                alert("판매수량을 입력하세요.");
                f.p_sell.focus();
                return false;
            }

            if (f.p_info.value === "") {
                alert("상품설명을 입력하세요.");
                f.p_info.focus();
                return false;
            }
            if (f.p_period.value === "") {
                alert("유통기한을 입력하세요.");
                f.p_period.focus();
                return false;
            }

            if (f.p_category.value === "") {
                alert("카테고리를 선택해주세요.");
                f.p_category.focus();
                return false;
            }

            if (f.p_discount.value === "") {
                alert("할인율을 입력하세요.");
                f.p_discount.focus();
                return false;
            }

            if (f.p_ancestry.value === "") {
                alert("원산지를 입력하세요.");
                f.p_ancestry.focus();
                return false;
            }

            if (f.p_weight.value === "") {
                alert("중량을 입력하세요.");
                f.p_weight.focus();
                return false;
            }









        }



    </script>

<%--    <style>--%>
<%--        .form-select {--%>
<%--            width: 100%;--%>
<%--            height: 50px;--%>
<%--            font-size: 16px;--%>
<%--            color: #6f6f6f;--%>
<%--            padding-left: 20px;--%>
<%--            margin-bottom: 30px;--%>
<%--            border: 1px solid #ebebeb;--%>
<%--            border-radius: 4px;--%>
<%--        }--%>



<%--    </style>--%>

</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>





<section class="breadcrumb-section set-bg" data-setbg="ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Contact Us</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html">Home</a>
                        <span>Contact Us</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>







<!-- Header Section End -->
<div class="contact-form spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact__form__title">
                    <h2>식자재 등록</h2>
                </div>
            </div>
        </div>


        <form name="f" method="post" action="/market/FoodInsert" onsubmit="return doRegUserCheck(this);"  enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>상품명<span></span></p>
                        <input type="text" name="p_name" >
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>상품가격</p>
                        <input type="text" name="p_price">
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>판매수량</p>
                        <input type="number" min="0" max="1000" step="1" name="p_sell" >
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>상품 할인율</p>
                        <input type="number" min="0" max="100" step="1" name="p_discount">
                    </div>
                </div>


                <div class="col-lg-6">
                    <div class="checkout__input" name="p_ancestry">
                        <p>원산지</p>
                        <input type="text" name="p_ancestry">
                    </div>
                </div>


                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>중량 ( 단위 : g )</p>
                        <input type="text" name="p_weight">
                    </div>
                </div>






                <div class="col-lg-6">
                <div class="input-group-icon mt-10">
                    <p>상품 카테고리</p>


                    <div class="form-select" id="default">
                        <select name="p_category" style="/* display: none; */" class="nice-select">
                            <option value="0" selected="">야채</option>
                            <option value="1">과일</option>
                            <option value="2">과자</option>
                        </select>
                    </div>


                </div>
                </div>









                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>유통기한</p>
                        <input type="date" name="p_period">
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="checkout__input">
                        <p>파일사진</p>
                        <input type="file"  multiple="multiple" name="fileUpload"/>
                    </div>
                </div>


            </div>




            <div class="checkout__input">
                <p>상품 설명</p>
                <textarea name="p_info"></textarea>


            </div>




            <div class="col-lg-12">
                <div class="text-center">

                <button type="submit" class="site-btn">등록하기</button>
            </div>

            </div>

        </form>


    </div>
</div>







<br>
<br>
s











<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>