<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*" %>

<%
	ProVO proVO = (ProVO) request.getAttribute("proVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�ӫ~���</title>

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
	width: 600px;
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
  
   tr img{
  width:200px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�ӫ~��� - ListOneProduct.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�����s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~�Ϥ�</th>
		<th>�ӫ~�y�z</th>
		<th>�P��q</th>
		<th>�w�s�q</th>
		<th>�W�[���</th>
		<th>�ӫ~���A</th>
	</tr>
	<tr>
		<td><%=proVO.getP_id()%></td>
		<td><%=proVO.getPt_id()%></td>
		<td><%=proVO.getP_name()%></td>
		<td><%=proVO.getP_price()%></td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/pro/proPic.do">
			<td><img src="<%=request.getContextPath()%>/back-end/pro/proPic.do?p_id=${proVO.p_id}"></td>
			</FORM>
		<td><%=proVO.getP_info()%></td>
		<td><%=proVO.getP_sales()%></td>
		<td><%=proVO.getP_stock()%></td>
		<td><%=proVO.getP_add_date()%></td>
		<td>${(proVO.p_stat==0)? "�U�[��":"�W�[��"}</td>
	</tr>
</table>


</body>
</html>