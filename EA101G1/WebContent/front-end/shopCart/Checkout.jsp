<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopCart.model.PRODUCT" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II �d�ҵ{�� - Checkout.jsp</title>
</head>
<body bgcolor="#FFFFFF">
<img src="images/tomcat.gif"> <font size="+3">�����ѩ� - ���b </font>
<hr><p><center>

<table border="1" width="720">
	<tr bgcolor="#999999">
		<th width="200">�ѦW</th>
		<th width="100">�@��</th>
		<th width="100">�X����</th>
		<th width="100">����</th>
		<th width="100">�ƶq</th>
		<th width="120"></th>
	</tr>
	
	<%
		Vector<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			BOOK order = buylist.get(i);
			String name = order.getName();
			String author = order.getAuthor();
			String publisher = order.getPublisher();
			float price = order.getPrice();
			int quantity = order.getQuantity();
	%>
	<tr>
		<td width="200"><div align="center"><b><%=name%></b></div></td>
		<td width="100"><div align="center"><b><%=author%></b></div></td>
		<td width="100"><div align="center"><b><%=publisher%></b></div></td>
		<td width="100"><div align="center"><b><%=price%></b></div></td>
		<td width="100"><div align="center"><b><%=quantity%></b></div></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>�`���B�G</b></font></div></td>
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
		<td></td>
	</tr>
</table>
<p><a href="EShop.jsp">�O�_�~���ʪ�</a>
</center>
</body>
</html>
