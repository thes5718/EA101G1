<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shopCart.model.PRODUCT" %>
<%@page import="com.member.model.*"%>
<%@page import="com.productOrderList.model.*"%>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>�ӫ����b- Checkout.jsp</title>
  <style>
		b>img{
			width: 100px;
			hieight:100px;
		}
 </style>
</head>
<body bgcolor="#FFFFFF">
<img src="images/tomcat.gif"> <font size="+3">�ӫ� - ���b </font>
<hr><p><center>

<table border="1" width="720">
	<tr bgcolor="#999999">
		<th width="500" colspan="2">�ӫ~����</th><th width="100">����</th><th width="100">�ƶq</th>
		<th width="120">�p�p</th>
	</tr>
	
	<%
		Vector<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");
		String amount =  (String) session.getAttribute("amount");
		List<PolVO> list = new ArrayList<PolVO>();
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			PRODUCT order = buylist.get(i);
			String name = order.getName();
			Double price= order.getPrice();
			Integer quantity = order.getQuantity();
			Double sub =order.getSub();
			String p_id = order.getId();
			
			PolVO polvo = new PolVO();
			polvo.setP_id(p_id);
			polvo.setOrder_qua(quantity);
			polvo.setP_price(price);
			
			list.add(polvo);
	%>
	<tr>
		<td width="300"><div align="center"><b><img src="<%=request.getContextPath()%>/front/product/ShopCartPic.do?p_id=<%=order.getId()%>"></b></div></td>
		<td width="200"><div align="center"><b><%=name%></b></div></td>
		<td width="100"><div align="center"><b><%=price%></b></div></td>
		<td width="100"><div align="center"><b><%=quantity%></b></div></td>
		<td width="120"><div align="center"><b><%=sub%></b></div></td>
	</tr>
	<%
		}
	session.setAttribute("list",list);
	%>
	<tr>
		<td></td>
		<td></td>
		<td colspan="3"><div align="center"><font color="red"><b>�`���B�G</b></font><font color="red"><b>$<%=amount%></b></font></div></td>
	</tr>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/shopCart/Po.do" method="POST">
              <input type="hidden" name="action" value="insert"> 
              <input type="hidden" name="mem_id" value="${sessionScope.memberVO.mem_id}">
              <input type="submit" value="�T�{���b">
          </form>
<p><a href="<%=request.getContextPath() %>/front-end/product/listAllProduct.jsp">�O�_�~���ʪ�</a>
</center>
</body>
</html>
