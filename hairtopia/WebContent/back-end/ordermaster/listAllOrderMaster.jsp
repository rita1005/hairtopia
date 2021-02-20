<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	OrderMasterService ordmSvc = new OrderMasterService();
	List<OrderMasterVO> list = ordmSvc.getAll();
	pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html>
<head>
<style type="text/css">
img {
	width: 150px;
}
</style>
<meta charset="UTF-8">
<title>所有品牌資料 - listAllOrderMaster.jsp</title>
</head>
<body>
	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有品牌資料 - listAllOrderMaster.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/ordermaster/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單狀態</th>
			<th>總金額</th>

		</tr>
		<%
			for (OrderMasterVO ordmVO : list) {
				
		%>
		<tr>
			<td><%=ordmVO.getOrdNo()%></td>
			<td><%=ordmVO.getMemNo()%></td>
			<td><%=ordmVO.getOrdStatus()%></td>
			<td><%=ordmVO.getOrdAmt()%></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/ordermaster/ordermaster.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="ordNo" value="<%=ordmVO.getOrdNo()%>"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/ordermaster/ordermaster.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="ordNo" value="<%=ordmVO.getOrdNo()%>"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
		<%
			}
		%>


	</table>


</body>
</html>