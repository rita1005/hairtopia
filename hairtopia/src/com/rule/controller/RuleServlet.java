package com.rule.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.rule.model.RuleDAO;
import com.rule.model.RuleService;
import com.rule.model.RuleVO;

@WebServlet("/rule/rule.do")
@MultipartConfig
public class RuleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ruleNo");//str==null防呆用
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入前台條款編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ruleNo = null;
				try {
					ruleNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("前台條款編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RuleService ruleSvc = new RuleService();
				RuleVO ruleVO = ruleSvc.getOneRule(ruleNo);
				if (ruleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ruleVO", ruleVO); // 資料庫取出的ruleVO物件,存入req
				String url = "/back-end/rule/listOneRule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRule.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rule/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllRule.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer ruleNo = new Integer(req.getParameter("ruleNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				RuleService ruleSvc = new RuleService();
				RuleVO ruleVO = ruleSvc.getOneRule(ruleNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("ruleVO", ruleVO); // 資料庫取出的ruleVO物件,存入req
				String url = "/back-end/rule/update_rule_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rule_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/listAllRule.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_rule_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer ruleNo = new Integer(req.getParameter("ruleNo").trim());
				
				String ruleName = req.getParameter("ruleName").trim();
				if (ruleName == null || ruleName.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				String ruleCon = req.getParameter("ruleCon").trim();
				if (ruleCon == null || ruleCon.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				RuleService ruleSvc = new RuleService();
				RuleVO ruleVO = ruleSvc.getOneRule(ruleNo);
				ruleVO.setRuleNo(ruleNo);
				ruleVO.setRuleName(ruleName);
				ruleVO.setRuleCon(ruleCon);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ruleVO", ruleVO); // 含有輸入格式錯誤的ruleVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/update_rule_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				
				ruleVO = ruleSvc.updateRule(ruleNo, ruleName, ruleCon);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ruleVO", ruleVO); // 資料庫update成功後,正確的的ruleVO物件,存入req
				String url = "/back-end/rule/listOneRule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRule.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/update_rule_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addRule.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String ruleName = req.getParameter("ruleName");
				if (ruleName == null || ruleName.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				String ruleCon = req.getParameter("ruleCon");
				if (ruleCon == null || ruleCon.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}

				RuleVO ruleVO = new RuleVO();
				ruleVO.setRuleName(ruleName);
				ruleVO.setRuleCon(ruleCon);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("ruleVO", ruleVO); // 含有輸入格式錯誤的ruleVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/addRule.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RuleService ruleSvc = new RuleService();
				ruleVO = ruleSvc.addRule(ruleName, ruleCon);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rule/listAllRule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllRule.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/addRule.jsp");
				failureView.forward(req, res);

			}
		}
		
		if ("delete".equals(action)) { // 來自listAllRule.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer ruleNo = new Integer(req.getParameter("ruleNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				RuleService ruleSvc = new RuleService();
				ruleSvc.deleteRule(ruleNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/rule/listAllRule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rule/listAllRule.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
