<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
 	//alert(document.getElementById("name"));
 	function check() {
 		if(document.f.name.value =="") {
 			alert("Name을 입력하십시오!");
 			doucument.f.name.focus();
 		}else if(document.f.java.value =="") {
 			alert("java를 입력하십시오!");
 			document.f.java.focus();
 		}else if(document.f.jsp.value =="") {
 			alert("jsp를 입력하십시오!");
 			document.f.jsp.focus();
 		}else if(document.f.spring.value =="") {
 			alert("spring를 입력하십시오!");
 			document.f.spring.focus();
 		}else {
 			document.f.submit();
 		}
 	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="/JSP/studentResult.jsp" method="post" name="f">
			<table border="1">
				<tr>
					<th>Name</th>
					<td><input id="name" type="text" name="name"></td>
				</tr>			
				<tr>
					<th>java</th>
					<td><input type="text" name="java"></td>
				</tr>
				<tr>
					<th>jsp</th>
					<td><input type="text" name="jsp"></td>
				</tr>
				<tr>
					<th>spring</th>
					<td><input type="text" name="spring"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" value="전송" onclick="javascript:check()">
						<input type="button" value="다시" onclick="document.f.reset()">
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
