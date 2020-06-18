package com.member.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.member.model.*;

//@WebServlet("/MemberServlet")
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 來自 select_page.jsp 的請求
		if("getOne_For_Display-front".equals(action)) { 
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_id").trim();
				String strReg = "^M[(0-9)]{6}$";
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				} else if (!str.matches(strReg)) {
					errorMsgs.add("會員編號： 請輸入正確格式");
				}
				
				String mem_id = null;
				try {
					mem_id = new String(str.toString());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the user back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_id);
				
				if (memberVO.getMem_id() == null) {
					errorMsgs.add("查無資料");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件, 存入 req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Display-back".equals(action)) { 
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			String url = "/back-end/member/select_page.jsp";
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				String mem_id = null;
				try {
					mem_id = new String(str.toString());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the user back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_id);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件, 存入 req
				String listOneURL = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(listOneURL); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update-front".equals(action)) { // 來自listAllEmp.jsp的請求
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
				req.setAttribute("memberVO", memberVO); // 資料庫取出的 memberVO 物件, 存入 req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_member_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料： " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listOneMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update-back".equals(action)) { // 來自listAllEmp.jsp的請求
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
				req.setAttribute("memberVO", memberVO); // 資料庫取出的 memberVO 物件, 存入 req
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_member_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料： " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listOneMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update-front".equals(action)) { // 來自 update_member_input.jsp 的請求
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// Send the ErrorPage View.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id").trim();
				
				String mem_email = req.getParameter("mem_email").trim();
				String mem_emailReg = "[@.(a-zA-Z0-9)]{2,30}";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員信箱： 請勿空白");
				} else if (!mem_email.trim().matches(mem_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱： 只能是英文字母(含大小寫)、數字和_ , 且長度必須在2到30之間");
				} else if (!mem_email.trim().contains("@")) {
					errorMsgs.add("會員信箱： 請輸入正確的電子信箱格式");
				}
				
				String mem_pass = req.getParameter("mem_pass").trim();
				String mem_passReg = "[(a-zA-Z0-9)]{2,30}";
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("會員密碼： 請勿空白");
				} else if (!mem_pass.trim().matches(mem_passReg)) {
					errorMsgs.add("會員密碼： 只能是英文字母(含大小寫)、數字, 且長度必須在2到30之間");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,30}$";
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員名稱： 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員名稱： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到30之間");
				}
				
				byte[] mem_icon = getPartByteArray(req);
				if (mem_icon.length == 0) {
					// 上船的圖片大小為 0 , 則取出資料庫該筆資料的圖片取代成要修改的圖片
					MemberService msv = new MemberService();
					MemberVO memberVODB = msv.getOneMember(mem_id);
					byte[] mem_iconDB = memberVODB.getMem_icon();
					mem_icon = mem_iconDB;
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_phoneReg = "[(0-9)]{10}";
				Integer mem_phoneInt = null;
				try {
					mem_phoneInt = new Integer(mem_phone);
				} catch (NumberFormatException e) {
					errorMsgs.add("會員手機： 請輸入有效格式");
				}
				
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("會員手機： 請勿空白");
				} else if (!mem_phone.trim().matches(mem_phoneReg)) {
					errorMsgs.add("會員手機： 只能是數字 , 且長度必須為10");
				}
				
				String mem_addr = req.getParameter("mem_addr").trim();
				String mem_addrReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{2,80}$";
				if (mem_addr == null || mem_addr.trim().length() == 0) {
					errorMsgs.add("會員地址： 請勿空白");
				} else if (!mem_addr.trim().matches(mem_addrReg)) {
					errorMsgs.add("會員地址： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到80之間");
				}
				
				String bank_acc = req.getParameter("bank_acc").trim();
				String bank_accReg = "[(0-9)]{2,14}";
				Double bank_accDou = null;
				try {
					bank_accDou = new Double(bank_acc);
				} catch (NumberFormatException e) {
					errorMsgs.add("銀行帳戶： 請輸入有效格式");
				}
				
				if (bank_acc == null || bank_acc.trim().length() == 0) {
					errorMsgs.add("銀行帳戶： 請勿空白");
				} else if (!bank_acc.trim().matches(bank_accReg)) {
					errorMsgs.add("銀行帳戶： 只能是數字 , 且長度必須在2到14之間");
				}
				
				String card_no = req.getParameter("card_no").trim();
				String card_noReg = "[(0-9)]{16}";
				Double card_noDou = null;
				try {
					card_noDou = new Double(card_no);
				} catch (NumberFormatException e) {
					errorMsgs.add("信用卡號： 請輸入有效格式");
				}
				
				if (card_no == null || card_no.trim().length() == 0) {
					errorMsgs.add("信用卡號： 請勿空白");
				} else if (!card_no.trim().matches(card_noReg)) {
					errorMsgs.add("信用卡號： 只能是數字 , 且長度必須為16");
				}
				
				String card_yy = req.getParameter("card_yy").trim();
				String card_yyReg = "[(0-9)]{4}";
				Integer card_yyInt = null;
				
				try {
					card_yyInt = new Integer(card_yy);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期年份： 請輸入有效格式");
				}
				
				if (card_yy == null || card_yy.trim().isEmpty()) {
					errorMsgs.add("到期年份： 請勿空白");
				} else if (!card_yy.trim().matches(card_yyReg)) {
					errorMsgs.add("到期年份： 只能是數字 , 且長度必須為4");
				}
				
				String card_mm = req.getParameter("card_mm").trim();
				String card_mmReg = "[(0-9)]{2}";
				Integer card_mmInt = null;
				
				try {
					card_mmInt = new Integer(card_mm);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期月份： 請輸入有效格式");
				}
				
				if (card_mm == null || card_mm.trim().isEmpty()) {
					errorMsgs.add("到期月份： 請勿空白");
				} else if (!card_mm.trim().matches(card_mmReg)) {
					errorMsgs.add("到期月份： 只能是數字 , 且長度必須為2");
				} else if (card_mmInt >= 13 || card_mmInt <= 0) {
					errorMsgs.add("到期月份： 請輸入有效月份");
				}
				
				java.lang.String card_sec = req.getParameter("card_sec").trim();
				String card_secReg = "[(0-9)]{3}";
				Integer card_secInt = null;
				
				try {
					card_secInt = new Integer(card_sec);
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片安全碼： 請輸入有效格式");
				}
				
				if (card_sec == null || card_sec.trim().isEmpty()) {
					errorMsgs.add("卡片安全碼： 請勿空白");
				} else if (!card_sec.trim().matches(card_secReg)) {
					errorMsgs.add("卡片安全碼： 只能是數字 , 且長度必須為3");
				}
				
				java.lang.Integer mem_autho = null;
				try {
					mem_autho = new Integer(req.getParameter("mem_autho").trim());
				} catch (NumberFormatException e) {
					mem_autho = 0;
					errorMsgs.add("權限等級： 請輸入權限等級");
				}
				
				String mem_bonusStr = req.getParameter("mem_bonus").trim();
				java.lang.Integer mem_bonus = null;
				try {
					mem_bonus = new Integer(mem_bonusStr);
				} catch (NumberFormatException e) {
					mem_bonus = 0;
					errorMsgs.add("紅利點數： 請輸入有效格式");
				}
				
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				java.sql.Date mem_joindat = null;
				try {
					mem_joindat = java.sql.Date.valueOf(req.getParameter("mem_joindat").trim());
					long nowLong = now.getTime();
					long inputLong = mem_joindat.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_joindat = now;
						errorMsgs.add("加入時間： 請輸入正確的日期");
					}
				} catch (IllegalArgumentException e) {
					mem_joindat = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("加入時間： 請輸入有效格式");
				}
				
				java.sql.Date mem_birth = null;
				try {
					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
					long nowLong = now.getTime();
					long inputLong = mem_birth.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_birth = now;
						errorMsgs.add("會員生日： 請輸入正確的日期");
					}
					
				} catch (IllegalArgumentException e) {
					mem_birth = new java.sql.Date(System.currentTimeMillis());
//					mem_birth = now;
					errorMsgs.add("會員生日： 請輸入有效格式");
				}
				
				Integer mem_warn = null;
				try {
					mem_warn = new Integer(req.getParameter("mem_warn").trim());
					
				} catch (NumberFormatException e) {
					mem_warn = 3;
					errorMsgs.add("警告次數： 請輸入有效格式");
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_id(mem_id);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_pass(mem_pass);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_icon(mem_icon);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_addr(mem_addr);
				memberVO.setBank_acc(bank_acc);
				memberVO.setCard_no(card_no);
				memberVO.setCard_yy(card_yy);
				memberVO.setCard_mm(card_mm);
				memberVO.setCard_sec(card_sec);
				memberVO.setMem_autho(mem_autho);
				memberVO.setMem_bonus(mem_bonus);
				memberVO.setMem_joindat(mem_joindat);
				memberVO.setMem_birth(mem_birth);
				memberVO.setMem_warn(mem_warn);
				
				// Send the user back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的 memberVO 物件, 也存入 req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/***************************2.開始修改資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_id, mem_email, mem_pass,
						mem_name, mem_icon, mem_phone, mem_addr, bank_acc,
						card_no, card_yy, card_mm, card_sec, mem_autho,
						mem_bonus, mem_joindat, mem_birth, mem_warn);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫 update 成功後, 正確的 memberVO 物件, 存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後, 轉交 listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗： " + e.getMessage());
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update-back".equals(action)) { // 來自 update_member_input.jsp 的請求
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// Send the ErrorPage View.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id").trim();
				
				String mem_email = req.getParameter("mem_email").trim();
				String mem_emailReg = "[@.(a-zA-Z0-9)]{2,30}";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員信箱： 請勿空白");
				} else if (!mem_email.trim().matches(mem_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱： 只能是英文字母(含大小寫)、數字和_ , 且長度必須在2到30之間");
				} else if (!mem_email.trim().contains("@")) {
					errorMsgs.add("會員信箱： 請輸入正確的電子信箱格式");
				}
				
				String mem_pass = req.getParameter("mem_pass").trim();
				String mem_passReg = "[(a-zA-Z0-9)]{2,30}";
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("會員密碼： 請勿空白");
				} else if (!mem_pass.trim().matches(mem_passReg)) {
					errorMsgs.add("會員密碼： 只能是英文字母(含大小寫)、數字, 且長度必須在2到30之間");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,30}$";
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員名稱： 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員名稱： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到30之間");
				}
				
				byte[] mem_icon = getPartByteArray(req);
				if (mem_icon.length == 0) {
					// 上船的圖片大小為 0 , 則取出資料庫該筆資料的圖片取代成要修改的圖片
					MemberService msv = new MemberService();
					MemberVO memberVODB = msv.getOneMember(mem_id);
					byte[] mem_iconDB = memberVODB.getMem_icon();
					mem_icon = mem_iconDB;
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_phoneReg = "[(0-9)]{10}";
				Integer mem_phoneInt = null;
				try {
					mem_phoneInt = new Integer(mem_phone);
				} catch (NumberFormatException e) {
					errorMsgs.add("會員手機： 請輸入有效格式");
				}
				
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("會員手機： 請勿空白");
				} else if (!mem_phone.trim().matches(mem_phoneReg)) {
					errorMsgs.add("會員手機： 只能是數字 , 且長度必須為10");
				}
				
				String mem_addr = req.getParameter("mem_addr").trim();
				String mem_addrReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{2,80}$";
				if (mem_addr == null || mem_addr.trim().length() == 0) {
					errorMsgs.add("會員地址： 請勿空白");
				} else if (!mem_addr.trim().matches(mem_addrReg)) {
					errorMsgs.add("會員地址： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到80之間");
				}
				
				String bank_acc = req.getParameter("bank_acc").trim();
				String bank_accReg = "[(0-9)]{2,14}";
				Double bank_accDou = null;
				try {
					bank_accDou = new Double(bank_acc);
				} catch (NumberFormatException e) {
					errorMsgs.add("銀行帳戶： 請輸入有效格式");
				}
				
				if (bank_acc == null || bank_acc.trim().length() == 0) {
					errorMsgs.add("銀行帳戶： 請勿空白");
				} else if (!bank_acc.trim().matches(bank_accReg)) {
					errorMsgs.add("銀行帳戶： 只能是數字 , 且長度必須在2到14之間");
				}
				
				String card_no = req.getParameter("card_no").trim();
				String card_noReg = "[(0-9)]{16}";
				Double card_noDou = null;
				try {
					card_noDou = new Double(card_no);
				} catch (NumberFormatException e) {
					errorMsgs.add("信用卡號： 請輸入有效格式");
				}
				
				if (card_no == null || card_no.trim().length() == 0) {
					errorMsgs.add("信用卡號： 請勿空白");
				} else if (!card_no.trim().matches(card_noReg)) {
					errorMsgs.add("信用卡號： 只能是數字 , 且長度必須為16");
				}
				
				String card_yy = req.getParameter("card_yy").trim();
				String card_yyReg = "[(0-9)]{4}";
				Integer card_yyInt = null;
				
				try {
					card_yyInt = new Integer(card_yy);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期年份： 請輸入有效格式");
				}
				
				if (card_yy == null || card_yy.trim().isEmpty()) {
					errorMsgs.add("到期年份： 請勿空白");
				} else if (!card_yy.trim().matches(card_yyReg)) {
					errorMsgs.add("到期年份： 只能是數字 , 且長度必須為4");
				}
				
				String card_mm = req.getParameter("card_mm").trim();
				String card_mmReg = "[(0-9)]{2}";
				Integer card_mmInt = null;
				
				try {
					card_mmInt = new Integer(card_mm);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期月份： 請輸入有效格式");
				}
				
				if (card_mm == null || card_mm.trim().isEmpty()) {
					errorMsgs.add("到期月份： 請勿空白");
				} else if (!card_mm.trim().matches(card_mmReg)) {
					errorMsgs.add("到期月份： 只能是數字 , 且長度必須為2");
				} else if (card_mmInt >= 13 || card_mmInt <= 0) {
					errorMsgs.add("到期月份： 請輸入有效月份");
				}
				
				java.lang.String card_sec = req.getParameter("card_sec").trim();
				String card_secReg = "[(0-9)]{3}";
				Integer card_secInt = null;
				
				try {
					card_secInt = new Integer(card_sec);
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片安全碼： 請輸入有效格式");
				}
				
				if (card_sec == null || card_sec.trim().isEmpty()) {
					errorMsgs.add("卡片安全碼： 請勿空白");
				} else if (!card_sec.trim().matches(card_secReg)) {
					errorMsgs.add("卡片安全碼： 只能是數字 , 且長度必須為3");
				}
				
				java.lang.Integer mem_autho = null;
				try {
					mem_autho = new Integer(req.getParameter("mem_autho").trim());
				} catch (NumberFormatException e) {
					mem_autho = 0;
					errorMsgs.add("權限等級： 請輸入權限等級");
				}
				
				String mem_bonusStr = req.getParameter("mem_bonus").trim();
				java.lang.Integer mem_bonus = null;
				try {
					mem_bonus = new Integer(mem_bonusStr);
				} catch (NumberFormatException e) {
					mem_bonus = 0;
					errorMsgs.add("紅利點數： 請輸入有效格式");
				}
				
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				java.sql.Date mem_joindat = null;
				try {
					mem_joindat = java.sql.Date.valueOf(req.getParameter("mem_joindat").trim());
					long nowLong = now.getTime();
					long inputLong = mem_joindat.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_joindat = now;
						errorMsgs.add("加入時間： 請輸入正確的日期");
					}
				} catch (IllegalArgumentException e) {
					mem_joindat = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("加入時間： 請輸入有效格式");
				}
				
				java.sql.Date mem_birth = null;
				try {
					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
					long nowLong = now.getTime();
					long inputLong = mem_birth.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_birth = now;
						errorMsgs.add("會員生日： 請輸入正確的日期");
					}
					
				} catch (IllegalArgumentException e) {
					mem_birth = new java.sql.Date(System.currentTimeMillis());
//					mem_birth = now;
					errorMsgs.add("會員生日： 請輸入有效格式");
				}
				
				Integer mem_warn = null;
				try {
					mem_warn = new Integer(req.getParameter("mem_warn").trim());
					
				} catch (NumberFormatException e) {
					mem_warn = 3;
					errorMsgs.add("警告次數： 請輸入有效格式");
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_id(mem_id);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_pass(mem_pass);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_icon(mem_icon);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_addr(mem_addr);
				memberVO.setBank_acc(bank_acc);
				memberVO.setCard_no(card_no);
				memberVO.setCard_yy(card_yy);
				memberVO.setCard_mm(card_mm);
				memberVO.setCard_sec(card_sec);
				memberVO.setMem_autho(mem_autho);
				memberVO.setMem_bonus(mem_bonus);
				memberVO.setMem_joindat(mem_joindat);
				memberVO.setMem_birth(mem_birth);
				memberVO.setMem_warn(mem_warn);
				
				// Send the user back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的 memberVO 物件, 也存入 req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/***************************2.開始修改資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_id, mem_email, mem_pass,
						mem_name, mem_icon, mem_phone, mem_addr, bank_acc,
						card_no, card_yy, card_mm, card_sec, mem_autho,
						mem_bonus, mem_joindat, mem_birth, mem_warn);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫 update 成功後, 正確的 memberVO 物件, 存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後, 轉交 listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗： " + e.getMessage());
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addMember.jsp 的請求
			
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_email = req.getParameter("mem_email").trim();
				String mem_emailReg = "[@.(a-zA-Z0-9)]{2,30}";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員信箱： 請勿空白");
				} else if (!mem_email.trim().matches(mem_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱： 只能是英文字母(含大小寫)、數字和_ , 且長度必須在2到30之間");
				} else if (!mem_email.trim().contains("@")) {
					errorMsgs.add("會員信箱： 請輸入正確的電子信箱格式");
				}
				
				String mem_pass = req.getParameter("mem_pass").trim();
				String mem_passReg = "[(a-zA-Z0-9)]{2,30}";
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("會員密碼： 請勿空白");
				} else if (!mem_pass.matches(mem_passReg)) {
					errorMsgs.add("會員密碼： 只能是英文字母(含大小寫)、數字, 且長度必須在2到30之間");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,30}$";
				if(mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員名稱： 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("會員名稱： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到30之間");
				}
				
				byte[] mem_icon = getPartByteArray(req);
