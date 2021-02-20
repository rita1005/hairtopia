<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	OrderMasterVO ordmVO = (OrderMasterVO) request.getAttribute("ordmVO"); //OrderMasterServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂單主檔資料 - listOneOrderMaster.jsp</title>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂單主檔資料 - ListOneOrderMaster.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/ordermaster/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單狀態</th>
		<th>總金額</th>
	</tr>
	<tr>
		<td>${ordmVO.ordNo}</td>
		<td>${ordmVO.memNo}</td>
		<td>${ordmVO.ordStatus}</td>
		<td>${ordmVO.ordAmt}</td>
	</tr>

</table>

</body>
</html>