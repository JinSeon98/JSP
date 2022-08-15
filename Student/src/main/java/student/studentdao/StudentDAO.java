package student.studentdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import student.studentdto.StudentDTO;

public class StudentDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public StudentDAO() {
		// 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	private void getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##jsp01";
		String password = "jsp";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 1.입력
	public boolean insert(StudentDTO dto) {
		boolean check = false;
		
		String sql = "insert into student values(?,?,?)";
		
		try {
			this.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getValue());
			ps.setInt(3, dto.getCode());
			if(ps.executeUpdate() != 0) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	// 2.수정
	public int update(StudentDTO dto) {
		int su = 0;
		
		String sql = "update student set value=?, code=? where name=?";
		
		try {
			this.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getValue());
			ps.setInt(2, dto.getCode());
			ps.setString(3, dto.getName());
			
			su = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
	
	// 3.삭제
	public int delete(String name) {
		int su = 0;
		String sql = "delete student where name=?";
		
		try {
			this.getConnection();
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
	
	// 4-(1).이름을 통해서 검색
		public StudentDTO selectOne(String name) {
			
			String sql = "select * from student where name=?";
			StudentDTO dto = null;
			
			try {
				this.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				
				rs = ps.executeQuery();
				if(rs.next()) {
					dto = new StudentDTO();
					dto.setName(rs.getString("name"));
					dto.setValue(rs.getString("value"));
					dto.setCode(rs.getInt("code"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				try {
					if(ps != null) {
						ps.close();
					}
					if(con != null) {
						con.close();
					}
					if(rs != null) {
						rs.close();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return dto;
		}
		//4-(2). 검색
		public List<StudentDTO> select(StudentDTO dto) {
			List<StudentDTO> list = new ArrayList<StudentDTO>();
			
			String sql = null;
			
			if(dto == null) {
				sql = "select * from student";
			}else if(dto.getName() != null) {
				sql = "select * from student where name like ?";
			}else {
				sql = "select * from student where code=?";
			}try {
				this.getConnection();
				ps = con.prepareStatement(sql);
				//dto가 null이 아닌 경우만 구분.
				if(dto != null) {
					if(dto.getName() != null) {
						ps.setString(1, "%" + dto.getName() + "%");
					}else {
						ps.setInt(1, dto.getCode());
					}
				}
				rs = ps.executeQuery();
				while(rs.next()) {
					String name = rs.getString("name");
					String value = rs.getString("value");
					int code = rs.getInt("code");
					
					StudentDTO dt = new StudentDTO(name, value, code);
					list.add(dt);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					if(con != null) con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(list.isEmpty()) {
				list = null;
			}
			return list;
		}
}
