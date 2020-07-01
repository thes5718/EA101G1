<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productOrder.model.*"%>

<%
    PoService poSvc = new PoService();
    List<PoVO> list = poSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="polSvc" scope="page" class="com.productOrderList.model.PolService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ordstat.model.OrdstatService" />
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProService" />
<html>
<head>
<title>所有商品資料 - listAllPro.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
	margin-left: auto;
	margin-right: auto;
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


</head>
<body bgcolor='white'>
<nav>
<div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
    <a class="nav-item nav-link active" id="nav-all-tab" data-toggle="tab" href="#nav-all" role="tab" aria-controls="nav-all" aria-selected="true">全部</a>
    <a class="nav-item nav-link" name="003" id="nav-waiting-tab" data-toggle="tab" href="#nav-waiting" role="tab" aria-controls="nav-waiting" aria-selected="false">待出貨</a>
    <a class="nav-item nav-link" name="006" id="nav-arrival-tab" data-toggle="tab" href="#nav-arrival" role="tab" aria-controls="nav-arrival" aria-selected="false">已到貨</a>
    <a class="nav-item nav-link" name="014" id="nav-finsh-tab" data-toggle="tab" href="#nav-finsh" role="tab" aria-controls="nav-finsh" aria-selected="false">完成</a>
    <a class="nav-item nav-link" name="007" id="nav-cancel-tab-tab" data-toggle="tab" href="#nav-cancel-tab" role="tab" aria-controls="nav-cancel-tab" aria-selected="false">取消</a>
  </div>
</nav>



<div class="tab-content" id="nav-tabContent">
  <div class="tab-pane fade show active" id="nav-all" role="tabpanel" aria-labelledby="nav-all-tab"><jsp:include page="listAllProductOrder.jsp" /> </div>
  <div class="tab-pane fade" id="nav-waiting" role="tabpanel" aria-labelledby="nav-waiting-tab"><jsp:include page="listWaitingProductOrder.jsp" /></div>
  <div class="tab-pane fade" id="nav-arrival" role="tabpanel" aria-labelledby="nav-arrival-tab"><jsp:include page="listArrivalProductOrder.jsp" /></div>
  <div class="tab-pane fade" id="nav-finsh" role="tabpanel" aria-labelledby="nav-finsh-tab"><jsp:include page="listFinshProductOrder.jsp" /></div>
  <div class="tab-pane fade" id="nav-cancel-tab" role="tabpanel" aria-labelledby="nav-cancel-tab-tab"><jsp:include page="listCancelProductOrder.jsp" /></div>
</div>




    <!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
    <script>
    $('<%= request.getAttribute("show")%>').tab('show')
    
    $('#nav-tab a').on('click', function (e) {
    	  e.preventDefault()
    	  $(this).tab('show')
    	  console.log('ordstat_id');
    	})
    </script>

</body>
</html>