<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.favouriteProduct.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    FavpService favpSvc = new FavpService();
    List<FavpVO> list = favpSvc.getProductByMem("M000001");
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProService" />
<jsp:useBean id="ptSvc" scope="page" class="com.productType.model.PtService" />

<html>
<head>
<title>最愛商品 </title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  td>img{
  width:200px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>最愛商品</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品圖片</th>
		<th>修改</th>
	</tr>
	<%@ include file="page/page1.file" %> 
	<c:forEach var="favpVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${proSvc.getOnePro(favpVO.p_id).p_name}</td>
			<td>${proSvc.getOnePro(favpVO.p_id).p_price}</td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/proPic.do">
			<td><img src="<%=request.getContextPath()%>/back-end/product/proPic.do?p_id=${favpVO.p_id}"></td>
			</FORM>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/favouriteProduct/favp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消">
			     <input type="hidden" name="p_id"  value="${favpVO.p_id}">
			     <input type="hidden" name="mem_id"  value="${favpVO.mem_id}">
			     <input type="hidden" name="action"	value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>

</body>
</html>