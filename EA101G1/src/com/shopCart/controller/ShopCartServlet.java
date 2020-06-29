package com.shopCart.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.product.model.*;
import com.shopCart.model.PRODUCT;

@MultipartConfig
public class ShopCartServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();

		HttpSession session = req.getSession();
		Vector<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				System.out.println("123");
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);//buylist.remove(d);新版寫法
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;
				System.out.println("123");
				// 取得後來新增的書籍
				PRODUCT aproduct = getProduct(req);
				Double sub = aproduct.getQuantity()*aproduct.getPrice();
				aproduct.setSub(sub);
				// 新增第一本書籍至購物車時
				if (buylist == null) {
					buylist = new Vector<PRODUCT>();
					buylist.add(aproduct);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						PRODUCT product = buylist.get(i);
						
						// 假若新增的書籍和購物車的書籍一樣時
						if (aproduct.getName().equals(product.getName())) {
							product.setQuantity(product.getQuantity()
									+ aproduct.getQuantity());
							sub=product.getQuantity()*product.getPrice();
							product.setSub(sub);
							buylist.setElementAt(product, i);//buylist.set(i, book);新版寫法 將book放到原來的位置
							match = true;
							
						} // end of if name matches
					} // end of for

					// 假若新增的書籍和購物車的書籍不一樣時
					if (!match) 
						buylist.add(aproduct);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = req.getParameter("url");
			res.sendRedirect(url);
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			double total = buylist.stream()
					.mapToDouble(p ->p.getPrice() * p.getQuantity())
					.sum();
			
//			for (int i = 0; i < buylist.size(); i++) {
//				PRODUCT order = buylist.get(i);
//				Double price = new Double(order.getPrice());
//				Integer quantity = order.getQuantity();
//				total += (price * quantity);
//			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private PRODUCT getProduct(HttpServletRequest req) throws IOException, ServletException {

		Integer quantity = new Integer(req.getParameter("quantity"));
		String p_name = req.getParameter("p_name");
		String p_id = req.getParameter("p_id");
		Double p_price = new Double(req.getParameter("p_price"));
		Integer p_stock = new Integer(req.getParameter("p_stock"));

		PRODUCT pro = new PRODUCT();

		pro.setName(p_name);
		pro.setId(p_id);
		pro.setPrice(p_price);
		pro.setQuantity(quantity);
		pro.setStock(p_stock);
		return pro;
	}
}
