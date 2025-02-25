<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.Cart_foodDTO" %>

<%
    //    session.setAttribute("SESSION_USER_ID", "USER01");

    List<Cart_foodDTO> rList = (List<Cart_foodDTO>) request.getAttribute("rList");


    if (rList == null) {
        rList = new ArrayList<Cart_foodDTO>();

    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글보기</title>

    <script type="text/javascript">

        //상세보기 이동
        function doDetail(p_num) {
            location.href = "/market/FoodDetail?nSeq="+ p_num;
        }

        function doSave(){ // 체크박스 값 묶어서 보내기

            let chk_langs = document.getElementsByName("chk_lang");
            let checked_lang_items = [];


            for(let i=0; i<chk_langs.length; i++){
                if(chk_langs[i].checked){
                    checked_lang_items.push(chk_langs[i].value)
                }
            }

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            $.ajax({
                type: 'POST',
                url :  "/Cart/cartDelete",
                data : {"cart_num" : checked_lang_items},//보낼 데이터의 키값과 value값
                dataType: "json",
                success(result) {
                    console.log(result)
                    if(result==null) {
                        alert("장바구니 목록이 다 삭제되었습니다")
                    } else{
                        $('#test').empty();
                        var str = '';
                        for(var i=0; i<result.length;i++) {
                            str +=
                                '<tr><td class="shoping__cart__item">' +


                                '<img style="width: 150px; height: 150px; object-fit: cover;" src="'+
                                result[i].p_filePath + '/' + result[i].p_fileName +
                                '" alt="">'




                                +
                                '<h5><a href="javascript:doDetail('+result[i].food_num
                                +');">'+ result[i].p_name + '</a></h5></td>'


                                + '<td class="shoping__cart__price">' + result[i].p_price + '</td>'
                                + '<td class="shoping__cart__price">' +       result[i].p_sell   +'</td>'


                                // + '<td class="shoping__cart__total">' + result[i].cart_count  +'</td>'

                                + '<td class="shoping__cart__price"><form name="form" method="get" ><input type="text" name="amount" value="'
                                + result[i].cart_count
                                +'"size="6" onchange="change();"><br><input type="button" value="-1 " onclick="del('
                                +result[i].food_num
                                +');">&nbsp;<input type="button" value=" +1" onclick="add('
                                +result[i].food_num
                                +');"></form></td>'









                                + '<td class="shoping__cart__total">' + result[i].p_ancestry  +'</td>'
                                +'<td class="shoping__cart__item__close"><input type="checkbox" name="chk_lang" value="' + result[i].cart_num + '"></td></tr>'



                        }
                        $('#test').append(str);
                    }
                }
            })
            console.log(checked_lang_items)
        }



        function Purchase() { //결제하기로 넘어가기

            let chk_langs = document.getElementsByName("chk_lang");
            let checked_lang_items = [];


            for (let i = 0; i < chk_langs.length; i++) {
                if (chk_langs[i].checked) {
                    checked_lang_items.push(chk_langs[i].value)
                }
            }

            // document.getElementById('checked_lang_items').value = str;
            // console.log(document.getElementById('checked_lang_items').value);
            //
            //
            //
            //     document.getElementById(del_form).submit();
            // );


            location.href = "/Cart/purchase?cart_num="+ checked_lang_items;




        }




        // 수량 증가 감소
        var amount;



        function add (count, food_num) {

            console.log( " p_num " + count)

            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            $.ajax({
                type: 'POST',
                url :  "/Cart/update_cart",
                data : {"p_num" : count},//보낼 데이터의 키값과 value값
                dataType: "json",

                success(data) {
                    if(data==null) {
                        alert("수량을 다시 변경해주세요")
                    } else{
                        console.log(data);
                        if (data.result==1) {
                            var inputId = '#'+food_num;
                            console.log("inputId : " + inputId);
                            var inputValue = $(inputId).val();
                            $(inputId).attr("value",parseInt(inputValue)+1);
                        }
                    }
                }
            })
            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        }



        function del (count, food_num) {

            console.log( " p_num " + count)

            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            $.ajax({
                type: 'POST',
                url :  "/Cart/update_del",
                data : {"p_num" : count},//보낼 데이터의 키값과 value값
                dataType: "json",

                success(data) {
                    if(data==null) {
                        alert("수량을 다시 변경해주세요")
                    } else{
                        console.log(data);
                        if (data.result==1) {
                            var inputId = '#'+food_num;
                            console.log("inputId : " + inputId);
                            var inputValue = $(inputId).val();
                            $(inputId).attr("value",parseInt(inputValue)-1);
                        }
                    }
                }
            })
            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        }



        // function change () {
        //     hm = document.form.amount;
        //     sum = document.form.sum;
        //
        //     if (hm.value < 0) {
        //         hm.value = 0;
        //     }
        //     sum.value = parseInt(hm.value) * sell_price;
        // }




    </script>


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

    <%--<style type="text/css">--%>
    <%--    .count-wrap {position: relative;padding: 0 38px;border: 1px solid #ddd;overflow: hidden;width: 60px;}--%>
    <%--    .count-wrap > button {border: 0;background: #ddd;color: #000;width: 38px;height: 38px;position: absolute;top: 0;font-size: 12px;}--%>
    <%--    .count-wrap > button.minus {left: 0;}--%>
    <%--    .count-wrap > button.plus {right: 0;}--%>
    <%--    .count-wrap .inp {border: 0;height: 38px;text-align: center;display: block;width: 100%;}--%>
    <%--</style>--%>


</head>
<body>
<!-- Header Section Begin -->
<%@include file="../includes/header.jsp"%>

<section class="breadcrumb-section set-bg" data-setbg="/ogani-master/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>장바구니</h2>
                    <div class="breadcrumb__option">
                        <a href="./index.html"></a>
                        <span></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="shoping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <table>
                        <thead>
                        <tr>
                            <th class="shoping__product">제품</th>
                            <th>가격</th>
                            <th>재고수량</th>
                            <th>구매수량</th>
                            <th>원산지</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="test">
                        <%
                            for (int i = 0; i < rList.size(); i++) {
                                Cart_foodDTO rDTO = rList.get(i);

                                if (rDTO == null) {
                                    rDTO = new Cart_foodDTO();
                                }

                                String food_num = CmmUtil.nvl(rDTO.getFood_num()+"");
                        %>

                        <tr>


                            <td class="shoping__cart__item">
                                <img style="width: 150px; height: 150px; object-fit: cover;" src="<%=rDTO.getP_filePath()%>/<%=rDTO.getP_fileName()%>" alt="">
                                <h5>  <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getP_num())%>');"> <%=CmmUtil.nvl(rDTO.getP_name())%> </a> </h5>
                            </td>

                            <%--                            <td class="shoping__cart__item"> <h5>--%>
                            <%--                                <a href="javascript:doDetail('<%=CmmUtil.nvl(Integer.toString(rDTO.getFood_num()))%>');">--%>
                            <%--                                    <%=CmmUtil.nvl(rDTO.getP_name())%> </a> </h5></td>--%>

                            <td class="shoping__cart__price"> <%=CmmUtil.nvl(rDTO.getP_price())%></td>

                            <td class="shoping__cart__price"> <%=CmmUtil.nvl(rDTO.getP_sell())%> </td>
                            <%--                            <td class="shoping__cart__price">--%>
                            <%--                                <div class="count-wrap _count">--%>
                            <%--                                    <button type="button" class="minus">-</button>--%>
                            <%--                                    <input type="text" class="inp" value="1" />--%>
                            <%--                                    <button type="button" class="plus">+</button>--%>
                            <%--                                </div>--%>
                            <%--                            </td>--%>


                            <%--                            <td class="shoping__cart__quantity">--%>
                            <%--                                <div class="quantity">--%>
                            <%--                                    <div class="pro-qty">--%>
                            <%--                                        <input type="text" value="<%=CmmUtil.nvl(rDTO.getP_sell())%>">--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </td>--%>






                            <%--                            <td class="shoping__cart__price"> <%=CmmUtil.nvl(Integer.toString(rDTO.getCart_count()))%></td>--%>




                            <td class="shoping__cart__price">
                                <form name="form" method="get" >

                                    <input type="text" id="<%=food_num%>" name="amount" value="<%=CmmUtil.nvl(Integer.toString(rDTO.getCart_count()))%>"   size="6" onchange="change();">
                                    <br><input type="button" value="-1 " onclick="del('<%=CmmUtil.nvl(rDTO.getP_num())%>', '<%=food_num%>');">&nbsp;
                                    <input type="button" value=" +1" onclick="add('<%=CmmUtil.nvl(rDTO.getP_num())%>', '<%=food_num%>');">
                                </form>
                            </td>













                            <td class="shoping__cart__total"><%=CmmUtil.nvl(rDTO.getP_ancestry())%></td>



                            <td class="shoping__cart__item__close">
                                <input type="checkbox" name="chk_lang" value="<%=CmmUtil.nvl(Integer.toString(rDTO.getCart_num()))%>">
                            </td>





                        </tr>

                        <% } %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__btns">
                    <a href="#" class="primary-btn cart-btn" onclick="doSave();">상품 삭제하기 </a>


                    <a href="#" class="primary-btn cart-btn cart-btn-right" onclick="Purchase();"><span class="icon_loading"></span>
                        상품 구매하기 </a>








                </div>
            </div>

            <%--            <div class="col-lg-6">--%>
            <%--                <div class="shoping__checkout">--%>
            <%--                    <h5>Cart Total</h5>--%>
            <%--                    <ul>--%>
            <%--                        <li>Subtotal <span>$454.98</span></li>--%>
            <%--                        <li>Total <span>$454.98</span></li>--%>
            <%--                    </ul>--%>
            <%--                    <a href="#" class="primary-btn">PROCEED TO CHECKOUT</a>--%>
            <%--                </div>--%>
            <%--            </div>--%>
        </div>
    </div>
</section>





<!-- Footer Section Begin -->
<%@include file="../includes/footer.jsp"%>
<!-- Footer Section End -->
</body>
</html>