<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.brand.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	BrandVO brandVO = (BrandVO) request.getAttribute("brandVO"); //BrandServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>品牌資料 - listOneBrand.jsp</title>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>品牌資料 - ListOneBrand.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/brand/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>品牌編號</th>
		<th>品牌名稱</th>
		<th>品牌logo</th>
		<th>品牌介紹</th>
	</tr>
	<tr>
		<%
				byte[] braLogo = brandVO.getBraLogo();
				Base64.Encoder encoder = Base64.getEncoder();
				String src = "data:image/jpeg;base64,";
				if (braLogo != null){
					src += encoder.encodeToString(braLogo); 
				}else{
					src="images/unupload.jpg";
				}
					
				pageContext.setAttribute("src", src);%>
		<td>${brandVO.braNo}</td>
		<td>${brandVO.braName}</td>
		<td><img src="${src}" alt='沒有圖片' /></td>
		<td>${brandVO.braIntro}</td>
	</tr>

</table>

</body>
</html>