<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productOrder.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PoService poSvc = new PoService();
	MemberVO memVO = (MemberVO) session.getAttribute("memberVO");
	String mem_id = memVO.getMem_id();
    List<PoVO> listAll = poSvc.getOrder("014");
    List<PoVO> list = poSvc.getOrderByMemId(mem_id, listAll);
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="polSvc" scope="page" class="com.productOrderList.model.PolService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ordstat.model.OrdstatService" />
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProService" />
<html>
<head>
<title>已完成訂單列表</title>

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


<table>

	<c:forEach var="poVO" items="${list}" >
	<tr>
		<th>訂單編號</th>
		<th>日期</th>
		<th>狀態</th>
		<th>總金額</th>
	</tr>
		
		<tr>
			<td>${poVO.po_id}</td>
			<td>${poVO.add_date}</td>
			<td>${ordSvc.listOneOrdstat(poVO.ordstat_id).ordstat}</td>
			<td>${poVO.amount}</td>
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


</body>
</html>