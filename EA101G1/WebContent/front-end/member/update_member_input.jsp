<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" %>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
	// MemberServlet.java (Controller) 存入 req 的 memberVO 物件(包括幫忙取出的 memberVO, 也包括輸入資料錯誤時的 memberVO 物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
<title>會員資料修改 - update_member_input.jsp</title>

<style>
	table#table-1 {
		background-color: #CCCCFF;
		border: 2px solid black;
		text-align: center;
	}
	table#table-1 h4 {
		color: red;
		display: block;
		matgin-bottom: 1px;
	}
	h4 {
		color: blue;
		display: inline;
	}
	table {
		width: 450px;
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
	img#display {
		width: 200px;
		height: 200px;
	}
</style>

</head>
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>會員資料修改 - update_member_input.jsp</h3>
			<h4><a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp"><img alt="" src="<%=request.getContextPath()%>/images/back1.gif" id="back">回首頁</a></h4>
		</td>
	</tr>
</table>

<h3>資料修改</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font>請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form action="member.do" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>會員編號：<font><b>*</b></font></td>
			<td><%=memberVO.getMem_id()%></td>
		</tr>
		<tr>
			<td>會員信箱：<font><b>*</b></font></td>
			<td><%=memberVO.getMem_email()%></td>
		</tr>
		<tr>
			<td>會員密碼：</td>
			<td><input type="text" name="mem_pass" size="45" value="<%=memberVO.getMem_pass()%>"></td>
		</tr>
		<tr>
			<td>會員名稱：</td>
			<td><input type="text" name="mem_name" size="45" value="<%=memberVO.getMem_name()%>"></td>
		</tr>
		<tr>
			<td>會員圖片：</td>
			<td>
				<label for="upload">
					<img alt="" src="<%=request.getContextPath()%>/member/ShowMemberPic.do?mem_id=<%=memberVO.getMem_id()%>" id="display">
				</label>
				<input type="file" name="mem_icon" id="upload">
			</td>
		</tr>
		<tr>
			<td>會員手機：</td>
			<td><input type="text" name="mem_phone" size="45" value="<%=memberVO.getMem_phone()%>"></td>
		</tr>
		<tr>
			<td>會員地址：</td>
			<td><input type="text" name="mem_addr" size="45" value="<%=memberVO.getMem_addr()%>"></td>
		</tr>
		<tr>
			<td>銀行帳戶：</td>
			<td><input type="text" name="bank_acc" size="45" value="<%=memberVO.getBank_acc()%>"></td>
		</tr>
		<tr>
			<td>信用卡號：</td>
			<td><input type="text" name="card_no" size="45" value="<%=memberVO.getCard_no()%>"></td>
		</tr>
		<tr>
			<td>到期年份：</td>
			<td><input type="text" name="card_yy" size="45" value="<%=memberVO.getCard_yy()%>"></td>
		</tr>
		<tr>
			<td>到期月份：</td>
			<td><input type="text" name="card_mm" size="45" value="<%=memberVO.getCard_mm()%>"></td>
		</tr>
		<tr>
			<td>卡片安全碼：</td>
			<td><input type="text" name="card_sec" size="45" value="<%=memberVO.getCard_sec()%>"></td>
		</tr>
		<tr>
			<td>會員權限：</td>
			<td>
				<select name="mem_autho">
					<option value="0" ${memberVO.mem_autho eq 0 ? "selected" : ""}>停權</option>
					<option value="1" ${memberVO.mem_autho eq 1 ? "selected" : ""}>一般會員</option>
					<option value="2" ${memberVO.mem_autho eq 2 ? "selected" : ""}>賣家資格會員</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>紅利點數：</td>
			<td><input type="text" name="mem_bonus" size="45" value="<%=memberVO.getMem_bonus()%>"></td>
		</tr>
		<tr>
			<td>加入時間：</td>
			<td><input type="text" name="mem_joindat" size="45" id="f_date1"></td>
		</tr>
		<tr>
			<td>會員生日：</td>
			<td><input type="text" name="mem_birth" size="45" id="f_date2"></td>
		</tr>
		<tr>
			<td>警告次數：</td>
			<td><input type="text" name="mem_warn" size="45" value="<%=memberVO.getMem_warn()%>"></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="update-front">
	<input type="hidden" name="mem_id" value="<%=memberVO.getMem_id()%>">
	<input type="hidden" name="mem_email" value="<%=memberVO.getMem_email()%>">
	<input type="submit" value="送出修改">
</form>


</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/files/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/files/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/files/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memberVO.getMem_joindat()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date2').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=memberVO.getMem_birth()%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
        function init() {
        var display = document.getElementById("display");
        var upload = document.getElementById("upload");

        upload.addEventListener("change", function(e){
            var files = upload.files;
            if (files && files[0]) {
                for (i = 0; i < files.length; i ++) {
                    if (files[i].type.indexOf("image") < 0) {
                        alert("上傳的格式不符");
                        upload.value = null;
                    } else {
                        var file = files[i];
                        var reader = new FileReader();

                        reader.onload = function(e) {
                            var result = e.target.result;

                            display.setAttribute("src", result);

                        }
                        reader.readAsDataURL(file);
                    }
                }
            }
        });
    }
    window.onload = init;
        
</script>


</html>