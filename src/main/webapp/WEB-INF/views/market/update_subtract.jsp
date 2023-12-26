<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글보기</title>

    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/ogani-master/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/ogani-master/css/style.css" type="text/css">

    <!--    registration css-->
    <link rel="stylesheet" href="/comport-master/assets/css/animate-3.7.0.css">
    <link rel="stylesheet" href="/comport-master/assets/css/font-awesome-4.7.0.min.css">
    <link rel="stylesheet" href="/comport-master/assets/fonts/flat-icon/flaticon.css">
    <link rel="stylesheet" href="/comport-master/assets/css/bootstrap-4.1.3.min.css">
    <link rel="stylesheet" href="/comport-master/assets/css/owl-carousel.min.css">
    <link rel="stylesheet" href="/comport-master/assets/css/nice-select.css">
    <link rel="stylesheet" href="/comport-master/assets/css/style.css">


    <%--    js--%>
    <script src="/ogani-master/js/jquery-3.3.1.min.js"></script>
    <script src="/ogani-master/js/bootstrap.min.js"></script>
    <script src="/ogani-master/js/jquery.nice-select.min.js"></script>
    <script src="/ogani-master/js/jquery-ui.min.js"></script>
    <script src="/ogani-master/js/jquery.slicknav.js"></script>
    <script src="/ogani-master/js/mixitup.min.js"></script>
    <script src="/ogani-master/js/owl.carousel.min.js"></script>
    <script src="/ogani-master/js/main.js"></script>

<%--    스캐너 js--%>

    <script src="/quaggaJS/dist/quagga.min.js"></script>
    <script src="/quaggaJS/dist/quagga.min.js"></script>
    <script src="/js/quagga.js"></script>



    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        .login-page {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
        }
        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }
        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #4CAF50;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }
        .form button:hover,.form button:active,.form button:focus {
            background: #43A047;
        }
        .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
        }
        .form .message a {
            color: #4CAF50;
            text-decoration: none;
        }
        .form .register-form {
            display: none;
        }

        .container:before, .container:after {
            content: "";
            display: block;
            clear: both;
        }
        .container .info {
            margin: 50px auto;
            text-align: center;
        }
        .container .info h1 {
            margin: 0 0 15px;
            padding: 0;
            font-size: 36px;
            font-weight: 300;
            color: #1a1a1a;
        }
        .container .info span {
            color: #4d4d4d;
            font-size: 12px;
        }
        .container .info span a {
            color: #000000;
            text-decoration: none;
        }
        .container .info span .fa {
            color: #EF3B3A;
        }

    </style>

</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>

<section class="breadcrumb-section set-bg" data-setbg="/ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>장바구니 수량 감소</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html">Home</a>
                        <span>Checkout</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>






<section class="contact-form section-padding3" style=" margin: auto;
" >


        <form method="post" name="ex_form" action="update_subtract" style="width:650px; margin: auto;">
<%--            <input type="text" name="p_barcode" value="" >--%>
<%--            <button type="submit" class="site-btn">수량빼기</button>--%>


    <input type="text" name="p_barcode" value="">
    <input type="button" name="anything_name" value="수량 1개 감소" onclick=ex_form.submit(); style="background-color:#7fad39; color: white;  font-size: 20px;">
    <input type="button" id="btn" value="바코드 스캔/ 중지"  style="background-color:#7fad39; color: white; font-size: 20px;"/>

    <div id="scanner-container" style="margin: auto"></div>


        </form>
</section>





















<script>
    var _scannerIsRunning = false;

    function startScanner() {

        Quagga.init({
            inputStream: {
                name: "Live",
                type: "LiveStream",
                target: document.querySelector('#scanner-container'),
                constraints: {
                    width: 640,
                    height: 480,
                    facingMode: "environment"
                },
            },
            decoder: {
                readers : ['code_128_reader'],
                debug: {
                    showCanvas: true,
                    showPatches: true,
                    showFoundPatches: true,
                    showSkeleton: true,
                    showLabels: true,
                    showPatchLabels: true,
                    showRemainingPatchLabels: true,
                    boxFromPatches: {
                        showTransformed: true,
                        showTransformedBox: true,
                        showBB: true
                    }
                }
            },

        }, function (err) {
            if (err) {
                console.log(err);
                return
            }

            console.log("Initialization finished. Ready to start");
            Quagga.start();

            // Set flag to is running
            _scannerIsRunning = true;
        });

        Quagga.onProcessed(function (result) {
            var drawingCtx = Quagga.canvas.ctx.overlay,
                drawingCanvas = Quagga.canvas.dom.overlay;


        });


        Quagga.onDetected(function (result) {
            console.log("Barcode detected and processed : [" + result.codeResult.code + "]", result);
            alert("Barcode detected and processed : [" + result.codeResult.code + "]")
            count_num = result.codeResult.code;
            document.ex_form.p_barcode.value = count_num;
        });
    }


    // Start/stop scanner
    document.getElementById("btn").addEventListener("click", function () {
        if (_scannerIsRunning) {
            Quagga.stop();
        } else {
            startScanner();
        }
    }, false);
</script>




<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>