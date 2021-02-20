<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rule.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	RuleVO ruleVO = (RuleVO) request.getAttribute("ruleVO"); //RuleServlet.java(Concroller), 存入req的ruleVO物件
%>

<html>
<head>
<title>前台條款資料 - listOneRule.jsp</title>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>前台條款資料 - ListOneRule.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/rule/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>前台條款編號</th>
		<th>前台條款名稱</th>
	</tr>
	<tr>

		<td>${ruleVO.ruleNo}</td>
		<td>${ruleVO.ruleName}</td>
		<td>${ruleVO.ruleCon}</td>

	</tr>

</table>

</body>
</html>