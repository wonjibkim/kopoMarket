<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zxx">

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

            height: 500px;
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

    </style>


    <%-- ㅅㅏ업자등록번호 api --%>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--sweet alert -->
    <script src="/assets/js/jquery-3.6.0.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>






    <%-- 카카오 주소 api --%>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
        //회원가입 정보의 유효청 체크
        function doRegMarketCheck(f){
            if (f.email_market.value === ""){
                alert("이메일을 입력하세요 ");
                f.email_market.focus();
                return false;
            }
            if (f.pwd_market.value === ""){
                alert(" 비밀번호 입력하세요");
                f.pwd_market.focus();
                return false;
            }
            if (f.pwd2_market.value === ""){
                alert(" 비밀번호를 확인하세요");
                f.pwd2_market.focus()
                return false;
            }
            //패스워드 체킹
            if (f.pwd_market.value !== f.pwd2_market.value){
                alert("비밀번호와 서로 일치하지 않습니다.")
                f.pwd2_market.focus();
                return false;
            }
            if (f.name_market.value === ""){
                alert(" 마켓이름을 입력하세요");
                f.name_market.focus();
                return false;
            }
            if (f.name_boss.value === ""){
                alert(" 대표자 성함을 입력하세요");
                f.name_boss.focus();
                return false;
            }
            if (f.addr1_market.value === ""){
                alert(" 주소를 입력하세요");
                f.addr1_market.focus();
                return false;
            }
            if (f.addr2_market.value === ""){
                alert("상세주소를 입력하세요");
                f.addr2_market.focus();
                return false;
            }
        }

        /*카카오 주소 우편번호찾기 api 아직 미완성 */
        function kakaoPost(f){
            new daum.Postcode({
                oncomplete: function (data){
                    /* kakao에서 제공하는 data는 json 구조로 주소 조회 결과값을 전달함
                    *  주요 결과값
                    *  주소 : data.(주소)
                    *  우편번호 : data.zonecode */
                    let address = data.address;//주소
                    let zonecode = data.zonecode;//우편번호
                    f.addr1_market.value = "(" + zonecode + ")" + address
                }
            }).open();
        }



        // 사업자등록번호 조회 api
        function marketCheck() {
            let b_no = document.getElementById("b_no").value;
            let start_dt = document.getElementById("start_dt").value;
            let p_nm = document.getElementById("p_nm").value;
            let data = {
                "businesses": [
                    {
                        "b_no": b_no,
                        "start_dt": start_dt,
                        "p_nm": p_nm,
                    }
                ]
            };
            $.ajax({
                url: "http://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=i6xokwKsdvlwERvOibTsIL6z%2Fx7Y1f3bZZSapli7HQFff1hKKi0qpR1Avr9aof8KZRK4TA%2FqzJT37gpPvxCt7Q%3D%3D",  // serviceKey 값을 xxxxxx에 입력
                type: "POST",
                data: JSON.stringify(data), // json 을 string으로 변환하여 전송
                dataType: "JSON",
                contentType: "application/json",
                accept: "application/json",
                success: function (result) {
                    console.log(result.data[0].valid);
                    if(result.data[0].valid =='01') {
                        swal('정상적인 사업자입니다.', "확인눌러 회원가입을 진행해주세요.", 'success')
                            .then(function(){
                                var form = document.createElement("form");

                                form.setAttribute("charset", "UTF-8");

                                form.setAttribute("method", "Post");  //Post 방식

                                form.setAttribute("action", "/signup/SignupMarket");

                                var hiddenField = document.createElement("input");
                                hiddenField.setAttribute("type", "hidden");
                                hiddenField.setAttribute("name", "b_no");
                                hiddenField.setAttribute("value", b_no);

                                form.appendChild(hiddenField);

                                var hiddenField2 = document.createElement("input");
                                hiddenField2.setAttribute("type", "hidden");
                                hiddenField2.setAttribute("name", "start_dt");
                                hiddenField2.setAttribute("value", start_dt);

                                form.appendChild(hiddenField2);

                                var hiddenField3 = document.createElement("input");
                                hiddenField3.setAttribute("type", "hidden");
                                hiddenField3.setAttribute("name", "p_nm");
                                hiddenField3.setAttribute("value", p_nm);

                                form.appendChild(hiddenField3);
                                document.body.appendChild(form);
                                form.submit();
                            });
                    }else {
                        swal('사업자를 다시 확인해주세요', "확인눌러 다시 진행해주세요", 'error')

                    }},



                error: function (result) {
                    console.log(result.responseText); //responseText의 에러메세지 확인
                    if(result.data[0].valid =='02'){
                        swal('사업자를 다시 확인해주세요', "확인눌러 다시 진행해주세요", 'error')

                    }else{
                        swal('정상적인 사업자입니다.', "확인눌러 회원가입을 진행해주세요.", 'success')
                            .then(function(){
                                location.href="/signup/SignupMarket";
                            });
                    }}
            });
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
            <a href="/login/login"><i class="fa fa-user"></i> Login</a>
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

<%@include file="../includes/header.jsp"%>



<!-- Pricing Table Starts -->
<section class="pricing-table section-padding">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-top text-center">
                    <h2>마트 회원가입  </h2>
                    <p>gormless cheeky bugger he nicked it golly gosh a arse show off show off</p>
                </div>
            </div>
        </div>

        <!--            회원가입 양식 폼 집어넣기   -->
        <div>
            <div class="signup2">
                <form method="post" action="/signup/SignupMarket" onsubmit="return doRegMarketCheck(this);">
                    <p>사업자 등록번호</p>
                    <input type="text" id="b_no" name="b_no" placeholder=" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'-'제거 10자리" onblur="this.placeholder = '-를 제거하고 입력하세요'"><br><br>
                    <p>개업일자</p>
                    <input type="text" id="start_dt" name="start_dt" placeholder=" &nbsp;&nbsp;&nbsp;&nbsp;'YYYYMMDD'형식 " onblur="this.placeholder='YYYYMMDD 형식'"><br><br>
                    <p>대표자 성명</p>
                    <input type="text" id="p_nm" name="p_nm" placeholder="대표자 성명" onblur="this.placeholder='대표자성명'"><br><br>
                    <button type="button" class="signupbutton" value="인증하기" onclick="marketCheck()";> 인증하기</button>

                    <br><br>

                </form>
            </div>
        </div>


    </div>
</section>




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