package web.groom.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLConnection {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {

		// DBCP이용
		// webapp - META-INF- context.xml에서설정
		// context.xml 객체생성
		Context init = new InitialContext();
		// lookup 메서드 자원위치/자원이름 불러오기
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/c1d2304t2");
		// 데이터소스(커넥션)을 리턴
		Connection con = ds.getConnection();
		return con;
	}

} //end_of_SQLConnection
