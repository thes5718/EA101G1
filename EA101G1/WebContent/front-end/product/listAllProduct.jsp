<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%	MemberVO memVO=null;

	if(session.getAttribute("memberVO")!=null){
		memVO = (MemberVO) session.getAttribute("memberVO");
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
<title>�Ҧ��ӫ~��� - listAllPro.jsp</title>
 <!-- TODO: ��title ��icon -->
    <link rel="icon shortcut" href="./img/ICON.ico">
    <!-- Bootstrap�x����� https://getbootstrap.com/ -->
    <!-- �s��Bootstrap.min.css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- �ϥ�font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
        integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <!-- �ϥ�google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Sedgwick+Ave+Display&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lakki+Reddy&display=swap" rel="stylesheet">

    <!-- �ϥ�style.css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">

    <!-- �s��Bootstrap�һݭn��js -->
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
		 	/*�S�@��*/
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
			/*�۹��.word����m*/
			position: absolute;
			top:10px;
			left: 0;
			/*���left width�]�n�� ���M�|�Y�X�h*/
			height: 30%;
			width: 100%;
		}
		.p_price{
			border:1px solid yellow;
			/*�۹��.word����m*/
			position: absolute;
			top:110px;
			left:0;
			/*���left width�]�n�� ���M�|�Y�X�h*/
			height:30%;
			width: 100%;
		}
		.p_love{
			border:1px solid yellow;
			/*�۹��.word����m*/
			position: absolute;
			right: 80px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
		}
		.p_car{
			border:1px solid deeppink;
			/*�۹��.word����m*/
			position: absolute;
			right: 20px;
			bottom: 10px;

			width: 50px;
			height: 50px;

			background-size: cover;
		}
		img.img-icon {
			width: 90%;
			height: 90%;
			float: right;
			margin: 0 2px;
		}
		img.img-icon:hover {
			cursor:pointer;
		}
	</style>

