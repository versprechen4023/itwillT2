<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
String url = "jdbc:mysql://192.168.1.4:3306/ur";
String user = "guest";
String dbPassword = "Mysql123!";


try{
	String getid = request.getParameter("user_id");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = DriverManager.getConnection(url, user, dbPassword);
	// SQL 쿼리 실행
	String query = "SELECT * FROM users WHERE id = ?"; // id부터 검색
	PreparedStatement statement = connection.prepareStatement(query);
	statement.setString(1, getid);
	ResultSet resultSet = statement.executeQuery();//실행구문
	
	if (resultSet.next()) {
		%>check<%
	} else { 
		%>ncehck<%
	}
} catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException ex) {
	// sql에러시 출력
	ex.printStackTrace();
}
%>