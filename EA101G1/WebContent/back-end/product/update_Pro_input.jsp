<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProVO proVO = (ProVO) request.getAttribute("proVO"); //ProServlet.java (Concroller) 存入req的proVO物件 (包括幫忙取出的proVO, 也包括輸入資料錯誤時的proVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_Pro_input.jsp</title>

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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

div #preview {
	width: 500px;
}

 #preview img {
	width: 200px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改 - update_Pro_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/pro.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品編號:</td>
				<td><%=proVO.getP_id()%></td>
			</tr>

			<jsp:useBean id="ptSvc" scope="page"
				class="com.productType.model.PtService" />
			<tr>
				<td>商品分類:<font color=red><b>*</b></font></td>
				<td><select size="1" name="pt_id">
						<c:forEach var="ptVO" items="${ptSvc.all}">
							<option value="${ptVO.pt_id}"
								${(proVO.pt_id==ptVO.pt_id)? 'selected':'' }>${ptVO.typename}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="p_name" size="45"
					value="<%=proVO.getP_name()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="p_price" size="45"
					value="<%=proVO.getP_price()%>" /></td>
			</tr>
			<tr>
				<td>商品圖片:</td>
				<td><input type="file" name="p_image" id="myFile"
					accept="image/gif, image/jpeg, image/png"
					value="<%=proVO.getP_image()%>" /><br>
					<div class="row">
						<div id="preview">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/product/proPic.do">
								<img src="<%=request.getContextPath()%>/back-end/product/proPic.do?p_id=${proVO.p_id}">
							</FORM>
						</div>
					</div></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><textarea name="p_info" rows="6" cols="40" 	value="<%=proVO.getP_info()%>"><%=proVO.getP_info()%></textarea></td>
			</tr>
			<tr>
				<td>庫存量:</td>
				<td><input type="TEXT" name="p_stock" size="45"
					value="<%=proVO.getP_stock()%>" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><select size="1" name="p_stat">
						<option value=0 ${(proVO.p_stat==0)? 'selected':'' }>下架
						
						<option value=1 ${(proVO.p_stat==1)? 'selected':'' }>上架
						
				</select></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="p_id" value="<%=proVO.getP_id()%>"> <input
			type="hidden" name="p_sales" value="<%=proVO.getP_sales()%>">
		<input type="hidden" name="p_add_date"
			value="<%=proVO.getP_add_date()%>"> <input type="submit"
			value="送出修改">
	</FORM>

	<script>
		function init() {

			var myFile = document.getElementById("myFile");
			var preview = document.getElementById('preview');

			myFile.addEventListener('change', function(e) {

				var files = myFile.files;

				if (files !== null && files.length > 0) {

					var file = files[0];

					console.log(file.type);
					if (file.type.indexOf('image') > -1) {

						var reader = new FileReader();

						reader.addEventListener('load', function(e) {

							var result = e.target.result;

							var img = document.createElement('img');

							img.src = result;
							preview.innerHTML = "";
							preview.append(img);
						});

						reader.readAsDataURL(file); // ***** 

					}
				}
			});
		}

		window.onload = init;
	</script>
</body>
</html>