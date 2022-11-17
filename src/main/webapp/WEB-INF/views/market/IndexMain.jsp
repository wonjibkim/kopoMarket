<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page import="kopo.poly.dto.RecipeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    List<FoodDTO> rList = (List<FoodDTO>) request.getAttribute("rList");
    List<RecipeDTO> nList = (List<RecipeDTO>) request.getAttribute("nList");

    if (rList == null) {
        rList = new ArrayList<FoodDTO>();

    }
    if (nList == null) {
        nList = new ArrayList<RecipeDTO>();

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

    <!-- Css Styles -->
    <link rel="stylesheet" href="ogani-master/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="ogani-master/css/style.css" type="text/css">
</head>

<body>


<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
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
                            <%if((session.getAttribute("seq") == null) && (session.getAttribute("SS_EMAIL_MARKET") == null)){%>
                            <a href="/login/loginUser"><i class="fa fa-user"></i> Login</a>
                            <%}else{%>
                            <a href="/login/MyPage"><i class="fa fa-user"></i> Mypage</a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="/market/index"><img src="/ogani-master/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="/ogani-master/index.html">Home</a></li>
                        <li><a href="/ogani-master/shop-grid.html">Shop</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/ogani-master/shop-details.html">Shop Details</a></li>
                                <li><a href="/ogani-master/shoping-cart.html">Shoping Cart</a></li>
                                <li><a href="/ogani-master/checkout.html">Check Out</a></li>
                                <li><a href="/ogani-master/blog-details.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="/ogani-master/blog.html">Blog</a></li>
                        <li><a href="/ogani-master/contact.html">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                        <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                    </ul>
                    <div class="header__cart__price">item: <span>$150.00</span></div>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
    <script>
        function doDetail(seq) {
            location.href = "/market/FoodDetail?nSeq=" + seq;
        }
        function doRecipeDetail(Recipe_name) {
            print("실행됨");
            location.href = "/market/RecipeDetail?Recipe_name=" + Recipe_name;
        }
    </script>
</header>

<!-- Hero Section Begin -->
<section class="hero">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="hero__categories">
                    <div class="hero__categories__all">
                        <i class="fa fa-bars"></i>
                        <span>All departments</span>
                    </div>
                    <ul>
                        <li><a href="#">Vegetables</a></li>
                        <li><a href="#">Meat</a></li>
                        <li><a href="#">Fruit</a></li>
                        <li><a href="#">Snack</a></li>
                        <li><a href="#">Fish</a></li>
                        <li><a href="#">Mushroom</a></li>
                        <li><a href="#">Bread</a></li>

                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form action="#">
                            <div class="hero__search__categories">
                                All Categories
                                <span class="arrow_carrot-down"></span>
                            </div>
                            <input type="text" placeholder="What do yo u need?">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form>
                    </div>
                    <div class="hero__search__phone">
                        <div class="hero__search__phone__icon">
                            <i class="fa fa-phone"></i>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5>+65 11.188.888</h5>
                            <span>support 24/7 time</span>
                        </div>
                    </div>
                </div>
                <div class="hero__item set-bg" data-setbg="/ogani-master/img/hero/banner.jpg">
                    <div class="hero__text">
                        <span>FRUIT FRESH</span>
                        <h2>Vegetable <br />100% Organic</h2>
                        <p>Free Pickup and Delivery Available</p>
                        <a href="#" class="primary-btn">SHOP NOW</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<!-- Categories Section Begin -->
<section class="categories">
    <div class="container">
        <div class="row">
            <div class="categories__slider owl-carousel">
                <%
                    for (int i = 0; i < nList.size(); i++) {
                        RecipeDTO nDTO = nList.get(i);

                        if (nDTO == null) {
                            nDTO = new RecipeDTO();
                        }
                %>
                <div class="col-lg-3">
                    <div class="categories__item set-bg" data-setbg="<%=nDTO.getFilename()%>">
                    </div>
                    <div class="product__item__text">
                        <h6><a href="javascript:doRecipeDetail('<%=CmmUtil.nvl(nDTO.getRecipe_name())%>');"><%=nDTO.getRecipe_name()%></a></h6>
                    </div>
                </div>
                <%}%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->

<!-- Featured Section Begin -->
<section class="featured spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Featured Product</h2>
                </div>
                <div class="featured__controls">
                    <ul>
                        <li class="active" data-filter="*">All</li>
                        <li data-filter=".vegetables">Vegetables</li>
                        <li data-filter=".meat">Meat</li>
                        <li data-filter=".fruit">Fruit</li>
                        <li data-filter=".snack">Snack</li>
                        <li data-filter=".fish">Fish</li>
                        <li data-filter=".mushroom">Mushroom</li>
                        <li data-filter=".bread">Bread</li>
                    </ul>
                </div>
            </div>
        </div>
        <%
            for (int i = 0; i < rList.size(); i++) {
                FoodDTO rDTO = rList.get(i);

                if (rDTO == null) {
                    rDTO = new FoodDTO();
                }
        %>
        <div class="row featured__filter">
            <div class="col-lg-3 col-md-4 col-sm-6 mix <%=CmmUtil.nvl(rDTO.getP_category())%>">
                <div class="featured__item">
                    <div class="featured__item__pic set-bg" data-setbg="<%=rDTO.getP_filePath()%>/<%=rDTO.getP_fileName()%>">
                        <ul class="featured__item__pic__hover">
                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                        </ul>
                    </div>
                    <div class="featured__item__text">
                        <h6><a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getP_num())%>');"><%=CmmUtil.nvl(rDTO.getP_name())%></a></h6>
                        <h5><%=CmmUtil.nvl(rDTO.getP_price())%></h5>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</section>
<!-- Featured Section End -->

<!-- Banner Begin -->
<div class="banner">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="banner__pic">
                    <img src="/ogani-master/img/banner/banner-1.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="banner__pic">
                    <img src="/ogani-master/img/banner/banner-2.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Banner End -->

<!-- Latest Product Section End -->

<!-- Blog Section Begin -->

<!-- Blog Section End -->

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