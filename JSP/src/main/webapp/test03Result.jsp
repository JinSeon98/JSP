<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//post방식으로 한글이 넘어오게 될 경우 문자형식을 지정하여 처리하여야 한다.
	request.setCharacterEncoding("UTF-8");
	//key값은 input태그에 있는 name 값
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
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
					<th>Age</th>
					<td><%=age %></td>
				</tr>
			</table>
	</div>
</body>
</html>