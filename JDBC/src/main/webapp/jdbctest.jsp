<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("utf-8"); %>

<%
		//Connection 객체 생성
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##jsp01";
		String password = "jsp";
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "insert into jdbc_test values(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("username"));
			ps.setString(2, request.getParameter("email"));
			
			//username 값을 입력한 경우 sql문을 수행
			if(request.getParameter("username") != null) {
				ps.executeUpdate();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC 테스트</title>
</head>
<body>
	<div align="center">
		<H2>이벤트 등록</H2>
		<hr>
		<form name=form1 method=post>
			등록이름: <input type=text name=username>
			주소: <input typp=text name=email size=20>
			<input type=submit value="등록">
		</form>
		<hr>
	</div>
	#등록 목록<p>
<%
	try {
		String sql = "select username, email from jdbc_test";
		ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		int i = 1;
		
		while(rs.next()){
			out.println(i + ". "+rs.getString(1) + ", " + rs.getString("email") + "<br>");
			i++;
		}
		rs.close();
		ps.close();
		con.close();
	}catch(Exception e) {
		System.out.println(e);
	}
%>
</body>
</html>