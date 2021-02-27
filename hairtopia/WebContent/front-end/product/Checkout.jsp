<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*,com.product.model.*,com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
 <title>Mode II �d�ҵ{�� - Checkout.jsp</title>
 </head>

<body>
<img src="images/tomcat.gif"> <font size="+3">�����ѩ� - ���b�G�]Checkout.jsp�^</font>
<hr><p>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" /> 
<jsp:useBean id="ptypeSvc" scope="page" class="com.ptype.model.PtypeService" /> 
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" /> 
<table id="table-1" style="margin: auto;">
	<tr>
		<th>�ӫ~���O</th>
	    <th>�~�P�W��</th>
	    <th>�ӫ~�W��</th>
	    <th>����</th>
	    <th>�ӫ~�D��</th>
	    <th>�ӫ~�ƹ�</th>
	    <th>�ӫ~�y�z</th>
	    <th>�ƶq</th>
		<th width="120"><h3>�p�p</h3></th>
	</tr></table>
	
<form action="${pageContext.request.contextPath}/ordermaster/ordermaster.do" method="POST">		

<table style="margin: auto;">

 <%Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
   MemVO memVO = (MemVO)session.getAttribute("memVO");
   String ordAmt =  (String) request.getAttribute("ordAmt");%>

<c:forEach var="productVO" items="<%=buylist%>">
	<tr>
		<td width="200">${ptypeSvc.getOnePtype(productVO.ptypeNo).ptypeName}</td>
		<td width="100">${brandSvc.getOneBrand(productVO.braNo).braName}</td>
		<td width="100">${productVO.proName}</td>
		<td width="100">${productVO.proPrice}</td>
		<td><img src="${pageContext.request.contextPath}/PicFinder?pic=1&table=product&column=proMpic&idname=proNo&id=${productVO.proNo}" alt='�S���Ϥ�'></td>
		<td><img src="${pageContext.request.contextPath}/PicFinder?pic=1&table=product&column=proPic&idname=proNo&id=${productVO.proNo}" alt='�S���Ϥ�'></td>
		<td width="120">${productVO.proDesc}</td>
		<td width="100">${productVO.quantity}</td>
		<td width="100">${productVO.proPrice*productVO.quantity}</td>
        <td width="120"></td>
	</tr>
 </c:forEach> 
	
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">�`���B�G <h4>${ordAmt}</h4> </font>
	    </td>
	</tr>
</table>
<%session.setAttribute("buylist",buylist);%>

<input type="hidden" name="memNo" value="${sessionScope.memVO.memNo}">	                                
<input type="hidden" name="ordAmt" value="<%=ordAmt %>">	                
<input type="hidden" name="action" value="PAY">
<input type="submit" value="�e�X">
</form>       
       
       <p><a href="${pageContext.request.contextPath}/front-end/product/EShop.jsp"><font size="+1"> �O �_ �~ �� �� ��</font></a>

</body>
</html>