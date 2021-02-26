package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;
import com.util.mail.*;

public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		// Login
		if ("login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 【取得使用者 帳號(account) 密碼(password)】
			String account = req.getParameter("account");
			String password = req.getParameter("password");

			/*************************** 2.開始查詢資料 *****************************************/
			// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
			// 【實際上應至資料庫搜尋比對】
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.validate(account, password);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			if (memVO == null) { // 【帳號 , 密碼無效時】
//		        out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//		        out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//		        out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/mem/login.jsp>重新登入</A>");
//		        out.println("</BODY></HTML>");
				errorMsgs.add("error account or password");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("account", account);
				session.setAttribute("memVO", memVO);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/front-end/member/login_success.jsp");
			}
		}
		// forget password
		if ("forgetPassword".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String account = req.getParameter("account");
			/*************************** 2.開始查詢資料 *****************************************/
			MemService memSvc = new MemService();
			MailService ms = new MailService();
			String memName = memSvc.validateEmail(account);
			if (memName != null) {
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				errorMsgs.add("信箱存在");

				String subject = "密碼通知";
				String password = genAuthCode();
				String messageText = "Thanks for signing up!\r\n"
						+ "Your pasword has been reset, you can login with the following credentials.\r\n"
						+ "  \r\n" + "------------------------\r\n" 
						+ "Username: " + memName  + "\r\n"
						+ "Password: " + password + "\r\n" + "------------------------\r\n" + "  \r\n";
//						+ "Please click this link to activate your account:\r\n"
//						+ "http://www.yourwebsite.com/verify.php?email='.$email.'&hash='.$hash.";
//				ms.sendMail(account, subject, messageText);
				
				/* test用 請改成自己的信箱*/
				ms.sendMail("b35698741@gmail.com", subject, messageText);
				
				/*change password by random generate password*/
				memSvc.updatePassword(account, password);
				/*Send the Success view*/
				RequestDispatcher SuccessView = req.getRequestDispatcher("/front-end/member/email_send_success.jsp");
				SuccessView.forward(req, res);
				return;
			} else {
				errorMsgs.add("信箱不存在");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/forgetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
//			memSvc.get
		}
	}

	public String genAuthCode() {
		char[] authCode = new char[8];
		byte[] randNum = new byte[8];
		// random 8 numbers to choice character
		for (int i = 0; i < randNum.length; i++) {
			randNum[i] = (byte) (Math.random() * 62);
			// System.out.print(randNum[i] + " ");
		}
//		System.out.println();
		// set a inner list 0-9 ->(char)0-9, 10-35 ->(char)A-Z, 36-61 -> (char)a-z
		// purpose：Save memory
		for (int i = 0; i < 8; i++) {
			if (randNum[i] >= 0 && randNum[i] <= 9) {
				authCode[i] = (char) (randNum[i] + 48);
			} else if (randNum[i] >= 10 && randNum[i] <= 35) {
				authCode[i] = (char) (randNum[i] + 55);
			} else if (randNum[i] >= 36 && randNum[i] <= 61) {
				authCode[i] = (char) (randNum[i] + 61);
			}
		}

		return new String(authCode);
	}

}