<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>

<%
	OrderMasterVO ordmVO = (OrderMasterVO) request.getAttribute("ordmVO");
%>


<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單主檔資料新增 - addOrderMaster.jsp</title>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>訂單主檔資料新增 - addOrderMaster.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/ordermaster/select_page.jsp">回首頁</a>
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

	

	<form METHOD="POST" ACTION="<%=request.getContextPath()%>/ordermaster/ordermaster.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memNi" size="45"
					value="<%= (ordmVO == null) ? "" : ordmVO.getMemNo()%>" /></td>
					
			</tr>

			<tr>
				<td>訂單狀態</td>
				<td><input type="TEXT" name="ordStatus" size="45"
					value="<%= (ordmVO == null) ? "" : ordmVO.getOrdStatus()%>" /></td>
			</tr>
			
			<tr>
				<td>總金額</td>
				<td><input type="TEXT" name="ordAmt" size="45"
					value="<%= (ordmVO == null) ? "" : ordmVO.getOrdAmt()%>" /></td>
			</tr>


		</table>
	
	
		<br> 
		<input name="action" value="insert" type="hidden" >
		<input type="submit" value="新增">
		
	</form>

</body>
</html>