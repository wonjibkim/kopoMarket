<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.MarketInfoDTO" %>

<%
//    session.setAttribute("SESSION_USER_ID", "USER01");

    List<MarketInfoDTO> mList = (List<MarketInfoDTO>) request.getAttribute("mList");


    if (mList == null) {
        mList = new ArrayList<MarketInfoDTO>();

    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글보기</title>
<%--    <script type="text/javascript">--%>
<%--        var gps_use = null; //gps의 사용가능 여부--%>
<%--        var gps_lat = null; // 위도--%>
<%--        var gps_lng = null; // 경도--%>
<%--        var gps_position; // gps 위치 객체--%>

<%--        gps_check();--%>
<%--        // gps가 이용가능한지 체크하는 함수이며, 이용가능하다면 show location 함수를 불러온다.--%>
<%--        // 만약 작동되지 않는다면 경고창을 띄우고, 에러가 있다면 errorHandler 함수를 불러온다.--%>
<%--        // timeout을 통해 시간제한을 둔다.--%>
<%--        function gps_check(){--%>
<%--            if (navigator.geolocation) {--%>
<%--                var options = {timeout:60000};--%>
<%--                navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);--%>
<%--            } else {--%>
<%--                alert("GPS_추적이 불가합니다.");--%>
<%--                gps_use = false;--%>
<%--            }--%>
<%--        }--%>



<%--        // error발생 시 에러의 종류를 알려주는 함수.--%>
<%--        function errorHandler(error) {--%>
<%--            if(error.code == 1) {--%>
<%--                alert("접근차단");--%>
<%--            } else if( err.code == 2) {--%>
<%--                alert("위치를 반환할 수 없습니다.");--%>
<%--            }--%>
<%--            gps_use = false;--%>
<%--        }--%>



<%--    </script>--%>



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




</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>

<!-- Shoping Cart Section End -->

<div class="map" id ="map"></div>

<%--    <div id="map" style="width:500px;height:400px;"></div>--%>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5d3b6787dfab0b9aebf37f542b61c877&libraries=services"></script>


<script>
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new daum.maps.LatLng(37.450701, 126.570667),
        level: 3
    };

    if (navigator.geolocation) {

        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);

        });

    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = 'geolocation을 사용할수 없어요..'

        displayMarker(locPosition, message);
    }




    var map = new daum.maps.Map(mapContainer, mapOption);

    var geocoder = new daum.maps.services.Geocoder();
    var listData = [
       <% for(MarketInfoDTO mDTO : mList) { %>

           '<%=CmmUtil.nvl(mDTO.getAddr1_market())%>',

       <%}%>
    ];

    var listname = [
        <% for(MarketInfoDTO mDTO : mList) { %>

        '<%=CmmUtil.nvl(mDTO.getName_market())%>',

        <%}%>


    ];

    listData.forEach(function(addr, index) {
        geocoder.addressSearch(addr, function(result, status) {
            if (status === daum.maps.services.Status.OK) {
                var coords = new daum.maps.LatLng(result[0].y, result[0].x);

                var marker = new daum.maps.Marker({
                    map: map,
                    position: coords
                });
                var infowindow = new daum.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">' + listname[index] + '</div>',
                    disableAutoPan: true
                });
                infowindow.open(map, marker);
            }
        });
    });

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    // 지도에 마커와 인포윈도우를 표시하는 함수입니다
    function displayMarker(locPosition, message) {

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });

        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
    }

</script>




</div>
<!-- Map End -->

<!-- Contact Form Begin -->
<div class="contact-form spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="contact__form__title">
                    <h2>Leave Message</h2>
                </div>
            </div>
        </div>
        <form action="#">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <input type="text" placeholder="Your name">
                </div>
                <div class="col-lg-6 col-md-6">
                    <input type="text" placeholder="Your Email">
                </div>
                <div class="col-lg-12 text-center">
                    <textarea placeholder="Your message"></textarea>
                    <button type="submit" class="site-btn">SEND MESSAGE</button>
                </div>
            </div>
        </form>
    </div>
</div>








<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>