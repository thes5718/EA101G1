<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProService" />
<jsp:useBean id="ptSvc" scope="page" class="com.productType.model.PtService" />
<!DOCTYPE html>
<html>
<head>
<title>IBM Pro: Home</title>
<style>
  table#table-1 {
	width: 450px;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Pro: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Pro: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllProduct.jsp'>List</a> all Pros.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="pro.do" >
        <b>輸入商品編號 (如P001):</b>
        <input type="text" name="p_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <li>
     <FORM METHOD="post" ACTION="pro.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="p_id">
         <c:forEach var="proVO" items="${proSvc.all}" > 
          <option value="${proVO.p_id}">${proVO.p_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="pro.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="p_id">
         <c:forEach var="proVO" items="${proSvc.all}" > 
          <option value="${proVO.p_id}">${proVO.p_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="pro.do" >
       <b>選擇商品種類:</b>
       <select size="1" name="pt_id">
         <c:forEach var="ptVO" items="${ptSvc.all}" > 
          <option value="${ptVO.pt_id}">${ptVO.typename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getAll_ByPtId">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>商品管理</h3>

<ul>
  <li><a href='addPro.jsp'>Add</a> a new pro.</li>
</ul>

</body>
</html>