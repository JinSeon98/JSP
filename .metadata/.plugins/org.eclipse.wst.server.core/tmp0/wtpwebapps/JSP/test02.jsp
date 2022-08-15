<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test02</title>
</head>
<body>
	<div align="center">
		<h2>구구단</h2>
		<table border=2>
		<tr>
			<% for(int i = 2;i <= 9; i++) {
				out.print("<th>" + i + "단</th>");
			} %>
		</tr>	
			<% for(int i = 1; i <= 9; i++) {%>
				<tr>
					<%for(int j = 2; j <= 9; j++ ){ %>
						<td><%=j %>x<%=i %>=<%=j*i %></td>
					<%} %>
				</tr>
				<%} %>
		</table>
	</div>
</body>
</html>