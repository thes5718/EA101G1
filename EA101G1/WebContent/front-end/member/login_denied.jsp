<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Login Denied</title>
<style>
	#container {
		border: 1px solid black;
		margin: 0 auto;
		width: 25%;
		text-align: center;
	}
	#container > div {
		margin: 10px;
	}
</style>
</head>
<body>

	<div id="container">
		<h2>�n�J����</h2>
		<div>
			�A���b���αK�X�L��
		</div>
		<div>
			�Ы����B���s�n�J
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/front-end/member/login.jsp">���s�n�J</a>
		</div>
	</div>
	
</body>
</html>