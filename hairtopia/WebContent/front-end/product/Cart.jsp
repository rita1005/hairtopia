<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.orderdetail.model.*" %>
<html>
<head>
 <title>Cart.jsp</title>
</head>
<body><br>
   <% @SuppressWarnings("unchecked")
   Vector<OrderDetailVO> buylist = (Vector<OrderDetailVO>) session.getAttribute("shoppingcart");%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" /> 
<jsp:useBean id="ptypeSvc" scope="page" class="com.ptype.model.PtypeService" /> 
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" /> 
<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="images/tomcat.gif"> <font size="+3">�ثe�ʪ��������e�p�U�G�]Cart.jsp�^</font>

<table id="table-1">
    <tr> 
	    <th>�ӫ~���O</th>
	    <th>�~�P�W��</th>
	    <th>�ӫ~�W��</th>
	    <th>����</th>
	    <th>�ӫ~�D��</th>
	    <th>�ӫ~�ƹ�</th>
	    <th>�ӫ~�y�z</th>
	    <th>�ƶq</th>
	    <th><img src="images/shopping-cart.png" width="45px" height="35px"></th>
    </tr>
</table>
   
<table>
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 OrderDetailVO order = buylist.get(index);
	%>
	<tr>
		<td width="200"><%=ptypeSvc.getOnePtype(productSvc.getOneProduct(order.getProNo()).getPtypeNo()).getPtypeName()%>     </td>
		<td width="100"><%=brandSvc.getOneBrand(productSvc.getOneProduct(order.getProNo()).getBraNo()).getBraName()%>   </td>
		<td width="100"><%=productSvc.getOneProduct(order.getProNo()).getProName()%></td>
		<td width="100"><%=order.getOrdDetPrice()%>    </td>
		<td><img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=product&column=proMpic&idname=proNo&id=${ProductVO.proNo}" alt='�S���Ϥ�'></td>
		<td><img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=product&column=proPic&idname=proNo&id=${ProductVO.proNo}" alt='�S���Ϥ�'></td>
		<td width="120"><%=productSvc.getOneProduct(order.getProNo()).getProDesc()%> </td>
		<td width="100"><%=order.getOrdDetAmt()%>    </td>
        <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/orderdetail/orderdetail.do" method="POST">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="�R ��" class="button">
          </form></td>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/orderdetail/orderdetail.do" method="POST">
              <input type="hidden" name="action"  value="CHECKOUT"> 
              <input type="submit" value="�I�ڵ��b" class="button">
          </form>
<%}%>
</body>
</html>