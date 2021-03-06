<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shopCart.model.PRODUCT" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%	Vector<PRODUCT> buylist=null; //購物車顯示有多少東西
	if(session.getAttribute("shoppingcart")!=null){
	buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");
	}else{
		buylist= new Vector<PRODUCT>();
	}
    ProService proSvc = new ProService();
    List<ProVO> list = proSvc.getAllFront();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="ptSvc" scope="page" class="com.productType.model.PtService" />
<jsp:useBean id="favpSvc" scope="page" class="com.favouriteProduct.model.FavpService" />
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>所有商品資料 - listAllPro.jsp</title>
 <!-- TODO: 換title 的icon -->
    <link rel="icon shortcut" href="./img/ICON.ico">
    <!-- Bootstrap官方網站 https://getbootstrap.com/ -->
    <!-- 連結Bootstrap.min.css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- 使用font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
        integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <!-- 使用google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Sedgwick+Ave+Display&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lakki+Reddy&display=swap" rel="stylesheet">

    <!-- 使用style.css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">

    <!-- 連結Bootstrap所需要的js -->
    <!-- jquery.min.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- popper.min.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <!-- bootstrap.min.js -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        
    <!-- SweetAlert2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

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
/*     border: 1px solid #CCCCFF; */
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
			position: relative;
			width: 750px;
			height: 200px;
			
			margin-bottom:5px;
		}
		.p_img{
			width: 200px;
/* 			border:1px solid blue; */
			position: absolute;
			height: 200px;

			background-size: cover;
			
		}
		.p_img>img{
			width: 90%;
			hieight:90%;
			
			 position:absolute;
			 left:5%;
			 top:5%;
		}
		.p_word{
/* 			border:1px solid red; */
			width: 68%;
			height: 100%;
			position:absolute;
			right: 10px;
		}
		.p_tital{
/* 			border:1px solid green; */
			/*相對於.word的位置*/
			position: absolute;
			top:10px;
			left: 0;
			/*改動left width也要改 不然會凸出去*/
			height: 30%;
			width: 100%;
		}
		.p_price{
/* 			border:1px solid yellow; */
			/*相對於.word的位置*/
			position: absolute;
			top:110px;
			left:0;
			/*改動left width也要改 不然會凸出去*/
			height:30%;
			width: 100%;
		}
		.p_love{
/* 			border:1px solid yellow; */
			/*相對於.word的位置*/
			position: absolute;
			right: 80px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
		}
		.p_car{
/* 			border:1px solid deeppink; */
			/*相對於.word的位置*/
			position: absolute;
			right: 20px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
		}
		.img-icon {
			width: 90%;
			height: 90%;
			float: right;
			margin: 0 2px;
		}
		.img-icon:hover {
			cursor:pointer;
		}
		
		front.p_name:hover {
			cursor:pointer;
			color:red;
		}
	</style>

