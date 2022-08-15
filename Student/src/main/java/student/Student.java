package student;

import java.util.List;
import java.util.Scanner;

import student.studentdao.StudentDAO;
import student.studentdto.StudentDTO;

public class Student {
	private final Scanner sc = new Scanner(System.in);
	
	public Student() {
		menu();
	}
	
	private void menu() {
		//���ѷ����� ��� ���� �Ʒ��� �ڵ忡 �������� ���ؼ� Unreachable code���� �߻�
		while(true) {
			System.out.print("****************\r\n"
					+ "   ����\r\n"
					+ "****************\r\n"
					+ "  1. �Է�\r\n"
					+ "  2. ����\r\n"
					+ "  3. ����\r\n"
					+ "  4. �˻�\r\n"
					+ "  5. ����\r\n"
					+ "****************\r\n"
					+ "  ��ȣ���� : ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				insertArticle();
				break;
			case 2:
				updateArticle();
				break;
			case 3:
				deleteArticle();
				break;
			case 4:
				selectArticle();
				break;
			case 5:
				exit();
			default:
				System.out.println("�߸��� �޴� �Է�!");
			}
		}
	}
	private void exit() {
		System.out.println("���α׷��� ����˴ϴ�!");
		sc.close();
		System.exit(0); //���α׷� ��������� ���
		//������ ���� - System.exit(1);
	}
	
	//1. �Է�
	private void insertArticle() {
		int code = codeInput();
		if(code == 4) {
			System.out.println("���� �޴���..");
			return;
		}else if(code < 1 || code > 4) {
			System.out.println("�߸��� ����! ���� �޴���..");
			return;
		}
		
		String name = nameInput("");
		String value = valueInput(code);
		
		StudentDTO dto = new StudentDTO(name, value, code);
		StudentDAO dao = new StudentDAO();
		
		boolean check = dao.insert(dto);
		if(check) {
			System.out.println(name + "���� ������ �߰� �Ǿ����ϴ�.");
		}else {
			System.out.println("���� �߰��� �����Ͽ����ϴ�.");
		}
	}
	private int codeInput() {
		System.out.print("****************\r\n"
				+ "   1. �л�\r\n"
				+ "   2. ����\r\n"
				+ "   3. ������\r\n"
				+ "   4. �����޴�\r\n"
				+ "****************\r\n"
				+ "   ��ȣ���� : ");
		int code = sc.nextInt();
		return code;
	}
	private String nameInput(String msg) {
		System.out.print( msg + "�̸� �Է� : ");
		String name = sc.next();
		return name;
	}
	private String valueInput(int code) {
		if(code == 1) {
			System.out.print("�й� �Է� : ");
		}else if(code == 2) {
			System.out.print("���� �Է� : ");
		}else if(code == 3) {
			System.out.print("�μ� �Է� : ");
		}
		
		return sc.next();
	}
	
	//2. ����
	private void updateArticle() {
		String name = nameInput("������ ");
		
		StudentDAO dao = new StudentDAO();
		StudentDTO dto = dao.selectOne(name);
		
		if(dto == null) {
			System.out.println("�˻� ����! ���� �޴���..");
			return;
		}
		
		System.out.println(dto.getName() + "���� ����");
		System.out.println(valueString(dto.getCode()) + dto.getValue());
		
		int code = codeInput();
		
		if(code == 4) {
			System.out.println("���� �޴���..");
			return;
		}else if(code < 1 || code > 4) {
			System.out.println("�߸��� ����! ���� �޴���...");
			return;
		}
		
		String value = valueInput(code);
		
		dto.setValue(value);
		dto.setCode(code);
		
		int su = dao.update(dto);
		
		if(su != 0) {
			System.out.println(dto.getName() + "���� ������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("���� ������ �����Ͽ����ϴ�.");
		}
	}
	private String valueString(int code) {
		if(code == 1) {
			return "�й� : ";
		}else if(code == 2) {
			return "���� : ";
		}else{
			return "�μ� : ";
		}
	}
	
	//3. ����
	private void deleteArticle() {
		String name = nameInput("������ ");
		
		StudentDAO dao = new StudentDAO();
		int su = dao.delete(name);
		
		if(su != 0) {
			System.out.println(name + "���� ������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("���� ������ �����Ͽ����ϴ�.");
		}
	}
	
	//4. �˻�
	private void selectArticle() {
		 System.out.print("****************\r\n" + 
                 "   1. �̸� �˻�\r\n" + 
                 "   2. �μ� �˻�\r\n" + 
                 "   3. ��ü �˻�\r\n" + 
                 "   4. �����޴�\r\n" + 
                 "****************\r\n" + 
                 "   ��ȣ���� : ");
		 int select = sc.nextInt();
		 
		 if(select == 4) {
			 System.out.println("�����޴���..");
			 return;
		 }else if(select < 1 || select > 4) {
			 System.out.println("�߸��� ����!! ���� �޴���..");
			 return;
		 }
		 StudentDTO dto = null;
		 //�̸� �˻�
		 if(select == 1) {
			 dto = new StudentDTO();
			 dto.setName(nameInput("�˻��� "));
		 }else if(select == 2) { 
			 int code = codeInput();
			 
			 if(select == 4) {
                 System.out.println("�����޴���..");
                 return;
			 }else if(select <1 || select > 4) {
                 System.out.println("�߸��� ����!! ���� �޴���..");
                 return;
			 }
			 //�߸��� �ڵ尪�� ���� �� �ֱ� ������ �ڵ尪 �Է¹ް� ��ü ����.
			 dto = new StudentDTO();
			 dto.setCode(code);
		 }
		 
		 StudentDAO dao = new StudentDAO();
		 
		 List<StudentDTO> list = dao.select(dto);
		 
		 if(list == null) {
			 System.out.println("�˻� ����� �����ϴ�!!");
		 }else {
			 for(StudentDTO d : list) {
				 System.out.println("�̸� : " + d.getName() + "\t" + valueString(d.getCode()) + d.getValue());
			 }
		 }
		 
	}
}
