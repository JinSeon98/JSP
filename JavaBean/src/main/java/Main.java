import java.util.List;
import java.util.Scanner;

import dbtest.dbtestdao.DBTestDAO;
import dbtest.dbtestdto.DBTestDTO;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		System.out.print("������ ������ �����ϼ���(1.�Է�/2.����/3.�˻�): ");
		int choice = sc.nextInt();
		
		DBTestDAO dao = new DBTestDAO();
		int su = 0;
		switch(choice) {
			case 1 : 
				System.out.print("�̸� �Է�: ");
				String name = sc.next();
				System.out.print("���� �Է�: ");
				int age = sc.nextInt();
				System.out.print("Ű �Է�: ");
				double height = sc.nextDouble();
				
				DBTestDTO dto = new DBTestDTO(name, age, height);
				
				su = dao.insert(dto);
				if(su != 0) {
					System.out.println("�Է� ����!");
				}else {
					System.out.println("�Է� ����!");
				}
				break;
			case 2: 
				System.out.print("�̸� �Է�: ");
				String del_name = sc.next();
				
				su = dao.delete(del_name);
				if(su != 0) {
					System.out.println("���� ����!");
				}else {
					System.out.println("���� ����!");
				}
				break;
			case 3:
				List<DBTestDTO> list = dao.select();
				for(DBTestDTO dto2 : list) {
					System.out.println(dto2.getName() + "\t" + dto2.getAge() + "\t" + dto2.getHeight() + "\t" + dto2.getLogtime());
				}
				break;
				
		}
		sc.close();
		//3.�˻�(select)-(1)
/*		DBTestDAO dao = new DBTestDAO();
		dao.select();
*/
		//3.�˻�(select)-(2)
/*		for(int i = 0; i < list.size(); i++) {
		DBTestDTO dto = list.get(i);
		System.out.println(dto.getName() + "\t" + dto.getAge() + "\t" + dto.getHeight() + "\t" + dto.getLogtime());
		}
*/
	}

}
