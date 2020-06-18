<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.member.model.*" %>
<%-- 此頁暫練習採用 Scrpit 的寫法取值 --%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	// MemberService.java (Controller), 存入 req 的 memberVO 物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

<style>
  table#table-1 {
  	width: 100%;
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
  body {
  	background-color: white;
  }
  #back {
  	width: 100px;
  	height: 32px;
  }
  font {
  	color: red;
  }
  li {
  	color: red;
  }
  #tomcat {
  	width: 100px;
  	height: 100px;
  }
  img#display {
  	width: 200px;
  	height: 200px;
  }
</style>

</head>
<body>

<h4>此頁暫練習採用 Script 的寫法取值：</h4>
<table id="table-1">
	<tr>
		<td>
			<h3>會員資料 - listOneMember.jsp</h3>
			<h4><a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp"><img alt="" src="<%=request.getContextPath()%>/images/back1.gif">回首頁</a></h4>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員信箱</th>
		<th>會員密碼</th>
		<th>會員名稱</th>
		<th>會員圖片</th>
		<th>會員手機</th>
		<th>會員地址</th>
		<th>銀行帳戶</th>
		<th>信用卡號</th>
		<th>到期年份</th>
		<th>到期月份</th>
		<th>卡片安全碼</th>
		<th>會員權限</th>
		<th>紅利點數</th>
		<th>加入時間</th>
		<th>會員生日</th>
		<th>警告次數</th>
	</tr>
	<tr>
		<td><%=memberVO.getMem_id()%></td>
		<td><%=memberVO.getMem_email()%></td>
		<td><%=memberVO.getMem_pass()%></td>
		<td><%=memberVO.getMem_name()%></td>
		<td><img alt="" src="<%=request.getContextPath()%>/member/ShowMemberPic.do?mem_id=<%=memberVO.getMem_id()%>" id="display"></td>
		<td><%=memberVO.getMem_phone()%></td>
		<td><%=memberVO.getMem_addr()%></td>
		<td><%=memberVO.getBank_acc()%></td>
		<td><%=memberVO.getCard_no()%></td>
		<td><%=memberVO.getCard_yy()%></td>
		<td><%=memberVO.getCard_mm()%></td>
		<td><%=memberVO.getCard_sec()%></td>
		<td><%=memberVO.getMem_autho()%></td>
		<td><%=memberVO.getMem_bonus()%></td>
		<td><%=memberVO.getMem_joindat()%></td>
		<td><%=memberVO.getMem_birth()%></td>
		<td><%=memberVO.getMem_warn()%></td>
		<td>
			<form action="<%=request.getContextPath()%>/member/member.do" method="post">
				<input type="submit" value="修改">
				<input type="hidden" name="mem_id" value="${memberVO.mem_id}">
				<input type="hidden" name="action" value="getOne_For_Update-front">
			</form>
		</td>
	</tr>
</table>

</body>
</html>