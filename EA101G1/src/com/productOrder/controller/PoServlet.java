package com.productOrder.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProDAO;
import com.productOrder.model.PoService;
import com.productOrderList.model.PolVO;

public class PoServlet extends HttpServlet {

	public PoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		
		if("insert".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id");
				System.out.println(mem_id);
				List <PolVO> list = (ArrayList<PolVO>)session.getAttribute("list");
				for(PolVO polvo : list) {
					System.out.println(polvo.getPo_id());
					System.out.println(polvo.getOrder_qua());
					System.out.println(polvo.getP_price());
				}
				PoService poSvc = new PoService();
				poSvc.AddOrder(mem_id, list);
				System.out.println("訂單已成立");
			}catch (Exception e) {
				System.out.println("訂單已失敗");
				e.printStackTrace();
			}
		}
	}

}
