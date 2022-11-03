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


    <link rel="stylesheet" href="/comport-master/assets/css/style.css">


    <style>
        input[type=text]{
            width:100%;
            border:2px solid #aaa;
            border-radius:4px;
            margin: 8px 0;
            outline: none;
            padding:8px;
            box-sizing: border-box;
            transition:.3s;
        }

        textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: solid 2px #1E90FF;
            border-radius: 5px;
            font-size: 16px;
            resize: both;
        }
    </style>



</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>
<!-- Header Section End -->



<!--  section 합치는 부분  들어갈 부분 -->


<section class="contact-form section-padding3">
    <div class="container">
        <div class="row">
            <div class="container">


                <form id="frm" name="frm" method="post" action="/notice/insertBoard">
                    제목<input type="text"  name="title" cols="100" rows="1"  placeholder="제목"  required>
                    작성자 <input type="text" name="email" readonly value="helper">
                    <br> <br>
                    문의글 내용<textarea name="contents" cols="100" rows="10"  placeholder="문의글 내용"  required></textarea>




                    <br><br>

                    <div class="text-center">
                        <button type="submit" class="template-btn">등록하기</button>

                    </div>
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