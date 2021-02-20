<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Hairtopia Rule: Home</title>
</head>
<body>
<table>
	<tr><td><h3>Hairtopia Rule: Home</h3></td></tr>
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
  <li><a href='listAllRule.jsp'>List</a> all Rules<h4>(byDAO)</h4></li>
  <br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rule/rule.do" >
        <b>輸入前台條款編號:</b>
        <input type="text" name="ruleNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller ).</h4> 
    </FORM>
  </li>


  <jsp:useBean id="dao" scope="page" class="com.rule.model.RuleService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rule/rule.do" >
       <b>選擇前台條款編號:</b>
       <select size="1" name="ruleNo">
         <c:forEach var="ruleVO" items="${dao.all}" > 
          <option value="${ruleVO.ruleNo}">${ruleVO.ruleNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rule/rule.do" >
       <b>選擇前台條款名稱:</b>
       <select size="1" name="ruleNo">
         <c:forEach var="ruleVO" items="${dao.all}" > 
          <option value="${ruleVO.ruleNo}">${ruleVO.ruleName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

	<h3>前台條款管理</h3>

	<ul>
		<li><a href='addRule.jsp'>Add</a> a new Rule.</li>
	</ul>


	
</body>
</html>