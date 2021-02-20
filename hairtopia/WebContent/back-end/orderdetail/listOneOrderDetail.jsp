<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	OrderDetailVO orderdetailVO = (OrderDetailVO) request.getAttribute("orderdetailVO"); //OrderDetailServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂單明細資料 - listOneOrderDetail.jsp</title>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂單明細資料 - ListOneOrderDetail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/orderdetail/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>訂購數量</th>
		<th>明細金額</th>
	</tr>
	<tr>

		<td>${orderdetailVO.ordNo}</td>
		<td>${orderdetailVO.proNo}</td>
		<td>${orderdetailVO.proDetAmt}</td>
		<td>${orderdetailVO.proDetPrice}</td>
	</tr>

</table>

</body>
</html>