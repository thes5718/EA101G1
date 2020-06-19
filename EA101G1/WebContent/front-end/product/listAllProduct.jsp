<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberVO memVO = (MemberVO) session.getAttribute("memberVO");
    ProService proSvc = new ProService();
    List<ProVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%=memVO.getMem_id() %>
<jsp:useBean id="ptSvc" scope="page" class="com.productType.model.PtService" />

<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<link rel="stylesheet" href="">

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

<style type="text/css" media="screen">
		 
		 body{
		 	/*沒作用*/
		 	margin: 0;
		 }

		.card{
			border:1px solid black;
			position: relative;
			width: 700px;
			height: 220px;
		}
		.p_img{
			width: 200px;
			border:1px solid blue;
			position: absolute;
			height: 200px;

			background-size: cover;
			
		}
		.p_img>img{
			width: 200px;
			hieight:200px;
		}
		.p_word{
			border:1px solid red;
			width: 68%;
			height: 100%;
			position:absolute;
			right: 10px;
		}
		.p_tital{
			border:1px solid green;
			/*相對於.word的位置*/
			position: absolute;
			top:10px;
			left: 0;
			/*改動left width也要改 不然會凸出去*/
			height: 30%;
			width: 100%;
		}
		.p_price{
			border:1px solid yellow;
			/*相對於.word的位置*/
			position: absolute;
			top:110px;
			left:0;
			/*改動left width也要改 不然會凸出去*/
			height:30%;
			width: 100%;
		}
		.p_love{
			border:1px solid yellow;
			/*相對於.word的位置*/
			position: absolute;
			right: 80px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
			background-image: url("https://via.placeholder.com/30x30.png");
		}
		.p_car{
			border:1px solid deeppink;
			/*相對於.word的位置*/
			position: absolute;
			right: 20px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
			background-image: url("https://via.placeholder.com/30x30.png");
		}
	</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	
	<%@ include file="page/page1.file" %> 
	<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<div class="display">
		<div class="img"><img src="" alt=""></div>
	<div class="card">
		<div class="p_img" name="p_image"><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/proPic.do">
			<img src="<%=request.getContextPath()%>/back-end/product/proPic.do?p_id=${proVO.p_id}">
			</FORM>
		</div>
		<div class="p_word">
			<div class="p_tital" name="p_name">${proVO.p_name}</div>
			<div class="p_price">$${proVO.p_price}</div>
			
			<div class="p_love" > 
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/product/favp.do" style="margin-bottom: 0px;">
            <input type="submit" value="修改">			
            <input type="hidden" name="p_id"  value="${proVO.p_id}">
			<input type="hidden" name="mem_id"  value="<%=memVO.getMem_id()%>">
			<input type="hidden" name="action"	value="insert">
			</FORM>
			</div>
			<div class="p_car" >車</div>
		</div>
	</div>
		
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>
</body>
</html>