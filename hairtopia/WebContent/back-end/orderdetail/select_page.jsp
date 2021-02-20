<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Hairtopia OrderDetail: Home</title>
</head>
<body>
<table>
	<tr><td><h3>Hairtopia OrderDetail: Home</h3></td></tr>
</table>
	
<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrderDetail.jsp'>List</a> all OrderDetails<h4>(byDAO)</h4></li>
  <br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderdetail/orderdetail.do" >
        <b>輸入品牌編號:</b>
        <input type="text" name="braNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller ).</h4> 
    </FORM>
  </li>


  <jsp:useBean id="dao" scope="page" class="com.orderdetail.model.OrderDetailService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderdetail/orderdetail.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ordNo">
         <c:forEach var="orddVO" items="${dao.all}" > 
          <option value="${orddVO.ordNo}">${orddVO.ordNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>  
  <li>
  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderdetail/orderdetail.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="proNo">
         <c:forEach var="orddVO" items="${dao.all}" > 
          <option value="${orddVO.proNo}">${orddVO.proNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

	<h3>品牌管理</h3>

	<ul>
		<li><a href='addOrderDetail.jsp'>Add</a> a new OrderDetail.</li>
	</ul>


	
</body>
</html>