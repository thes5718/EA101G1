package com.favouriteProduct.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favouriteProduct.model.FavpService;

@MultipartConfig
public class FavpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("delete".equals(action)) {
			
			try {
				String p_id = req.getParameter("p_id");
				String mem_id = req.getParameter("mem_id");
				
				FavpService favpSvc = new FavpService();
				favpSvc.deleteFavp(p_id, mem_id);
				
				String url = "listAllFavouriteProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			} catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllFavouriteProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
