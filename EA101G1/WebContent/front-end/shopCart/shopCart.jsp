<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shopCart.model.*" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II �d�ҵ{��  - Cart.jsp</title>
 <style>
		b>img{
			width: 100px;
			hieight:100px;
		}
 </style>
</head>
<body bgcolor="#FFFFFF">

<%Vector<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="images/tomcat.gif"> <font size="+3">�ثe�z�ʪ��������e�p�U�G</font><p>

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="500" colspan="2">�ӫ~����</th><th width="100">����</th><th width="100">�ƶq</th><th width="100">�w�s</th>
		<th width="120">�p�p</th><th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		PRODUCT order = buylist.get(index);
	%>
	<tr>
		<td width="300"><div align="center"><b><img src="<%=request.getContextPath()%>/front/product/ShopCartPic.do?p_id=<%=order.getId()%>"></b></div></td>
		<td width="200"><div align="center"><b><%=order.getName()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getPrice()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getQuantity()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getStock()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getSub()%></b></div></td>
		
		<td width="100"><div align="center">
          <form name="deleteForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action" value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="�R��"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="�I�ڵ��b">
          </form>
          <p><a href="listAllProduct.jsp">�O�_�~���ʪ�</a>
<%}%>
</body>
</html>