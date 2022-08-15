//import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) {
		//����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("�ε� ����!");
		}catch(ClassNotFoundException e){
			System.err.println("�ε� ����!");
			e.printStackTrace();
		}
		
		//Connection ��ü ����
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##jsp01";
		String password = "jsp";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("���� ����!");
		}catch(SQLException e) {
			System.err.println("���� ����!");
		}
		
		//���� �Է�
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�̸� �Է�: ");
		String name = sc.next();
		System.out.print("���� �Է�: ");
		int age = sc.nextInt();
		System.out.print("Ű �Է�: ");
		double height = sc.nextDouble();
		
		sc.close();
/*		
		//Statement ����
		Statement st = null;
		
		String sql = "insert into dbtest values('" + name +"'," + age + ", " + height + ", sysdate)";
		
		try {
			st = con.createStatement();
			int su = st.executeUpdate(sql);
			if(su != 0) {
				System.out.println("�Է� ����!");
			} else {
				System.out.println("�Է� ����!");
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
			//PreparedStatement ����
			PreparedStatement ps = null;
			
			String sql = "insert into dbtest values(?,?,?,sysdate)";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, age);
				ps.setDouble(3, height);
				
				int su = ps.executeUpdate();
				if(su != 0) {
					System.out.println("�Է� ����!");
				}else {
					System.out.println("�Է� ����!");
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
