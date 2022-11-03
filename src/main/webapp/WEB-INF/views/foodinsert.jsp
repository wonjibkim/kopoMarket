<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> 웹페이지 제목 </title>
</head>
<body>
 <div class ="form">







        <h1> 연습 음식 등록 페이지 </h1>
        <form action="/food/insert_St" method="post" enctype="multipart/form-data">



            상품명 : <input type="text" name="p_name">
            <br>
            상품가격 : <input type="password" name="p_price">
            <br>


            판매수량 <input type="text" name="p_sell">
            <br>
            상품설명 <input type="text" name="p_info">


            <br>
            상품카테고리
            <select name="p_category">
                <option value="">상품카테고리</option>
                <option value="1" name="1">야채</option>
                <option value="2" name="2">과일</option>
                <option value="3" name="3">과자</option>
            </select>

            <br>

            유통기한
            <input type="date"
                   name="p_period"
                   max="2077-06-20"
                   min="2077-06-05"
                   value="2077-06-15">

            <br>
            <input type="file"  multiple="multiple" name="fileUpload"/>
            <br>

            <input type="submit" value="등록하기">


        </form>
 <form action="login" method="get">

 </form>
</div>



</form>
</body>
</html>