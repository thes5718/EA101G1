<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productOrder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
PoService poSvc = new PoService();
List<PoVO> list = poSvc.getOrder("003");
pageContext.setAttribute("list",list);
%>
<jsp:useBean id="polSvc" scope="page" class="com.productOrderList.model.PolService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ordstat.model.OrdstatService" />
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProService" />
<html>
<head>
<title>所有商品資料 - listAllPro.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有商品資料 - listAllProduct.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
<%@ include file="page/page1.file" %> 
	<c:forEach var="poVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<th>訂單編號</th>
		<th>日期</th>
		<th>狀態</th>
		<th>總金額</th>
		<th>操作</th>
	</tr>
		
		<tr>
			<td>${poVO.po_id}</td>
			<td>${poVO.add_date}</td>
			<td>${ordSvc.listOneOrdstat(poVO.ordstat_id).ordstat}</td>
			<td>${poVO.amount}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/productorder/Po.do" style="margin-bottom: 0px;">
			     <input type="submit" value="出貨">
			     <input type="hidden" name="po_id"  value="${poVO.po_id}">
			     <input type="hidden" name="ordstat_id"  value="006">
			     <input type="hidden" name="url" value="<%=request.getServletPath()%>?<%=request.getQueryString()%>">
			     <input type="hidden" name="action"	value="updateOrdStat"></FORM>
			</td>
		</tr>
		<c:forEach var="polVO" items="${polSvc.getPolbyPoId(poVO.po_id)}">
		<tr>
			<td>${proSvc.getOnePro(polVO.p_id).p_name}</td>
			<td>${polVO.order_qua}</td>
			<td>${polVO.p_price}</td>
		</tr>	
		</c:forEach>
</c:forEach>
</table>
<%@ include file="page/page2.file" %>

</body>
</html>