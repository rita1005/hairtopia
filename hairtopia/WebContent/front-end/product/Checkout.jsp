<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*,com.orderdetail.model.*,com.member.model.*"%>
<html>
<head>
 <title>Mode II �d�ҵ{�� - Checkout.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
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
		<th width="120"><h3>�`��</h3></th>
	</tr></table>
	
<form action="<%=request.getContextPath()%>/ordermaster/ordermaster.do" method="POST">		

<table style="margin: auto;">
	<%  @SuppressWarnings("unchecked")
		Vector<OrderDetailVO> buylist = (Vector<OrderDetailVO>) session.getAttribute("shoppingcart");
	 	MemVO memVO = (MemVO)session.getAttribute("memVO");
		String ordAmt =  (String) request.getAttribute("ordAmt");
	%>
	<%	for (int i = 0; i < buylist.size(); i++) {
			OrderDetailVO order = buylist.get(i);
			Integer proNo = order.getProNo();
			Integer ordDetAmt = order.getOrdDetAmt();
			Integer ordDetPrice = order.getOrdDetPrice();
			
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
        <td width="120"></td>
	</tr>
	<%
		}
	%>
	 
	
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">�`���B�G <h4>$<%=ordAmt%></h4> </font>
	    </td>
	</tr>
</table>
<%session.setAttribute("buylist",buylist);%>

<input type="hidden" name="memNo" value="${memVO.memNo}">
<input type="hidden" name="ordAmt" value="${ordAmt}">	                
<input type="hidden" name="action" value="PAY">
<input type="submit" value="�e�X">
</form>       
       
       <p><a href="<%=request.getContextPath()%>/front-end/product/EShop.jsp"><font size="+1"> �O �_ �~ �� �� ��</font></a>

</body>
</html>