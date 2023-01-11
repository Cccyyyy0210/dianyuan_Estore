<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--该页面作为嵌入页面之一，css样式没有引用，与index配套引用时才引入css--%>
<%--该页面存放着其它页面的访问路径，存在许多404--%>
<%--访问其它jsp页面，建议使用绝对路径——"${pageContext.request.contextPath }/**"--%>
<!--header-->

<div class="header">
  <div class="container">
    <nav class="navbar navbar-default" role="navigation">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <h1 class="navbar-brand"><a href="${pageContext.request.contextPath}">滇园食品旗舰店</a></h1>
      </div>

      <!--navbar-header-->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}" <c:if test="${param.flag==1}">class="active"</c:if>>首页</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle <c:if test="${param.flag==2}">active</c:if>" data-toggle="dropdown">商品分类<b class="caret"></b></a>
            <ul class="dropdown-menu multi-column columns-2">
              <li>
                <div class="row">
                  <div class="col-sm-12">
                    <h4>商品分类</h4>
                    <ul class="multi-column-dropdown">

<%--                      <li><a class="list" href="${pageContext.request.contextPath}/goods_list">全部系列</a></li>--%>

                      <c:forEach items="${typeList}" var="t">
                        <li><a class="list" href="${pageContext.request.contextPath}/goods_list?id=${t.id}">${t.name}</a></li>
                      </c:forEach>


                    </ul>
                  </div>
                </div>
              </li>
            </ul>
          </li>

          <c:choose><c:when test="${empty user }">
            <li><a href="${pageContext.request.contextPath}/user_register.jsp" <c:if test="${param.flag==10 }">class="active"</c:if>>注册</a></li>
            <li><a href="${pageContext.request.contextPath}/user_login.jsp" <c:if test="${param.flag==9 }">class="active"</c:if>>登录</a></li>
          </c:when><c:otherwise>
            <li><a href="${pageContext.request.contextPath}/order_list" <c:if test="${param.flag==5 }">class="active"</c:if>>我的订单</a></li>
            <li><a href="${pageContext.request.contextPath}/user_center.jsp" <c:if test="${param.flag==4 }">class="active"</c:if>>个人中心</a></li>
            <li><a href="${pageContext.request.contextPath}/user_logout" >退出</a></li>
          </c:otherwise>
          </c:choose>

          <c:if test="${!empty user && user.isadmin }">
            <li><a href="${pageContext.request.contextPath}/admin/index.jsp" target="_blank">后台管理</a></li>
          </c:if>
        </ul>
        <!--/.navbar-collapse-->
      </div>
      <!--//navbar-header-->

    </nav>
    <div class="header-info">
      <div class="header-right search-box">
        <a href="javascript:"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
        <div class="search">
          <form class="navbar-form" action="${pageContext.request.contextPath }/goods_search">
            <input type="text" class="form-control" name="keyword">
            <button type="submit" class="btn btn-default <c:if test="${param.flag==7 }">active</c:if>" aria-label="Left Align">搜索</button>
          </form>
        </div>
      </div>

      <div class="header-right cart">
        <a href="goods_cart.jsp">
          <span class="glyphicon glyphicon-shopping-cart <c:if test="${param.flag==8 }">active</c:if>" aria-hidden="true"><span class="card_num"><c:choose><c:when test="${empty order}">0</c:when><c:otherwise>${order.amount}</c:otherwise></c:choose></span></span>
        </a>
      </div>
      <div class="clearfix"> </div>
    </div>
    <div class="clearfix"> </div>
  </div>
</div>
<!--//header-->

