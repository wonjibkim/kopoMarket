<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    List<NoticeDTO> rList = (List<NoticeDTO>) request.getAttribute("rList");

    if (rList == null) {
        rList = new ArrayList<NoticeDTO>();

    }
%>

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
    <script>
    function doDetail(seq) {
    location.href = "/noticeinfo?nSeq=" + seq;
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



<!--  section 합치는 부분  들어갈 부분 -->


<section class="notice">
    <div class="page-title">
        <div class="container">
            <h3>고객 센터 게시판</h3>
        </div>
    </div>

    <!-- board seach area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="">
                    <div class="search-wrap">
                        <label for="search" class="blind">공지사항 내용 검색</label>
                        <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
                        <button type="submit" class="btn btn-dark">검색</button>
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
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-date">작성자</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (int i = 0; i < rList.size(); i++) {
                        NoticeDTO rDTO = rList.get(i);

                        if (rDTO == null) {
                            rDTO = new NoticeDTO();
                        }

                %>
                <tr>
                    <td>
                        <%=CmmUtil.nvl(rDTO.getNotice_seq())%>
                    </td>
                    <td>
                        <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getNotice_seq())%>');">
                            <%=CmmUtil.nvl(rDTO.getTitle()) %>
                        </a>
                    </td>
                    <td>
                    <%=CmmUtil.nvl(rDTO.getEmail()) %>
                    </td>
                </tr>
                <% }%>
                </tbody>
            </table>
            <div class="text-center">
                <button style="float: right" type="submit" type="button" onclick="location.href='/notice/noticeboard'" class="template-btn">문의하기</button>
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