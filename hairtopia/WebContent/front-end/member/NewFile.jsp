<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<%
	MemDAO dao = new MemDAO();
	MemVO memVO = dao.findByPrimaryKey(1);
	session.setAttribute("memVO", memVO);
%>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <title>HTML5-表單</title>
</head>

<body>
    
<!--  -->
    <form action="" align="center">
        <fieldset>
            <legend>Contact US</legend>
            <p><label for="p1">Name:</label><input type="text" id="p1" placeholder="請輸入正確姓名" required autocomplete="off"></p>
            <p><label for="p2">Email:</label><input type="email" id="p2" required></p>
            <p><label for="p3">Phone:</label><input type="tel" id="p3" maxlength="10"></p>
            <p><label for="p4">Web:</label><input type="url" id="p4"></p>
            <p><label for="p5">Birthday:</label><input type="date" id="p5"></p>
            <p><label for="p6">Order:</label><input type="number" id="p6" value="1" max="10" min="1" step="1"></p>
            <p><label for="p7">Product:</label><input type="text" id="p7" list="ss"></p>
            <p><label for="p8">Satesfied: <br>OK | GOOD | PERFECT <br></label><input type="range" id="p8" max="3" min="1" step="1"></p>
            <p><input type="submit" value="寫好送出"> <input type="reset" value="清除重填"></p>
            <select name="" id="">
                <option value="">1</option>
                <option value="">2</option>
                <option value="">3</option>
                <option value="">4</option>
                <option value="">5</option>
            </select>
        </fieldset>
    </form>
</body>

</html>