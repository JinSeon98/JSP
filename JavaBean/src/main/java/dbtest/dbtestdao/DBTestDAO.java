package dbtest.dbtestdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbtest.dbtestdto.DBTestDTO;


public class DBTestDAO {
	
	public DBTestDAO() {
		//����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("�ε� ����!");
		}catch(ClassNotFoundException e){
			System.err.println("�ε� ����!");
			e.printStackTrace();
		}
	}
	private Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##jsp01";
		String password = "jsp";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("���� ����!");
		}catch(SQLException e) {
			System.err.println("���� ����!");
			e.printStackTrace();
		}
		
		return con;
	}
	//�Է�
	public int insert(DBTestDTO dto) {
		int su = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "insert into dbtest values(?,?,?, sysdate)";
		
		try {
			con = this.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getAge());
			ps.setDouble(3, dto.getHeight());
			
			su = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
	
	//����
	public int delete(String name) {
		int su = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "delete dbtest where name=?";
		
		try {
			con = this.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			su = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
	
	//�˻�-(1)
/*	public void select() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from dbtest";
		
		try {
			con = this.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double height = rs.getDouble("height");
				Date logtime = rs.getDate("logtime");
				//String logtime = rs.getString("logtime"); ��-��-�� ��:��:��
				
				System.out.println(name + "\t" + age + "\t" + height + "\t" + logtime);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	//�˻�-(2)
	public List<DBTestDTO> select() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<DBTestDTO> list = new ArrayList<DBTestDTO>();
		String sql = "select * from dbtest";
		
		try {
			con = this.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double height = rs.getDouble("height");
				Date logtime = rs.getDate("logtime");
				
				list.add(new DBTestDTO(name, age, height, logtime));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(list.isEmpty()) {
			list = null;
		}else {
			list.trimToSize();//�뷮�� ArrayList�� ���� ��� ���� ����
		}
		return list;
	}
}
