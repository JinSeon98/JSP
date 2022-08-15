<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private int age = 19;
	public void addAge() {
		age++;
	}
%>
<%
	String name = "김소희";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test01</title>
</head>
<body>
	<div align="center">
		이름 : <%=name %> <br>
		나이 : <%=age %><br>
	</div>
	<% if(age >= 20) { %>
		성인
	<% }else {%>
		미성년자
	<% }%>
	<%addAge(); %>
</body>
</html> 