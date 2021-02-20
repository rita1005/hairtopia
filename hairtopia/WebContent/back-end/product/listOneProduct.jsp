<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java(Concroller), 存入req的productVO物件
%>

<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>商品資料 - ListOneProduct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品類別編號</th>
		<th>品牌編號</th>
		<th>商品名稱</th>
		<th>上架狀態</th>
		<th>商品單價</th>
		<th>商品主圖</th>
		<th>商品副圖</th>
		<th>商品描述</th>
	</tr>
	<tr>
		
		<td>${productVO.proNo}</td>
		<jsp:useBean id="ptypeSvc" scope="page" class="com.ptype.model.PtypeService" />
		<td>${(ptypeSvc.onePtype.(productVO.ptypeNo)).ptypeName}</td>
		<td>${productVO.braNo}</td>
		<td>${productVO.proName}</td>
		<td>${productVO.proStatus}</td>
		<td>${productVO.proPrice}</td>
		<td>${productVO.proMpic}</td>
		<td>${productVO.proPic}</td>
		<td>${productVO.proDesc}</td>
		
		

	</tr>

</table>

</body>
</html>