<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.RecipeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    FoodDTO rDTO = (FoodDTO) request.getAttribute("rDTO");
    List<RecipeDTO> lList = (List<RecipeDTO>) request.getAttribute("lList");

    if (lList == null) {
        lList = new ArrayList<RecipeDTO>();

    }
//공지글 정보를 못불러왔다면, 객체 생성
    if (rDTO == null) {
        rDTO = new FoodDTO();

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

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <script>
        function doRecipeDetail(Recipe_name) {
            print("실행됨");
            location.href = "/market/RecipeDetail?Recipe_name=" + Recipe_name;
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

<!-- Product Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <div class="product__details__pic">
                    <div class="product__details__pic__item">
                        <img class="product__details__pic__item--large"
                             src="/ogani-master/product/details/product-details-1.jpg" alt="">
                    </div>
                    <div class="product__details__pic__item">
                        <img src="<%=rDTO.getP_filePath()%>/<%=rDTO.getP_fileName()%>">
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <div class="product__details__text">
                    <h3><%=CmmUtil.nvl(rDTO.getP_name())%></h3>
                    <div class="product__details__rating">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star-half-o"></i>
                        <span>(18 reviews)</span>
                    </div>
                    <div class="product__details__price"><%=CmmUtil.nvl(rDTO.getP_price())%></div>
                    <p><%=CmmUtil.nvl(rDTO.getP_info())%></p>

                    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
                    <form action="/market/cart_add" method="post">
                    <div class="product__details__quantity">
                        <div class="quantity">
                            <div class="pro-qty">
                                <input type="text"  name="cart_count" value="1">
                            </div>
                        </div>
                    </div>

                        <input type="hidden" value="<%=CmmUtil.nvl(rDTO.getP_num())%>" name="food_num">

                        <input type="submit" class="primary-btn"  value="ADD TO CARD">
<%--                    <a href="sumbit" class="primary-btn">ADD TO CARD</a>--%>

                    </form>
<%--                    <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>--%>
                    <ul>
                        <li><b>유통기한</b> <span><%=CmmUtil.nvl(rDTO.getP_period())%></span></li>
                        <li><b>할인율</b> <span><%=CmmUtil.nvl(rDTO.getP_discount())%></span></li>
                        <li><b>중량</b> <span><%=CmmUtil.nvl(rDTO.getP_weight())%></span></li>
                        <li><b>원산지</b> <span><%=CmmUtil.nvl(rDTO.getP_ancestry())%></span></li>
                        <li><b>수량</b> <span><%=CmmUtil.nvl(rDTO.getP_sell())%></span></li>
                        <li><b>Share on</b>
                            <div class="share">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                                <a href="#"><i class="fa fa-pinterest"></i></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
    <div class="container">
        <div class="row">
            <%
                for (int i = 0; i < lList.size(); i++) {
                    RecipeDTO lDTO = lList.get(i);

                    if (lDTO == null) {
                        lDTO = new RecipeDTO();
                    }
            %>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="<%=lDTO.getFilename()%>">
                        <ul class="product__item__pic__hover">
                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6><a href="javascript:doRecipeDetail('<%=CmmUtil.nvl(lDTO.getRecipe_name())%>');"><%=lDTO.getRecipe_name()%></a></h6>
                    </div>
                </div>
            </div>
            <%}%>
            </div>
        </div>
    </div>
</section>
<!-- Related Product Section End -->

<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->



</body>

</html>