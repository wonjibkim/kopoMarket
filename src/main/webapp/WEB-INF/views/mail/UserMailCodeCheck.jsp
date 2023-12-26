<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    String jspRes = CmmUtil.nvl((String)request.getAttribute("res"), "0");

    String email_user = CmmUtil.nvl(request.getParameter("email_user"));
%>
<!DOCTYPE html>
<html lang="zxx">


<%--메일 인증 --%>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <!-- Google Font -->
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


    <style>
        .signup2 {

            height: 600px;
            text-align: center;
        }
        .signupbutton {
            color: #ffffff;
            font-size: 12.8px;
            background: #F8B600;
            margin: 10px 10px 0px 0px;
            padding: 0px 30px;
            height: 30px;
            width: 160px;
            border-radius: 20px;
        }
        .signup2.nice-select


    </style>
    <script type="text/javascript">
        //회원가입 정보의 유효성 체크하기
        function doRegUserCheck(f){
            if (f.user_email.value === ""){
                alert("아이디를 입력하세요");
                f.user_email.focus();
                return false;
            }

            if (f.pwd_user.value === ""){
                alert("비밀번호를 입력하세요");
                f.pwd_user.focus();
                return false;
            }

        }
    </script>


</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="/ogani-master/img/logo.png" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
        </ul>
        <div class="header__cart__price">item: <span>$150.00</span></div>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__language">
            <img src="/ogani-master/img/language.png" alt="">
            <div>English</div>
            <span class="arrow_carrot-down"></span>
            <ul>
                <li><a href="#">Spanis</a></li>
                <li><a href="#">English</a></li>
            </ul>
        </div>
        <div class="header__top__right__auth">
            <a href="/login/login"><i class="fa fa-user"></i> Login </a>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="./index.html">Home</a></li>
            <li><a href="./shop-grid.html">Shop</a></li>
            <li><a href="#">Pages</a>
                <ul class="header__menu__dropdown">
                    <li><a href="./shop-details.html">Shop Details</a></li>
                    <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                    <li><a href="./checkout.html">Check Out</a></li>
                    <li><a href="./blog-details.html">Blog Details</a></li>
                </ul>
            </li>
            <li><a href="./blog.html">Blog</a></li>
            <li><a href="./contact.html">Contact</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
    </div>
    <div class="humberger__menu__contact">
        <ul>
            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
            <li>Free Shipping for all Order of $99</li>
        </ul>
    </div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>
<!-- Header Section End -->



<!--  section 합치는 부분  들어갈 부분 -->


<section class="pricing-table section-padding">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-top text-center">
                    <h2> 메일 인증받기  </h2>
                    <p>Please verify your email first</p>
                </div>
            </div>
        </div>

        <!--            메일 인증 받기   -->
        <div>
            <div class="signup2">

                    <%
                        if (jspRes.equals("1")){
                            out.println(email_user+"님에게 인증번호가 전송되었습니다. \n 메일을 확인하고 인증번호를 입력해주세요 \n\n");
                        }else {
                            out.println(email_user+"님에게 인증번호가 전송되지않았습니다. 다시한번 시도해주세요 \n\n");
                        }
                    %>

                <form method="post" action="/signup/userMailCodeCheck" onsubmit=""><br><br>
                    <input type="text" name="email_user" value="<%=email_user%>" style="display: none;">
                    <input type="text" name="mail_code" id="MailCode" placeholder="E-Mail_Code" onfocus="this.placeholder=''" onblur="this.placeholder = 'E-Mail'" required>
                    <input type="submit" value="회원가입 하러가기">


                </form>




            </div>
        </div>
    </div>
</section>
<!-- Pricing Table End -->







<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->

<!-- Js Plugins -->
<script src="ogani-master/js/jquery-3.3.1.min.js"></script>
<script src="ogani-master/js/bootstrap.min.js"></script>
<script src="ogani-master/js/jquery.nice-select.min.js"></script>
<script src="ogani-master/js/jquery-ui.min.js"></script>
<script src="ogani-master/js/jquery.slicknav.js"></script>
<script src="ogani-master/js/mixitup.min.js"></script>
<script src="ogani-master/js/owl.carousel.min.js"></script>
<script src="ogani-master/js/main.js"></script>



</body>

</html>