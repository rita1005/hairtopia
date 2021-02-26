<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>


<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listOrderMasters_ByCompositeQuery" scope="request" type="java.util.List<OrderMasterVO>" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemService" />


<html>
<head><title>�ƦX�d�� - listOrderMasters_ByCompositeQuery.jsp</title>



</head>
<body bgcolor='white'>

<h4>
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��ӫ~��� - listAllOrderMaster.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/ordermaster/select_page.jsp">>�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�q��s��</th>
		<th>�|��</th>
		<th>�q�ʤ��</th>
		<th>�q�檬�A</th>
		<th>�q����B</th>
	</tr>
	<c:forEach var="ordermasterVO" items="${listOrderMasters_ByCompositeQuery}">
		<tr>
			<td>${ordermasterVO.ordNo}</td>
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${ordermasterVO.memNo==memVO.memNo}">
	                    ${memVO.memNo}�i${memVO.memName}�j
                    </c:if>
                </c:forEach>
            </td>
			<td>${ordermasterVO.ordDate}</td>
			<td>
				<c:if test="${ordermasterVO.ordStatus==0}">���X�f</c:if>
				<c:if test="${ordermasterVO.ordStatus==1}">�w�X�f</c:if>
				<c:if test="${ordermasterVO.ordStatus==2}">�w����</c:if>
				<c:if test="${ordermasterVO.ordStatus==3}">�q�����</c:if>
				<c:if test="${ordermasterVO.ordStatus==9}">�h�f</c:if>
			</td>				
			<td>${ordermasterVO.ordAmt}</td>	
			
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ordermaster/ordermaster.do" style="margin-bottom: 0px;">
					<input type="submit" value="�ק�"> 
					<input type="hidden" name="ordNo" value="${ordermasterVO.ordNo}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ordermaster/ordermaster.do" style="margin-bottom: 0px;">
					<input type="submit" value="�R��"> 
					<input type="hidden" name="ordNo" value="${ordermasterVO.ordNo}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>