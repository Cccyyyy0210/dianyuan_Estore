<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>支付成功</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
<%--	<script type="text/javascript" src="js/cart.js"></script>--%>
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
	<jsp:include page="header.jsp"></jsp:include>
	<!--//header-->


	<!--cart-items-->
	<div class="cart-items">
		<div class="container">

			<c:if test="${!empty msg }">
				<div class="alert alert-success">${msg }</div>
			</c:if>
            <%--使用绝对路径--%>
			<p><a class="btn btn-success" href="${pageContext.request.contextPath}/order_list">查看我的订单</a></p>
		</div>
	</div>
	<!--//cart-items-->






	<!--footer-->
	<jsp:include page="footer.jsp"></jsp:include>
	<!--//footer-->


</body>
</html>