</head>
<body>
<!-- navbar -->
    <!-- 使用Boostrap Navbar -->
    <!-- 設定Navbar緊貼畫面上緣 -->
    <!-- b4-navbar-default 安裝Bootstrap外掛,可以使用快捷指令 -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <a class="navbar-brand" href="index.html">
            <span class="logo"><i class="fas fa-bomb"></i></span>
            <span class="logo2">S.F.G</span>
            <span class="logo3">{{{</span>
        </a>
        <!-- 手機選單按鈕 -->
        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="navbar2 navbar-dark">
            <div class="row">
                <div class="item col-md-2"><a href="#"></a>商城</div>
                <div class="item col-md-2"><a href="#"></a>團購</div>
                <div class="item col-md-2"><a href="#"></a>交易</div>
                <div class="item col-md-2"><a href="#"></a>討論區</div>
                <div class="item col-md-2"><a href="#"></a>紅利</div>
                <div class="item col-md-2"><a href="#"></a>Q&A</div>
            </div>
        </div>

        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">登入</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">註冊</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">會員中心</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/favouriteProduct/listAllFavouriteProduct.jsp">我的最愛</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/shopCart/shopCart.jsp">購物車(<%=buylist.size() %>)</a>
                </li>

            </ul>
        </div>


    </nav>
    <!-- navbar end -->
    <section class="blank0"></section>
    
    <!-- 內容 -->
    <section class="blank1">

<div id="productShow" style="margin-left:350px">
	
	<%@ include file="page/page1.file" %> 
	<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<div class="display" >
		<div class="img"><img src="" alt=""></div>
	<div class="card">
		<div class="p_img" name="p_image">
			<img class="rounded" src="<%=request.getContextPath()%>/front-end/product/proPic.do?p_id=${proVO.p_id}">
		</div>
		<div class="p_word">
		<div class="p_tital" name="p_name"><a href="<%=request.getContextPath()%>/front-end/product/listOneProduct.jsp?p_id=${proVO.p_id}"><front class="p_name">${proVO.p_name}</front></a></div>
		<form class="p_nameform" action="<%=request.getContextPath()%>/front-end/product/pro.do" method="POST">
			<input type="hidden" name="p_id" value="${proVO.p_id}">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
			<div class="p_price">$${proVO.p_price}</div>
			
			<div class="p_love" > 
			<c:choose>
			<c:when test="${favpSvc.getOne(proVO.p_id, sessionScope.memberVO.mem_id).p_id eq null}">
			<img class="img-icon" alt="" src="<%=request.getContextPath()%>/front-end/product/images/icons/empty.png" id="${proVO.p_id}${sessionScope.memberVO.mem_id}" title="加入最愛">
			</c:when>
			<c:otherwise>
			<img class="img-icon" alt="" src="<%=request.getContextPath()%>/front-end/product/images/icons/full.png" id="${proVO.p_id}${sessionScope.memberVO.mem_id}" title="取消最愛">
			</c:otherwise>
			</c:choose>
			</div>
			
			<div class="p_car" >
			<form name="shoppingForm" action="<%=request.getContextPath()%>/front-end/product/Shopping.do" method="POST">
			<input type="hidden" name="p_id" value="${proVO.p_id}">
      		<input type="hidden" name="p_name" value="${proVO.p_name}">
      		<input type="hidden" name="quantity" value="1">
      		<input type="hidden" name="p_price" value="${proVO.p_price}">
      		<input type="hidden" name="p_stock" value="${proVO.p_stock}">
      		<input type="hidden" name="action" value="ADD">	
      		<input type="hidden" name="url" value="<%=request.getRequestURI()%>?<%=request.getQueryString()%>">
      		<input type="image" class="img-icon" alt="Submit" src="<%=request.getContextPath()%>/front-end/product/images/icons/shopping-cart.png"  title="加入購物車" >
			</FORM>
			</div>
			
		</div>
	</div>
		
	</c:forEach>
</div>
<%@ include file="page/page2.file" %>
<br>

<script>
// 加入最愛
$('img.img-icon').click(function(){
	var source = $(this).attr('src');
	if (source.includes('empty')){
		// empty 為 空心愛心圖案, 代表收藏文章功能, 按下後置換圖片, 並且以AJAX方式送出請求
		if(${sessionScope.memberVO ne null}) {
		var thisID = this.id;
		var p_id = thisID.substring(0, 4);
		var mem_id = thisID.substring(4, 11);
		$(this).attr('src', '<%=request.getContextPath()%>/front-end/product/images/icons/full.png');
		$(this).attr('title', '取消最愛');
		$.ajax({
			url: '<%=request.getContextPath()%>/front-end/product/favp.do',
			type: 'POST',
			data: {
				p_id: p_id,
				mem_id: mem_id,
				action: 'insert'
			},
			success: function(){
				Swal.fire({
					icon: 'info',
					title: '加入成功',
					showConfirmButton: false,
					timer: 750
				})
			}
		});
		}else{
			<%
				session.setAttribute("location", request.getRequestURI()+"?"+request.getQueryString());
			%>
			Swal.fire({
				icon: 'info',
				title: '請登入會員',
				showConfirmButton: false,
				timer: 1500
			})
			var timer = setTimeout(function(){
				document.location.href = '<%=request.getContextPath()%>/front-end/member/login.jsp';
			}, 1500);
				
			
		}
	}
	else if (source.includes('full')){
		if(${sessionScope.memberVO ne null}) {
		var thisID = this.id;
		var p_id = thisID.substring(0, 4);
		var mem_id = thisID.substring(4, 11);
		$(this).attr('src', '<%=request.getContextPath()%>/front-end/product/images/icons/empty.png');
		$(this).attr('title', '加入最愛');
		$.ajax({
			url: '<%=request.getContextPath()%>/front-end/product/favp.do',
			type: 'POST',
			data: {
				p_id: p_id,
				mem_id: mem_id,
				action: 'delete2'
			},
			success: function(){
				Swal.fire({
					icon: 'info',
					title: '已取消',
					showConfirmButton: false,
					timer: 750
				})
			}
	});
	}else{
		<%session.setAttribute("location", request.getRequestURI()+"?"+request.getQueryString());%>	
		document.location.href = '<%=request.getContextPath()%>/front-end/member/login.jsp';
	}
	}
	});
	
$('input.img-icon').click(function(){
	Swal.fire({
		icon: 'info',
		title: '加入成功',
		showConfirmButton: false,
		timer: 750
	})
	});
	
//連結至商品
// $('front.p_name').click(function(){
// 	var index =$('front.p_name').index(this);
// 	console.log(index);
// $(".p_nameform").eq(index).submit()
// 	});
</script>




</body>
</html>