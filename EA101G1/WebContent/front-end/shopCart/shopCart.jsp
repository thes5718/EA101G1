<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shopCart.model.*" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式  - Cart.jsp</title>
 <style>
		b>img{
			width: 100px;
			hieight:100px;
		}
 </style>
</head>
<body bgcolor="#FFFFFF">

<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="500" colspan="2">商品明細</th><th width="100">價格</th><th width="100">數量</th><th width="100">庫存</th>
		<th width="120">小計</th><th width="120"></th>
	</tr>
<%Vector<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>
<img src="images/tomcat.gif"> <font size="+3">目前您購物車的內容如下：</font><p>



	
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
          <form name="deleteForm" action="<%=request.getContextPath()%>/front-end/shopCart/Shopping.do" method="POST">
              <input type="hidden" name="action" value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="hidden" name="url" value="<%=request.getRequestURI()%>">
              <input type="submit" value="刪除">
          </form>
              </div>
        </td>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/front-end/shopCart/Shopping.do" method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
<%}else{%>
	<table border="1" width="740">無商品</table>
<%}%>
	<p><a href="<%=request.getContextPath() %>/front-end/product/listAllProduct.jsp">是否繼續購物</a>
</body>
</html>