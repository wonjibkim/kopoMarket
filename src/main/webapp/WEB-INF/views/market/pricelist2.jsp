<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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




    <!--      게시판 스타일 -->
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
        }
        section.notice {
            padding: 80px 0;
        }

        .page-title {
            margin-bottom: 60px;
        }
        .page-title h3 {
            font-size: 28px;
            color: #333333;
            font-weight: 400;
            text-align: center;
        }

        #board-search .search-window {
            padding: 15px 0;
            background-color: #f9f7f9;
        }
        #board-search .search-window .search-wrap {
            position: relative;
            /*   padding-right: 124px; */
            margin: 0 auto;
            width: 80%;
            max-width: 564px;
        }
        #board-search .search-window .search-wrap input {
            height: 40px;
            width: 100%;
            font-size: 14px;
            padding: 7px 14px;
            border: 1px solid #ccc;
        }
        #board-search .search-window .search-wrap input:focus {
            border-color: #333;
            outline: 0;
            border-width: 1px;
        }
        #board-search .search-window .search-wrap .btn {
            position: absolute;
            right: 0;
            top: 0;
            bottom: 0;
            width: 108px;
            padding: 0;
            font-size: 16px;
        }

        .board-table {
            font-size: 13px;
            width: 100%;
            border-top: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
        }

        .board-table a {
            color: #333;
            display: inline-block;
            line-height: 1.4;
            word-break: break-all;
            vertical-align: middle;
        }
        .board-table a:hover {
            text-decoration: underline;
        }
        .board-table th {
            text-align: center;
        }

        .board-table .th-num {
            width: 100px;
            text-align: center;
        }

        .board-table .th-date {
            width: 200px;
        }

        .board-table th, .board-table td {
            padding: 14px 0;
        }

        .board-table tbody td {
            border-top: 1px solid #e7e7e7;
            text-align: center;
        }

        .board-table tbody th {
            padding-left: 28px;
            padding-right: 14px;
            border-top: 1px solid #e7e7e7;
            text-align: left;
        }

        .board-table tbody th p{
            display: none;
        }

        .btn {
            display: inline-block;
            padding: 0 30px;
            font-size: 15px;
            font-weight: 400;
            background: transparent;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            border: 1px solid transparent;
            text-transform: uppercase;
            -webkit-border-radius: 0;
            -moz-border-radius: 0;
            border-radius: 0;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            -ms-transition: all 0.3s;
            -o-transition: all 0.3s;
            transition: all 0.3s;
        }

        .btn-dark {
            background: #555;
            color: #fff;
        }

        .btn-dark:hover, .btn-dark:focus {
            background: #373737;
            border-color: #373737;
            color: #fff;
        }

        .btn-dark {
            background: #555;
            color: #fff;
        }

        .btn-dark:hover, .btn-dark:focus {
            background: #373737;
            border-color: #373737;
            color: #fff;
        }

        /* reset */

        * {
            list-style: none;
            text-decoration: none;
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        .clearfix:after {
            content: '';
            display: block;
            clear: both;
        }
        .container {
            width: 1100px;
            margin: 0 auto;
        }
        .blind {
            position: absolute;
            overflow: hidden;
            clip: rect(0 0 0 0);
            margin: -1px;
            width: 1px;
            height: 1px;
        }

    </style>
    <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
    <script>

        function doSearch() {
            var keyword = document.getElementById("search").value;
            console.log(keyword);
            location.href = "/price/list?keyword=" + keyword;
        }
    </script>

</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>



<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>
<!-- Header Section End -->

<section class="breadcrumb-section set-bg" data-setbg="/ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>음식 시세 보기</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html"></a>
                        <span></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--  section 합치는 부분  들어갈 부분 -->


<section class="notice">


    <!-- board seach area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="">
                    <div class="search-wrap">
                        <label for="search" class="blind">공지사항 내용 검색</label>
                        <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
                        <input type="button" class="btn btn-dark" value="검색" onclick="doSearch()"/>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- board list area -->
    <div id="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-num">품목명</th>
                    <th scope="col" class="th-title">지역</th>
                    <th scope="col" class="th-date">무게</th>
                    <th scope="col" class="th-date">가격</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="data">
                    <tr>
                        <td>${data.PRDLST_NM}</td>
                        <td>${data.AREA_NM}</td>
                        <td>${data.EXAMIN_UNIT}</td>
                        <td>${data.AMT}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <div class="product__pagination">
                <ul class="paging">
                    <c:if test="${paging.prev}">
                        <span><a href='<c:url value="/price/list?page=${paging.startPage-1}"/>'>이전</a></span>
                    </c:if>
                    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
                        <span><a href='<c:url value="/price/list?page=${num}"/>'>${num}</a></span>
                    </c:forEach>
                    <c:if test="${paging.next && paging.endPage>0}">
                        <span><a href='<c:url value="/price/list?page=${paging.endPage+1}"/>'>다음</a></span>
                    </c:if>
                </ul>
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