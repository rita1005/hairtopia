<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rule.model.*"%>

<%
	RuleVO ruleVO = (RuleVO) request.getAttribute("ruleVO");
%>


<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>前台條款資料新增 - addRule.jsp</title>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>前台條款資料新增 - addRule.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/rule/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>資料新增:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	

	<form METHOD="POST" ACTION="<%=request.getContextPath()%>/rule/rule.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>前台條款名稱:</td>
				<td><input type="TEXT" name="ruleName" size="45"
					value="<%= (ruleVO == null) ? "" : ruleVO.getRuleName()%>" />
				</td>					
			</tr>
			<tr>
				<td>前台條款內容:</td>
				<td>
				<textarea name="ruleCon"><%= (ruleVO == null) ? "" : ruleVO.getRuleCon()%></textarea>
				</td>				
			</tr>
		</table>
	
	
		<br> 
		<input name="action" value="insert" type="hidden" >
		<input type="submit" value="新增">
		
	</form>

</body>
</html>