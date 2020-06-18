<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Member: Home</title>

<style>
	table#table-1 {
		width: 100%;
		background-color: #CCCCFF;
		margin-top: 5px;
		margin-bottom: 10px;
		border: 3px ridge Gray;
		height: 80px;
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
	body {
		background: white;
	}
	font {
		color: red;
	}
	#errormsg {
		color: red;
	}
</style>

</head>
<body>

<table id="table-1">
	<tr><td><h3>Member: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Member: Home</p>

<h3>��Ƭd��</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs }">
	<font>�Эץ��H�U���~�G</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li id="errormsg">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form action="member.do" method="post">
		<c:if test="${not empty memberVO }">
			<div id="logout">
				<input type="hidden" name="action" value="logout">
				<input type="submit" value="�n�X">
			</div>
		</c:if>
		<c:if test="${empty memberVO}">
			<a href="<%=request.getContextPath()%>/front-end/member/login.jsp">�n�J</a>
		</c:if>
	</form>

<ul>
<%-- �e�x�����Ѭd�ߩҦ��|��
	<li><a href='<%=request.getContextPath()%>/front-end/member/listAllMember.jsp'>List</a> all Members. <br><br></li>
--%>	
	<c:if test="${not empty sessionScope.memberVO}">
		<li>
			<a href="<%=request.getContextPath()%>/front-end/member/member.do?action=getOne_For_Display-front&mem_id=${sessionScope.memberVO.mem_id}">�d�ߦۤv</a>
		</li>
	</c:if>
	
	<li>
		<form action="member.do" method="post">
			<b>�п�J�|���s�� (�pM000005)</b>
			<input type="text" name="mem_id">
			<input type="hidden" name="action" value="getOne_For_Display-front">
			<input type="submit" value="�e�X">
		</form>
	</li>
	
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

	<li>
		<form action="member.do" method="post">
			<b>��ܷ|���s��</b>
			<select size="1" name="mem_id">
				<c:forEach var="memberVO" items="${memberSvc.all}">
					<option value="${memberVO.mem_id}">${memberVO.mem_id}
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="getOne_For_Display-front">
			<input type="submit" value="�e�X">
		</form>
	</li>
	
	<li>
		<form action="member.do" method="post">
			<b>�п�ܷ|���W��</b>
			<select size="1" name="mem_id">
				<c:forEach var="memberVO" items="${memberSvc.all}" >
					<option value="${memberVO.mem_id}">${memberVO.mem_name}
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="getOne_For_Display-front">
			<input type="submit" value="�e�X">
		</form>
	</li>
</ul>

<h3>�|���޲z</h3>

<ul>
	<li><a href="<%=request.getContextPath()%>/front-end/member/addMember.jsp">add</a> a new Member.</li>
</ul>

</body>
</html>