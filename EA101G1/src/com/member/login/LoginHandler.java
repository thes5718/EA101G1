package com.member.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

//@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 檢查使用者輸入的帳號 (account) 密碼 (password) 是否有效
	protected boolean allowUser(String mem_email, String mem_pass, HttpServletRequest req) {
		
		MemberService memberSvc = new MemberService();
		// 利用 mem_email 查詢出該筆資料的 email 及 password 再回傳 memberVO 出來
		
		MemberVO memberVO = memberSvc.loginByEmail(mem_email);
		
		// 取出回傳 memberVO 的 password
		
//		String mem_emailDB = memberVO.getMem_email();
		String mem_passDB = memberVO.getMem_pass();
		
		if (mem_passDB == null) {
			// mem_pass 註冊為 not null, 若取出空值表示無此筆資料
			return false;
		} else if (!mem_pass.matches(mem_passDB)) {
			// 將傳入的 password 與 資料庫查詢出來的 password 比對
			return false;
		}
		
		// 驗證成功, 把 memberVO 存入
				
//		javax.servlet.http.HttpSession session = req.getSession();
//		session.setAttribute("memberVO", memberVO);
		req.setAttribute("memberVO", memberVO);
		
		return true;
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String loginDenied = 	"/front-end/login/login_denied.jsp";
		String loginSuccess = 	"/front-end/login/login_success.jsp";
		String login = 			"/front-end/login/login.jsp";
		
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
			
			if (!allowUser(mem_email, mem_pass, req)){
				// 帳號或密碼無效時進入的區塊
				RequestDispatcher login_denied = req.getRequestDispatcher(loginDenied);
				login_denied.forward(req, res);
//				res.sendRedirect(req.getContextPath() + loginDenied);
			} else {
					// 帳號及密碼有效時進入的區塊
					javax.servlet.http.HttpSession session = req.getSession();
					
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
					RequestDispatcher successView = req.getRequestDispatcher(loginSuccess);
					successView.forward(req, res);
//					res.sendRedirect(req.getContextPath() + loginSuccess);
			}
			
		} catch (Exception ignored) {
			System.out.println(ignored.getMessage());
		}
		
	}
}
