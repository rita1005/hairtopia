<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderdetail.model.*"%>


<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listOrderDetails_ByCompositeQuery" scope="request" type="java.util.List<OrderDetailVO>" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />


<html>
<head><title>�ƦX�d�� - listOrderDetails_ByCompositeQuery.jsp</title>



</head>
<body bgcolor='white'>

<h4>
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��ӫ~��� - listAllOrderDetail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/orderdetail/select_page.jsp">>�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�q��s��</th>
		<th>�ӫ~�W��</th>
		<th>�q�ʼƶq</th>
		<th>���Ӫ��B</th>
	</tr>
	<c:forEach var="orderdetailVO" items="${listOrderDetails_ByCompositeQuery}">
		<tr>
			<td>${orderdetailVO.ordNo}</td>
			<td>
				<c:forEach var="productVO" items="${productSvc.all}">
                    <c:if test="${orderdetailVO.proNo==productVO.proNo}">
	                    ${productVO.proNo}�i${productVO.proName}�j
                    </c:if>
                </c:forEach>
            </td>
			<td>${orderdetailVO.ordDetAmt}</td>
			<td>${orderdetailVO.ordDetPrice}</td>				

			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderdetail/orderdetail.do" style="margin-bottom: 0px;">
					<input type="submit" value="�ק�"> 
					<input type="hidden" name="ordNo" value="${orderdetailVO.ordNo}">
					<input type="hidden" name="proNo" value="${orderdetailVO.proNo}"> 
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orderdetail/orderdetail.do" style="margin-bottom: 0px;">
					<input type="submit" value="�R��"> 
					<input type="hidden" name="ordNo" value="${orderdetailVO.ordNo}">
					<input type="hidden" name="proNo" value="${orderdetailVO.proNo}">  
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>