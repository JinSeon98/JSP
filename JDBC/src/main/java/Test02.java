import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) {
		//이름을 통해서 나이와 키 수정
		
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 성공!");
		}catch(ClassNotFoundException e){
			System.err.println("로딩 실패!");
			e.printStackTrace();
		}
		
		//Connection 객체 생성
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##jsp01";
		String password = "jsp";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공!");
		}catch(SQLException e) {
			System.err.println("연결 실패!");
		}
		
		//정보 입력
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정할 이름 입력: ");
		String name = sc.next();
		System.out.print("수정할 나이 입력: ");
		int age = sc.nextInt();
		System.out.print("수정할 키 입력: ");
		double height = sc.nextDouble();
		
		sc.close();
/*		
		//Statement 생성
		Statement st = null;
		
		String sql = "update dbtest set age=" + age + ", height=" + height + " where name='"+ name + "'";
		//String sql = String.format("update dbtest set age = %d, height = %f where name = '%s'", age, height, name);
		try {
			st = con.createStatement();
			int su = st.executeUpdate(sql);
			if(su != 0) {
				System.out.println("입력 성공!");
			} else {
				System.out.println("입력 실패!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(st != null) {
					st.close();
				}if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
*/

		//PreparedStatement 생성
			PreparedStatement ps = null;
			
			String sql = "update dbtest set age=?, height=? where name=?";
			
			try {
				ps = con.prepareStatement(sql);			
				ps.setInt(1, age);
				ps.setDouble(2, height);
				ps.setString(3, name);
				
				int su = ps.executeUpdate();
				if(su != 0) {
					System.out.println("입력 성공!");
				}else {
					System.out.println("입력 실패!");
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
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
	}

}
