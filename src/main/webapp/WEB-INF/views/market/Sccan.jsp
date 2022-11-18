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
        #img_container {
            position:absolute;
            width:100%;
            height:100%;

        }

        #img_container img {
            position:absolute;
            top:50%;
            left:50%;
            margin-top:-25px;
            margin-left:-25px;

        }
    </style>


</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>






<%--<script src="/quaggaJS/dist/quagga.min.js"></script>--%>


<div id="scanner-container"></div>
<input type="button" id="btn" value="Start/Stop the scanner" />

<!-- Include the image-diff library -->
<script src="/quaggaJS/dist/quagga.min.js"></script>


<!-- Div to show the scanner -->

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

                readers: [

                    "code_93_reader"

                ],

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



            if (result) {

                if (result.boxes) {

                    drawingCtx.clearRect(0, 0, parseInt(drawingCanvas.getAttribute("width")), parseInt(drawingCanvas.getAttribute("height")));

                    result.boxes.filter(function (box) {

                        return box !== result.box;

                    }).forEach(function (box) {

                        Quagga.ImageDebug.drawPath(box, { x: 0, y: 1 }, drawingCtx, { color: "green", lineWidth: 2 });

                    });

                }



                if (result.box) {

                    Quagga.ImageDebug.drawPath(result.box, { x: 0, y: 1 }, drawingCtx, { color: "#00F", lineWidth: 2 });

                }



                if (result.codeResult && result.codeResult.code) {

                    Quagga.ImageDebug.drawPath(result.line, { x: 'x', y: 'y' }, drawingCtx, { color: 'red', lineWidth: 3 });

                }

            }

        });





        Quagga.onDetected(function (result) {

            console.log("Barcode detected and processed : [" + result.codeResult.code + "]", result);

            alert("Barcode detected and processed : [" + result.codeResult.code + "]")

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



<form method="post" name="ex_form" action="doService">
    <input type="text" name="target_name" value="result.codeResult.code">
    <input type="button" name="anything_name" value="submit" onclick=ex_form.submit();>
</form>



<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>