//				if (mem_icon == null) {
//					errorMsgs.add("會員圖片： 請勿空白");
//				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_phoneReg = "[(0-9)]{10}";
				Integer mem_phoneInt = null;
				try {
					mem_phoneInt = new Integer(mem_phone);
				} catch (NumberFormatException e) {
					errorMsgs.add("會員手機： 請輸入有效格式");
				}
				
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("會員手機： 請勿空白");
				} else if (!mem_phone.trim().matches(mem_phoneReg)) {
					errorMsgs.add("會員手機： 只能是數字 , 且長度必須為10");
				}
				
				String mem_addr = req.getParameter("mem_addr").trim();
				String mem_addrReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{2,80}$";
				if (mem_addr == null || mem_addr.trim().length() == 0) {
					errorMsgs.add("會員地址： 請勿空白");
				} else if (!mem_addr.trim().matches(mem_addrReg)) {
					errorMsgs.add("會員地址： 只能是中、英文字母(含大小寫)、數字和_ , 且長度在2到80之間");
				}
				
				String bank_acc = req.getParameter("bank_acc").trim();
				String bank_accReg = "[(0-9)]{2,14}";
				Double bank_accDou = null;
				try {
					bank_accDou = new Double(bank_acc);
				} catch (NumberFormatException e) {
					errorMsgs.add("銀行帳戶： 請輸入有效格式");
				}
				
				if (bank_acc == null || bank_acc.trim().length() == 0) {
					errorMsgs.add("銀行帳戶： 請勿空白");
				} else if (!bank_acc.trim().matches(bank_accReg)) {
					errorMsgs.add("銀行帳戶： 只能是數字 , 且長度必須在2到14之間");
				}
				
				String card_no = req.getParameter("card_no").trim();
				String card_noReg = "[(0-9)]{16}";
				Double card_noDou = null;
				try {
					card_noDou = new Double(card_no);
				} catch (NumberFormatException e) {
					errorMsgs.add("信用卡號： 請輸入有效格式");
				}
				
				if (card_no == null || card_no.trim().length() == 0) {
					errorMsgs.add("信用卡號： 請勿空白");
				} else if (!card_no.trim().matches(card_noReg)) {
					errorMsgs.add("信用卡號： 只能是數字 , 且長度必須為16");
				}
				
				String card_yy = req.getParameter("card_yy").trim();
				String card_yyReg = "[(0-9)]{4}";
				Integer card_yyInt = null;
				
				try {
					card_yyInt = new Integer(card_yy);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期年份： 請輸入有效格式");
				}
				
				if (card_yy == null || card_yy.trim().isEmpty()) {
					errorMsgs.add("到期年份： 請勿空白");
				} else if (!card_yy.trim().matches(card_yyReg)) {
					errorMsgs.add("到期年份： 只能是數字 , 且長度必須為4");
				}
				
				String card_mm = req.getParameter("card_mm").trim();
				String card_mmReg = "[(0-9)]{2}";
				Integer card_mmInt = null;
				
				try {
					card_mmInt = new Integer(card_mm);
				} catch (NumberFormatException e) {
					errorMsgs.add("到期月份： 請輸入有效格式");
				}
				
				if (card_mm == null || card_mm.trim().isEmpty()) {
					errorMsgs.add("到期月份： 請勿空白");
				} else if (!card_mm.trim().matches(card_mmReg)) {
					errorMsgs.add("到期月份： 只能是數字 , 且長度必須為2");
				} else if (card_mmInt >= 13 || card_mmInt <= 0) {
					errorMsgs.add("到期月份： 請輸入有效月份");
				}
				
				java.lang.String card_sec = req.getParameter("card_sec").trim();
				String card_secReg = "[(0-9)]{3}";
				Integer card_secInt = null;
				
				try {
					card_secInt = new Integer(card_sec);
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片安全碼： 請輸入有效格式");
				}
				
				if (card_sec == null || card_sec.trim().isEmpty()) {
					errorMsgs.add("卡片安全碼： 請勿空白");
				} else if (!card_sec.trim().matches(card_secReg)) {
					errorMsgs.add("卡片安全碼： 只能是數字 , 且長度必須為3");
				}
				
				java.lang.Integer mem_autho = null;
				try {
					mem_autho = new Integer(req.getParameter("mem_autho").trim());
				} catch (NumberFormatException e) {
					mem_autho = 0;
					errorMsgs.add("權限等級： 請輸入權限等級");
				}
				
				String mem_bonusStr = req.getParameter("mem_bonus").trim();
				java.lang.Integer mem_bonus = null;
				try {
					mem_bonus = new Integer(mem_bonusStr);
				} catch (NumberFormatException e) {
					mem_bonus = 0;
					errorMsgs.add("紅利點數： 請輸入有效格式");
				}
				
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				java.sql.Date mem_joindat = null;
				try {
					mem_joindat = java.sql.Date.valueOf(req.getParameter("mem_joindat").trim());
					long nowLong = now.getTime();
					long inputLong = mem_joindat.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_joindat = now;
						errorMsgs.add("加入時間： 請輸入正確的日期");
					}
				} catch (IllegalArgumentException e) {
					mem_joindat = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("加入時間： 請輸入有效格式");
				}
				
				java.sql.Date mem_birth = null;
				try {
					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
					long nowLong = now.getTime();
					long inputLong = mem_birth.getTime();
					if (inputLong - nowLong > 0) {
						// 輸入的日期是現在之後(未來)
						mem_birth = now;
						errorMsgs.add("會員生日： 請輸入正確的日期");
					}
					
				} catch (IllegalArgumentException e) {
					mem_birth = new java.sql.Date(System.currentTimeMillis());
//					mem_birth = now;
					errorMsgs.add("會員生日： 請輸入有效格式");
				}
				
				Integer mem_warn = null;
				try {
					mem_warn = new Integer(req.getParameter("mem_warn").trim());
					
				} catch (NumberFormatException e) {
					mem_warn = 3;
					errorMsgs.add("警告次數： 請輸入有效格式");
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_email(mem_email);
				memberVO.setMem_pass(mem_pass);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_icon(mem_icon);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_addr(mem_addr);
				memberVO.setBank_acc(bank_acc);
				memberVO.setCard_no(card_no);
				memberVO.setCard_yy(card_yy);
				memberVO.setCard_mm(card_mm);
				memberVO.setCard_sec(card_sec);
				memberVO.setMem_autho(mem_autho);
				memberVO.setMem_bonus(mem_bonus);
				memberVO.setMem_joindat(mem_joindat);
				memberVO.setMem_birth(mem_birth);
				memberVO.setMem_warn(mem_warn);
				
				// Send the user back to the form, if there were error
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的 memberVO 物件, 也存入 req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_email, mem_pass, mem_name,
						mem_icon, mem_phone, mem_addr, bank_acc,
						card_no, card_yy, card_mm, card_sec, mem_autho,
						mem_bonus, mem_joindat, mem_birth, mem_warn);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("memberVO", memberVO);
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗： " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {
			
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(mem_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗： " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("login".equals(action)) {
			String loginDenied = 	"/front-end/member/login_denied.jsp";
			String loginSuccess = 	"/front-end/member/login_success.jsp";
			String login = 			"/front-end/member/login.jsp";
			String select_page = 	"/front-end/member/select_page.jsp";
			String index = 			"/front-end/index.jsp";
			
			java.util.List<String> errorMsgs = new java.util.LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				
				String mem_email = req.getParameter("mem_email").trim();
				String mem_emailReg = "[@.(a-zA-Z0-9)]{2,30}";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員信箱： 請勿空白");
				} else if (!mem_email.trim().matches(mem_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員信箱： 只能是英文字母(含大小寫)、數字和_ , 且長度必須在2到30之間");
				} else if (!mem_email.trim().contains("@")) {
					errorMsgs.add("會員信箱： 請輸入正確的電子信箱格式");
				}
				
				String mem_pass = req.getParameter("mem_pass").trim();
				String mem_passReg = "^[(a-zA-Z0-9)]{2,30}$";
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("會員密碼： 請勿空白");
				} else if (!mem_pass.trim().matches(mem_passReg)) {
					errorMsgs.add("會員密碼： 只能是英文字母(含大小寫)、數字, 且長度必須在2到30之間");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(login);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************2.開始驗證***************************************/
				javax.servlet.http.HttpSession session = req.getSession();
				
				if (!allowUser(mem_email, mem_pass, session)){
					// 帳號或密碼無效時進入的區塊
					RequestDispatcher login_denied = req.getRequestDispatcher(loginDenied);
					login_denied.forward(req, res);
				} else {
						// 帳號及密碼有效時進入的區塊
						
						try {
							// 嘗試尋找來源網頁
							String location = (String) session.getAttribute("location");
							if (location != null) {
								// 當有來源網頁時, 因為已經取得來源網頁的位置作為區域變數
								// 所以先把 sessionScope 裡面的來源網頁 (location) 移除, 
								// 再重導到來源網頁
								session.removeAttribute("location");
								res.sendRedirect(location);
								return;
							}
						} catch (Exception ignored) {
							System.out.println(ignored.getMessage());
						}
						RequestDispatcher successView = req.getRequestDispatcher(index);
						successView.forward(req, res);
				}
				
			} catch (Exception ignored) {
				System.out.println(ignored.getMessage());
			}
		}
		
		if ("logout".equals(action)) {
			
			javax.servlet.http.HttpSession session = req.getSession();
			
			String login = "/front-end/member/login.jsp";
			String index = "/front-end/index.jsp";
			
			try {
//				session.invalidate();
				session.removeAttribute("memberVO");
				
			} catch (Exception ignored) {
				System.out.println(ignored.getMessage());
			}
			
			RequestDispatcher toLogin = req.getRequestDispatcher(index);
			toLogin.forward(req, res);
		}
	}
	
	
	public byte[] getPartByteArray(HttpServletRequest req) throws ServletException, IOException {
		
		Part part = req.getPart("mem_icon"); // Servlet3.0 新增了 Part 介面，讓我們方便地進行檔案上傳處理
		java.io.InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		
		return buf;
	}
	
		
	protected boolean allowUser(String mem_email, String mem_pass, HttpSession session) {
		
		MemberService memberSvc = new MemberService();
		// 利用 mem_email 查詢出該筆資料的 email 及 password 再回傳 memberVO 出來
		
		MemberVO memberVO = memberSvc.loginByEmail(mem_email);
		
		// 取出回傳 memberVO 的 password
//			String mem_emailDB = memberVO.getMem_email();
		String mem_passDB = memberVO.getMem_pass();
		
		if (mem_passDB == null) {
			// mem_pass 註冊為 not null, 若取出空值表示無此筆資料
			return false;
			
		} else if (!mem_pass.matches(mem_passDB)) {
			// 將傳入的 password 與 資料庫查詢出來的 password 比對
			return false;
			
		} else {
			// 驗證成功, 把 memberVO 存入 session
		
			session.setAttribute("memberVO", memberVO);
			
			return true;
		}
	}
		
		
}
