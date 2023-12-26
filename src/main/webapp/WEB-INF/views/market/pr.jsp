<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static java.awt.SystemColor.info" %>
<%@ page import="kopo.poly.dto.KakaoPayApprovalVO" %>
<%@ page import="kopo.poly.dto.PayDTO" %>
<%
    List<PayDTO> rList = (List<PayDTO>) request.getAttribute("rList");

    if (rList == null) {
        rList = new ArrayList<>();
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



    <link href="/fullcalendar-5.11.0/lib/main.css" rel="stylesheet">
    <script src="/fullcalendar-5.11.0/lib/main.js"></script>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/fullcalendar-5.11.0/lib/locales/ko.js"></script>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl,
                {


                    initialView: 'dayGridMonth',// 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)

                    headerToolbar: {// 헤더에 표시할 툴 바
                        left: '',
                        center: 'title',
                        right: 'prev,next today'

                    },

                    titleFormat : function(date) {
                        return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
                    },
                    nowIndicator: true, // 현재 시간 마크
                    locale: 'ko', // 한국어 설정



                    events: [
                        <%
                               for(PayDTO mDTO : rList) {

                                   String name = mDTO.getP_name();
                                   int count = mDTO.getCart_count();
                                   int price = Integer.parseInt(mDTO.getP_price());
                                   String date =mDTO.getPay_date();

                                   %>
                        {
                            title: '<%=name%>' + ":"  +'<%=count%>' + "x" + '<%=price%>' +'=' +'<%=price*count%>원',
                            start: '<%=date%>',
                            end: '<%=date%>'
                        },

                        <%
                               }
                           %>
                    ]

                });
            calendar.render();
        });
    </script>




    <style>
        #calendar{
            width:60%;
            margin:20px auto;
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
                    <h2>일별 구매 내역</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html"></a>
                        <span></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<div id='calendar'></div>







<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>