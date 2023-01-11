<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>购物车</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
<%--    <script type="text/javascript" src="js/cart.js"></script>--%>
    <script>

        /**
         * 加入购物车
         */
        function buy(goodid){
            $.post("goods_buy", {goodsid:goodid}, function(data){
                if(data=="ok")
                {
                    layer.msg("添加到购物车!", {time:800}, function(){
                        location.reload();
                    });
                }
                else if(data=="fail")
                {
                    layer.msg("库存不足,请购买其他商品!", {time:800}, function(){

                    });
                }
            });
        }

        function lessen(goodsid){
            $.post("goods_lessen", {goodsid:goodsid}, function(data){
                if(data=="ok"){
                    layer.msg("操作成功!", {time:800}, function(){
                        location.reload();
                    });
                }
            });
        }
        /**
         * 购物车删除
         */
        function deletes(goodid){
            $.post("goods_delete", {goodsid:goodid}, function(data){
                if(data=="ok"){
                    layer.msg("删除成功!", {time:800}, function(){
                        location.reload();
                    });
                }
            });
        }
    </script>
</head>
<body>







<!--header-->
<jsp:include page="header.jsp">
    <jsp:param name="flag" value="7"></jsp:param>
</jsp:include>
<!--//header-->


<!--cart-items-->
<div class="cart-items">
    <div class="container">



        <h2>我的购物车</h2>


        <c:forEach items="${order.itemMap }" var="item">
            <div class="cart-header col-md-6">
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <a href="/goods_detail?id=${item.key}">
                            <img src="${pageContext.request.contextPath}/${item.value.goods.cover}" class="img-responsive">
                        </a>
                    </div>
                    <div class="cart-item-info">
                        <h3><a href="/goods_detail?id=${item.key}">${item.value.goods.name}</a></h3>
                        <h3><span>单价: ¥ ${item.value.price}</span></h3>
                        <h3><span>数量: ${item.value.amount}</span></h3>
                        <a class="btn btn-info" href="javascript:buy(${item.key});">增加</a>
                        <a class="btn btn-warning" href="javascript:lessen(${item.key});">减少</a>  <%--减少出现不响应--%>
                        <a class="btn btn-danger" href="javascript:deletes(${item.key});">删除</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </c:forEach>

        <div class="cart-header col-md-12">
            <hr>
            <h3>订单总金额: ¥ ${order.total}</h3>
            <%--使用绝对路径--%>
            <a class="btn btn-success btn-lg" style="margin-left:74%" href="${pageContext.request.contextPath}/order_submit">提交订单</a>
        </div>



    </div>
</div>
<!--//cart-items-->






<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>