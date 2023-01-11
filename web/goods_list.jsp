<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <title>滇园食品旗舰店</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
  <link type="text/css" rel="stylesheet" href="css/style.css">
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/simpleCart.min.js"></script>
  <script type="text/javascript" src="layer/layer.js"></script>
<%--  <script type="text/javascript" src="js/cart.js"></script>--%>
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
  <jsp:param name="flag" value="2"></jsp:param>
</jsp:include>
<!--//header-->

<!--products-->
<div class="products">
  <div class="container">
    <h2><c:choose><c:when test="${empty t}">全部系列</c:when><c:otherwise>${t.name}</c:otherwise> </c:choose></h2>

    <div class="col-md-12 product-model-sec">

      <c:forEach items="${p.list}" var="g">
        <div class="product-grid">
          <a href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">
            <div class="more-product"><span> </span></div>
            <div class="product-img b-link-stripe b-animate-go  thickbox">
              <img src="${g.cover}" class="img-responsive" alt="${g.name}" width="350" height="350">
              <div class="b-wrapper">
                <h4 class="b-animate b-from-left  b-delay03">
                  <button href="${pageContext.request.contextPath}/goods_detail?id=${g.id}">查看详情</button>
                </h4>
              </div>
            </div>
          </a>
          <div class="product-info simpleCart_shelfItem">
            <div class="product-info-cust prt_name">
              <h4>${g.name}</h4>
              <span class="item_price">¥ ${g.price}</span>
              <input type="button" class="item_add items" value="加入购物车" onclick="buy(${g.id})">
              <div class="clearfix"> </div>
            </div>
          </div>
        </div>
      </c:forEach>



    </div>

    <jsp:include page="page.jsp">
      <jsp:param name="url" value="/goods_list"></jsp:param>
      <jsp:param name="param" value="&id=${typeid}"></jsp:param>
    </jsp:include>
  </div>
</div>
</div>
<!--//products-->






<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