</head>
<body>
<!-- navbar -->
    <!-- �ϥ�Boostrap Navbar -->
    <!-- �]�wNavbar��K�e���W�t -->
    <!-- b4-navbar-default �w��Bootstrap�~��,�i�H�ϥΧֱ����O -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <a class="navbar-brand" href="index.html">
            <span class="logo"><i class="fas fa-bomb"></i></span>
            <span class="logo2">S.F.G</span>
            <span class="logo3">{{{</span>
        </a>
        <!-- ��������s -->
        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="navbar2 navbar-dark">
            <div class="row">
                <div class="item col-md-2"><a href="#"></a>�ӫ�</div>
                <div class="item col-md-2"><a href="#"></a>����</div>
                <div class="item col-md-2"><a href="#"></a>���</div>
                <div class="item col-md-2"><a href="#"></a>�Q�װ�</div>
                <div class="item col-md-2"><a href="#"></a>���Q</div>
                <div class="item col-md-2"><a href="#"></a>Q&A</div>
            </div>
        </div>

        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">�n�J</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">���U</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">�|������</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/front-end/favouriteProduct/listAllFavouriteProduct.jsp">�ڪ��̷R</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">�ʪ���</a>
                </li>

            </ul>
        </div>


    </nav>
    <!-- navbar end -->
    <section class="blank0"></section>
    
    <!-- ���e -->
    <section class="blank1">
	<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="test">
	
	<%@ include file="page/page1.file" %> 
	<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<div class="display" >
		<div class="img"><img src="" alt=""></div>
	<div class="card" style="${(proVO.p_stat==0)?"display:none":""}">
		<div class="p_img" name="p_image">
			<img src="<%=request.getContextPath()%>/back-end/product/proPic.do?p_id=${proVO.p_id}">
		</div>
		<div class="p_word">
			<div class="p_tital" name="p_name">${proVO.p_name}</div>
			<div class="p_price">$${proVO.p_price}</div>
			
			<div class="p_love" > 
			<c:choose>
			<c:when test="${favpSvc.getOne(proVO.p_id, sessionScope.memberVO.mem_id).p_id eq null}">
			<img class="img-icon" alt="" src="<%=request.getContextPath()%>/front-end/product/images/icons/empty.png" id="${proVO.p_id}${sessionScope.memberVO.mem_id}" title="�[�J�̷R">
			</c:when>
			<c:otherwise>
			<img class="img-icon" alt="" src="<%=request.getContextPath()%>/front-end/product/images/icons/full.png" id="${proVO.p_id}${sessionScope.memberVO.mem_id}" title="�����̷R">
			</c:otherwise>
			</c:choose>
			</div>
			
			<div class="p_car" >
			<img class="img-icon" alt="" src="<%=request.getContextPath()%>/front-end/product/images/icons/shopping-cart.png" id="${proVO.p_name}${proVO.p_id}${proVO.p_price}${proVO.p_stock}" title="�[�J�ʪ���">
			</FORM>
			</div>
			
		</div>
	</div>
		
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>
<br>

<script>
$('img.img-icon').click(function(){
	var source = $(this).attr('src');
	if (source.includes('empty')){
		// empty �� �Ť߷R�߹Ϯ�, �N���ä峹�\��, ���U��m���Ϥ�, �åB�HAJAX�覡�e�X�ШD
		var thisID = this.id;
		var p_id = thisID.substring(0, 4);
		var mem_id = thisID.substring(4, 11);
		$(this).attr('src', '<%=request.getContextPath()%>/front-end/product/images/icons/full.png');
		$(this).attr('title', '�����̷R');
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
					title: '�[�J���\',
					showConfirmButton: false,
					timer: 750
				})
			}
		});
		
	} else if (source.includes('full')){
		var thisID = this.id;
		var p_id = thisID.substring(0, 4);
		var mem_id = thisID.substring(4, 11);
		$(this).attr('src', '<%=request.getContextPath()%>/front-end/product/images/icons/empty.png');
		$(this).attr('title', '�[�J�̷R');
		$.ajax({
			url: '<%=request.getContextPath()%>/front-end/product/favp.do',
			type: 'POST',
			data: {
				p_id: p_id,
				mem_id: mem_id,
				arcion: 'delete'
			},
			success: function(){
				Swal.fire({
					icon: 'info',
					title: '�w����',
					showConfirmButton: false,
					timer: 750
				})
			}
		});
	}
});
</script>


    </section>
    <!-- ���e ---end  -->


        <!-- footer -->
        <footer id="footer" class="pt-5 ">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6 footer-list">
                        <h5>�ֳt�s��</h4>
                        <ul>
                            <li>
                                <a class="footer-link" href="">S.F.G����</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">���U�|��</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">�ӫ�</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">����</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 footer-list">
                        <h5 class="text-uppercase">�|������</h5>
                        <ul>
                            <li>
                                <a class="footer-link" href="">�v�а�</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">���ʰ�</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">�Q�װ�</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">��ѫ�</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 footer-list">
                        <h5 class="text-uppercase">����ڭ�</h5>
                        <ul>
                            <li>
                                <a class="footer-link" href="">����S.F.G</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">�̷s����</a>
                            </li>
                            <li>
                                <a class="footer-link" href="">���p�v�F��</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 footer-list">
                        <h5 class="text-uppercase">�޳N�䴩</h5>
                        <ul>
                            <li>
                                <a class="footer-link" href="">�s��W��</a>
                            </li>
                            <li>
                                <a class="footer-link" href="<%=request.getContextPath()%>/front-end/product/listAllProduct.jsp">���U&�䴩</a>
                            </li>
                            <li>
                                <a class="footer-link" href="<%=request.getContextPath()%>/front-end/favouriteProduct/listAllFavouriteProduct.jsp">�A�ȱ���</a>
                            </li>
                        </ul>                    
                    </div>
                </div>
            </div>

            <div class="footer-end bg-dark mt-5 py-2">
                <p class="text-center">&copy; Copy right 2020</p>
            </div>
        </footer>

        <button id="chatBtn" class="bg-primary">
            <i class="fas fa-comments"></i>
        </button>



</body>
</html>