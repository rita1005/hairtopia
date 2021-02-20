<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rule.model.*"%>

<%
	RuleVO ruleVO = (RuleVO) request.getAttribute("ruleVO");
%>


<html>
<head>
<meta charset="UTF-8">
<title>前台條款資料修改 - update_rule_input.jsp</title>
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>前台條款資料修改 - update_rule_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/rule/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>


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
				<td>前台條款編號:<font color=red><b>*</b></font></td>
				<td><%=ruleVO.getRuleNo()%></td>
			</tr>
			<tr>
				<td>前台條款名稱:</td>
				<td><input type="TEXT" name="ruleName" size="45"
					value="<%=ruleVO.getRuleName()%>" /></td>
			</tr>
			<tr>
				<td>前台條款內容:</td>
				<td>
				<textarea name="ruleCon"><%=ruleVO.getRuleCon()%></textarea>
				</td>
			</tr>


		</table>
	
	
	<br> <input type="hidden" name="action" value="update"> 
		 <input type="hidden" name="ruleNo" value="<%=ruleVO.getRuleNo()%>">
		 <input type="submit" value="送出修改">
		
	</form>


</body>
</html>