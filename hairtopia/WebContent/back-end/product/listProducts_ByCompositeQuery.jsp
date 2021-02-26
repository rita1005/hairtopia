<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>


<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listProducts_ByCompositeQuery" scope="request" type="java.util.List<ProductVO>" />
<jsp:useBean id="ptypeSvc" scope="page" class="com.ptype.model.PtypeService" />
<jsp:useBean id="brandSvc" scope="page" class="com.brand.model.BrandService" />


<html>
<head><title>�ƦX�d�� - listProducts_ByCompositeQuery.jsp</title>



</head>
<body bgcolor='white'>

<h4>
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��ӫ~��� - listAllProduct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp">>�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~���O�s��</th>
		<th>�~�P�s��</th>
		<th>�ӫ~�W��</th>
		<th>�W�[���A</th>
		<th>�ӫ~���</th>
		<th>�ӫ~�D��</th>
		<th>�ӫ~�ƹ�</th>
		<th>�ӫ~�y�z</th>
	</tr>
	<c:forEach var="productVO" items="${listProducts_ByCompositeQuery}">
		<tr>
			<td>${productVO.proNo}</td>
			<td>
				<c:forEach var="ptypeVO" items="${ptypeSvc.getAll() }">
                    <c:if test="${productVO.ptypeNo==ptypeVO.ptypeNo}">
	                    ${ptypeVO.ptypeNo}�i${ptypeVO.ptypeName}�j
                    </c:if>
                </c:forEach>
            </td>
			<td>
				<c:forEach var="brandVO" items="${brandSvc.all}">
                    <c:if test="${productVO.braNo==brandVO.braNo}">
	                    ${brandVO.braNo}�i${brandVO.braName}�j
                    </c:if>
                </c:forEach>
			</td>
			<td>${productVO.proName}</td>			
			<td>${productVO.proStatus==true?"�W�[":"�U�["}</td>
			<td>${productVO.proPrice}</td>
			<td><img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=product&column=proMpic&idname=proNo&id=${productVO.proNo}" alt='�S���Ϥ�' /></td>
			<td><img src="<%=request.getContextPath()%>/PicFinder?pic=1&table=product&column=proPic&idname=proNo&id=${productVO.proNo}" alt='�S���Ϥ�' /></td>
			<td>${productVO.proDesc}</td>
			
			

			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/product/product.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="�ק�"> <input type="hidden"
						name="proNo" value="${productVO.proNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/product/product.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="�R��"> <input type="hidden"
						name="proNo" value="${productVO.proNo}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>