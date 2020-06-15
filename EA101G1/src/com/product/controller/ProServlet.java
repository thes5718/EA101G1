package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.*;

public class ProServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("p_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String p_id = null;
				try {
					p_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�ӫ~�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ProService ProSvc = new ProService();
				ProVO proVO = ProSvc.getOnePro(p_id);
				if (proVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("proVO", proVO); // ��Ʈw���X��proVO����,�s�Jreq
				String url = "listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnePro.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
			}

		}
		

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllPro.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String p_id = new String(req.getParameter("p_id"));//p001
				
				/*************************** 2.�}�l�d�߸�� ****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(p_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("proVO", proVO); // ��Ʈw���X��proVO����,�s�Jreq
				String url = "update_Pro_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_Pro_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_pro_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String p_id = new String(req.getParameter("p_id").trim());

				String p_name = req.getParameter("p_name");
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Double p_price = null;
				try {
					p_price = new Double(req.getParameter("p_price").trim());
				} catch (NumberFormatException e) {
					p_price = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				String p_info = req.getParameter("p_info").trim();
				if (p_info == null || p_info.trim().length() == 0) {
					errorMsgs.add("�y�z�ФŪť�");
				}

				Integer p_stock = null;
				try {
					p_stock = new Integer(req.getParameter("p_stock").trim());
				} catch (NumberFormatException e) {
					p_stock = 0;
					errorMsgs.add("�w�s�ж�Ʀr.");
				}

				Integer p_stat = null;
				try {
					 p_stat = new Integer(req.getParameter("p_stat").trim());
				} catch (NumberFormatException e) {
					p_stat = 0;
					errorMsgs.add("���A�ж�Ʀr.");
				}
				
				byte[] p_image = req.getParameter("p_image").trim().getBytes();
				String pt_id = req.getParameter("pt_id").trim();
				Integer p_sales = new Integer(req.getParameter("p_sales"));
				
				java.sql.Date p_add_date = java.sql.Date.valueOf(req.getParameter("p_add_date").trim());;

				ProVO proVO = new ProVO();
				proVO.setP_id(p_id);
				proVO.setPt_id(pt_id);
				proVO.setP_name(p_name);
				proVO.setP_price(p_price);
				proVO.setP_image(p_image);
				proVO.setP_info(p_info);
				proVO.setP_stock(p_stock);
				proVO.setP_stat(p_stat);
				proVO.setP_sales(p_sales);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // �t����J�榡���~��proVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("update_Pro_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ProService proSvc = new ProService();
				proVO = proSvc.updatePro(p_id, pt_id, p_name, p_price, p_image, p_info, p_sales,p_stock, p_add_date, p_stat);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("proVO", proVO); // ��Ʈwupdate���\��,���T����proVO����,�s�Jreq
				String url = "listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePro.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("update_Pro_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addPro.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String p_name = req.getParameter("p_name");
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Double p_price = null;
				try {
					p_price = new Double(req.getParameter("p_price").trim());
				} catch (NumberFormatException e) {
					p_price = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				String p_info = req.getParameter("p_info").trim();
				if (p_info == null || p_info.trim().length() == 0) {
					errorMsgs.add("�y�z�ФŪť�");
				}

				Integer p_stock = null;
				try {
					p_stock = new Integer(req.getParameter("p_stock").trim());
				} catch (NumberFormatException e) {
					p_stock = 0;
					errorMsgs.add("�w�s�ж�Ʀr.");
				}

				Integer p_stat = null;
				try {
					 p_stat = new Integer(req.getParameter("p_stat").trim());
				} catch (NumberFormatException e) {
					p_stat = 0;
					errorMsgs.add("���A�ж�Ʀr.");
				}
				
				byte[] p_image = req.getParameter("p_image").trim().getBytes();
				String pt_id = req.getParameter("pt_id").trim();

				ProVO proVO = new ProVO();
				proVO.setPt_id(pt_id);
				proVO.setP_name(p_name);
				proVO.setP_price(p_price);
				proVO.setP_image(p_image);
				proVO.setP_info(p_info);
				proVO.setP_stock(p_stock);
				proVO.setP_stat(p_stat);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // �t����J�榡���~��proVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("addPro.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ProService proSvc = new ProService();
				proVO = proSvc.addPro(pt_id, p_name, p_price, p_image, p_info, p_stock, p_stat);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPro.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addPro.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String p_id = req.getParameter("p_id");

				/*************************** 2.�}�l�R����� ***************************************/
				ProService proSvc = new ProService();
				proSvc.deletePro(p_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
