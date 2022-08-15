<%@page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	int java = Integer.parseInt(request.getParameter("java"));
	int jsp = Integer.parseInt(request.getParameter("jsp"));
	int spring = Integer.parseInt(request.getParameter("spring"));
	
	int sum = java+jsp+spring;
	double avg = (double)sum/3;
	//소수점 셋팅
	DecimalFormat df = new DecimalFormat("00.00");
	double sa = Double.parseDouble(df.format(avg));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h3><%=request.getMethod() %></h3>
		<table border="1">
			<tr>
				<th>Name</th>
				<td><%=name %></td>
			</tr>
			<tr>
				<th>java</th>
				<td><%=java %></td>
			</tr>
			<tr>
				<th>jsp</th>
				<td><%=jsp %></td>
			</tr>
			<tr>
				<th>spring</th>
				<td><%=spring %></td>
			</tr>
			<tr>
				<th>총점</th>
				<td><%=sum %></td>
			</tr>
			<tr>
				<th>평균</th>
				<td><%=sa %></td>
			</tr>
		</table>
		<% if(sa >= 60) { %>
				합격
			<% }else { %>	
				실패
			<% }%>
	</div>
</body>
</html>