package com.favouriteProduct.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favouriteProduct.model.FavpService;
import com.favouriteProduct.model.FavpVO;

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
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
			} catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllFavouriteProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete2".equals(action)) {// ajax用 不轉傳任何網頁
			try {
				System.out.println("delete");
				String p_id = req.getParameter("p_id");
				String mem_id = req.getParameter("mem_id");
				
				FavpService favpSvc = new FavpService();
				favpSvc.deleteFavp(p_id, mem_id);
				
			} catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllFavouriteProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			
			try {
				
				String p_id = req.getParameter("p_id");
				String mem_id = req.getParameter("mem_id");
				if("guest".equals(mem_id)) {
					String url= "/front-end/member/login.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req,res);
				}else {
					FavpVO favpVO = new FavpVO();
					favpVO.setP_id(p_id);
					favpVO.setMem_id(mem_id);
					
					FavpService favpSvc = new FavpService();
					favpSvc.addFavp(favpVO);
					
					String url = "listAllProduct.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
			}catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
